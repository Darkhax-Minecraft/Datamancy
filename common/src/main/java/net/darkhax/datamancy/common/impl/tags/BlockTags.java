package net.darkhax.datamancy.common.impl.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BlockTags extends TagEntries<Block> {

    public final TagKey<Block> SUPPORTS_TURTLE_EGGS = of("supports_turtle_eggs");

    protected BlockTags() {
        super(Registries.BLOCK);
    }
}