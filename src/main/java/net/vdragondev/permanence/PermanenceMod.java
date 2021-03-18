package net.vdragondev.permanence;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.*;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vdragondev.permanence.blocks.*;
import net.vdragondev.permanence.enchantments.DamnationEnchantment;
import net.vdragondev.permanence.enchantments.FrostAspectEnchantment;
import net.vdragondev.permanence.enchantments.HeroismEnchantment;
import net.vdragondev.permanence.enchantments.VenomousEnchantment;
import net.vdragondev.permanence.kiln.*;
import net.vdragondev.permanence.mats.PermaToolMaterials;
import net.vdragondev.permanence.quern.QuernBlockEntity;
import net.vdragondev.permanence.quern.QuernFurnace;
import net.vdragondev.permanence.quern.QuernRecipe;
import net.vdragondev.permanence.quern.QuernScreenHandler;
import net.vdragondev.permanence.toolstuff.DrillToolItem;
import net.vdragondev.permanence.toolstuff.MattockItem;
import net.vdragondev.permanence.toolstuff.UnvanishableToolItem;

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
    public static final Item BITUMEN = new Item(new Item.Settings().group(ItemGroup.MISC));
    //tools
  public static final UnvanishableToolItem ADVANCED_DRILL = new DrillToolItem(2, -1, PermaToolMaterials.ADVANCED_MACHINE, ImmutableSet.of(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, new Block[]{Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.SOUL_SOIL, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.NETHER_GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.PISTON_HEAD}), new Item.Settings());
	public static final ToolItem WOODEN_MATTOCK = new MattockItem(2, -3,ToolMaterials.WOOD, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem STONE_MATTOCK = new MattockItem(3, -3,ToolMaterials.STONE, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem IRON_MATTOCK = new MattockItem(3, -3,ToolMaterials.IRON, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem GOLDEN_MATTOCK = new MattockItem(3, -3,ToolMaterials.GOLD, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem DIAMOND_MATTOCK = new MattockItem(3, -3,ToolMaterials.DIAMOND, new Item.Settings().group(ItemGroup.TOOLS));
	public static final ToolItem NETHERITE_MATTOCK = new MattockItem(6, -3,ToolMaterials.NETHERITE, new Item.Settings().group(ItemGroup.TOOLS).fireproof());
	//blocks
	public static final Block QUERN;
	public static final Block KILN;
  public static final Block OBSIDIAN_DOOR = new ImmobileDoor(FabricBlockSettings.of(Material.METAL, MaterialColor.BLACK).sounds(BlockSoundGroup.STONE).strength(50.0F, 1200.0F).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool().nonOpaque());
  public static final Block STONE_DOOR = new StandardDoor(FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY).sounds(BlockSoundGroup.STONE).strength(1.5f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool().nonOpaque());
  public static final Block ANDESITE_DOOR = new StandardDoor(FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY).sounds(BlockSoundGroup.STONE).strength(1.5f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool().nonOpaque());
  public static final Block DIORITE_DOOR = new StandardDoor(FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY).sounds(BlockSoundGroup.STONE).strength(1.5f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool().nonOpaque());
  public static final Block GRANITE_DOOR = new StandardDoor(FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY).sounds(BlockSoundGroup.STONE).strength(1.5f, 6.0f).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool().nonOpaque());
	public static final Block PROPULSION_GEL = new PropulsionGelBlock(FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MaterialColor.ORANGE).strength(0,0).slipperiness(0.1f).sounds(BlockSoundGroup.SLIME));
  public static final Block BLAST_GLASS = new ImmobileBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.BLACK).strength(50.0F, 1200.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
  public static final Block BLAST_GLASS_MKII = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.BLACK).strength(50.0F, 1200.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
  public static final Block FRAMED_GLASS = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.GRAY).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
  public static final Block FRAMED_GLASS_PANE = new CustomPaneBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.GRAY).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block WHITE_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.WHITE).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block ORANGE_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.ORANGE).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block MAGENTA_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.MAGENTA).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block LIGHT_BLUE_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.LIGHT_BLUE).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block YELLOW_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.YELLOW).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block LIME_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.LIME).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block PINK_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.PINK).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block GRAY_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.GRAY).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block LIGHT_GRAY_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.LIGHT_GRAY).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block CYAN_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.CYAN).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block PURPLE_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.PURPLE).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block BLUE_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.BLUE).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block BROWN_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.BROWN).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block GREEN_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.GREEN).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block RED_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.RED).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block BLACK_CRYSTAL = new GlassBlock(FabricBlockSettings.of(Material.GLASS, MaterialColor.BLACK).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool());
  public static final Block BLOCK_OF_BITUMEN = new BitumenBlock(FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MaterialColor.BLACK).strength(0,0).slipperiness(1.0f).sounds(BlockSoundGroup.SLIME));

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
		//breedingTable

	}
	@Override
	public void onInitialize() {
		//renderLayers
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.OBSIDIAN_DOOR, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.BLAST_GLASS, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.BLAST_GLASS_MKII, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.FRAMED_GLASS_PANE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.FRAMED_GLASS, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.WHITE_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.ORANGE_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.MAGENTA_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.LIGHT_BLUE_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.YELLOW_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.LIME_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.PINK_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.GRAY_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.LIGHT_GRAY_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.CYAN_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.PURPLE_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.BLUE_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.BROWN_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.GREEN_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.RED_CRYSTAL, RenderLayer.getTranslucent());
    BlockRenderLayerMap.INSTANCE.putBlock(PermanenceMod.BLACK_CRYSTAL, RenderLayer.getTranslucent());
    //items
	  Registry.register(Registry.ITEM, new Identifier(MOD_ID, "iron_ore_chunk"), IRON_ORE_CHUNK);
	  Registry.register(Registry.ITEM, new Identifier(MOD_ID, "gold_ore_chunk"), GOLD_ORE_CHUNK);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "netherite_nugget"), NETHERITE_NUGGET);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "diamond_shard"), DIAMOND_SHARD);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "emerald_shard"), EMERALD_SHARD);
      Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bitumen"), BITUMEN);
	  //tools
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "advanced_drill"), ADVANCED_DRILL);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "netherite_mattock"), NETHERITE_MATTOCK);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "diamond_mattock"), DIAMOND_MATTOCK);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "iron_mattock"), IRON_MATTOCK);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stone_mattock"), STONE_MATTOCK);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wooden_mattock"), WOODEN_MATTOCK);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "golden_mattock"), GOLDEN_MATTOCK);
    //blocks
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "obsidian_door"), OBSIDIAN_DOOR);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "stone_door"), STONE_DOOR);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "andesite_door"), ANDESITE_DOOR);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "diorite_door"), DIORITE_DOOR);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "granite_door"), GRANITE_DOOR);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "propulsion_gel"), PROPULSION_GEL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "blast_glass"), BLAST_GLASS);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "blast_glass_mkii"), BLAST_GLASS_MKII);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "framed_glass"), FRAMED_GLASS);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "framed_glass_pane"), FRAMED_GLASS_PANE);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "white_crystal"), WHITE_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "orange_crystal"), ORANGE_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "magenta_crystal"), MAGENTA_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "light_blue_crystal"), LIGHT_BLUE_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "yellow_crystal"), YELLOW_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "lime_crystal"), LIME_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "pink_crystal"), PINK_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "gray_crystal"), GRAY_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "light_gray_crystal"), LIGHT_GRAY_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cyan_crystal"), CYAN_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "purple_crystal"), PURPLE_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "blue_crystal"), BLUE_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "brown_crystal"), BROWN_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "green_crystal"), GREEN_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "red_crystal"), RED_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "black_crystal"), BLACK_CRYSTAL);
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "block_of_bitumen"), BLOCK_OF_BITUMEN);
      //blockItems
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "obsidian_door"), new BlockItem(OBSIDIAN_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "stone_door"), new BlockItem(STONE_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "andesite_door"), new BlockItem(ANDESITE_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "diorite_door"), new BlockItem(DIORITE_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "granite_door"), new BlockItem(GRANITE_DOOR, new Item.Settings().group(ItemGroup.REDSTONE)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "propulsion_gel"), new BlockItem(PROPULSION_GEL, new Item.Settings()));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blast_glass"), new BlockItem(BLAST_GLASS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blast_glass_mkii"), new BlockItem(BLAST_GLASS_MKII, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "framed_glass"), new BlockItem(FRAMED_GLASS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "framed_glass_pane"), new BlockItem(FRAMED_GLASS_PANE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "white_crystal"), new BlockItem(WHITE_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "orange_crystal"), new BlockItem(ORANGE_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "magenta_crystal"), new BlockItem(MAGENTA_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "light_blue_crystal"), new BlockItem(LIGHT_BLUE_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "yellow_crystal"), new BlockItem(YELLOW_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lime_crystal"), new BlockItem(LIME_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "pink_crystal"), new BlockItem(PINK_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "gray_crystal"), new BlockItem(GRAY_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "light_gray_crystal"), new BlockItem(LIGHT_GRAY_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cyan_crystal"), new BlockItem(CYAN_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "purple_crystal"), new BlockItem(PURPLE_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blue_crystal"), new BlockItem(BLUE_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "brown_crystal"), new BlockItem(BROWN_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "green_crystal"), new BlockItem(GREEN_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_crystal"), new BlockItem(RED_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "black_crystal"), new BlockItem(BLACK_CRYSTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "block_of_bitumen"), new BlockItem(BLOCK_OF_BITUMEN, new Item.Settings().group(ItemGroup.REDSTONE)));
  }
}
