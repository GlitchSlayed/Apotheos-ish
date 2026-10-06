package com.summit.mixin;

import com.summit.spawner.SpawnerBlockEntityHelper;
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
    @Inject(method = "saveAdditional", at = @At("HEAD"))
    private void summit$saveModifiers(ValueOutput output, CallbackInfo ci) {
        CompoundTag tag = new CompoundTag();
        SpawnerModifiers modifiers = SpawnerBlockEntityHelper.getModifiers((net.minecraft.world.level.block.entity.SpawnerBlockEntity) (Object) this);
        modifiers.writeNbt(tag);
        output.store("SummitModifiers", CompoundTag.CODEC, tag);
    }

    @Inject(method = "loadAdditional", at = @At("RETURN"))
    private void summit$loadModifiers(ValueInput input, CallbackInfo ci) {
        SpawnerModifiers modifiers = new SpawnerModifiers();
        input.read("SummitModifiers", CompoundTag.CODEC).ifPresent(modifiers::readNbt);
        SpawnerBlockEntityHelper.onLoaded((net.minecraft.world.level.block.entity.SpawnerBlockEntity) (Object) this, modifiers);
    }
}
