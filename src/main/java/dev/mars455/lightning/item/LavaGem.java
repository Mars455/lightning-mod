package dev.mars455.lightning.item;

import com.nimbusds.openid.connect.sdk.assurance.Status;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LavaGem extends Item {
	public LavaGem(Settings settings) {
		super(settings);
	}

	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos().offset(context.getSide());
		if (!world.isClient && world.getBlockState(pos).isAir() || world.getBlockState(pos).isLiquid()) {
			world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 11);
			context.getStack().decrement(1);
            assert context.getPlayer() != null;
            context.getPlayer().playSound(
					SoundEvents.BLOCK_GLASS_BREAK,
					2.0F, // volume
					0.1F  // pitch
			);
			return ActionResult.SUCCESS;
		}

        return ActionResult.PASS;
	}
}
