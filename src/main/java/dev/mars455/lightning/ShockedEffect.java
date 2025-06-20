package dev.mars455.lightning;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.server.world.ServerWorld;

public class ShockedEffect extends StatusEffect {
    protected ShockedEffect() {
        super(StatusEffectCategory.HARMFUL, 0x0084ff);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity.age % 20 == 0) {
            DamageSource damageSource = world.getDamageSources().lightningBolt();
            entity.damage(world, damageSource, 0.5F);
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
