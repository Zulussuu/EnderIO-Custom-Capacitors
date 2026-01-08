package com.zulu.customcapacitors.compat;

import com.zulu.customcapacitors.item.EnergyCapacitorItem;
import net.minecraft.world.item.ItemStack;

public class EnderIOCompat {
    public static float getCapacitorLevel(ItemStack stack) {
        return stack.getItem() instanceof EnergyCapacitorItem c ? c.getLevel() : 0;
    }
}