package net.darkhax.datamancy.common.mixin.block;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RespawnAnchorBlock.class)
public class MixinRespawnAnchorBlock {

    @Inject(method = "isRespawnFuel(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
    private static void isRespawnFuel(ItemStack stack, CallbackInfoReturnable<Boolean> cbr) {
        if (!cbr.getReturnValue() && stack.is(Tags.ITEMS.RESPAWN_ANCHOR_FUEL)) {
            cbr.setReturnValue(true);
        }
    }
}
