package dev.mars455.lightning;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningStick extends Item {
	public LightningStick(Settings settings) {
		super(settings);
	}
	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		// Ensure we don't spawn the lightning only on the client.
		// This is to prevent desync.
		if (world.isClient) {
			return ActionResult.PASS;
		}

		BlockPos frontOfPlayer = user.getBlockPos().offset(user.getFacing(), 5);

		// Spawn the lightning bolt.
		LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
		lightningBolt.setPosition(frontOfPlayer.toCenterPos());
		world.spawnEntity(lightningBolt);
		ItemStack heldStack = user.getStackInHand(hand);
		heldStack.decrement(1);

		return ActionResult.SUCCESS;
	}

}