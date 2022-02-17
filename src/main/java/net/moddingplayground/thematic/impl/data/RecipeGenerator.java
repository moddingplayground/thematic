package net.moddingplayground.thematic.impl.data;

import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.moddingplayground.frame.api.toymaker.v0.generator.recipe.AbstractRecipeGenerator;
import net.moddingplayground.thematic.api.Thematic;
import net.moddingplayground.thematic.api.tag.ThematicItemTags;
import net.moddingplayground.thematic.api.theme.Decoratable;
import net.moddingplayground.thematic.api.theme.Theme;
import net.moddingplayground.thematic.api.theme.data.DecoratableDataToymaker;

import static net.moddingplayground.thematic.impl.block.ThematicBlocks.*;

public class RecipeGenerator extends AbstractRecipeGenerator {
    public RecipeGenerator(String modId) {
        super(modId);
    }

    @Override
    public void generate() {
        this.add("decorators_table", ShapedRecipeJsonFactory.create(DECORATORS_TABLE, 1)
                                                            .input('#', Ingredient.fromTag(ItemTags.PLANKS))
                                                            .input('@', Ingredient.fromTag(ThematicItemTags.DYES))
                                                            .pattern("@@")
                                                            .pattern("##")
                                                            .pattern("##")
                                                            .criterion("has_plank", hasItems(ItemTags.PLANKS))
                                                            .criterion("has_dye", hasItems(ThematicItemTags.DYES)));

        for (Theme theme : Thematic.THEME_REGISTRY) {
            for (Decoratable decoratable : Thematic.DECORATABLE_REGISTRY) {
                decoratable.getData(theme, DecoratableDataToymaker.class).ifPresent(t -> t.generateRecipes(this));
            }
        }
    }
}
