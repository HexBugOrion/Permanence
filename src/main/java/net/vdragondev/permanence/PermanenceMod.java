package net.vdragondev.permanence;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.*;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vdragondev.permanence.enchantments.DamnationEnchantment;
import net.vdragondev.permanence.enchantments.FrostAspectEnchantment;
import net.vdragondev.permanence.enchantments.HeroismEnchantment;
import net.vdragondev.permanence.enchantments.VenomousEnchantment;
import net.vdragondev.permanence.kiln.*;
import net.vdragondev.permanence.quern.QuernBlockEntity;
import net.vdragondev.permanence.quern.QuernFurnace;
import net.vdragondev.permanence.quern.QuernRecipe;
import net.vdragondev.permanence.quern.QuernScreenHandler;
import net.vdragondev.permanence.toolstuff.MattockItem;

import java.util.function.ToIntFunction;

public class PermanenceMod implements ModInitializer {

	public static String MOD_ID = "permanence";

	private static ToIntFunction<BlockState> createLightLevelFromBlockState(int litLevel) {
		return (blockState) -> {
			return (Boolean)blockState.get(Properties.LIT) ? litLevel : 0;
		};
	}

	//items
	public static final Item IRON_ORE_CHUNK = new Item(new Item.Settings().group(ItemGroup.MISC));
	public static final Item GOLD_ORE_CHUNK = new Item(new Item.Settings().group(ItemGroup.MISC));
	public static final Item NETHERITE_NUGGET = new Item(new Item.Settings().group(ItemGroup.MISC).fireproof());
	public static final Item DIAMOND_SHARD = new Item(new Item.Settings().group(ItemGroup.MISC));
	public static final Item EMERALD_SHARD = new Item(new Item.Settings().group(ItemGroup.MISC));
	//tools
	public static final ToolItem WOODEN_MATTOCK = new MattockItem(2, -3,ToolMaterials.WOOD, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem STONE_MATTOCK = new MattockItem(3, -3,ToolMaterials.STONE, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem IRON_MATTOCK = new MattockItem(3, -3,ToolMaterials.IRON, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem GOLDEN_MATTOCK = new MattockItem(3, -3,ToolMaterials.GOLD, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem DIAMOND_MATTOCK = new MattockItem(3, -3,ToolMaterials.DIAMOND, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem NETHERITE_MATTOCK = new MattockItem(6, -3,ToolMaterials.NETHERITE, new Item.Settings().group(ItemGroup.TOOLS).fireproof());
	//blocks
	public static final Block QUERN;
	public static final Block KILN;
	//blockEntities
	public static final BlockEntityType QUERN_TYPE;
	public static final BlockEntityType KILN_TYPE;
	//recipeTypes and serializers
	public static final RecipeType<QuernRecipe> QUERN_RECIPE_TYPE;
	public static final RecipeType<KilnRecipe> KILN_RECIPE_TYPE;
	public static final RecipeSerializer<QuernRecipe> QUERN_SERIALIZER;
	public static final RecipeSerializer<KilnRecipe> KILN_SERIALIZER;
	//screenHandlers
	public static final ScreenHandlerType<QuernScreenHandler> QUERN_SCREEN;
	public static final ScreenHandlerType<KilnScreenHandler> KILN_SCREEN;
	//enchants
	public static Enchantment DAMNATION = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "damnation"), new DamnationEnchantment(0));
	public static Enchantment FROST_ASPECT = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "frost_aspect"), new FrostAspectEnchantment());
    public static Enchantment VENOMOUS = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "venomous"), new VenomousEnchantment());
    public static Enchantment HEROISM = Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "heroism"), new HeroismEnchantment(0));

	static {
		//quern
		QUERN = Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "quern"), new QuernFurnace(FabricBlockSettings.of(Material.STONE).strength(3.5f, 3.5f).breakByTool(FabricToolTags.PICKAXES).requiresTool().luminance(createLightLevelFromBlockState(13)).breakByTool(FabricToolTags.PICKAXES)));
		QUERN_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "quern"), BlockEntityType.Builder.create(QuernBlockEntity::new, QUERN).build(null));
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "quern"), new BlockItem(QUERN, new Item.Settings().group(ItemGroup.DECORATIONS)));
		QUERN_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, "quern"), new RecipeType<QuernRecipe>() {
			@Override
			public String toString() {
				return "quern";
			}
		});
		QUERN_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, "quern"), new CookingRecipeSerializer<>(QuernRecipe::new, 200));
		//kiln
		KILN = Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "kiln"), new KilnFurnace(FabricBlockSettings.of(Material.STONE).strength(3.5f, 3.5f).breakByTool(FabricToolTags.PICKAXES).requiresTool().luminance(createLightLevelFromBlockState(13)).breakByTool(FabricToolTags.PICKAXES)));
		KILN_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "kiln"), BlockEntityType.Builder.create(KilnBlockEntity::new, KILN).build(null));
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "kiln"), new BlockItem(KILN, new Item.Settings().group(ItemGroup.DECORATIONS)));
		KILN_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, "kiln"), new RecipeType<KilnRecipe>() {
			@Override public String toString() {
				return "kiln";
			}
		});
		KILN_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, "kiln"), new CookingRecipeSerializer<>(KilnRecipe::new, 200));
		KILN_SCREEN = ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID, "kiln"), KilnScreenHandler::new);
		QUERN_SCREEN = ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID, "quern"), QuernScreenHandler::new);
	}
	@Override
	public void onInitialize() {
			//items
			Registry.register(Registry.ITEM, new Identifier(MOD_ID, "iron_ore_chunk"), IRON_ORE_CHUNK);
			Registry.register(Registry.ITEM, new Identifier(MOD_ID, "gold_ore_chunk"), GOLD_ORE_CHUNK);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "netherite_nugget"), NETHERITE_NUGGET);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "diamond_shard"), DIAMOND_SHARD);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "emerald_shard"), EMERALD_SHARD);
			//tools
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "netherite_mattock"), NETHERITE_MATTOCK);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "diamond_mattock"), DIAMOND_MATTOCK);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "iron_mattock"), IRON_MATTOCK);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stone_mattock"), STONE_MATTOCK);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wooden_mattock"), WOODEN_MATTOCK);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "golden_mattock"), GOLDEN_MATTOCK);
	}
}
