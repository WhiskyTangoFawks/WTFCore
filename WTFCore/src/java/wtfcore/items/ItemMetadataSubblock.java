package wtfcore.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemMetadataSubblock extends ItemBlockWithMetadata {

	public ItemMetadataSubblock(Block block) {
		super(block, block);
		setHasSubtypes(true);

	}

	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "."+itemstack.getItemDamage();
	}
}
