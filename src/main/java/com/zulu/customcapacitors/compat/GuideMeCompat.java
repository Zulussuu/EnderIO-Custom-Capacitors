package com.zulu.customcapacitors.compat;

import com.zulu.customcapacitors.CustomCapacitors;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CustomCapacitors.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GuideMeCompat {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                Class<?> guidesClass = Class.forName("guideme.Guides");
                Class<?> guideBuilderClass = Class.forName("guideme.GuideBuilder");
                
                ResourceLocation guideId = new ResourceLocation(CustomCapacitors.MOD_ID, "guide");
                
                Object builder = guideBuilderClass.getConstructor(ResourceLocation.class).newInstance(guideId);
                Object guide = guideBuilderClass.getMethod("build").invoke(builder);
                
                guidesClass.getMethod("register", Class.forName("guideme.Guide")).invoke(null, guide);
                
                CustomCapacitors.LOGGER.info("[CustomCapacitors] GuideMe integration loaded!");
            } catch (Exception e) {
                CustomCapacitors.LOGGER.debug("[CustomCapacitors] GuideMe not available: {}", e.getMessage());
            }
        });
    }
}