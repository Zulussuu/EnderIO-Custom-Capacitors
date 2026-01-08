package com.zulu.customcapacitors.item;

import com.zulu.customcapacitors.CustomCapacitors;
import com.zulu.customcapacitors.capacitor.ConfigLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, CustomCapacitors.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CustomCapacitors.MOD_ID);

    public static final RegistryObject<Item> GUIDE_HINT = ITEMS.register("guide_hint",
            () -> new Item(new Item.Properties().stacksTo(1)) {
                @Override
                public Component getName(ItemStack stack) {
                    return Component.literal("Custom Capacitors Guide");
                }
            });

    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> ConfigLoader.getItems().stream().findFirst()
                            .map(h -> h.get().getDefaultInstance())
                            .orElse(net.minecraft.world.item.Items.REDSTONE.getDefaultInstance()))
                    .title(Component.translatable("itemGroup.custom_capacitors.tab"))
                    .displayItems((p, o) -> {
                        o.accept(GUIDE_HINT.get());
                        ConfigLoader.getItems().forEach(h -> o.accept(h.get()));
                    })
                    .build());

    public static void register(IEventBus bus) {
        ConfigLoader.load();
        ConfigLoader.register(ITEMS);
        ITEMS.register(bus);
        TABS.register(bus);
    }
}