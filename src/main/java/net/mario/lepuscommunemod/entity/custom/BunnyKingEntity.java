//package net.mario.lepuscommunemod.entity.custom;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.ai.goal.FloatGoal;
//import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
//import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
//import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
//import net.minecraft.world.entity.animal.Rabbit;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.phys.Vec3;
//import net.minecraft.server.level.ServerLevel;
//
//import javax.annotation.Nullable;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//public class BunnyKingEntity extends Rabbit {
//    /** Shared resource stash for the entire commune */
//    private final Map<String, Integer> resourceStash = new HashMap<>();
//
//    public BunnyKingEntity(EntityType<? extends Rabbit> pEntityType, Level pLevel) {
//        super(pEntityType, pLevel);
//    }
//}