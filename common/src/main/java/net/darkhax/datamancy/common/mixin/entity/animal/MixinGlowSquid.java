package net.darkhax.datamancy.common.mixin.entity.animal;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlowSquid.class)
public class MixinGlowSquid {

    @Inject(method = "checkGlowSquideSpawnRules(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Z", at = @At("HEAD"), cancellable = true)
    private static void checkGlowSquideSpawnRules(EntityType<? extends LivingEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource rng, CallbackInfoReturnable<Boolean> cbr) {

        final BlockState state = level.getBlockState(pos);
        if (state.is(Tags.BLOCKS.AQUATIC_SPAWNS_IN_AT_ANY_HEIGHT)) {
            cbr.setReturnValue(true);
        }
        else if (state.is(Tags.BLOCKS.AQUATIC_SPAWNS_IN)) {
            if (pos.getY() <= level.getSeaLevel() - 33 && level.getRawBrightness(pos, 0) == 0) {
                cbr.setReturnValue(true);
            }
        }
    }
}