package net.darkhax.datamancy.common.mixin.world;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NaturalSpawner.class)
public class MixinNaturalSpawner {

    @Inject(method = "isSpawnPositionOk(Lnet/minecraft/world/entity/SpawnPlacements$Type;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/EntityType;)Z", at = @At("HEAD"), cancellable = true)
    private static void check(SpawnPlacements.Type placementType, LevelReader level, BlockPos pos, EntityType<?> entityType, CallbackInfoReturnable<Boolean> cbi) {
        if (placementType == SpawnPlacements.Type.IN_WATER && entityType == EntityType.SQUID) {
            final BlockState state = level.getBlockState(pos);
            if (state.is(Tags.BLOCKS.SQUID_SPAWNS_IN) || state.is(Tags.BLOCKS.SQUID_SPAWNS_IN_AT_ANY_HEIGHT)) {
                cbi.setReturnValue(true);
            }
        }
    }
}
