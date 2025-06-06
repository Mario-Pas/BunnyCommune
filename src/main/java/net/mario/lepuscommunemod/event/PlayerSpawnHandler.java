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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;

/**
 * Spawns exactly one “leader” BunnyEntity when the first player logs in.
 * If that leader dies, BunnyLeaderHandler (in a separate class) will respawn it later.
 */
@Mod.EventBusSubscriber(modid = LepusCommune.MOD_ID)
public class PlayerSpawnHandler {
    // Ensures we only run this logic once per world‐load, so exactly one leader spawns.
    private static boolean spawnedOnce = false;

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (spawnedOnce) return;


        // Use event.getEntity(), not getPlayer(), then cast to ServerPlayer
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Use player.level() to get the Level, then cast to ServerLevel
        ServerLevel world = (ServerLevel) player.level();

        // 1) Create a new CommuneData at the player's position
        BlockPos center = player.blockPosition();
        CommuneData newCommune = new CommuneData(center, player.getUUID());
        CommuneManager.registerCommune(newCommune);

        // 2) Spawn exactly one leader bunny next to the player
        BlockPos spawnPos = center.offset(1, 0, 1); // offset so it doesn't overlap
        BunnyEntity leader = ModEntities.COMMUNE_BUNNY.get().spawn(
                world,
                 null,  // no extra NBT
                (Consumer<BunnyEntity>) null,    // no custom name
                spawnPos,
                MobSpawnType.EVENT,  // spawn reason
                true,                // randomize rotation
                false                // do not bypass collision
        );
        if (leader != null) {
            leader.joinCommune(newCommune);
            // Equip an iron hoe so ItemInHandLayer has something to render
            leader.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.IRON_HOE));
            world.addFreshEntity(leader);
        }

        // Prevent this from running again during this world session
        spawnedOnce = true;
    }
}
