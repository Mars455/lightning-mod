package dev.mars455.lightning.item;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.server.command.SetBlockCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LavaSwordItem extends SwordItem {
	public LavaSwordItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		World world = target.getWorld();
		if (!world.isClient && world instanceof ServerWorld serverWorld) {
			BlockPos pos = target.getBlockPos();
			world.setBlockState(pos, Blocks.LAVA.getDefaultState());
			target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 1 * 20, 1), attacker);
			attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6*20, 1));
			target.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2*20, 1));
		}
		return super.postHit(stack, target, attacker);
	}
}
