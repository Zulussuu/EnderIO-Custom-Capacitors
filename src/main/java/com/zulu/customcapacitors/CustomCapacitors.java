package com.zulu.customcapacitors;

import com.zulu.customcapacitors.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(CustomCapacitors.MOD_ID)
public class CustomCapacitors {
    public static final String MOD_ID = "custom_capacitors";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @SuppressWarnings("removal")
    public CustomCapacitors() {
        LOGGER.info("[CustomCapacitors] Initializing...");
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(bus);
        LOGGER.info("[CustomCapacitors] Mod initialized successfully!");
    }
}