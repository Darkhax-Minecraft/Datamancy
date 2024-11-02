package net.darkhax.datamancy.common.impl.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTags extends TagEntries<Item> {

    public final TagKey<Item> DISTRACTS_PIGLINS = of("distracts_piglins");
    public final TagKey<Item> DISTRACTS_ENDERMAN = of("distracts_enderman");
    public final TagKey<Item> BREWING_STAND_FUEL = of("brewing_fuel");
    public final TagKey<Item> PREVENT_TRAMPLING = of("prevents_trampling");
    public final TagKey<Item> RESPAWN_ANCHOR_FUEL = of("respawn_anchor_fuel");

    protected ItemTags() {
        super(Registries.ITEM);
    }
}