package wtfcore.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChildBlockCustomMetadata extends Block{

	public Block parentBlock;
	public int parentMeta;


	protected ChildBlockCustomMetadata(Block block, int meta) {
		super(block.getMaterial());
		this.parentBlock = block;
		this.parentMeta = meta;
		this.setStepSound(parentBlock.stepSound);
		this.setHarvestLevel(parentBlock.getHarvestTool(parentMeta), parentBlock.getHarvestLevel(parentMeta));
		//this.setCreativeTab(CaveBiomes.tabCaveDecorations);
	}



	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
		return parentBlock.getIcon(p_149691_1_, parentMeta);
    }

    @Override
	public boolean isOpaqueCube(){return true;}
    @Override
    public boolean renderAsNormalBlock(){return true;}
	@Override
	@SideOnly(Side.CLIENT)
    public int getRenderType(){
		return parentBlock.getRenderType();
		}


    @Override
	@SideOnly(Side.CLIENT)
    public int getRenderBlockPass(){
    	return parentBlock.getRenderBlockPass();
    }
	@Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return parentBlock.getItemDropped(parentMeta, random, fortune);
    }

	@Override
	public void onBlockDestroyedByPlayer(World p_149664_1_, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_){
		if (parentBlock != Blocks.ice){
			parentBlock.onBlockDestroyedByPlayer(p_149664_1_, p_149664_2_, p_149664_3_, p_149664_4_, parentMeta);
		}
	}

    @Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        parentBlock.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, parentMeta);
    }
    @Override
	public float getPlayerRelativeBlockHardness(EntityPlayer p_149737_1_, World p_149737_2_, int p_149737_3_, int p_149737_4_, int p_149737_5_)
    {
        return ForgeHooks.blockStrength(parentBlock, p_149737_1_, p_149737_2_, p_149737_3_, p_149737_4_, p_149737_5_);
    }
    @Override
	public void dropXpOnBlockBreak(World p_149657_1_, int p_149657_2_, int p_149657_3_, int p_149657_4_, int p_149657_5_){
    	parentBlock.dropXpOnBlockBreak(p_149657_1_, p_149657_2_, p_149657_3_, p_149657_4_, p_149657_5_);
    }
    @Override
	public float getExplosionResistance(Entity p_149638_1_)
    {
        return parentBlock.getExplosionResistance(p_149638_1_);
    }
    @Override
	public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_){
    	parentBlock.harvestBlock(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, parentMeta);;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        parentBlock.registerBlockIcons(iconRegister);

    }

    @Override
    public int damageDropped(int metadata){
    	return parentBlock.damageDropped(parentMeta);
    }
    @Override
    public boolean canRenderInPass(int pass)
    {
    	return parentBlock.canRenderInPass(pass);
    }
    @Override
	@SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
       return parentBlock.getBlockColor();
    }
    @Override
	@SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_)
    {
        return parentBlock.getBlockColor();
    }
    @Override
	@SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
    	return parentBlock.colorMultiplier(p_149720_1_, p_149720_2_, p_149720_3_, p_149720_4_);
    }
    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        return parentBlock.getBlockHardness(world, x, y, z);
    }

    @Override
    public String getHarvestTool(int metadata)
    {
        return parentBlock.getHarvestTool(metadata);
    }
    @Override
    public int getHarvestLevel(int metadata)
    {
        return parentBlock.getHarvestLevel(metadata);
    }
    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return Blocks.fire.getFlammability(this);
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return parentBlock.getFlammability(world, x, y, z, face) > 0;
    }


    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return parentBlock.getFireSpreadSpeed(world, x, y, z, face);
    }
    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        return parentBlock.canHarvestBlock(player, meta);
    }

    @Override
	@SideOnly(Side.CLIENT)
    	public int getMixedBrightnessForBlock(IBlockAccess p_149677_1_, int p_149677_2_, int p_149677_3_, int p_149677_4_)
    {
    	return parentBlock.getMixedBrightnessForBlock(p_149677_1_, p_149677_2_, p_149677_3_, p_149677_4_);
    }
    @Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return getLightValue();
    }

}
