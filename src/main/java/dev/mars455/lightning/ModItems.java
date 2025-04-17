package dev.mars455.lightning;

import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
	public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
		// Create the item key.
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Lightning.MOD_ID, name));

		// Create the item instance.
		Item item = itemFactory.apply(settings.registryKey(itemKey));

		// Register the item.
		Registry.register(Registries.ITEM, itemKey, item);

		return item;
	}
	public static final Item LIGHTNING_DUST = register("lightning_dust", Item::new, new Item.Settings());
	public static final ConsumableComponent ELECTRIC_FOOD_CONSUMABLE_COMPONENT = ConsumableComponents.food()
			// The duration is in ticks, 20 ticks = 1 second
			.consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.SATURATION, 6 * 20, 5), 0.8f))
			.build();
	public static final FoodComponent ELECTRIC_FOOD_COMPONENT = new FoodComponent.Builder()
			.alwaysEdible()
			.build();
	public static final Item SHOCKING_APPLE = register(
		"shocking_apple",
		Item::new,
		new Item.Settings().food(ELECTRIC_FOOD_COMPONENT, ELECTRIC_FOOD_CONSUMABLE_COMPONENT)
		);
	public static final ToolMaterial SHOCKING_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_WOODEN_TOOL,
			4550,
			15.0F,
			15F,
			30,
			ToolMaterial.DIAMOND.repairItems()
	);
	public static final Item SHOCKING_SWORD = register(
			"shocking_sword",
			settings -> new SwordItem(SHOCKING_TOOL_MATERIAL, 19f, 0.5f, settings),
			new Item.Settings()
	);

	public static final Item SHOCKING_HELMET = register(
			"shocking_helmet",
			settings -> new ArmorItem(ShockingArmorMaterial.INSTANCE, EquipmentType.HELMET, settings),
			new Item.Settings().maxDamage(EquipmentType.HELMET.getMaxDamage(ShockingArmorMaterial.BASE_DURABILITY))
	);
	public static final Item SHOCKING_CHESTPLATE = register("shocking_chestplate",
			settings -> new ArmorItem(ShockingArmorMaterial.INSTANCE, EquipmentType.CHESTPLATE, settings),
			new Item.Settings().maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ShockingArmorMaterial.BASE_DURABILITY))
	);

	public static final Item SHOCKING_LEGGINGS = register(
			"shocking_leggings",
			settings -> new ArmorItem(ShockingArmorMaterial.INSTANCE, EquipmentType.LEGGINGS, settings),
			new Item.Settings().maxDamage(EquipmentType.LEGGINGS.getMaxDamage(ShockingArmorMaterial.BASE_DURABILITY))
	);

	public static final Item SHOCKING_BOOTS = register(
			"shocking_boots",
			settings -> new ArmorItem(ShockingArmorMaterial.INSTANCE, EquipmentType.BOOTS, settings),
			new Item.Settings().maxDamage(EquipmentType.BOOTS.getMaxDamage(ShockingArmorMaterial.BASE_DURABILITY))
	);

	public static void initialize() {
		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.LIGHTNING_DUST, 128 * 20);
			builder.add(ModItems.SHOCKING_APPLE, 128 * 20);
		});

	}
}

