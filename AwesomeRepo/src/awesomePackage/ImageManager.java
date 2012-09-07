package awesomePackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageManager {
	
	public BufferedImage crop(BufferedImage image, int x, int y, int width, int height) {
		return image.getSubimage(x, y, (width > 0 ? width : 1), (height > 0 ? height : 1));
	}
	
	public BufferedImage getAnimationFrame(BufferedImage image, int x, int y, int width, int height) {
		return image.getSubimage(x * width, y * height, (width > 0 ? width : 1), (height > 0 ? height : 1));
	}
	
	public BufferedImage newImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("No image; \"" + path + "\"");
		}
		return img;
	}
}
