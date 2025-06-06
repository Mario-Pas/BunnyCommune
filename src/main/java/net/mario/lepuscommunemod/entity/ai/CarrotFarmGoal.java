package net.mario.lepuscommunemod.entity.ai;

import net.mario.lepuscommunemod.entity.custom.BunnyEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.RandomSource;

import java.util.EnumSet;

public class CarrotFarmGoal extends Goal {
    private final BunnyEntity bunny;
    private final double speed;
    private BlockPos targetPos;
    private boolean tilled = false;

    // Constructor: Take bunny and speed for movement
    public CarrotFarmGoal(BunnyEntity bunny, double speed) {
        this.bunny = bunny;
        this.speed = speed;
        // Needs MOVE (path) and LOOK (face block)
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (bunny.getMainHandItem().getItem() != Items.IRON_HOE) return false;
        if (bunny.level().getGameTime() % 200 != 0) return false;

        Level level = bunny.level();
        RandomSource rand = RandomSource.create();

        for (int attempts = 0; attempts < 10; attempts++) {
            int dx = rand.nextInt(7) - 3;
            int dz = rand.nextInt(7) - 3;
            int x = bunny.blockPosition().getX() + dx;
            int z = bunny.blockPosition().getZ() + dz;

            // Get surface Y at (x, z)
            int y = level.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, x, z) - 1;
            BlockPos candidate = new BlockPos(x, y, z);
            BlockState ground = level.getBlockState(candidate);
            BlockState above = level.getBlockState(candidate.above());

            System.out.println("Checking block at " + candidate + ": " + ground.getBlock().getName());
            System.out.println("Above is: " + candidate.above() + ": " + above.getBlock().getName());

            if ((ground.getBlock() == Blocks.DIRT || ground.getBlock() == Blocks.GRASS_BLOCK)
                    && level.isEmptyBlock(candidate.above())){
                targetPos = candidate;
                tilled = false;
                System.out.println("Valid farming spot found at: " + targetPos);
                return true;
            }
        }

        System.out.println("No valid dirt block found.");
        return false;
    }


    @Override
    public void start() {
        // Move the bunny to the target position
        if (targetPos != null) {
            bunny.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speed);
        }
    }

    @Override
    public void tick() {
        if (targetPos == null) return;

        // If the bunny is far from the target, keep moving towards it
        if (bunny.distanceToSqr(Vec3.atCenterOf(targetPos)) > 2) {
            bunny.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), speed);
        } else {
            // If the bunny is close enough to the dirt block
            if (!tilled) {
                // Till the dirt block into farmland
                bunny.level().setBlockAndUpdate(targetPos, Blocks.FARMLAND.defaultBlockState());
                tilled = true;
                return;  // Stop here until the block is tilled
            }

            // If the dirt is tilled, plant carrots on the farmland
            BlockPos plantPos = targetPos.above();
            if (bunny.level().isEmptyBlock(plantPos)) {
                bunny.level().setBlockAndUpdate(plantPos, Blocks.CARROTS.defaultBlockState());
            }

            // Goal is complete, reset target position
            targetPos = null;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return targetPos != null; // Continue until the goal is completed
    }

    @Override
    public void stop() {
        targetPos = null;  // Reset target when goal is stopped
    }
}
