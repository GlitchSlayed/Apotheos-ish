package com.summit.mixin;

import com.summit.spawner.SpawnerModifiers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.world.level.block.entity.SpawnerBlockEntity.class)
public class SpawnerBlockEntityMixin {
    private SpawnerModifiers summit$modifiers;

    public SpawnerModifiers summit$getModifiers() {
        return summit$modifiers;
    }

    public void summit$setModifiers(SpawnerModifiers modifiers) {
        this.summit$modifiers = modifiers;
    }

    @Inject(method = "saveAdditional", at = @At("HEAD"))
    private void summit$saveModifiers(ValueOutput output, CallbackInfo ci) {
        SpawnerModifiers modifiers = summit$modifiers;
        if (modifiers == null) {
            modifiers = new SpawnerModifiers();
        }
        CompoundTag tag = new CompoundTag();
        modifiers.writeNbt(tag);
        output.store("SummitModifiers", CompoundTag.CODEC, tag);
    }

    @Inject(method = "loadAdditional", at = @At("RETURN"))
    private void summit$loadModifiers(ValueInput input, CallbackInfo ci) {
        SpawnerModifiers modifiers = new SpawnerModifiers();
        input.read("SummitModifiers", CompoundTag.CODEC).ifPresent(modifiers::readNbt);
        summit$modifiers = modifiers;
    }
}
