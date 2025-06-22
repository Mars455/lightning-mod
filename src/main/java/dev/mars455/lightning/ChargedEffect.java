package dev.mars455.lightning;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;

public class ChargedEffect extends StatusEffect {
    protected ChargedEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x0084ff);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity.age % 20 == 0) {
            entity.heal(1.0f);
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 3*20, 5), entity);
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
