package net.mario.lepuscommunemod.event;

import net.mario.lepuscommunemod.LepusCommune;
import net.mario.lepuscommunemod.commune.CommuneData;
import net.mario.lepuscommunemod.commune.CommuneManager;
import net.mario.lepuscommunemod.entity.ModEntities;
import net.mario.lepuscommunemod.entity.custom.BunnyEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.*;
import java.util.function.Consumer;

/**
 * If the current leader bunny dies, schedules a replacement to spawn 200 ticks later.
 */
@Mod.EventBusSubscriber(modid = LepusCommune.MOD_ID)
public class BunnyLeaderHandler {
    // Maps communeId → worldGameTime (tick) at which to respawn
    private static final Map<UUID, Long> respawnSchedule = new HashMap<>();

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();

        // Only care if a BunnyEntity died
        if (!(entity instanceof BunnyEntity bunny)) return;

        // Get the commune this bunny belonged to (if any)
        CommuneData comm = bunny.getCommune();
        if (comm == null) return;

        // If that bunny was the leader, we remove it and schedule a new one
        if (bunny.getUUID().equals(comm.getLeaderUUID())) {
            // Removing from members will also clear leaderUUID if it matches
            comm.removeMember(bunny.getUUID());

            // Grab the ServerLevel via bunny.level() — not the private 'level' field
            ServerLevel world = (ServerLevel) bunny.level();

            long currentTime = world.getGameTime();
            // Schedule a respawn 200 ticks (≈10 sec) later
            respawnSchedule.put(comm.getId(), currentTime + 200);
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.ServerTickEvent.Phase.END) return;

        // Fetch the overworld ServerLevel. (Assumes a single Overworld‐based commune.)
        ServerLevel world = ServerLifecycleHooks
                .getCurrentServer()
                .getLevel(Level.OVERWORLD);
        if (world == null) return;

        long now = world.getGameTime();

        Iterator<Map.Entry<UUID, Long>> it = respawnSchedule.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, Long> entry = it.next();
            UUID commId = entry.getKey();
            long scheduledTick = entry.getValue();

            if (now < scheduledTick) {
                // Not yet time to respawn
                continue;
            }

            CommuneData comm = CommuneManager.getCommune(commId);
            if (comm != null && comm.getLeaderUUID() == null) {
                // Spawn a new leader bunny at the commune’s center
                BlockPos center = comm.getCenter();
                BlockPos spawnPos = center.offset(1, 0, 1);

                BunnyEntity newLeader = ModEntities.COMMUNE_BUNNY.get().spawn(
                        world,
                        (CompoundTag) null,   // no extra NBT
                        (Consumer<BunnyEntity>) null,     // no custom name
                        spawnPos,
                        MobSpawnType.EVENT,   // spawn reason
                        true,                 // randomize rotation
                        false                 // do not bypass collision
                );
                if (newLeader != null) {
                    comm.setLeaderUUID(newLeader.getUUID());
                    newLeader.joinCommune(comm);
                    world.addFreshEntity(newLeader);
                }
            }
            // Removed the entry whether or not we successfully spawned
            it.remove();
        }
    }
}
