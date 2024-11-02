package net.darkhax.datamancy.fabric;

import net.darkhax.datamancy.common.impl.DatamancyMod;
import net.fabricmc.api.ModInitializer;

public class DatamancyFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        DatamancyMod.init();
    }
}