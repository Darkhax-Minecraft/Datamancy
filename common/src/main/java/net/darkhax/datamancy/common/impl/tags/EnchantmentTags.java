package net.darkhax.datamancy.common.impl.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentTags extends TagEntries<Enchantment> {

    protected EnchantmentTags() {
        super(Registries.ENCHANTMENT);
    }
}
