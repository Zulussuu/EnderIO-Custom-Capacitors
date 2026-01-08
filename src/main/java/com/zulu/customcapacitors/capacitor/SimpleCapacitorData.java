package com.zulu.customcapacitors.capacitor;

import com.enderio.api.capacitor.CapacitorModifier;
import com.enderio.api.capacitor.ICapacitorData;

import java.util.Collections;
import java.util.Map;

public record SimpleCapacitorData(float base) implements ICapacitorData {

    @Override
    public float getBase() {
        return base;
    }

    @Override
    public float getModifier(CapacitorModifier modifier) {
        return base;
    }

    @Override
    public Map<CapacitorModifier, Float> getAllModifiers() {
        return Collections.emptyMap();
    }
}