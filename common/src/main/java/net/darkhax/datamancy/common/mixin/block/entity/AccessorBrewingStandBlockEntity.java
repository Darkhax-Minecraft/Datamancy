package net.darkhax.datamancy.common.mixin.block.entity;

import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BrewingStandBlockEntity.class)
public interface AccessorBrewingStandBlockEntity {

    @Accessor("fuel")
    int datamancy$getFuel();

    @Accessor("fuel")
    void datamancy$setFuel(int fuel);
}
