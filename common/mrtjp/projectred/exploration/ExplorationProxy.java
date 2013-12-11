package mrtjp.projectred.exploration;

import static mrtjp.projectred.ProjectRedExploration.*;
import mrtjp.projectred.ProjectRedExploration;
import mrtjp.projectred.core.Configurator;
import mrtjp.projectred.core.IProxy;
import mrtjp.projectred.core.PRColors;
import mrtjp.projectred.exploration.BlockOre.EnumOre;
import mrtjp.projectred.exploration.BlockSpecialStone.EnumSpecialStone;
import mrtjp.projectred.exploration.BlockStainedLeaf.EnumDyeTrees;
import net.minecraft.block.Block;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.microblock.BlockMicroMaterial;
import codechicken.microblock.MicroMaterialRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ExplorationProxy implements IProxy
{
    @Override
    public void preinit()
    {
    }

    @Override
    public void init()
    {
        if (Configurator.retroGeneration)
            RetroGenerationManager.registerRetroGenerators();
        else
            GameRegistry.registerWorldGenerator(GenerationManager.instance);

        itemWoolGin = new ItemWoolGin(Configurator.item_woolginID.getInt());
        itemBackpack = new ItemBackpack(Configurator.item_backpackID.getInt());

        blockOres = new BlockOre(Configurator.block_oresID.getInt());
        GameRegistry.registerBlock(blockOres, ItemBlockOre.class, "projectred.exploration.ore");
        for (EnumOre o : EnumOre.VALID_ORES)
            MinecraftForge.setBlockHarvestLevel(blockOres, "pickaxe", o.harvesLevel);

        blockStones = new BlockSpecialStone(Configurator.block_stonesID.getInt());
        GameRegistry.registerBlock(blockStones, ItemBlockSpecialStone.class, "projectred.exploration.stone");

        blockStoneWalls = new BlockSpecialStoneWall(Configurator.block_stoneWallsID.getInt());
        GameRegistry.registerBlock(blockStoneWalls, ItemBlockSpecialStoneWalls.class, "projectred.exploration.stonewalls");

        blockStainedLeaf = new BlockStainedLeaf(Configurator.block_stainedLeafID.getInt());
        GameRegistry.registerBlock(blockStainedLeaf, ItemBlockMetaHandler.class, "projectred.exploration.dyeleaf");

        blockStainedSapling = new BlockStainedSapling(Configurator.block_stainedSaplingID.getInt());
        GameRegistry.registerBlock(blockStainedSapling, ItemBlockStainedSapling.class, "projectred.exploration.dyesapling");

        for (int i = 0; i < 16; i++)
            OreDictionary.registerOre(PRColors.get(i).getOreDict(), EnumDyeTrees.VALID_FOLIAGE[i].getSappling());

        if (Configurator.gen_SpreadingMoss.getBoolean(true))
        {
            int mc = Block.cobblestoneMossy.blockID;
            Block.blocksList[mc] = null;
            new BlockPhotosyntheticCobblestone(mc);

            int sb = Block.stoneBrick.blockID;
            Block.blocksList[sb] = null;
            new BlockPhotosyntheticStoneBrick(sb);
        }

        toolMaterialRuby = EnumHelper.addToolMaterial("RUBY", 2, 500, 8.0F, 4, 12);
        toolMaterialSapphire = EnumHelper.addToolMaterial("SAPPHIRE", 2, 500, 8.0F, 3, 16);
        toolMaterialPeridot = EnumHelper.addToolMaterial("PERIDOT", 2, 500, 8.75F, 3.25F, 12);

        itemRubyAxe = new ItemGemAxe(Configurator.item_rubyAxe.getInt(), EnumSpecialTool.RUBYAXE);
        itemSapphireAxe = new ItemGemAxe(Configurator.item_sapphireAxe.getInt(), EnumSpecialTool.SAPPHIREAXE);
        itemPeridotAxe = new ItemGemAxe(Configurator.item_peridotAxe.getInt(), EnumSpecialTool.PERIDOTAXE);
        MinecraftForge.setToolClass(itemRubyAxe, "axe", 2);
        MinecraftForge.setToolClass(itemSapphireAxe, "axe", 2);
        MinecraftForge.setToolClass(itemPeridotAxe, "axe", 2);

        itemRubyHoe = new ItemGemHoe(Configurator.item_rubyHoe.getInt(), EnumSpecialTool.RUBYHOE);
        itemSapphireHoe = new ItemGemHoe(Configurator.item_sapphireHoe.getInt(), EnumSpecialTool.SAPPHIREHOE);
        itemPeridotHoe = new ItemGemHoe(Configurator.item_peridotHoe.getInt(), EnumSpecialTool.PERIDOTHOE);
        MinecraftForge.setToolClass(itemRubyHoe, "hoe", 2);
        MinecraftForge.setToolClass(itemSapphireHoe, "hoe", 2);
        MinecraftForge.setToolClass(itemPeridotHoe, "hoe", 2);

        itemRubyPickaxe = new ItemGemPickaxe(Configurator.item_rubyPickaxe.getInt(), EnumSpecialTool.RUBYPICKAXE);
        itemSapphirePickaxe = new ItemGemPickaxe(Configurator.item_sapphirePickaxe.getInt(), EnumSpecialTool.SAPPHIREPICKAXE);
        itemPeridotPickaxe = new ItemGemPickaxe(Configurator.item_peridotPickaxe.getInt(), EnumSpecialTool.PERIDOTPICKAXE);
        MinecraftForge.setToolClass(itemRubyPickaxe, "pickaxe", 2);
        MinecraftForge.setToolClass(itemSapphirePickaxe, "pickaxe", 2);
        MinecraftForge.setToolClass(itemPeridotPickaxe, "pickaxe", 2);

        itemRubyShovel = new ItemGemShovel(Configurator.item_rubyShovel.getInt(), EnumSpecialTool.RUBYSHOVEL);
        itemSapphireShovel = new ItemGemShovel(Configurator.item_sapphireShovel.getInt(), EnumSpecialTool.SAPPHIRESHOVEL);
        itemPeridotShovel = new ItemGemShovel(Configurator.item_peridotShovel.getInt(), EnumSpecialTool.PERIDOTSHOVEL);
        MinecraftForge.setToolClass(itemRubyShovel, "shovel", 2);
        MinecraftForge.setToolClass(itemSapphireShovel, "shovel", 2);
        MinecraftForge.setToolClass(itemPeridotShovel, "shovel", 2);

        itemRubySword = new ItemGemSword(Configurator.item_rubySword.getInt(), EnumSpecialTool.RUBYSWORD);
        itemSapphireSword = new ItemGemSword(Configurator.item_sapphireSword.getInt(), EnumSpecialTool.SAPPHIRESWORD);
        itemPeridotSword = new ItemGemSword(Configurator.item_peridotSword.getInt(), EnumSpecialTool.PERIDOTSWORD);
        MinecraftForge.setToolClass(itemRubySword, "sword", 2);
        MinecraftForge.setToolClass(itemSapphireSword, "sword", 2);
        MinecraftForge.setToolClass(itemPeridotSword, "sword", 2);

        itemGoldSaw = new ItemGemSaw(Configurator.item_goldSaw.getInt(), EnumSpecialTool.GOLDSAW);
        itemRubySaw = new ItemGemSaw(Configurator.item_rubySaw.getInt(), EnumSpecialTool.RUBYSAW);
        itemSapphireSaw = new ItemGemSaw(Configurator.item_sapphireSaw.getInt(), EnumSpecialTool.SAPPHIRESAW);
        itemPeridotSaw = new ItemGemSaw(Configurator.item_peridotSaw.getInt(), EnumSpecialTool.PERIDOTSAW);

        itemWoodSickle = new ItemGemSickle(Configurator.item_woodSickle.getInt(), EnumSpecialTool.WOODSICKLE);
        itemStoneSickle = new ItemGemSickle(Configurator.item_stoneSickle.getInt(), EnumSpecialTool.STONESICKLE);
        itemIronSickle = new ItemGemSickle(Configurator.item_ironSickle.getInt(), EnumSpecialTool.IRONSICKLE);
        itemGoldSickle = new ItemGemSickle(Configurator.item_goldSickle.getInt(), EnumSpecialTool.GOLDSICKLE);
        itemRubySickle = new ItemGemSickle(Configurator.item_rubySickle.getInt(), EnumSpecialTool.RUBYSICKLE);
        itemSapphireSickle = new ItemGemSickle(Configurator.item_sapphireSickle.getInt(), EnumSpecialTool.SAPPHIRESICKLE);
        itemPeridotSickle = new ItemGemSickle(Configurator.item_peridotSickle.getInt(), EnumSpecialTool.PERIDOTSICKLE);
        itemDiamondSickle = new ItemGemSickle(Configurator.item_diamondSickle.getInt(), EnumSpecialTool.DIAMONDSICKLE);

        for (EnumSpecialStone s : EnumSpecialStone.VALID_STONE)
            MicroMaterialRegistry.registerMaterial(new BlockMicroMaterial(ProjectRedExploration.blockStones, s.meta), ProjectRedExploration.blockStones.getUnlocalizedName() + (s.meta > 0 ? "_" + s.meta : ""));

        ExplorationRecipes.initOreDict();
    }

    @Override
    public void postinit()
    {
        ExplorationRecipes.initRecipes();
    }
}
