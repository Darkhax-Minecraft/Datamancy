package net.darkhax.datamancy.common.impl;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class DatamancyMod {

    public static final String MOD_ID = "datamancy";
    public static final String MOD_NAME = "Datamancy";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);
    private static final Map<String, ResourceLocation> ID_CACHE = new HashMap<>();

    public static ResourceLocation id(String path) {
        return ID_CACHE.computeIfAbsent(path, p -> ResourceLocation.tryBuild(MOD_ID, p));
    }

    public static void init() {
    }
}