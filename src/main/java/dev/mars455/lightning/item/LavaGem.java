package dev.mars455.lightning.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LavaGem extends Item {
	public LavaGem(Settings settings) {
		super(settings);
	}

	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos().offset(context.getSide());
		context.getPlayer().playSound(
			SoundEvents.BLOCK_GLASS_BREAK,
				2.0F, // volume
				0.1F  // pitch
		);
        	if (!world.isClient && world.getBlockState(pos).isAir() || world.getBlockState(pos).isLiquid()) {
			world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 11);
			context.getStack().decrement(1);
            	assert context.getPlayer() != null;
		return ActionResult.SUCCESS;
		}

        return ActionResult.PASS;
	}
}
