package wtfcore.texturestitcher;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import wtfcore.WTFCore;
import wtfcore.blocks.TextureInformation;
import wtfcore.proxy.ClientProxy;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;

public class CustomTextureGenerator extends BlockTextureGenerator{

	@SideOnly(Side.CLIENT)
	public static class TextureGenerationException extends Exception {
		private static final long serialVersionUID = 7339757761980002651L;
	};

	public Map<ResourceLocation, IResource> resourceCache = Maps.newHashMap();

	public CustomTextureGenerator(String domainName, ResourceLocation missingResource) {
		super(domainName);
	}

	@Override
	public IResource getResource(ResourceLocation resourceLocation) throws IOException {
		//WTFCore.log.info("Get resource called for "+ resourceLocation.toString());
		ResourceLocation originalresourcelocation = resourceLocation;
		IResourceManager resourceManager = Minecraft.getMinecraft().getResourceManager();
		resourceLocation = ResourceUtils.unwrapResource(resourceLocation);

		// use animation metadata as-is
		if (resourceLocation.getResourcePath().toLowerCase().endsWith(".mcmeta")) return resourceManager.getResource(resourceLocation);

		TextureInformation textureinfo = ClientProxy.alphaMaskMap.get(resourceLocation.getResourcePath());
		if (textureinfo != null){
			BufferedImage result;
			try {
				result = generateTexture(resourceLocation, textureinfo);
			}catch (TextureGenerationException e) {
				WTFCore.log.info(String.format("Error generating for resource: %s", resourceLocation.toString()));
				return resourceManager.getResource(resourceLocation);
			}
			IResource generated = new BufferedImageResource(result, originalresourcelocation);
			resourceCache.put(resourceLocation, generated);
			return generated;
		}
		else {
			WTFCore.log.info("No hashmap entry found for "+ resourceLocation.getResourcePath());
			return null;
		}
	}

	protected BufferedImage loadMaskImage(TextureInformation textureinfo, ResourceLocation blockResourceLocation, int size) throws IOException {
		IResourceManager resourceManager = Minecraft.getMinecraft().getResourceManager();
		IResource maskResource = null;

		String masktype = textureinfo.alphaMaskTexture;
		if (masktype == null){WTFCore.log.info("AlphaMask type not found in hashmap " + blockResourceLocation.toString());}

		String maskLocation = textureinfo.overlayDomain+masktype+"_"+size+".png";
		//WTFCore.log.info(maskLocation);

		try {
			maskResource = resourceManager.getResource(new ResourceLocation(maskLocation));
		} catch (Exception e) {

		}

		if (maskResource == null){
			//WTFCore.log.info("AlphaMask loading failed for " + blockResourceLocation.toString());
			maskLocation = textureinfo.overlayDomain+masktype+"_16.png";
			maskResource = resourceManager.getResource(new ResourceLocation(maskLocation));
			BufferedImage maskImage =  ImageIO.read(maskResource.getInputStream());


			BufferedImage dimg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = dimg.createGraphics();
		    g2d.drawImage(maskImage, 0, 0, size, size, null);
		    g2d.dispose();
		    return dimg;
		}
		else {
			return ImageIO.read(maskResource.getInputStream());
		}

	}

	protected BufferedImage generateTexture(ResourceLocation originalResource, TextureInformation textureinfo) throws IOException, TextureGenerationException {

		//WTFCore.log.info("Generating resource for "+ originalResource.toString());

		BufferedImage parentIcon = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(textureinfo.parentTexture).getInputStream());
		int size = parentIcon.getWidth();
		BufferedImage maskImage = loadMaskImage(textureinfo, originalResource, size);

		if (parentIcon.getHeight() != maskImage.getHeight()){
			WTFCore.log.info("Found Animated Texture : " + originalResource.toString());

			BufferedImage genIcon = new BufferedImage(parentIcon.getWidth(), parentIcon.getWidth(), BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D genGraphics = genIcon.createGraphics();

			genGraphics.drawImage(parentIcon, 0, 0, null);
			parentIcon = genIcon;
		}

		if (maskImage.getWidth() != parentIcon.getWidth()){
			WTFCore.log.info("Image to overlay file is incorrect size : " + originalResource.toString());
		}

		if (textureinfo.alphaMask){
			return ApplyTransparency(parentIcon, maskImage);
		}
		else {
			return ApplyOverlay(parentIcon, maskImage);
		}
	}

	private BufferedImage ApplyTransparency(BufferedImage image, BufferedImage mask)
	{
		//WTFCore.log.info("Applying alpha mask" );
		BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(image, 0, 0, null);
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_IN, 1.0F);
		g2.setComposite(ac);
		g2.drawImage(mask, 0, 0, null);
		g2.dispose();
		return dest;
	}

	private BufferedImage ApplyOverlay(BufferedImage image, BufferedImage overlay){
		BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(),	BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.drawImage(overlay, 0, 0, null);
		g2.dispose();
		return dest;
	}

	@Override
	@SubscribeEvent
	public void handleTextureReload(Pre event) {
		super.handleTextureReload(event);
		if (event.map.getTextureType() != 0) return;
		resourceCache.clear();
	}

	@Override
	@SubscribeEvent
	public void endTextureReload(TextureStitchEvent.Post event) {
		super.endTextureReload(event);
		if (event.map.getTextureType() != 0) return;
		WTFCore.log.info(String.format("Loaded overlaid textures"));
	}

}

