package com.zulu.customcapacitors.client;

import com.zulu.customcapacitors.CustomCapacitors;
import com.zulu.customcapacitors.capacitor.ConfigLoader;
import com.zulu.customcapacitors.item.EnergyCapacitorItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CustomCapacitors.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        ConfigLoader.getItems().forEach(holder -> {
            EnergyCapacitorItem item = holder.get();
            int color = item.getColor();
            event.register((stack, layer) -> layer == 1 ? (color | 0xFF000000) : 0xFFFFFFFF, item);
        });
    }

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        ResourceLocation parentModelLoc = new ResourceLocation(CustomCapacitors.MOD_ID, "item/capacitor");
        event.register(parentModelLoc);
    }

    @SubscribeEvent
    public static void modifyModels(ModelEvent.ModifyBakingResult event) {
        var models = event.getModels();
        ResourceLocation parentLoc = new ResourceLocation(CustomCapacitors.MOD_ID, "item/capacitor");
        var parentModel = models.get(parentLoc);

        if (parentModel != null) {
            ConfigLoader.getData().forEach(data -> {
                var itemLoc = new ModelResourceLocation(new ResourceLocation(CustomCapacitors.MOD_ID, data.id()), "inventory");
                models.put(itemLoc, parentModel);
            });
        }
    }
}