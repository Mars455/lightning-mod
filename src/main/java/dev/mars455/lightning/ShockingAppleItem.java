package dev.mars455.lightning;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShockingAppleItem extends Item {
    public ShockingAppleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient) {
            BlockPos pos = user.getBlockPos();
            Entity lightning = new net.minecraft.entity.LightningEntity(EntityType.LIGHTNING_BOLT, (ServerWorld) world);
            lightning.refreshPositionAfterTeleport(pos.getX(), pos.getY(), pos.getZ());
            ((ServerWorld) world).spawnEntity(lightning);
        }
        return super.finishUsing(stack, world, user);
    }
}
