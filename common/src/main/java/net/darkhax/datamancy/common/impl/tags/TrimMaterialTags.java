package net.darkhax.datamancy.common.impl.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.armortrim.TrimMaterial;

public class TrimMaterialTags extends TagEntries<TrimMaterial> {

    public final TagKey<TrimMaterial> DISTRACTS_PIGLINS = of("distracts_piglins");
    public final TagKey<TrimMaterial> DISTRACTS_ENDERMAN = of("distracts_enderman");

    public TrimMaterialTags() {
        super(Registries.TRIM_MATERIAL);
    }
}