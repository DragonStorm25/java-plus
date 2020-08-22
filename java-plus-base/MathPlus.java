package setup.javaPlus;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * The class {@code MathPlus} contains methods for performing more complex
 * mathematical operations and extensions to existing methods in {@link Math},
 * along with a few important mathematical constants.
 * 
 * @author jonatanfontanez
 * @see {@link Math}
 *
 */
public final class MathPlus {

	/**
	 * Don't let anyone instantiate this class
	 */
	private MathPlus() {

	}

	/**
	 * The {@code double} value that is closer than any other to <i>τ</i>, the ratio
	 * of the circumference of a circle to its radius.
	 */
	public static final double TAU = 6.2831853071795864769;

	/**
	 * The {@code double} value that is closer than any other to <i>φ</i>, the
	 * golden ratio.
	 */
	public static final double PHI = 1.6180339887498948482;

	/**
	 * The {@code double} value that is closer than any other to <i>Ω</i>, the value
	 * that is defined by Ωe^Ω=1.
	 */
	public static final double OMEGA = 0.5671432904097838729;

	/**
	 * The {@code double} value that is closer than any other to <i>√2</i>, the
	 * positive square root of two.
	 */
	public static final double ROOT_TWO = 1.4142135623730950488;

	/**
	 * Returns a value <code>c</code> of the way between <code>a</code> and
	 * <code>b</code>. For example, given <code>a=2.0</code>, <code>b=6.0</code>,
	 * and <code>c=0.5</code>, the returned value will be 4.
	 * 
	 * @param a first value to be used, usually smaller than <code>b</code>
	 * @param b second value to be used, usually larger than <code>a</code>
	 * @param c number to find position of returned value
	 * @return a number <code>c</code> of the way between <code>a</code> and
	 *         <code>b</code>
	 */
	public static double linearInterpolation(double a, double b, double c) {
		return a * (1 - c) + b * c;
	}

	/**
	 * Returns a value <code>c</code> of the way between <code>a</code> and
	 * <code>b</code>, following a circle-like curve. For example, given
	 * <code>a=2.0</code>, <code>b=6.0</code>, and <code>c=0.5</code>, the returned
	 * value will be ~2.536.
	 * 
	 * @param a first value to be used, usually smaller than <code>b</code>
	 * @param b second value to be used, usually larger than <code>a</code>
	 * @param c number to find position of returned value
	 * @return a number <code>c</code> of the way between <code>a</code> and
	 *         <code>b</code>, following a circle-like curve
	 */
	public static double circularInterpolation(double a, double b, double c) {
		return (a - b) * Math.sqrt(1 - c * c) + b;
	}

	/**
	 * Produces a random integer value in the range <code>[0, max]</code>.
	 * 
	 * @param max the maximum value to be returned
	 * @return random integer between 0 and <code>max</code>
	 */
	public static int random(int max) {
		if (0 > max)
			throw new IllegalArgumentException("Maximum value cannot be less than zero!");
		return (int) MathPlus.random(max + 1 * 1.0);
	}

	/**
	 * Produces a random integer value in the range <code>[min, max]</code>.
	 * 
	 * @param min the minimum value to be returned
	 * @param max the maximum value to be returned
	 * @return random integer value between <code>min</code> and <code>max</code>
	 */
	public static int random(int min, int max) {
		if (min > max)
			throw new IllegalArgumentException("Minimum value cannot be greater than maximum value!");
		return (int) MathPlus.random(min * 1.0, max + 1 * 1.0);
	}

	/**
	 * Produces a random decimal value in the range <code>[0, max)</code>.
	 * 
	 * @param max the maximum value for the return range
	 * @return random decimal in range <code>[0, max)</code>
	 */
	public static double random(double max) {
		if (0 > max)
			throw new IllegalArgumentException("Maximum value cannot be less than zero!");
		return MathPlus.random(0, max);
	}

	/**
	 * Produces a random decimal value in the range <code>[min, max)</code>.
	 * 
	 * @param min the minimum value for the return range
	 * @param max the maximum value for the return range
	 * @return random decimal value in the range <code>[min, max)</code>
	 */
	public static double random(double min, double max) {
		if (min > max)
			return MathPlus.random(max, min);
		return (Math.random() * (max - min)) + min;
	}

	/**
	 * Produces a random value from the given {@code values} where each value has a
	 * weight found in the corresponding given {@code weights}.<br>
	 * Higher weights result in a higher chance to produce a certain value and vice
	 * versa.
	 * 
	 * @param values  values that can be produced
	 * @param weights weights of each values
	 * @return random value, each weighted with {@code weights}
	 */
	public static double weightedRandom(double[] values, double[] weights) {
		if (values.length != weights.length)
			throw new IllegalArgumentException("The amount of values and weights are not equal!");
		double sum = 0.0;
		double rand = MathPlus.random(MathPlus.sum(weights));
		for (int i = 0; i < values.length; i++) {
			if (weights[i] < 0)
				throw new IllegalArgumentException("Weights cannot be less than zero!");
			sum += weights[i];
			if (rand <= sum)
				return values[i];
		}
		return 0.0;
	}

	public static int weightedRandom(int[] values, double[] weights) {
		return (int) MathPlus.weightedRandom(ArraysPlus.toDoubleArray(values), weights);
	}

	/**
	 * Produces a random decimal value using a "power distribution"<br>
	 * Equivalent to <code>Math.random()^power</code> <br>
	 * <br>
	 * 
	 * Distribution:
	 * <ul>
	 * <li>0 <= <code>power</code> < 1 : Smaller numbers are rare
	 * <li><code>power</code> = 1 : Every number is equally rare
	 * <li><code>power</code> > 1 : Larger numbers are rare <br>
	 * 
	 * @param power power to raise generated number to
	 * @return random decimal in range <code>[0, 1)</code>
	 */
	public static double powerRandom(double power) {
		if (power < 0)
			throw new IllegalArgumentException("Power cannot be less than 0!");
		return Math.pow(Math.random(), power);
	}

	/**
	 * Produces a random decimal value using a "power distribution"<br>
	 * <br>
	 * 
	 * Distribution:
	 * <ul>
	 * <li>0 ≤ <code>power</code> < 1 : Smaller numbers are rare
	 * <li><code>power</code> = 1 : Every number is equally rare
	 * <li><code>power</code> > 1 : Larger numbers are rare
	 * </ul>
	 * 
	 * This method is equivalent to calling:<br>
	 * <br>
	 * &nbsp&nbsp&nbsp&nbsp<code>Math.pow(MathPlus.random(min, max), power)</code>
	 * <br>
	 * <br>
	 * 
	 * @param power power to raise generated number to
	 * @param min   the minimum value for the return range
	 * @param max   the maximum value for the return range
	 * @return random number in range <code>[min, max)</code>
	 */
	public static double powerRandom(double power, double min, double max) {
		if (min > max)
			throw new IllegalArgumentException("Minimum value cannot be greater than maximum value!");
		return (MathPlus.powerRandom(power) * (max - min)) + min;
	}

	/**
	 * Produces a random decimal value using the beta distribution.<br>
	 * <br>
	 * 
	 * The larger the input value <code>val</code>, the more common the extremes of
	 * the range are and the less common values in the center are.
	 * 
	 * @param val
	 * @return
	 */
	public static double betaDistributionRandom(double val) {
		if (val <= 1)
			throw new IllegalArgumentException("Input for beta function cannot be less than 1!");
		double rand = Math.random();
		double ret = Math.pow(rand, val - 1) * Math.pow(1 - rand, val - 1);
		double factor = 1 / (2 * Math.pow(0.5, val - 1) * Math.pow(0.5, val - 1));
		if (rand < 0.5)
			return factor * ret;
		else
			return 1 - factor * ret;
	}

	/**
	 * Produces a random decimal value using the beta distribution.<br>
	 * <br>
	 * 
	 * The larger the input value <code>val</code>, the more common the extremes of
	 * the range are and the less common values in the center are.
	 * 
	 * @param val
	 * @param min
	 * @param max
	 * @return
	 */
	public static double betaDistributionRandom(double val, double min, double max) {
		if (min > max)
			throw new IllegalArgumentException("Minimum value cannot be greater than maximum value!");
		return (MathPlus.betaDistributionRandom(val) * (max - min)) + min;
	}

	/**
	 * Returns the correctly rounded positive square root of a {@code double} value.
	 * Special cases:
	 * <ul>
	 * <li>If the argument is negative, then the result is a
	 * <code>ComplexNumber</code> object.
	 * <li>If the argument is NaN, then the result is NaN.
	 * <li>If the argument is positive infinity, then the result is positive
	 * infinity.
	 * <li>If the argument is positive zero or negative zero, then the result is the
	 * same as the argument.
	 * </ul>
	 * Otherwise, the result is the {@code double} value closest to the true
	 * mathematical square root of the argument value.
	 *
	 * @param a a value.
	 * @return the positive square root of {@code a}. If the argument is NaN or less
	 *         than zero, the result is NaN.
	 */
	public static ComplexNumber sqrt(double a) {
		if (a < 0)
			return new ComplexNumber(0.0, Math.sqrt(-a));
		else
			return new ComplexNumber(Math.sqrt(a), 0.0);
	}

	/**
	 * Returns the greatest of all {@code double} values. That is, the result is the
	 * argument closer to the value of {@link Double#MAX_VALUE}. If all the
	 * arguments have the same value, the result is that same value.
	 *
	 * @param value the input values
	 * @return the largest value in the input
	 */
	public static double max(double... values) {
		double maximum = values[0];
		for (int i = 1; i < values.length; i++) {
			if (values[i] > maximum)
				maximum = values[i];
		}
		return maximum;
	}

	/**
	 * Returns the least of all {@code double} values. That is, the result is the
	 * argument closer to the value of {@link Double#MIN_VALUE}. If all the
	 * arguments have the same value, the result is that same value.
	 *
	 * @param value the input values
	 * @return the smallest value in the input
	 */
	public static double min(double... values) {
		double minimum = values[0];
		for (int i = 1; i < values.length; i++) {
			if (values[i] < minimum)
				minimum = values[i];
		}
		return minimum;
	}

	/**
	 * Returns the range of the set {@code values}.<br>
	 * <br>
	 * 
	 * This method is equivalent to calling: <br>
	 * <br>
	 * 
	 * &nbsp&nbsp&nbsp&nbsp<code>MathPlus.max(values) - MathPlus.min(values)</code>
	 * 
	 * @param values the input values
	 * @return the range of the set of the argument
	 */
	public static double range(double... values) {
		return MathPlus.max(values) - MathPlus.min(values);
	}

	/**
	 * Returns the sum of all {@code double} values.
	 * 
	 * @param ds values to be summed
	 * @return sum of all values
	 */
	public static double sum(double... values) {
		double sum = 0.0;
		for (int i = 0; i < values.length; i++)
			sum += values[i];
		return sum;
	}

	public static double harmonicSum(double... values) {
		double harmSum = 0.0;
		for (int i = 0; i < values.length; i++)
			harmSum += 1.0 / values[i];
		return harmSum;
	}

	/**
	 * Returns the product of all {@code double} values.
	 * 
	 * @param values values to be multiplied
	 * @return product of all values
	 */
	public static double product(double... values) {
		double prod = 1.0;
		for (int i = 0; i < values.length; i++)
			prod *= values[i];
		return prod;
	}

	/**
	 * Returns if the value is between the minimum and the maximum, exclusive.
	 * 
	 * @param val value to check
	 * @param min minimum value in range
	 * @param max maximum value in range
	 * @return if the value is within minimum and maximum
	 */
	public static boolean inRange(double val, double min, double max) {
		if (min > max)
			throw new IllegalArgumentException("Minimum value cannot be greater than maximum value!");
		return val > min && val < max;
	}

	/**
	 * Returns if the value is between the minimum and the maximum, inclusive.
	 * 
	 * @param val value to check
	 * @param min minimum value in range
	 * @param max maximum value in range
	 * @return if the value is within minimum and maximum
	 */
	public static boolean inRangeEqual(double val, double min, double max) {
		if (min > max)
			throw new IllegalArgumentException("Minimum value cannot be greater than maximum value!");
		return val >= min && val <= max;
	}

	/**
	 * Returns <br>
	 * <ul>
	 * <li>the minimum value, if the given value is less than the minimum
	 * <li>the maximum if the given value is greater than the maximum
	 * <li>the value otherwise
	 * 
	 * @param min minimum value to return
	 * @param max maximum value to return
	 * @param val value to test
	 * @return value constricted by the minimum and maximum
	 */
	public static double constrictValue(double min, double max, double val) {
		if (min > max)
			return constrictValue(max, min, val);
		if (val > max)
			return max;
		if (val < min)
			return min;
		return val;
	}

	/**
	 * Returns the arithmetic mean of all {@code double} values.
	 * 
	 * @param ds values to be averaged
	 * @return arithmetic mean of all values
	 */
	public static double arithmeticMean(double... ds) {
		return MathPlus.sum(ds) / ds.length;
	}

	/**
	 * Returns the weighted mean of all {@code double} values with their respective
	 * {@code double} weights.
	 * 
	 * @param values  values to be averaged
	 * @param weights weights to use
	 * @return weighted mean of all values
	 */
	public static double weightedArithmeticMean(double[] values, double[] weights) {
		if (values.length != weights.length)
			throw new IllegalArgumentException("The amount of values and weights are not equal!");
		double sum = 0.0;
		for (int i = 0; i < values.length; i++)
			sum += values[i] * weights[i];
		sum /= MathPlus.sum(weights);
		return sum;
	}

	/**
	 * Returns the geometric mean of all {@code double} values.
	 * 
	 * @param ds values to be averaged
	 * @return geometric mean of all values
	 */
	public static double geometricMean(double... ds) {
		return Math.pow(MathPlus.product(ds), 1 / ds.length);
	}

	/**
	 * Returns the harmonic mean of all {@code double} values.
	 * 
	 * @param ds values to be averaged
	 * @return harmonic mean of all values
	 */
	public static double harmonicMean(double... ds) {
		return ds.length / MathPlus.harmonicSum(ds);
	}

	/**
	 * Returns the mathematical mode or most common value(s) of all {@code double}
	 * values. Returns an empty array if there is no mode, and multiple numbers if
	 * they are all equally common.
	 * 
	 * @param ds values to find the mode of
	 * @return the mode(s) of all values
	 */
	public static double[] mode(double... ds) {
		ArrayList<Double> commonValues = new ArrayList<Double>();
		ArrayList<Integer> valueOccurence = new ArrayList<Integer>();
		for (int i = 0; i < ds.length; i++) {
			if (commonValues.contains(ds[i])) {
				valueOccurence.set(commonValues.indexOf(ds[i]), valueOccurence.get(commonValues.indexOf(ds[i])) + 1);
			} else {
				commonValues.add(ds[i]);
				valueOccurence.add(1);
			}
		}
		double[] occurences = new double[valueOccurence.size()];
		for (int i = 0; i < occurences.length; i++) {
			occurences[i] = valueOccurence.get(i);
		}
		int minNeeded = (int) MathPlus.max(occurences);
		for (int i = 0; i < commonValues.size(); i++) {
			if (valueOccurence.get(i) < minNeeded) {
				valueOccurence.remove(i);
				commonValues.remove(i);
				i--;
			}
		}
		double[] mode = new double[commonValues.size()];
		for (int i = 0; i < mode.length; i++) {
			mode[i] = commonValues.get(i);
		}
		return mode;
	}

	/**
	 * Returns the value of the factorial of n, or n!, <br>
	 * where n! = n * (n-1) * (n-2) * ... * 2 * 1
	 * 
	 * @param n value to find the factorial of
	 * @return factorial of n, or n!
	 */
	public static double factorial(double n) {
		if (n < 0)
			throw new IllegalArgumentException("Value cannot be negative!");
		else if (n % 1 != 0)
			throw new IllegalArgumentException("Value must be an integer!");
		else if (n == 0 || n == 1)
			return 1.0;
		else if (n - 1 == n) // Occurse at n ~ 1E16
			return Double.MAX_VALUE;
		else
			return n * factorial(n - 1);
	}

	/**
	 * Returns the value of the <code>n</code>th root of <code>a</code>, equivalent
	 * to <code>Math.pow(a, 1.0/n)</code>.
	 * 
	 * @param a radicand of radical
	 * @param n degree of radical
	 * @return <code>n</code>th root of <code>a</code>
	 */
	public static double nthrt(double a, double n) {
		return Math.pow(a, 1.0 / n);
	}

	/**
	 * Returns the decimal modulus of <code>a%b</code>, which work similar to the
	 * integer modulus
	 * 
	 * @deprecated Java inherently takes the double modulus, not the integer
	 *             modulus.
	 * 
	 * @param a value to be taken a modulus of
	 * @param b value to be used as the modulus
	 * @return decimal modulus of <code>a%b</code>
	 */
	@Deprecated
	public static double doubleMod(double a, double b) {
		return a - (Math.floor(a / b) * b);
	}

	/**
	 * Returns a <code>Point</code> object which is a reflected <code>p</code>
	 * across a line designated where p1 = (x0, y0) and p2 = (x1, y1).
	 * 
	 * @param p  point to be reflected
	 * @param x0 x coordinate of first point
	 * @param y0 y coordinate of first point
	 * @param x1 x coordinate of second point
	 * @param y1 y coordinate of second point
	 * @return point reflected across designated line
	 */
	public static Point2D mirror(Point2D p, double x0, double y0, double x1, double y1) {
		double dx, dy, a, b;
		double x2, y2;
		Point2D p1; // reflected point to be returned
		dx = x1 - x0;
		dy = y1 - y0;
		a = (dx * dx - dy * dy) / (dx * dx + dy * dy);
		b = 2 * dx * dy / (dx * dx + dy * dy);
		x2 = a * (p.getX() - x0) + b * (p.getY() - y0) + x0;
		y2 = b * (p.getX() - x0) - a * (p.getY() - y0) + y0;
		p1 = new Point2D.Double(x2, y2);
		return p1;
	}

	/**
	 * Returns a <code>Point</code> object which is a reflected <code>p</code>
	 * across a line designated by <code>p1</code> and <code>p2</code>
	 * 
	 * @param p  point to be reflected
	 * @param p1 first point of the line
	 * @param p2 second point of the line
	 * @return point reflected across designated line
	 */
	public static Point2D mirror(Point p, Point p1, Point p2) {
		return MathPlus.mirror(p, p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

	/**
	 * Returns the angle between two vectors, originating at the origin and with
	 * ends at (x1, y1) and (x2, y2), respectively, in radians
	 * 
	 * @param x1 x coordinate of first point
	 * @param y1 y coordinate of first point
	 * @param x2 x coordinate of second point
	 * @param y2 y coordinate of second point
	 * @return angle "between" points in radians
	 */
	public static double angleBetween(double x1, double y1, double x2, double y2) {
		double angle = Math.atan2(y2 - y1, x2 - x1);
		return angle;
	}

	/**
	 * Returns the angle between two vectors, originating at the origin and with
	 * ends at <code>p1</code> and <code>p2</code>, respectively, in radians
	 * 
	 * @param p1 first point
	 * @param p2 second point
	 * @return angle "between" points in radians
	 */
	public static double angleBetween(Point2D p1, Point2D p2) {
		return angleBetween(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

	/**
	 * An immutable representation of complex numbers in the form <code>a+bi</code>,
	 * where <code>a</code> and <code>b</code> are real numbers<br>
	 * <br>
	 * 
	 * Supports the four basic algebraic operators (+, -, *, /), power,
	 * exponentiation, logarithm, conjugation, and magnitude/theta calculation
	 * 
	 * @author jonatanfontanez
	 *
	 */
	static final class ComplexNumber {
		private final double realNumber;
		private final double imaginaryNumber;

		public ComplexNumber() {
			this(0, 0);
		}

		public ComplexNumber(final double real, final double i) {
			realNumber = real;
			imaginaryNumber = i;
		}

		/**
		 * Creates and returns a string representation of the complex number in the form
		 * {@code a +/- bi}
		 * 
		 * @return string representation of the {@code ComplexNumber}
		 */
		public String toString() {
			return this.getRealComponent() + (this.getImaginaryComponent() < 0 ? " - " : " + ")
					+ Math.abs(this.getImaginaryComponent()) + "i";
		}

		/**
		 * Returns the sum of this and {@code c2}
		 * 
		 * @param c2 the complex number to add
		 * @return the sum of this and {@code c2}
		 */
		public ComplexNumber add(final ComplexNumber c2) {
			return new ComplexNumber(this.getRealComponent() + c2.getRealComponent(),
					this.getImaginaryComponent() + c2.getImaginaryComponent());
		}

		/**
		 * Returns this subtracted by {@code c2}
		 * 
		 * @param c2 the complex number to subtract
		 * @return the difference between this and {@code c2}
		 */
		public ComplexNumber subtract(final ComplexNumber c2) {
			return new ComplexNumber(this.getRealComponent() - c2.getRealComponent(),
					this.getImaginaryComponent() - c2.getImaginaryComponent());
		}

		/**
		 * Returns the product of this and {@code c2}
		 * 
		 * @param c2 the complex number to multiply by
		 * @return the product of this and {@code c2}
		 */
		public ComplexNumber multiply(final ComplexNumber c2) {
			double real1 = this.getRealComponent() * c2.getRealComponent();
			double real2 = -this.getImaginaryComponent() * c2.getImaginaryComponent();
			double imaginary1 = this.getRealComponent() * c2.getImaginaryComponent();
			double imaginary2 = this.getImaginaryComponent() * c2.getRealComponent();
			return new ComplexNumber(real1 + real2, imaginary1 + imaginary2);
		}

		/**
		 * Returns the complex number equivalent to this complex number divided by
		 * {@code c2}
		 * 
		 * @param c2 the denominator of the fraction
		 * @return the division of this by {@code c2}
		 */
		public ComplexNumber divide(final ComplexNumber c2) {
			ComplexNumber c1 = this.clone(), c3 = c2.clone();
			c1 = c1.multiply(c2.conjugate());
			c3 = c2.multiply(c2.conjugate());
			return new ComplexNumber(c1.getRealComponent() / c3.getRealComponent(),
					c1.getImaginaryComponent() / c3.getRealComponent());
		}

		/**
		 * Returns the complex number equivalent to <code>z^pow</code>, <br>
		 * where <code>z</code> is the complex number base and <code>pow</code> is the
		 * power
		 * 
		 * @param pow
		 * @return
		 */
		public ComplexNumber pow(double pow) {
			return new ComplexNumber(Math.cos(pow * this.theta()), Math.sin(pow * this.theta()))
					.multiply(new ComplexNumber(Math.pow(this.magnitude(), pow), 0));
		}

		/**
		 * Returns the complex number equivalent to <code>e^z</code>, where
		 * <code>z</code> is the complex number
		 * 
		 * @return the value of the exponential function at {@code z}
		 */
		public ComplexNumber exp() {
			double realExp = Math.exp(this.getRealComponent());
			return new ComplexNumber(realExp * Math.cos(this.getImaginaryComponent()),
					realExp * Math.sin(this.getImaginaryComponent()));
		}

		/**
		 * Returns the complex number equivalent to <code>ln(z)</code>, where
		 * <code>z</code> is the argument. <br>
		 * Only returns the principal value
		 * 
		 * @return the principal value of the natural logarithmic function at {@code z}
		 */
		public ComplexNumber log() {
			return new ComplexNumber(Math.log(this.magnitude()), this.theta());
		}

		/**
		 * Returns the conjugate of the complex number, where the imaginary components
		 * sign is switched
		 * 
		 * @return conjuaget of complex number
		 */
		public ComplexNumber conjugate() {
			return new ComplexNumber(this.getRealComponent(), -this.getImaginaryComponent());
		}

		/**
		 * Returns the real component of this complex number
		 * 
		 * @return real component
		 */
		public double getRealComponent() {
			return realNumber;
		}

		/**
		 * Returns the imaginary component of this complex number
		 * 
		 * @return imaginary component
		 */
		public double getImaginaryComponent() {
			return imaginaryNumber;
		}

		/**
		 * Returns the magnitude of the complex number, equivalent to the length of
		 * a<br>
		 * vector constructed with the coordinates being the component of the
		 * complex<br>
		 * number
		 * 
		 * @return magnitude of the complex number
		 */
		public double magnitude() {
			return Math.sqrt(realNumber * realNumber + imaginaryNumber * imaginaryNumber);
		}

		/**
		 * Returns the theta of the complex number, equivalent to the angle between
		 * a<br>
		 * vector constructed with the coordinates being the component of the<br>
		 * complex number and the positive side of the x-axis
		 * 
		 * @return theta of the complex number
		 */
		public double theta() {
			return Math.atan2(this.getImaginaryComponent(), this.getRealComponent());
		}

		/**
		 * Returns a copy of the current complex number, with a real and imaginary<br>
		 * component that are equal
		 * 
		 * @return copy of the complex number
		 */
		@Override
		public ComplexNumber clone() {
			return new ComplexNumber(this.getRealComponent(), this.getImaginaryComponent());
		}

		/**
		 * Checks if two complex numbers are equal<br>
		 * To be considered equal, these conditions must be met:
		 * <ul>
		 * <li>the real components must be equal
		 * <li>the imaginary component must be equal
		 * </ul>
		 * 
		 * @return true if they are equal, false otherwise
		 * 
		 */
		@Override
		public boolean equals(final Object obj) {
			if (obj == null)
				return false;
			if (obj instanceof ComplexNumber)
				return ((ComplexNumber) obj).getRealComponent() == this.getRealComponent()
						&& ((ComplexNumber) obj).getImaginaryComponent() == this.getImaginaryComponent();
			return false;
		}

	}

}
