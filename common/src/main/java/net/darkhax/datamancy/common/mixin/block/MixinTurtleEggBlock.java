package net.darkhax.datamancy.common.mixin.block;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TurtleEggBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TurtleEggBlock.class)
public class MixinTurtleEggBlock {

    @Inject(method = "isSand(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    private static void isSand(BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cbr) {
        if (level.getBlockState(pos).is(Tags.BLOCKS.SUPPORTS_TURTLE_EGGS)) {
            cbr.setReturnValue(true);
        }
    }
}