package net.mario.lepuscommunemod.entity.custom;

import net.mario.lepuscommunemod.commune.CommuneData;
import net.mario.lepuscommunemod.commune.CommuneManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.goal.*;
import net.mario.lepuscommunemod.entity.ai.CarrotFarmGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.world.entity.animal.Rabbit;
import org.jetbrains.annotations.NotNull;

public class BunnyEntity extends Rabbit {
    public BunnyEntity(EntityType<? extends Rabbit> type, Level world) {
        super(type, world);
        this.setMaxUpStep(1.0F);
        this.jumpControl = new BunnyJumpControl(this);

        // Allows bunny to climb 1-block high ledges
    }

    private UUID communeId = null;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(4, new CarrotFarmGoal(this, 1.0));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public class BunnyJumpControl extends RabbitJumpControl {
        private final PathfinderMob bunny;

        public BunnyJumpControl(PathfinderMob bunny) {
            super((Rabbit) bunny);
            this.bunny = bunny;
        }

        @Override
        public void tick() {
            if (this.bunny.onGround() && this.bunny.getNavigation().isInProgress()) {
                this.bunny.getJumpControl().jump();
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData(); // This is critical!
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.RABBIT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source){
        return SoundEvents.RABBIT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.RABBIT_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    public CommuneData getCommune() {
        if (communeId == null) return null;
        return CommuneManager.getCommune(communeId);
    }

    /** Makes this bunny join the given commune. */
    public void joinCommune(CommuneData comm) {
        if (comm == null) return;
        this.communeId = comm.getId();
        comm.addMember(this.getUUID());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (communeId != null) {
            tag.putUUID("CommuneId", communeId);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID("CommuneId")) {
            this.communeId = tag.getUUID("CommuneId");
        }
    }

    /** Makes this bunny leave its current commune. */
//    public void leaveCommune() {
//        CommuneData current = getCommune();
//        if (current != null) {
//            current.removeMember(this.getUUID());
//        }
//        this.communeId = null;
//    }



    //    Temp role for all bunnies will change
//    private enum Role { BUILDER, GUARD }
//    private Role role = Role.BUILDER;
//
//    private static final Map<UUID, Integer> playerTrust = new HashMap<>();

}
