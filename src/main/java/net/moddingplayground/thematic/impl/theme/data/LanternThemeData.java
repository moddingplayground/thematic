package net.moddingplayground.thematic.impl.theme.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.moddingplayground.frame.api.toymaker.v0.generator.model.block.AbstractStateModelGenerator;
import net.moddingplayground.frame.api.toymaker.v0.generator.model.item.AbstractItemModelGenerator;
import net.moddingplayground.frame.api.toymaker.v0.generator.tag.AbstractTagGenerator;
import net.moddingplayground.thematic.api.theme.Decoratable;
import net.moddingplayground.thematic.api.theme.Theme;
import net.moddingplayground.thematic.api.theme.data.BlockItemThemeData;

import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.BuildingBlocks.*;

public class LanternThemeData extends BlockItemThemeData {
    public LanternThemeData(Theme theme, BlockFactory block) {
        super(theme, () -> block.create(FabricBlockSettings.copyOf(Blocks.LANTERN)));
    }

    public static LanternThemeData of(Theme theme, BlockFactory block) {
        return new LanternThemeData(theme, block);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerClient(Decoratable decoratable) {
        Block block = this.getBlock();
        BlockRenderLayerMap renderLayers = BlockRenderLayerMap.INSTANCE;
        renderLayers.putBlock(block, RenderLayer.getCutout());
    }

    @Override
    public void generateStateModels(AbstractStateModelGenerator gen) {
        Block block = this.getBlock();
        gen.add(block, b -> hanging(gen.name(b)));
    }

    @Override
    public void generateItemModels(AbstractItemModelGenerator gen) {
        Item item = this.getItem();
        gen.add(item, gen::generatedItem);
    }

    @Override
    public void generateBlockTags(AbstractTagGenerator<Block> gen) {
        gen.add(BlockTags.PICKAXE_MINEABLE, this.getBlock());
    }

    @FunctionalInterface public interface BlockFactory { Block create(AbstractBlock.Settings settings); }
}
