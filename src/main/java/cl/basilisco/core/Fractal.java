package cl.basilisco.core;

import java.awt.Color;

public class Fractal {

	public static Color iterate(ComplexNumber c, int limit, boolean grayscale, float r, float g, float b) {
		ComplexNumber Z = new ComplexNumber();
		ComplexNumber C = c;
		Color es = calculate(Z, C, 0, limit, grayscale, r, g, b);
		return es;
	}

	private static Color calculate(ComplexNumber Z, ComplexNumber C, int counter, int limit, boolean grayscale, float r,
			float g, float b) {
		// Z = Z^3 + C
		double magn = Z.getModulus();
		if (magn >= 2) {
			return Mandelbrot.getColor(counter, magn, grayscale, r, g, b);// passes two, not part of the set
		} else if (counter > limit) {
			return Color.BLACK;// if it hasn't passed, it is part of the set
		}
		Z = Z.pow(3);
		Z = Z.add(C);

		return calculate(Z, C, counter + 1, limit, grayscale, r, g, b);
	}

}
