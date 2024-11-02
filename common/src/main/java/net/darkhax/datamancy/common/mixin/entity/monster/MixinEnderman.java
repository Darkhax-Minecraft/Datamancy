package net.darkhax.datamancy.common.mixin.entity.monster;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderMan.class)
public class MixinEnderman {

    @Inject(method = "isLookingAtMe(Lnet/minecraft/world/entity/player/Player;)Z", at = @At("RETURN"), cancellable = true)
    private void isLookingAtMe(Player player, CallbackInfoReturnable<Boolean> cbr) {
        if (cbr.getReturnValue()) {
            for (ItemStack stack : player.getArmorSlots()) {
                if (stack.hasTag()) {
                    final ArmorTrim trim = ArmorTrim.getTrim(player.level().registryAccess(), stack).orElse(null);
                    if (trim != null && trim.material().is(Tags.TRIM_MATERIALS.DISTRACTS_ENDERMAN)) {
                        cbr.setReturnValue(false);
                        return;
                    }
                }
                if (stack.is(Tags.ITEMS.DISTRACTS_ENDERMAN)) {
                    cbr.setReturnValue(false);
                    return;
                }
            }
        }
    }
}