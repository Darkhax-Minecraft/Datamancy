package net.darkhax.datamancy.common.mixin.item;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Inject(method = "isDamageableItem()Z", at = @At("HEAD"), cancellable = true)
    public void canDamage(CallbackInfoReturnable<Boolean> cir) {
        if (this.is(Tags.ITEMS.UNBREAKABLE)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "getTooltipLines", at = @At(value = "RETURN"))
    private void getTooltips(@Nullable Player player, TooltipFlag flag, CallbackInfoReturnable<List<Component>> cir) {
        if (this.is(Tags.ITEMS.UNBREAKABLE) && shouldShowInTooltip(this.getHideFlags(), ItemStack.TooltipPart.UNBREAKABLE)) {
            cir.getReturnValue().add(Component.translatable("item.unbreakable").withStyle(ChatFormatting.BLUE));
        }
    }

    @Shadow
    public abstract boolean is(TagKey<Item> $$0);

    @Shadow
    private static boolean shouldShowInTooltip(int hideFlags, ItemStack.TooltipPart part) {
        return false;
    }

    @Shadow
    protected abstract int getHideFlags();
}