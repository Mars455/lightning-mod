package dev.mars455.lightning;

import net.minecraft.item.equipment.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ShockingArmorMaterial {
	public static final int BASE_DURABILITY = 1500;
	public static final RegistryKey<EquipmentAsset> SHOCKING_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(Lightning.MOD_ID, "shocking"));
	public static final ArmorMaterial INSTANCE = new ArmorMaterial(
			BASE_DURABILITY,
			Map.of(
					EquipmentType.HELMET, 12,
					EquipmentType.CHESTPLATE, 25,
					EquipmentType.LEGGINGS, 20,
					EquipmentType.BOOTS, 10
			),
			25,
			SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
			10.0F,
			10.0F,
			ArmorMaterials.DIAMOND.repairIngredient(),
			SHOCKING_ARMOR_MATERIAL_KEY
	);
}
