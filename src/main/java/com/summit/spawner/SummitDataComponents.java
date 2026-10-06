package com.summit.spawner;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.component.CustomData;

public final class SummitDataComponents {
    public static final DataComponentType<CustomData> SPAWNER_MODIFIERS = DataComponentType.<CustomData>builder()
            .persistent(CustomData.CODEC)
            .build();

    private SummitDataComponents() {}
}
