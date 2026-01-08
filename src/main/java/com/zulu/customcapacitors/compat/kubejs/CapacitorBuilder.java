package com.zulu.customcapacitors.compat.kubejs;

import com.zulu.customcapacitors.CustomCapacitors;
import com.zulu.customcapacitors.capacitor.SimpleCapacitorData;
import com.enderio.api.capability.IMultiCapabilityItem;
import com.enderio.api.capability.MultiCapabilityProvider;
import com.enderio.api.capacitor.ICapacitorData;
import com.enderio.base.common.init.EIOCapabilities;
import com.enderio.base.common.item.capacitors.BaseCapacitorItem;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

public class CapacitorBuilder extends ItemBuilder {
    public float level = 1;
    public boolean glow = false;
    public int color = 0xFFFFFF;
    public String capName = null;

    public CapacitorBuilder(ResourceLocation id) {
        super(new ResourceLocation(CustomCapacitors.MOD_ID, id.getPath()));
        maxStackSize(64);
    }

    public CapacitorBuilder level(float l) {
        level = l;
        return this;
    }

    public CapacitorBuilder glowing(boolean g) {
        glow = g;
        return this;
    }

    public CapacitorBuilder color(int c) {
        color = c;
        return this;
    }

    public CapacitorBuilder capName(String name) {
        capName = name;
        return this;
    }

    @Override
    public Item createObject() {
        return new CapItem(this);
    }

    public static class CapItem extends BaseCapacitorItem implements IMultiCapabilityItem {
        private final CapacitorBuilder b;
        private final ICapacitorData data;

        public CapItem(CapacitorBuilder b) {
            super(b.createItemProperties());
            this.b = b;
            this.data = new SimpleCapacitorData(b.level);
        }

        @Nullable
        @Override
        public MultiCapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt, MultiCapabilityProvider provider) {
            provider.add(EIOCapabilities.CAPACITOR, LazyOptional.of(() -> data));
            return provider;
        }

        @Override
        public Component getName(ItemStack stack) {
            return b.capName != null ? Component.literal(b.capName) : super.getName(stack);
        }

        @Override
        public boolean isFoil(ItemStack s) {
            return b.glow;
        }

        public ICapacitorData getData() {
            return data;
        }

        public int getColor() {
            return b.color;
        }
    }
}