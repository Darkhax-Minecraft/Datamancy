package net.darkhax.datamancy.common.impl.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BlockTags extends TagEntries<Block> {

    public final TagKey<Block> SUPPORTS_TURTLE_EGGS = of("supports_turtle_eggs");
    public final TagKey<Block> AQUATIC_SPAWNS_IN = of("aquatic_spawns_in");
    public final TagKey<Block> AQUATIC_SPAWNS_IN_AT_ANY_HEIGHT = of("aquatic_spawns_in_at_any_height");

    protected BlockTags() {
        super(Registries.BLOCK);
    }
}