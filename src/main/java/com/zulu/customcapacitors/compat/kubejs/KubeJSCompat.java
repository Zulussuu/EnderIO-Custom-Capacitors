package com.zulu.customcapacitors.compat.kubejs;

import com.zulu.customcapacitors.CustomCapacitors;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;

public class KubeJSCompat extends KubeJSPlugin {
    
    @Override
    public void initStartup() {
        RegistryInfo.ITEM.addType(CustomCapacitors.MOD_ID + ":capacitor", CapacitorBuilder.class, CapacitorBuilder::new);
        CustomCapacitors.LOGGER.info("[CustomCapacitors] KubeJS integration loaded!");
    }
}