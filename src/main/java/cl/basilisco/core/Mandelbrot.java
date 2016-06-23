package cl.basilisco.core;

import java.awt.Color;

/**
 * Esta clase no deberia instanciarse.
 * 
 * @author Jaime Tobar Diaz
 *
 */
public class Mandelbrot {
	/**
	 * Metodo estatico para iterar un numero de acuerdo<br>
	 * a la formula de Benoit Mandelbrot
	 * @param c numero complejo constante, corresponde al punto en el plano.
	 * @param tope Cantidad de veces que iterara antes de considerar al numero fractal
	 * @return el Color para pintar, negro en caso de pertenecer al conjunto mandelbrot
	 */
	public static Color itera(ComplexNumber c,int tope, boolean bn, float r, float g, float b){
		ComplexNumber Z = new ComplexNumber();
		ComplexNumber C = c;
		Color es = formula(Z, C,0,tope,bn,r,g,b);
		return es;
	}
	
	/*
	 * Recursivo es mejor que iterativo
	 */
	private static Color formula(ComplexNumber Z, ComplexNumber C, int contador, int tope, boolean bn, float r, float g, float b){
		//Z = Z^2 + C
		//tomamos Z que comienza en 0+0i y lo iteramos sumando el punto.
		double magn = Z.getModulo();
		if(contador>tope){
			return Color.BLACK;//si ya no se paso, es parte del conjunto
		}else if(magn >= 2){
			return queColor(contador,magn, bn,r,g,b);//se pasa de dos, no es parte del conjunto
		}
		Z=Z.eleva(2);
		Z=Z.suma(C);
		
		return formula(Z, C,contador+1,tope,bn,r,g,b);
	}
	
	
	public static Color queColor(int c,double magn, boolean bn,double r2, double g2, double b2){
		int r, g ,b;
		if(bn){
			int n = 255-(c*5)%255;
			r = n;
			g = n;
			b = n;
		}else{
			if(r2==0) r2=magn;
			if(g2==0) g2=magn;
			if(b2==0) b2=magn;
			r = 255-(c*(int)(magn*r2))%250;
			g = 255-(c*(int)(magn*g2))%255;
			b = 255-(c*(int)(magn*b2))%245;
		}
		Color rt = new Color(r,g,b);
		return rt;
	}

}
