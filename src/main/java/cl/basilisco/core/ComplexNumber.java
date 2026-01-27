package cl.basilisco.core;

/**
 * 
 * Representation of a complex number<br>
 * and some of its operations.
 * 
 * @author Jaime Tobar Diaz, jaime.tobar.diaz@gmail.com
 * 
 *
 */
public class ComplexNumber implements Comparable<ComplexNumber> {
	private double real;
	private double imaginary;

	/**
	 * Empty constructor, creates a complex number at 0
	 */
	public ComplexNumber() {
		this.real = 0;
		this.imaginary = 0;
	}

	/**
	 * Constructor with both parts, real and imaginary.
	 * 
	 * @param r corresponds to the real part, X axis on the graph
	 * @param i corresponds to the imaginary part, Y axis on the graph
	 */
	public ComplexNumber(double r, double i) {
		this.real = r;
		this.imaginary = i;
	}

	// setters and getters

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImaginary() {
		return imaginary;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}

	// properties of complex numbers
	/**
	 * The modulus is also known as magnitude or absolute value.<br>
	 * Corresponds to the following formula:<br>
	 * |Z| = sqrt(Re(Z^2)+Im(Z^2))
	 * 
	 * @return Value of the magnitude.
	 */
	public double getModulus() {
		if (real != 0 || imaginary != 0)
			return Math.sqrt((real * real + imaginary * imaginary));
		else
			return 0d;
	}

	/**
	 * Returns the argument of the number
	 * atan(im/real)
	 * 
	 * @return
	 */
	public double getArgument() {
		return Math.atan2(this.getImaginary(), this.getReal());
	}

	/**
	 * Conjugate of the complex number<br>
	 * Corresponds to same real part + negative imaginary part
	 * 
	 * @return
	 */
	public ComplexNumber getConjugate() {
		return new ComplexNumber(real, imaginary * -1);
	}

	// mathematical operations

	/**
	 * Adds the current number plus the passed complex number.
	 * 
	 * @param b complex number to add.
	 * @return new complex number.
	 */
	public ComplexNumber add(ComplexNumber b) {
		return new ComplexNumber(real + b.getReal(), imaginary + b.getImaginary());
	}

	/**
	 * Subtracts the passed number from the current number
	 * 
	 * @param b complex number to subtract.
	 * @return new complex number.
	 */
	public ComplexNumber subtract(ComplexNumber b) {
		return new ComplexNumber(real - b.getReal(), imaginary - b.getImaginary());
	}

	/**
	 * Multiplies the current number by the parameter.
	 * 
	 * @param b complex number to multiply.
	 * @return new complex number.
	 */
	public ComplexNumber multiply(ComplexNumber b) {
		double r = (this.real * b.getReal()) - (this.imaginary * b.getImaginary());
		double i = (this.real * b.getImaginary()) + (b.getReal() * this.imaginary);
		return new ComplexNumber(r, i);
	}

	/**
	 * Divides the current number by the parameter.
	 * 
	 * @param b
	 * @return new Complex Number.
	 */
	public ComplexNumber divide(ComplexNumber b) {
		double den = Math.pow(b.getModulus(), 2);
		return new ComplexNumber((real * b.getReal() + imaginary * b.getImaginary()) / den,
				(imaginary * b.getReal() - real * b.getImaginary()) / den);

	}

	/**
	 * exp(Z), that is, Euler's number e^Z
	 * 
	 * @return new Complex Number.
	 */
	public ComplexNumber exp() {
		double aux = Math.exp(real);
		return new ComplexNumber(aux * Math.cos(imaginary), aux * Math.sin(imaginary));
	}

	/**
	 * Logarithm of the number or Log(Z)
	 * 
	 * @return new Complex Number.
	 */
	public ComplexNumber log() {
		return new ComplexNumber(Math.log(this.getModulus()), this.getArgument());

	}

	/**
	 * Square root or sqrt(Z)
	 * 
	 * @return new Complex Number.
	 */
	public ComplexNumber sqrt() {
		double r = Math.sqrt(this.getModulus());
		double theta = this.getArgument() / 2;
		return new ComplexNumber(r * Math.cos(theta), r * Math.sin(theta));
	}

	// Real cosh function (used to compute complex trig functions)
	private double cosh(double theta) {
		return (Math.exp(theta) + Math.exp(-theta)) / 2;
	}

	// Real sinh function (used to compute complex trig functions)
	private double sinh(double theta) {
		return (Math.exp(theta) - Math.exp(-theta)) / 2;
	}

	/**
	 * Sine of the number or sin(Z)
	 * 
	 * @return new Complex Number.
	 */
	public ComplexNumber sin() {
		return new ComplexNumber(cosh(imaginary) * Math.sin(real), sinh(imaginary) * Math.cos(real));
	}

	/**
	 * Cosine of the number or cos(Z)
	 * 
	 * @return new Complex Number.
	 */
	public ComplexNumber cos() {
		return new ComplexNumber(cosh(imaginary) * Math.cos(real), -sinh(imaginary) * Math.sin(real));
	}

	/**
	 * Hyperbolic sine of Z or sinh(Z)
	 * 
	 * @return new Complex Number.
	 */
	public ComplexNumber sinh() {
		return new ComplexNumber(sinh(real) * Math.cos(imaginary), cosh(real) * Math.sin(imaginary));
	}

	/**
	 * Hyperbolic Cosine of Z or cosh(Z)
	 * 
	 * @return new Complex Number.
	 */
	public ComplexNumber cosh() {
		return new ComplexNumber(cosh(real) * Math.cos(imaginary), sinh(real) * Math.sin(imaginary));
	}

	/**
	 * Tangent of Z or tan(Z)
	 * 
	 * @return
	 */
	public ComplexNumber tan() {
		return (this.sin()).divide(this.cos());
	}

	public ComplexNumber negative() {
		return new ComplexNumber(real * -1, imaginary * -1);
	}

	/**
	 * Implementing De Moivre's Theorem
	 * z^n = r^n*(cos(n*rho) + i sin(n*rho))
	 * where r = |z| is the modulus and rho is the argument of z.
	 * 
	 * @param n power
	 * @return new complex number.
	 */
	public ComplexNumber pow(double n) {
		double r = this.getModulus();
		double rho = this.getArgument();
		double aux1 = Math.pow(r, n);
		double aux2 = n * rho;
		double real = aux1 * (Math.cos(aux2));
		double img = aux1 * Math.sin(aux2);
		return new ComplexNumber(real, img);
	}

	/**
	 * To improve efficiency, common version without decimals for power
	 * 
	 * @return
	 */
	public ComplexNumber pow(int n) {
		ComplexNumber z = new ComplexNumber();
		if (n != 0) {
			z = this;
			for (int j = 1; j < n; j++)
				z = z.multiply(this);
		}
		return z;
	}

	public ComplexNumber pow(Double n) {
		double n2 = n.doubleValue();
		return pow(n2);

	}

	// others
	/**
	 * Overridden method to show the traditional representation<br>
	 * of a complex number.<br>
	 * Real part + imaginary part + i
	 */
	@Override
	public String toString() {
		return this.real + " + " + this.imaginary + "i";
	}

	/**
	 * Compares with another Complex comparing only their values.
	 * 
	 * @param obj Object to compare
	 * @return boolean is equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ComplexNumber) {
			ComplexNumber aux = (ComplexNumber) obj;
			return (aux.getReal() == this.real && aux.getImaginary() == this.imaginary);
		}
		return false;
	}

	// Kept for compatibility if needed, but standard equals is better.
	public boolean equalsM(Object obj) {
		return equals(obj);
	}

	@Override
	public int compareTo(ComplexNumber aux) {
		if (aux.getReal() == this.real && aux.getImaginary() == this.imaginary) {
			return 0;
		}
		return -1;
	}
}
