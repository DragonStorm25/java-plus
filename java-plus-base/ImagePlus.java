package setup.javaPlus;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.ImageIcon;

public final class ImagePlus {
	
	/**
	 * Don't let anyone instantiate this class
	 */
	private ImagePlus() {

	}

	public static BufferedImage colorImage(Image loadImg, Color c) {
		int w = loadImg.getWidth(null);
		int h = loadImg.getHeight(null);
		BufferedImage dyed = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = dyed.createGraphics();
		g.drawImage(loadImg, 0, 0, null);
		g.setComposite(AlphaComposite.SrcAtop.derive(0.5f));
		g.setColor(c);
		g.fillRect(0, 0, w, h);
		g.dispose();
		return dyed;
	}

	public static ImageIcon[] bufferedImageArrayToImageIcon(BufferedImage... bi) {
		ImageIcon[] ii = new ImageIcon[bi.length];
		for (int i = 0; i < ii.length; i++) {
			ii[i] = new ImageIcon(bi[i]);
		}
		return ii;
	}

	public static BufferedImage imageIconToBufferedImage(ImageIcon icon) {
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();
		return bi;
	}

	public static BufferedImage[] readGif(InputStream stream) throws IOException {
		ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
		ImageReader reader = (ImageReader) ImageIO.getImageReadersByFormatName("gif").next();
		reader.setInput(ImageIO.createImageInputStream(stream));
		for (int i = 0; i < reader.getNumImages(true); i++)
			frames.add(reader.read(i));
		reader.dispose();
		return frames.toArray(new BufferedImage[frames.size()]);
	}

	public static BufferedImage rotateImage(BufferedImage image, double rad) {
		double radians = rad;
		double sin = Math.abs(Math.sin(radians));
		double cos = Math.abs(Math.cos(radians));
		int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
		int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);
		BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotate.createGraphics();
		int x = (newWidth - image.getWidth()) / 2;
		int y = (newHeight - image.getHeight()) / 2;
		AffineTransform at = new AffineTransform();
		at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
		at.translate(x, y);
		g2d.setTransform(at);
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return rotate;
	}

	public static BufferedImage flipImageHorizontally(BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(img, null);
	}

	public static BufferedImage flipImageVertically(BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -img.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(img, null);
	}

	public static BufferedImage changeAlpha(BufferedImage img, double alpha) {
		if (alpha < 0 || alpha > 1) {
			throw new IllegalArgumentException("Alpha value is out of bounds!");
		}
		BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = newImg.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
		g2d.drawImage(img, 0, 0, null);
		return newImg;
	}

}
