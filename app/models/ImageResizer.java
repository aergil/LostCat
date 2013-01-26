package models;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizer {

	public static BufferedImage resize(File file, int maxWidth, int maxHeight) {
		BufferedImage originalImage;
		try {
			originalImage = ImageIO.read(file);
			int height = originalImage.getHeight();
			int width = originalImage.getWidth();

			if (width <= maxWidth && height <= maxHeight)
				return originalImage;

			int scaledWidth = width;
			int scaledHeight = height;
			double ratio = new Double(width) / new Double(height);

			if (scaledWidth > maxWidth) {
				scaledWidth = maxWidth;
				scaledHeight = (int) (scaledWidth / ratio);
			}
			if (scaledHeight > maxHeight) {
				scaledHeight = maxHeight;
				scaledWidth = (int) (scaledHeight * ratio);
			}

			BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = scaledBI.createGraphics();
			g.setComposite(AlphaComposite.Src);
			g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
			g.dispose();

			return scaledBI;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
