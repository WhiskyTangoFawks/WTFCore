package wtfcore.proxy;

import java.util.HashMap;
import java.util.HashSet;
import wtfcore.blocks.TextureInformation;
import wtfcore.texturestitcher.CustomTextureEnumerator;
import wtfcore.texturestitcher.CustomTextureGenerator;
import wtfcore.texturestitcher.OptifineIntegration;
import wtfcore.texturestitcher.ShadersModIntegration;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;


public class ClientProxy extends CommonProxy {

	public static HashMap<String, TextureInformation> alphaMaskMap = new HashMap<String, TextureInformation>();
	public static final String overlayDomain = "overlays";
	public static HashSet<Object> retextureBlock = new HashSet<Object>();


	@Override
	public void clientRegister(){


			MinecraftForge.EVENT_BUS.register(new CustomTextureEnumerator());
			MinecraftForge.EVENT_BUS.register(new CustomTextureGenerator(overlayDomain, null));


		OptifineIntegration.init();
		ShadersModIntegration.init();

	}

	public static void registerBlockOverlay(String textureName, String parentTexture, String masktype, String maskDomain, boolean alphaMask){

		ResourceLocation parent = new ResourceLocation(parentTexture);
		parent = new ResourceLocation(parent.getResourceDomain()+":textures/blocks/"+parent.getResourcePath()+".png");
		alphaMaskMap.put(textureName, new TextureInformation(parent, masktype, maskDomain, alphaMask));

		String textureNameN = textureName + "_n";
		String parentTextureN = parentTexture + "_n";

		parent = new ResourceLocation(parentTextureN);
		parent = new ResourceLocation(parent.getResourceDomain()+":textures/blocks/"+parent.getResourcePath()+".png");
		alphaMaskMap.put(textureNameN, new TextureInformation(parent, masktype, maskDomain, alphaMask));

		String textureNameS = textureName + "_s";
		String parentTextureS = parentTexture + "_s";

		parent = new ResourceLocation(parentTextureS);
		parent = new ResourceLocation(parent.getResourceDomain()+":textures/blocks/"+parent.getResourcePath()+".png");
		alphaMaskMap.put(textureNameS, new TextureInformation(parent, masktype, maskDomain, alphaMask));


	}
	public static void registerBlockOverlayOverride(Block oreBlock, String textureName, String parentTexture, String masktype, String maskDomain, boolean alphaMask){
		registerBlockOverlay(textureName, parentTexture, masktype, maskDomain, alphaMask);
		ClientProxy.retextureBlock.add(oreBlock);
		ClientProxy.retextureBlock.add(textureName);
	}

}
