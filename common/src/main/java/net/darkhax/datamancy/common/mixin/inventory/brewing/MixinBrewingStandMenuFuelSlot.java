package net.darkhax.datamancy.common.mixin.inventory.brewing;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.inventory.BrewingStandMenu$FuelSlot")
public class MixinBrewingStandMenuFuelSlot {

    @Inject(method = "mayPlaceItem(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
    private static void mayPlaceItem(ItemStack stack, CallbackInfoReturnable<Boolean> cbr) {
        if (!cbr.getReturnValue() && stack.is(Tags.ITEMS.BREWING_STAND_FUEL)) {
            cbr.setReturnValue(true);
        }
    }
}