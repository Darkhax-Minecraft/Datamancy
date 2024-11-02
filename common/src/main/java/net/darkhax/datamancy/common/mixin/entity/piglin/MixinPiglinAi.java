package net.darkhax.datamancy.common.mixin.entity.piglin;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinAi.class)
public class MixinPiglinAi {

    @Inject(method = "isWearingGold(Lnet/minecraft/world/entity/LivingEntity;)Z", at = @At("RETURN"), cancellable = true)
    private static void isWearingGold(LivingEntity wearer, CallbackInfoReturnable<Boolean> cbi) {
        if (!cbi.getReturnValue()) {
            for (ItemStack stack : wearer.getArmorSlots()) {
                if (stack.hasTag()) {
                    final ArmorTrim trim = ArmorTrim.getTrim(wearer.level().registryAccess(), stack).orElse(null);
                    if (trim != null && trim.material().is(Tags.TRIM_MATERIALS.DISTRACTS_PIGLINS)) {
                        cbi.setReturnValue(true);
                        return;
                    }
                }
                if (stack.is(Tags.ITEMS.DISTRACTS_PIGLINS)) {
                    cbi.setReturnValue(true);
                    return;
                }
            }
        }
    }
}