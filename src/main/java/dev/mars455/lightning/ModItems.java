package dev.mars455.lightning;

import dev.mars455.lightning.item.LavaGem;
import dev.mars455.lightning.item.LavaSwordItem;
import dev.mars455.lightning.item.ShockingSwordItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
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
	public static final RegistryKey<ItemGroup> LIGHTNING_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Lightning.MOD_ID, "lightning"));
	public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(ModItems.SHOCKING_APPLE))
			.displayName(Text.translatable("itemGroup.lightning"))
			.build();
	public static final Item LAVA_GEM = register(
			"lava_gem",
            LavaGem::new,
			new Item.Settings()
	);

	public static final Item LIGHTNING_STICK = register(
			"lightning_stick",
			LightningStick::new,
			new Item.Settings()
	);
	public static final ConsumableComponent ELECTRIC_FOOD_CONSUMABLE_COMPONENT = ConsumableComponents.food()
			// The duration is in ticks, 20 ticks = 1 second

			.consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.SATURATION, 6 * 20, 5), 0.8f))
			.consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.SPEED, 30 * 20, 5), 1.0f))
			.consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5 * 20, 10), 1.0f))
			.build();
	public static final FoodComponent ELECTRIC_FOOD_COMPONENT = new FoodComponent.Builder()
			.alwaysEdible()
			.nutrition(10)
			.build();
	public static final Item SHOCKING_APPLE = register(
			"shocking_apple",
			ShockingAppleItem::new,
			new Item.Settings().food(ELECTRIC_FOOD_COMPONENT, ELECTRIC_FOOD_CONSUMABLE_COMPONENT)
	);
	public static final Item LIGHTNING_DUST = register(
			"lightning_dust",
			LightningStick::new,
			new Item.Settings()
	);
	public static final Item LIGHTNING_INGOT = register(
			"lightning_ingot",
			Item::new,
			new Item.Settings()
	);
	public static final Item ELEMENTAL_DUST = register(
			"elemental_dust",
			Item::new,
			new Item.Settings()
	);
	public static final ToolMaterial SHOCKING_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_WOODEN_TOOL,
			4550,
			15.0F,
			1.0F,
			30,
			ToolMaterial.NETHERITE.repairItems()
	);
	public static final ToolMaterial LAVA_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_WOODEN_TOOL,
			2990,
			1.0F,
			0.1F,
			30,
			ToolMaterial.NETHERITE.repairItems()
	);
	public static final Item SHOCKING_SWORD = register(
			"shocking_sword",
			settings -> new ShockingSwordItem(SHOCKING_TOOL_MATERIAL, 7.0f, 3.0f, settings),
			new Item.Settings()
	);
	public static final Item LAVA_GEMSTONE_SWORD = register(
			"lava_gemstone_sword",
			settings -> new LavaSwordItem(LAVA_TOOL_MATERIAL, 18.0f, -3.0f, settings),
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
	public static final Item CARBON_FIBRE = register(
			"carbon_fibre",
			Item::new,
			new Item.Settings()
	);
	public static final Item CARBON_THREADS = register(
			"carbon_threads",
			Item::new,
			new Item.Settings()

	);
	public static final Item CARBON_TUBE = register(
			"carbon_tube",
			Item::new,
			new Item.Settings()
	);

	public static final Item TASER_PART = register(
			"taser_part",
			Item::new,
			new Item.Settings()
	);
	public static final Item ESCRIMA = register(
			"escrima",
			settings -> new SwordItem(SHOCKING_TOOL_MATERIAL, 10.0f, 1.0f, settings),
			new Item.Settings()
	);
	public static void initialize() {
		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.LIGHTNING_DUST, 128 * 20);
			builder.add(ModItems.SHOCKING_APPLE, 128 * 20);
		});
		Registry.register(Registries.ITEM_GROUP, LIGHTNING_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
		ItemGroupEvents.modifyEntriesEvent(LIGHTNING_ITEM_GROUP_KEY).register(itemGroup -> {
			itemGroup.add(ModItems.LIGHTNING_DUST);
			itemGroup.add(ModItems.SHOCKING_APPLE);
			itemGroup.add(ModItems.SHOCKING_SWORD);
			itemGroup.add(ModItems.SHOCKING_HELMET);
			itemGroup.add(ModItems.SHOCKING_BOOTS);
			itemGroup.add(ModItems.SHOCKING_LEGGINGS);
			itemGroup.add(ModItems.SHOCKING_CHESTPLATE);
			itemGroup.add(ModItems.LAVA_GEMSTONE_SWORD);
			itemGroup.add(ModItems.ELEMENTAL_DUST);
			itemGroup.add(ModItems.LIGHTNING_INGOT);
			itemGroup.add(ModItems.LIGHTNING_STICK);
		});
	}
}

