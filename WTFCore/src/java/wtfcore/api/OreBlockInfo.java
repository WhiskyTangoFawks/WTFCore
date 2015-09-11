package wtfcore.api;

import net.minecraft.block.Block;

public class OreBlockInfo {

	public Block ore;
	public Block stone;
	int oreMeta;
	int oreLevel;


		public OreBlockInfo(Block ore, int oreMeta, Block stone){
			this.ore = ore;
			this.stone = stone;
			this.oreMeta = oreMeta;
			this.oreLevel = 0;

		}
		public OreBlockInfo(Block ore, int oreMeta, Block stone, int oreLevel){
			this.ore = ore;
			this.stone = stone;
			this.oreMeta = oreMeta;
			this.oreLevel = oreLevel;

		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((ore == null) ? 0 : ore.hashCode());
			result = prime * result + oreLevel;
			result = prime * result + oreMeta;
			result = prime * result + ((stone == null) ? 0 : stone.hashCode());
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
			OreBlockInfo other = (OreBlockInfo) obj;
			if (ore == null) {
				if (other.ore != null)
					return false;
			} else if (!ore.equals(other.ore))
				return false;
			if (oreLevel != other.oreLevel)
				return false;
			if (oreMeta != other.oreMeta)
				return false;
			if (stone == null) {
				if (other.stone != null)
					return false;
			} else if (!stone.equals(other.stone))
				return false;
			return true;
		}



}
