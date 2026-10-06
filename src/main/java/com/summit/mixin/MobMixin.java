package com.summit.mixin;

import com.summit.spawner.DespawnGrace;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class MobMixin {
    @Inject(method = "checkDespawn", at = @At("HEAD"), cancellable = true)
    private void summit$skipDespawnDuringGrace(CallbackInfo ci) {
        Mob self = (Mob) (Object) this;
        if (DespawnGrace.hasGrace(self)) {
            ci.cancel();
        }
    }
}
