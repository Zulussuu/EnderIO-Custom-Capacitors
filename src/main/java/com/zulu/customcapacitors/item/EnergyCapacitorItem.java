package com.zulu.customcapacitors.item;

import com.enderio.api.capability.IMultiCapabilityItem;
import com.enderio.api.capability.MultiCapabilityProvider;
import com.enderio.api.capacitor.ICapacitorData;
import com.enderio.base.common.init.EIOCapabilities;
import com.enderio.base.common.item.capacitors.BaseCapacitorItem;
import com.zulu.customcapacitors.capacitor.SimpleCapacitorData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

public class EnergyCapacitorItem extends BaseCapacitorItem implements IMultiCapabilityItem {
    private final ICapacitorData data;
    private final String displayName;
    private final boolean glowing;
    private final int color;

    public EnergyCapacitorItem(Properties properties, float level, String displayName, boolean glowing, int color) {
        super(properties);
        this.data = new SimpleCapacitorData(level);
        this.displayName = displayName;
        this.glowing = glowing;
        this.color = color;
    }

    @Nullable
    @Override
    public MultiCapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt, MultiCapabilityProvider provider) {
        provider.add(EIOCapabilities.CAPACITOR, LazyOptional.of(() -> data));
        return provider;
    }

    public ICapacitorData getCapacitorData() {
        return data;
    }

    public float getLevel() {
        return data.getBase();
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isGlowing() {
        return glowing;
    }

    public int getColor() {
        return color;
    }

    @Override
    public Component getName(ItemStack stack) {
        return displayName != null ? Component.literal(displayName) : super.getName(stack);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return glowing || super.isFoil(stack);
    }
}