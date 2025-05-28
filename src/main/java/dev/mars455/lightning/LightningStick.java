package dev.mars455.lightning;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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

		// Get the player's position and direction
		Vec3d playerPos = user.getPos();
		float yaw = user.getYaw();
		float pitch = user.getPitch();

		// Calculate the direction the player is facing
		double xOffset = -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
		double yOffset = -Math.sin(Math.toRadians(pitch));
		double zOffset = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));

		// Create a vector for the strike position
		Vec3d strikePos = playerPos.add(xOffset * 5, yOffset * 5, zOffset * 5);
		BlockPos strikeBlockPos = new BlockPos((int) strikePos.x, (int) strikePos.y, (int) strikePos.z); // Cast to int

		// Spawn the lightning bolt.
		LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
		lightningBolt.setPosition(strikeBlockPos.getX() + 0.5, strikeBlockPos.getY(), strikeBlockPos.getZ() + 0.5);
		world.spawnEntity(lightningBolt);

		// Decrement the item stack
		ItemStack heldStack = user.getStackInHand(hand);
		heldStack.decrement(1);

		return ActionResult.SUCCESS;
	}
}
