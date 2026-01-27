package cl.basilisco.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cl.basilisco.core.Biomorph;
import cl.basilisco.core.ComplexNumber;
import cl.basilisco.core.Fractal;
import cl.basilisco.core.Julia;
import cl.basilisco.core.Lambda;
import cl.basilisco.core.Mandelbrot;

/**
 * Panel to paint our fractal.
 * 
 * @author Jaime Tobar Diaz, jaime.tobar.diaz@gmail.com
 *
 */
public class MyCanvas extends JPanel {

	private static final long serialVersionUID = -8016335228852792096L;

	// variables for plotting the plane
	private int size = 600; // window size
	private double pixelRatio;// Ratio Pixel - Point on the line
	private double cornerX = -3, cornerY = 3;// corner coordinates
	private Image bufferImage; // for double buffering and saving the image.

	// variables for the selection box (zoom)
	private int boxStartX, boxStartY, boxEndX, boxEndY;
	// is it grayscale?
	private boolean grayscale;

	// which fractal is used
	// 0=Mandelbrot, 1=Julia
	private int fractalType;
	private int maxIterations = 255;// number of times the numbers will iterate

	// factor for Julia and Others
	private ComplexNumber constant;

	// user formula (unused in current code but kept fields)

	// colors
	float r = 8;
	float g = 2;
	float b = 3;

	/**
	 * Constructor, initializes variables.
	 */
	public MyCanvas() {
		this.pixelRatio = (cornerY * 2) / this.getPreferredSize().width;
		this.grayscale = false;
		super.setDoubleBuffered(true);
		fractalType = 0;
	}

	public void zoomArea() {
		if (boxEndX > boxStartX + 5) {// if the box was created intentionally and not a small mouse movement
			double cornerXSel, cornerYSel, cornerXEndSel;

			cornerXSel = boxStartX;
			cornerXSel = cornerXSel * pixelRatio;
			cornerXSel = cornerX + cornerXSel;

			cornerYSel = boxStartY;
			cornerYSel = cornerYSel * pixelRatio;
			cornerYSel = cornerY - cornerYSel;

			cornerXEndSel = boxEndX;
			cornerXEndSel = cornerXEndSel * pixelRatio;
			cornerXEndSel = cornerX + cornerXEndSel;

			cornerX = cornerXSel;
			cornerY = cornerYSel;
			pixelRatio = (cornerXEndSel - cornerXSel) / this.getSize().width;
			this.paintFractal();

		}
	}

	/**
	 * Returns the fractal to the initial state.
	 */
	public void resetCoords() {
		this.cornerX = -3;
		this.cornerY = 3;
		this.pixelRatio = (cornerY * 2) / this.getSize().height;
	}

	public void reset() {
		resetCoords();
		this.paintFractal();
	}

	/**
	 * Moves the plane to center the point.
	 * 
	 * @param x x point.
	 * @param y y point.
	 */
	public void pan(int x, int y) {
		this.cornerX = cornerX - ((this.getSize().width / 2 - x) * pixelRatio);
		this.cornerY = cornerY - ((y - this.getSize().height / 2) * pixelRatio);
		paintFractal();
	}

	// double buffer effect.
	@Override
	public void paint(Graphics g) {
		if (this.bufferImage != null)
			g.drawImage(this.bufferImage, 0, 0, this);
		else
			g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	}

	/**
	 * Method that looks for a point (x,y) and returns its corresponding complex
	 * number<br>
	 * in the current plane.
	 * 
	 * @param x x in the plane.
	 * @param y y in the plane.
	 * @return complex number representing the requested point.
	 */
	public ComplexNumber getCoordinate(int x, int y) {
		ComplexNumber c;

		double cx = x;
		cx = cx * pixelRatio;
		cx = cornerX + cx;

		double cy = y;
		cy = cy * pixelRatio;
		cy = cornerY - cy;
		c = new ComplexNumber(cx, cy);
		return c;
	}

	/**
	 * Draws the mouse box and sets variables for scaling.
	 * 
	 * @param x x in the plane.
	 * @param y y in the plane.
	 * @param n 0 when mouse down, 1 when dragged, 2 when released.
	 */
	public void handleMouse(int x, int y, int n) {
		if (this.bufferImage != null) {
			if (n == 0) {
				boxStartX = x;
				boxStartY = y;
				boxEndX = x;
				boxEndY = y;
			}
			if (n == 2) {
				// otherwise, calculate proportion
				int width = this.getSize().width;
				int height = this.getSize().height;
				if (x > boxStartX && y > boxStartY) {// area created down-right
					if ((x - boxStartX) > (y - boxStartY)) {// x line is longer than y line, use that as reference
						boxEndX = (x);
						boxEndY = boxStartY + (int) ((x - boxStartX) * (float) height / (float) width);
					} else {
						boxEndY = y;
						boxEndX = boxStartX + (int) ((y - boxStartY) * (float) width / (float) height);
					}

				} else if (x > boxStartX && y < boxStartY) {// area created up-right
					if ((x - boxStartX) > (boxStartY - y)) {
						boxEndX = (x);
						boxEndY = boxStartY - (int) ((x - boxStartX) * (float) height / (float) width);
					} else {
						boxEndY = y;
						boxEndX = boxStartX + (int) ((boxStartY - y) * (float) width / (float) height);
					}
				} else if (x < boxStartX && y > boxStartY) {// area created down-left
					if ((boxStartX - x) > (y - boxStartY)) {
						boxEndX = (x);
						boxEndY = boxStartY + (int) ((boxStartX - x) * (float) height / (float) width);
					} else {
						boxEndY = y;
						boxEndX = boxStartX - (int) ((y - boxStartY) * (float) width / (float) height);
					}
				} else {// area created up-left
					if ((boxStartX - x) > (boxStartY - y)) {
						boxEndX = (x);
						boxEndY = boxStartY - (int) ((boxStartX - x) * (float) height / (float) width);
					} else {
						boxEndY = y;
						boxEndX = boxStartX - (int) ((boxStartY - y) * (float) width / (float) height);
					}
				}

				// if area selected in reverse
				if (boxStartX > boxEndX) {
					int xresp = boxStartX;
					boxStartX = boxEndX;
					boxEndX = xresp;
				}
				if (boxStartY > boxEndY) {
					int yresp = boxStartY;
					boxStartY = boxEndY;
					boxEndY = yresp;
				}
			}
			Graphics g = this.getGraphics();
			g.drawImage(bufferImage, 0, 0, this);
			g.setColor(Color.YELLOW);
			if (n == 2) {
				g.drawLine(boxStartX, boxStartY, boxEndX, boxStartY);
				g.drawLine(boxStartX, boxStartY, boxStartX, boxEndY);
				g.drawLine(boxEndX, boxStartY, boxEndX, boxEndY);
				g.drawLine(boxStartX, boxEndY, boxEndX, boxEndY);
				zoomArea();
			} else {
				g.drawLine(boxStartX, boxStartY, x, boxStartY);
				g.drawLine(boxStartX, boxStartY, boxStartX, y);
				g.drawLine(x, boxStartY, x, y);
				g.drawLine(boxStartX, y, x, y);
			}
		}
	}

	/**
	 * Toggles the graph to binary black and white / color.
	 */
	public void toggleColorMode() {
		this.grayscale = !this.grayscale;
		this.paintFractal();
	}

	public void saveImage(String path) {
		BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(this.bufferImage, 0, 0, this);
		g2.dispose();

		if (!path.endsWith("png") && !path.endsWith("PNG"))
			path = path + ".png";
		try {
			ImageIO.write(bi, "PNG", new File(path));
		} catch (IOException e) {
			System.out.println("error saving image");
		}
	}

	/**
	 * Sets the graph to the Mandelbrot formula.
	 */
	public void setMandelbrot() {
		this.fractalType = 1;
	}

	/**
	 * Sets the graph to the Julia formula.
	 * 
	 * @param r real part of the number to use.
	 * @param i imaginary part of the number to use.
	 */
	public void setJulia(double r, double i) {
		this.fractalType = 2;
		this.constant = new ComplexNumber(r, i);
	}

	/**
	 * Other formulas.
	 */
	public void setMandelbrot2() {
		this.fractalType = 3;
	}

	public void setLambda(double r, double i) {
		this.fractalType = 4;
		this.constant = new ComplexNumber(r, i);
	}

	public void setBiomorph() {
		this.fractalType = 5;
	}

	public void setComplexConstant(double r, double i) {
		this.constant = new ComplexNumber(r, i);
	}

	public void setColors(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(size, size);
	}

	/**
	 * the method that paints the fractal on an image.
	 */
	public void paintFractal() {
		// JOptionPane.showMessageDialog(this, "Calculating, this operation may take
		// time", "Loading...", JOptionPane.INFORMATION_MESSAGE);
		this.bufferImage = createImage(this.getSize().width, this.getSize().height);
		if (this.bufferImage == null)
			return; // safety check

		Graphics2D db = (Graphics2D) this.getGraphics();
		Graphics2D db2 = (Graphics2D) this.bufferImage.getGraphics();

		// x and y are the pixels
		int x, y;
		// a and b traverse the axis
		double a, b;
		Color color = null;
		// while x traverses the x axis of the canvas, a delivers its real value.
		int xEnd = this.getSize().width;
		int yEnd = this.getSize().height;

		for (x = 0, a = cornerX; x < xEnd; x++, a = a + pixelRatio) {
			// while y traverses the y axis of the canvas, b delivers its imaginary value.
			for (y = 0, b = cornerY; y < yEnd; y++, b = b - pixelRatio) {
				// create the complex number of this coordinate (x,y)
				ComplexNumber c = new ComplexNumber(a, b);

				// depending on the fractal formula we use
				switch (this.fractalType) {
					case 1:
						color = Mandelbrot.iterate(c, maxIterations, grayscale, this.r, this.g, this.b); // pick the
																											// color
																											// resulting
																											// from the
																											// formula
						break;
					case 2: // Julia
						color = Julia.iterate(c, constant, maxIterations, grayscale, this.r, this.g, this.b);
						break;
					case 3: // Mandelbrot2
						color = Fractal.iterate(c, maxIterations, grayscale, this.r, this.g, this.b);
						break;
					case 4: // Lambda
						color = Lambda.iterate(c, constant, maxIterations, grayscale, this.r, this.g, this.b);
						break;
					case 5: // Biomorph
						color = Biomorph.iterate(c, maxIterations, grayscale, this.r, this.g, this.b);
						break;
				}

				if (db != null) {
					db.setColor(color);// paint the pixel
					db.drawLine(x, y, x, y);
				}
				db2.setColor(color);// paint the pixel on buffer
				db2.drawLine(x, y, x, y);
			}
		}
	}

}
