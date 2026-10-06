package com.summit.mixin;

import com.summit.spawner.SpawnerModifiers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(net.minecraft.world.level.block.entity.SpawnerBlockEntity.class)
public interface SpawnerBlockEntityAccessor {
    @Accessor
    SpawnerModifiers summit$getModifiers();

    @Accessor
    void summit$setModifiers(SpawnerModifiers modifiers);
}
