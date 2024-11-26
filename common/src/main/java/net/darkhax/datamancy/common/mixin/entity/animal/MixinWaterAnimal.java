package net.darkhax.datamancy.common.mixin.entity.animal;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterAnimal.class)
public class MixinWaterAnimal {

    @Inject(method = "checkSurfaceWaterAnimalSpawnRules(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Z", at = @At("HEAD"), cancellable = true)
    private static void checkSpawn(EntityType<? extends WaterAnimal> type, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource rng, CallbackInfoReturnable<Boolean> cbi) {
        final BlockState state = level.getBlockState(pos);
        if (state.is(Tags.BLOCKS.AQUATIC_SPAWNS_IN_AT_ANY_HEIGHT)) {
            cbi.setReturnValue(true);
        }
        else if (state.is(Tags.BLOCKS.AQUATIC_SPAWNS_IN)) {
            final int seaLevel = level.getSeaLevel();
            final int depthLimit = seaLevel - 13;
            if (pos.getY() >= depthLimit && pos.getY() <= seaLevel) {
                cbi.setReturnValue(true);
            }
        }
    }
}