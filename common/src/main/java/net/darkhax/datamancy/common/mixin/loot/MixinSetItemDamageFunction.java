package net.darkhax.datamancy.common.mixin.loot;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SetItemDamageFunction.class)
public class MixinSetItemDamageFunction {

    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    public void run(ItemStack stack, LootContext ctx, CallbackInfoReturnable<ItemStack> cbi) {
        // The vanilla code tries to print a warning if an item that can not be
        // damaged is damaged in a loot table. We are returning early to avoid
        // the warning so pack devs don't have to rewrite every loot table.
        if (stack.is(Tags.ITEMS.UNBREAKABLE)) {
            cbi.setReturnValue(stack);
        }
    }
}