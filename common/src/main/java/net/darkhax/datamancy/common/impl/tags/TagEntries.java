package net.darkhax.datamancy.common.impl.tags;

import net.darkhax.datamancy.common.impl.DatamancyMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.HashMap;
import java.util.Map;

public class TagEntries<T> {

    private final ResourceKey<Registry<T>> regKey;
    private final Map<String, TagKey<T>> cache = new HashMap<>();

    public TagEntries(ResourceKey<Registry<T>> regKey) {
        this.regKey = regKey;
    }

    public TagKey<T> of(String type, ResourceLocation id) {
        return of(id.getNamespace() + "/" + id.getPath() + "/" + type);
    }

    public TagKey<T> of(String path) {
        return cache.computeIfAbsent(path, p -> TagKey.create(this.regKey, DatamancyMod.id(p)));
    }
}