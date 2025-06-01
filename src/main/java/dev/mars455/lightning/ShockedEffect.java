package dev.mars455.lightning;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class ShockedEffect extends StatusEffect {
    protected ShockedEffect() {
        super(StatusEffectCategory.HARMFUL, 0x0084ff); // Light blue color
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // Apply effect every tick
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity.age % 20 == 0) {
            DamageSource damageSource = world.getDamageSources().lightningBolt();
            entity.damage(world, damageSource, 1.0F); // <-- Updated to 1.20.5+ method signature
        }

        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
