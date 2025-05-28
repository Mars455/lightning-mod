package dev.mars455.lightning.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ShockingSwordItem extends SwordItem {
	public ShockingSwordItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		World world = target.getWorld();
		if (!world.isClient && world instanceof ServerWorld serverWorld) {
			BlockPos pos = target.getBlockPos();
			LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, serverWorld);
			lightning.refreshPositionAfterTeleport(pos.getX(), pos.getY(), pos.getZ());
			serverWorld.spawnEntity(lightning);
		}
		return super.postHit(stack, target, attacker);
	}
}
