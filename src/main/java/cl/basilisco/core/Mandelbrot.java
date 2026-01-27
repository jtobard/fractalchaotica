package cl.basilisco.core;

import java.awt.Color;

/**
 * This class should not be instantiated.
 * 
 * @author Jaime Tobar Diaz
 *
 */
public class Mandelbrot {
	/**
	 * Static method to iterate a number according to<br>
	 * Benoit Mandelbrot's formula
	 * 
	 * @param c     constant complex number, corresponds to the point in the plane.
	 * @param limit Amount of times it will iterate before considering the number a
	 *              fractal
	 * @return the Color to paint, black in case of belonging to the Mandelbrot set
	 */
	public static Color iterate(ComplexNumber c, int limit, boolean grayscale, float r, float g, float b) {
		ComplexNumber Z = new ComplexNumber();
		ComplexNumber C = c;
		Color es = calculate(Z, C, 0, limit, grayscale, r, g, b);
		return es;
	}

	/*
	 * Recursive is better than iterative
	 */
	private static Color calculate(ComplexNumber Z, ComplexNumber C, int counter, int limit, boolean grayscale, float r,
			float g, float b) {
		// Z = Z^2 + C
		// we take Z starting at 0+0i and iterate it adding the point.
		double magn = Z.getModulus();
		if (counter > limit) {
			return Color.BLACK;// if it hasn't passed, it is part of the set
		} else if (magn >= 2) {
			return getColor(counter, magn, grayscale, r, g, b);// passes two, not part of the set
		}
		Z = Z.pow(2);
		Z = Z.add(C);

		return calculate(Z, C, counter + 1, limit, grayscale, r, g, b);
	}

	public static Color getColor(int c, double magn, boolean grayscale, double r2, double g2, double b2) {
		int r, g, b;
		if (grayscale) {
			int n = 255 - (c * 5) % 255;
			r = n;
			g = n;
			b = n;
		} else {
			if (r2 == 0)
				r2 = magn;
			if (g2 == 0)
				g2 = magn;
			if (b2 == 0)
				b2 = magn;
			r = 255 - (c * (int) (magn * r2)) % 250;
			g = 255 - (c * (int) (magn * g2)) % 255;
			b = 255 - (c * (int) (magn * b2)) % 245;
		}
		Color rt = new Color(r, g, b);
		return rt;
	}

}
