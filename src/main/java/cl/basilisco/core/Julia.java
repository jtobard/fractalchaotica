package cl.basilisco.core;

import java.awt.Color;

/**
 * This class should not be instantiated.
 * 
 * @author Jaime Tobar Diaz, jaime.tobar.diaz@gmail.com
 *
 */
public class Julia {

	/**
	 * Static method to iterate a number according to<br>
	 * Gaston Julia's formula
	 * 
	 * @param z     base number to iterate, corresponds to the point in the plane.
	 * @param c     constant value, corresponds to a complex number<br>
	 *              Should not be greater or less than the golden ratio in positive
	 *              or negative.
	 * @param limit Amount of times it will iterate before considering the number a
	 *              fractal
	 * @return the Color to paint, black in case of belonging to the Julia set
	 */
	public static Color iterate(ComplexNumber z, ComplexNumber c, int limit, boolean grayscale, float r, float g,
			float b) {
		Color es = calculate(z, c, 0, limit, grayscale, r, g, b);
		return es;
	}

	/*
	 * Recursive is better than iterative
	 */
	private static Color calculate(ComplexNumber Z, ComplexNumber C, int counter, int limit, boolean grayscale, float r,
			float g, float b) {
		// Z = Z^2 + C --> The formula is the same as Mandelbrot,
		// but here the point is iterated and another constant is used
		double magn = Z.getModulus();
		if (magn >= 2) {
			return Mandelbrot.getColor(counter, magn, grayscale, r, g, b);// passes two, not part of the set
		} else if (counter > limit) {
			return Color.BLACK;// if it hasn't passed, it is part of the set
		}
		Z = Z.pow(2);
		Z = Z.add(C);

		return calculate(Z, C, counter + 1, limit, grayscale, r, g, b);
	}

	/**
	 * Famous and tested Julia numbers
	 * 
	 * @return An array of known complex numbers
	 */
	public static ComplexNumber[] famousValues() {
		ComplexNumber[] ns = { new ComplexNumber(-1, 0),
				new ComplexNumber(0.687, 0.312),
				new ComplexNumber(0.6, 0.55),
				new ComplexNumber(0.8, 0.6),
				new ComplexNumber(0.3, 0.6),
				new ComplexNumber(0.25, 0),
				new ComplexNumber(0.4, 0.6),
				new ComplexNumber(0.282, 0),
				new ComplexNumber(0.285, 0.01),
				new ComplexNumber(0.45, 0.1428),
				new ComplexNumber(-0.70176, -0.3842),
				new ComplexNumber(-0.835, -0.2321),
				new ComplexNumber(-0.8, +0.156),
		};

		return ns;

	}

}
