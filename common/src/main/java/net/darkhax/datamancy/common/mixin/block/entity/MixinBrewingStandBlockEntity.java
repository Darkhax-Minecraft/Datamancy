package net.darkhax.datamancy.common.mixin.block.entity;

import net.darkhax.datamancy.common.impl.tags.Tags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingStandBlockEntity.class)
public class MixinBrewingStandBlockEntity extends BlockEntity {

    @Inject(method = "canPlaceItem(ILnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void canPlace(int slotId, ItemStack stack, CallbackInfoReturnable<Boolean> cbr) {
        if (slotId == 4 && stack.is(Tags.ITEMS.BREWING_STAND_FUEL)) {
            cbr.setReturnValue(true);
        }
    }

    @Inject(method = "serverTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BrewingStandBlockEntity;)V", at = @At("HEAD"))
    private static void serverTick(Level level, BlockPos pos, BlockState state, BrewingStandBlockEntity entity, CallbackInfo cbi) {
        if (entity instanceof AccessorBrewingStandBlockEntity accessor && accessor.datamancy$getFuel() <= 0) {
            final ItemStack fuelStack = entity.getItem(4);
            if (fuelStack.is(Tags.ITEMS.BREWING_STAND_FUEL)) {
                accessor.datamancy$setFuel(20);
                fuelStack.shrink(1);
                BlockEntity.setChanged(level, pos, state);
            }
        }
    }

    public MixinBrewingStandBlockEntity() {
        super(null, null, null);
    }
}
