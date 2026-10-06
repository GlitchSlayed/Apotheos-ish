package com.summit.mixin;

import com.summit.config.SummitConfig;
import com.summit.Summit;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Adds Capturing's independent spawn-egg roll after vanilla has produced normal death loot. */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    private static final ResourceKey<Enchantment> CAPTURING = ResourceKey.create(Registries.ENCHANTMENT, Summit.id("capturing"));

    @Inject(method = "dropAllDeathLoot", at = @At("TAIL"))
    private void summit$dropSpawnEgg(ServerLevel level, DamageSource damageSource, CallbackInfo callback) {
        if (!SummitConfig.get().spawnerModule.capturingEnabled) return;
        if (!(damageSource.getEntity() instanceof Player player)) return;

        Registry<Enchantment> enchantments = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        java.util.Optional<Holder.Reference<Enchantment>> capturing = enchantments.get(CAPTURING);
        if (capturing.isEmpty()) return;

        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(capturing.get(), player.getMainHandItem());
        if (enchantmentLevel <= 0) return;

        float chance = SummitConfig.get().spawnerModule.capturingChances[Math.min(enchantmentLevel - 1, 4)];
        if (level.getRandom().nextFloat() >= chance) return;

        LivingEntity self = (LivingEntity) (Object) this;
        SpawnEggItem.byId(self.getType()).ifPresent(egg -> self.spawnAtLocation(level, new ItemStack(egg)));
    }
}