package wtfcore.api;

import net.minecraft.block.Block;


public class BlockInfo {


	public Block block;
	public int meta;
	public BlockSets.Modifier addon = null;


	public Block fluidBlock;
	public int fluidMeta;




	public  BlockInfo(Block blockToStore, int metaToStore, BlockSets.Modifier addonToStore){
		this.block = blockToStore;
		this.meta = metaToStore;
		this.addon = addonToStore;

	}

	public BlockInfo(Block blockToStore, int metaToStore) {
		this.block = blockToStore;
		this.meta = metaToStore;
		this.addon = null;
	}

	public  BlockInfo(Block blockToStore, int metaToStore, BlockSets.Modifier addonToStore, Block blockfluid, int metafluid){
		this.block = blockToStore;
		this.meta = metaToStore;
		this.addon = addonToStore;
		this.fluidMeta = metafluid;
		this.fluidBlock = blockfluid;
	}



	public void setAddon(BlockSets.Modifier block){
		this.addon = block;
	}

	//everything but block and addon block have been disabled in the hashcode and equals methdos
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addon == null) ? 0 : addon.hashCode());
		result = prime * result + ((block == null) ? 0 : block.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockInfo other = (BlockInfo) obj;
		if (addon == null) {
			if (other.addon != null)
				return false;
		} else if (!addon.equals(other.addon))
			return false;
		if (block == null) {
			if (other.block != null)
				return false;
		} else if (!block.equals(other.block))
			return false;
		return true;
	}


}
