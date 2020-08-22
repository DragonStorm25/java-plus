//package setup.javaPlus;

import java.awt.Color;

/**
 * The <code>ColorPlus</code> class is meant to build upon the {@link Color}
 * class by adding various utility methods and new colors to improve the
 * creation and usage of colors in Java.
 * 
 * @author jonatanfontanez
 * @see Color
 *
 */
public final class ColorPlus {
	private static final float DIM_FACTOR = 0.7f;

	/**
	 * The color green yellow. In the sRGB space.
	 */
	public final static Color GREEN_YELLOW = new Color(173, 255, 47);

	/**
	 * The color green yellow. In the sRGB space.
	 */
	public final static Color greenYellow = GREEN_YELLOW;

	/**
	 * The color mint green. In the sRGB space.
	 */
	public final static Color MINT_GREEN = new Color(152, 255, 152);

	/**
	 * The color mint green. In the sRGB space.
	 */
	public final static Color mintGreen = MINT_GREEN;

	/**
	 * The color aquamarine. In the sRGB space.
	 */
	public final static Color AQUAMARINE = new Color(127, 255, 212);

	/**
	 * The color aquamarine. In the sRGB space.
	 */
	public final static Color aquamarine = AQUAMARINE;

	/**
	 * The color gold. In the sRGB space.
	 */
	public final static Color GOLD = new Color(255, 215, 0);

	/**
	 * The color gold. In the sRGB space.
	 */
	public final static Color gold = GOLD;

	/**
	 * The color khaki. In the sRGB space.
	 */
	public final static Color KHAKI = new Color(240, 230, 140);

	/**
	 * The color khaki. In the sRGB space.
	 */
	public final static Color khaki = KHAKI;

	/**
	 * The color brown. In the sRGB space.
	 */
	public final static Color BROWN = new Color(139, 69, 19);

	/**
	 * The color brown. In the sRGB space.
	 */
	public final static Color brown = BROWN;

	/**
	 * The color orange; more orange than <code>Color.ORANGE</code>. In the sRGB
	 * space.
	 */
	public final static Color ORANGE = new Color(255, 165, 0);

	/**
	 * The color orange; more orange than <code>Color.ORANGE</code>. In the sRGB
	 * space.
	 */
	public final static Color orange = ORANGE;

	/**
	 * The color purple. In the sRGB space.
	 */
	public final static Color PURPLE = new Color(128, 0, 128);

	/**
	 * The color purple. In the sRGB space.
	 */
	public final static Color purple = PURPLE;

	/**
	 * The color indigo. In the sRGB space.
	 */
	public final static Color INDIGO = ColorPlus.linearBlend(Color.BLUE, ColorPlus.PURPLE, 0.5f);

	/**
	 * The color indigo. In the sRGB space.
	 */
	public final static Color indigo = INDIGO;

	/**
	 * The color sky blue. In the sRGB space.
	 */
	public final static Color SKY_BLUE = ColorPlus.linearBlend(Color.CYAN, Color.BLUE, 0.5f);

	/**
	 * The color sky blue. In the sRGB space.
	 */
	public final static Color skyBlue = SKY_BLUE;

	/**
	 * The color baby blue. In the sRGB space.
	 */
	public final static Color BABY_BLUE = new Color(137, 207, 240);

	/**
	 * The color baby blue. In the sRGB space.
	 */
	public final static Color babyBlue = BABY_BLUE;

	/**
	 * The color burgundy. In the sRGB space.
	 */
	public final static Color BURGUNDY = new Color(128, 0, 32);

	/**
	 * The color burgundy. In the sRGB space.
	 */
	public final static Color burgundy = BURGUNDY;

	/**
	 * The color light pink. In the sRGB space.
	 */
	public final static Color LIGHT_PINK = new Color(252, 182, 193);

	/**
	 * The color light pink. In the sRGB space.
	 */
	public final static Color lightPink = LIGHT_PINK;

	/**
	 * Don't let anyone instantiate this class
	 */
	private ColorPlus() {

	}
	
	/**
	 * Linearly blends two given colors with a ratio of 1:<code>blend</code>. Uses
	 * linear interpolation to create a proper blending ratio. Great for
	 * representing both colors equally, but not great for intermediate colors.
	 * 
	 * @param c1    first color to blend
	 * @param c2    second color to blend
	 * @param blend factor to blend colors
	 * @return a blended color <code>blend</code> of the way, in linear terms,
	 *         between the first and second colors
	 */
	public static Color linearBlend(Color c1, Color c2, float blend) {
		if (blend < 0.0f || blend > 1.0f)
			throw new IllegalArgumentException("Blend value is out of bounds!");
		else if (blend == 0.0f)
			return c1;
		else if (blend == 1.0f)
			return c2;
		float inverse_blending = 1 - blend;
		float red = c2.getRed() * blend + c1.getRed() * inverse_blending;
		float green = c2.getGreen() * blend + c1.getGreen() * inverse_blending;
		float blue = c2.getBlue() * blend + c1.getBlue() * inverse_blending;
		float alpha = c2.getAlpha() * blend + c1.getAlpha() * inverse_blending;
		return new Color(red / 255f, green / 255f, blue / 255f, alpha / 255f);
	}

	/**
	 * Smoothly blends two given colors with a ratio of 1:<code>blend</code>. Uses
	 * square roots to create proper a blending ratio. Equally good at showing base
	 * colors and intermediate colors.
	 * 
	 * @param c1    first color to blend
	 * @param c2    second color to blend
	 * @param blend factor to blend colors
	 * @return a blended color <code>blend</code> of the way, in square root terms,
	 *         between the first and second colors
	 */
	public static Color smoothBlend(Color c1, Color c2, float blend) {
		Color linBlend = ColorPlus.linearBlend(c1, c2, blend);
		int linRed = linBlend.getRed();
		int linGreen = linBlend.getGreen();
		int linBlue = linBlend.getBlue();
		int linAlpha = linBlend.getAlpha();
		float red = (float) Math.sqrt(linRed / 255f);
		float green = (float) Math.sqrt(linGreen / 255f);
		float blue = (float) Math.sqrt(linBlue / 255f);
		float alpha = (float) Math.sqrt(linAlpha / 255f);
		return new Color(red, green, blue, alpha);
	}

	/**
	 * Circularly blends two given colors with a ratio of 1:<code>blend</code>. Uses
	 * the shape of an arc to create a proper blending ratio. Great for showing
	 * intermediate colors, but not great for showing base colors.
	 * 
	 * @param c1    first color to blend
	 * @param c2    second color to blend
	 * @param blend factor to blend colors
	 * @return a blended color <code>blend</code> of the way, in circular terms,
	 *         between the first and second colors
	 */
	public static Color circularBlend(Color c1, Color c2, float blend) {
		Color linBlend = ColorPlus.linearBlend(c1, c2, blend);
		float linRed = linBlend.getRed() / 255f;
		float linGreen = linBlend.getGreen() / 255f;
		float linBlue = linBlend.getBlue() / 255f;
		float linAlpha = linBlend.getAlpha() / 255f;
		float red = (float) Math.sqrt(1 - Math.pow(linRed - 1, 2));
		float green = (float) Math.sqrt(1 - Math.pow(linGreen - 1, 2));
		float blue = (float) Math.sqrt(1 - Math.pow(linBlue - 1, 2));
		float alpha = (float) Math.sqrt(1 - Math.pow(linAlpha - 1, 2));
		return new Color(red, green, blue, alpha);
	}

	/**
	 * Blends two given colors with a ratio of 1:<code>blend</code>. Uses the shape
	 * of an arc, similar to a circle, to create a proper blending ratio. The arc
	 * follows the shape of the graph (1-(1-x)^<code>pow</code>)^(1/<code>pow</code>).
	 * Calling this method with a <code>pow</code> of 2 is equivalent to calling
	 * <code>ColorPlus.circularBlend</code>.
	 * 
	 * @param c1    first color to blend
	 * @param c2    second color to blend
	 * @param blend factor to blend colors
	 * @param pow   power to use in formula
	 * @return
	 */
	public static Color powerBlend(Color c1, Color c2, float blend, int pow) {
		Color linBlend = ColorPlus.linearBlend(c1, c2, blend);
		float linRed = linBlend.getRed() / 255f;
		float linGreen = linBlend.getGreen() / 255f;
		float linBlue = linBlend.getBlue() / 255f;
		float linAlpha = linBlend.getAlpha() / 255f;
		float red = (float) Math.pow(1.0 - Math.pow(1-linRed, pow), 1.0 / pow);
		float green = (float) Math.pow(1.0 - Math.pow(1-linGreen, pow), 1.0 / pow);
		float blue = (float) Math.pow(1.0 - Math.pow(1-linBlue, pow), 1.0 / pow);
		float alpha = (float) Math.pow(1.0 - Math.pow(1-linAlpha, pow), 1.0 / pow);
		return new Color(red, green, blue, alpha);
	}

	/**
	 * Gives the inverse of the input color, in sRGB space.
	 * 
	 * @param c color to be inversed
	 * @return the sRGB space equivalent of the inverse color
	 */
	public static Color inverseRGB(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

	/**
	 * Gives the inverse of the input color, in HSB space.
	 * 
	 * @param c color to be inversed
	 * @return the HSB space equivalent of the inverse color
	 */
	public static Color inverseHSB(Color c) {
		float hsb[] = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		return new Color(Color.HSBtoRGB((hsb[0] + 0.5f) % 1.0f, hsb[1], hsb[2]));
	}

	/**
	 * Gives a slightly dimmer version of the input color. Similar to
	 * {@link Color#darker()}, but through HSB space instead of sRGB space. Repeated
	 * dims will bring a color closer to black. Black will not be affected.
	 * 
	 * @param c color to be dimmed
	 * @return a dimmer version of the input color
	 */
	public static Color dimmer(Color c) {
		float hsb[] = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2] * DIM_FACTOR));
	}

	/**
	 * Give a slightly paler version of the input color. Similar to the inverse of
	 * {@link ColorPlus#dimmer(Color)}. Repeated pales will bring a color closer to
	 * white. White will not be affected.
	 * 
	 * @param c color to be paled
	 * @return a paler version of the input color
	 */
	public static Color paler(Color c) {
		float hsb[] = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		if (c.getRed() != c.getBlue() && c.getRed() != c.getGreen())
			return new Color(Color.HSBtoRGB(hsb[0], hsb[1] * DIM_FACTOR, hsb[2]));
		else
			return new Color(Color.HSBtoRGB(hsb[0], hsb[1] * DIM_FACTOR, Math.min(1f, hsb[2] + (1 - DIM_FACTOR))));
	}

	/**
	 * Applies the darker method to a Color multiple times. If <code>amount</code>
	 * is less than 1, nothing will happen
	 * 
	 * @param c      color to be darkened
	 * @param amount amount of darker() to be applied
	 * @return
	 */
	public static Color multiDarker(Color c, int amount) {
		for (int i = 0; i < amount; i++)
			c = c.darker();
		return c;
	}

	/**
	 * Applies the brighter method to a Color multiple times. If <code>amount</code>
	 * is less than 1, nothing will happen
	 * 
	 * @param c      color to be brightened
	 * @param amount amount of bright() to be applied
	 * @return
	 */
	public static Color multiBrighter(Color c, int amount) {
		for (int i = 0; i < amount; i++)
			c = c.brighter();
		return c;
	}

	/**
	 * Returns a color created through CMYK space. This color space is used in
	 * printing; the C refers to the value of cyan, the M to the value of magenta,
	 * the Y to the value of yellow, and the K to the value of black. All values
	 * input must be normalized.
	 * 
	 * @param c value of cyan
	 * @param m value of magenta
	 * @param y value of yellow
	 * @param k value of black
	 * @return a color created through CMYK space
	 */
	public static Color fromCMYK(float c, float m, float y, float k) {
		if (!MathPlus.inRange(c, 0.0, 1.0))
			throw new IllegalArgumentException("Cyan value out of bounds!");
		else if (!MathPlus.inRange(m, 0.0, 1.0))
			throw new IllegalArgumentException("Magenta value out of bounds!");
		else if (!MathPlus.inRange(y, 0.0, 1.0))
			throw new IllegalArgumentException("Yellow value out of bounds!");
		else if (!MathPlus.inRange(k, 0.0, 1.0))
			throw new IllegalArgumentException("Black value out of bounds!");
		float red = (1 - c) * (1 - k);
		float green = (1 - m) * (1 - k);
		float blue = (1 - y) * (1 - k);
		return new Color(red, green, blue);
	}

	/**
	 * Returns a grayed-out version of the input color based on the average of the
	 * highest and lowest RGB values. This method is considered the least accurate
	 * due to loss of contrast.
	 * 
	 * @param c input color
	 * @return grayed-out color through minimum-maximum RGB average
	 */
	public static Color lightnessGrayscale(Color c) {
		int value = (int) ((MathPlus.max(c.getRed(), c.getGreen(), c.getBlue())
				+ MathPlus.min(c.getRed(), c.getGreen(), c.getBlue())) / 2);
		return new Color(value, value, value);
	}

	/**
	 * Returns a grayed-out version of the input color based on the average of the
	 * RGB values. This method is considered the second most accurate to obtain
	 * grayed-out colors.
	 * 
	 * @param c input color
	 * @return grayed-out color through RGB average
	 */
	public static Color averageGrayscale(Color c) {
		int value = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
		return new Color(value, value, value);
	}

	/**
	 * Returns a grayed-out version of the input color based on human vision. This
	 * method is considered the most accurate to obtain grayed-out colors.
	 * 
	 * @param c input color
	 * @return grayed-out color through human vision sensitivity
	 */
	public static Color luminosityGrayscale(Color c) {
		int gray = (int) ((c.getRed() * 0.2126) + (c.getGreen() * 0.7152) + (c.getBlue() * 0.0722));
		return new Color(gray, gray, gray);
	}

	/**
	 * Gives a color with the same RGB values and a changed alpha value.
	 * 
	 * @param c   color to be changed
	 * @param num alpha to apply
	 * @return the same color with a different alpha
	 */
	public static Color changeAlpha(Color c, int num) {
		if (num < 0 || num > 255)
			throw new IllegalArgumentException("Alpha value is out of bounds!");
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), num);
	}

	/**
	 * Gives a color with the same RGB values and a changed alpha value.
	 * 
	 * @param c   color to be changed
	 * @param num alpha to apply
	 * @return the same color with a different alpha
	 */
	public static Color changeAlpha(Color c, float num) {
		if (num < 0.0f || num > 1.0f)
			throw new IllegalArgumentException("Alpha value is out of bounds!");
		return new Color(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, num);
	}

	/**
	 * Gives a random color using the RGB space.
	 * 
	 * @return random RGB color
	 */
	public static Color randomRGBColor() {
		return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
	}

	/**
	 * Gives a random color using the HSB space.
	 * 
	 * @return random HSB color
	 */
	public static Color randomHSBColor() {
		return new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
	}
}
