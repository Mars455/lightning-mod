package dev.mars455.lightning;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.world.World;

public class SparkEntity extends BlazeEntity {
    public SparkEntity(EntityType<? extends BlazeEntity> entityType, World world) {
        super(entityType, world);
    }
}
