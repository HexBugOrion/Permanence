package net.vdragondev.permanence;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vdragondev.permanence.blockentities.QuernBlockEntity;
import net.vdragondev.permanence.furnaces.QuernFurnace;
import net.vdragondev.permanence.recipes.QuernRecipe;
import net.vdragondev.permanence.screens.QuernScreenHandler;

public class PermanenceMod implements ModInitializer {

	public static String MOD_ID = "permanence";

	//blocks
	public static final Block QUERN;
	//blockEntities
	public static final BlockEntityType QUERN_TYPE;
	//recipeTypes and serializers
	public static final RecipeType<QuernRecipe> QUERN_RECIPE_TYPE;
	public static final RecipeSerializer<QuernRecipe> QUERN_SERIALIZER;
	//screenHandlers
	public static final ScreenHandlerType<QuernScreenHandler> QUERN_SCREEN;

	static {

		//blocks
		QUERN = Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "quern"), new QuernFurnace(FabricBlockSettings.of(Material.STONE).strength(3.5f, 3.5f).breakByTool(FabricToolTags.PICKAXES)));
		//blockEntities
		QUERN_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "quern"), BlockEntityType.Builder.create(QuernBlockEntity::new, QUERN).build(null));
		//items and blockItems
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "quern"), new BlockItem(QUERN, new Item.Settings().group(ItemGroup.DECORATIONS)));
		//recipes and serializers
		QUERN_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, "quern"), new RecipeType<QuernRecipe>() {
			@Override
			public String toString() {
				return "quern";
			}
		});
		QUERN_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, "quern"), new CookingRecipeSerializer<>(QuernRecipe::new, 200));
		QUERN_SCREEN = ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID, "quern"), QuernScreenHandler::new);
	}

	@Override
	public void onInitialize() {


	}

}
