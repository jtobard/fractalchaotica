package cl.basilisco.core;

import java.awt.Color;

public class Lambda {

	public static Color iterate(ComplexNumber z, ComplexNumber c, int limit, boolean grayscale, float r, float g,
			float b) {
		Color es = calculate(z, c, 0, limit, grayscale, r, g, b);
		return es;
	}

	private static Color calculate(ComplexNumber Z, ComplexNumber C, int counter, int limit, boolean grayscale, float r,
			float g, float b) {
		// z = c*z(1-z)
		double magn = Z.getModulus();
		if (magn >= 2) {
			return Mandelbrot.getColor(counter, magn, grayscale, r, g, b);// passes two, not part of the set
		} else if (counter > limit) {
			return Color.BLACK;// if it hasn't passed, it is part of the set
		}

		double x, y, a, b2, tmp;
		x = Z.getReal();
		y = Z.getImaginary();
		a = C.getReal();
		b2 = C.getImaginary();
		// still don't understand this chunk, copied it shamelessly
		tmp = x;
		x = (x - x * x + y * y) * a - (y - 2 * x * y) * b2;
		y = (y - 2 * tmp * y) * a + (tmp - tmp * tmp + y * y) * b2;

		return calculate(new ComplexNumber(x, y), C, counter + 1, limit, grayscale, r, g, b);
	}

}
