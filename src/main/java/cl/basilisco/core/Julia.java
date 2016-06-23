package cl.basilisco.core;

import java.awt.Color;

/**
 * Esta clase no deberia instanciarse.
 * 
 * @author Jaime Tobar Diaz, jaime.tobar.diaz@gmail.com
 *
 */
public class Julia{
	
	/**
	 * Metodo estatico para iterar un numero de acuerdo<br>
	 * a la formula de Gaston Julia
	 * @param z numero base a iterar, corresponde al punto en el plano.
	 * @param c valor constante, corresponde a un numero complejo<br>
	 * No debera ser mayor ni menor al numero aureo en positivo o negativo.
	 * @param tope Cantidad de veces que iterara antes de considerar al numero fractal
	 * @return el Color para pintar, negro en caso de pertenecer al conjunto Julia
	 */
	public static Color itera(ComplexNumber z,ComplexNumber c,int tope,boolean bn, float r, float g, float b){
		Color es = formula(z, c,0,tope,bn,r,g,b);
		return es;
	}
	
	/*
	 * Recursivo es mejor que iterativo
	 */
	private static Color formula(ComplexNumber Z, ComplexNumber C, int contador, int tope, boolean bn, float r, float g, float b){
		//Z = Z^2 + C --> La formula es igual a la de Mandelbrot, 
		//pero aqui se itera el punto y se usa otra constante
		double magn = Z.getModulo();
		if(magn >= 2){
			return Mandelbrot.queColor(contador,magn,bn,r,g,b);//se pasa de dos, no es parte del conjunto
		}else if(contador>tope){
			return Color.BLACK;//si ya no se paso, es parte del conjunto
		}
		Z=Z.eleva(2);
		Z=Z.suma(C);
		
		return formula(Z, C,contador+1,tope,bn,r,g,b);
	}
	
	/**
	 * Numeros famosos y probados de Julia
	 * @return Un arreglo de numero complejos conocidos
	 */
	public static ComplexNumber[] valores(){
		ComplexNumber[] ns = {new ComplexNumber(-1,0),
				new ComplexNumber(0.687,0.312),
				new ComplexNumber(0.6,0.55),
				new ComplexNumber(0.8,0.6),
				new ComplexNumber(0.3,0.6),
				new ComplexNumber(0.25,0),
				new ComplexNumber(0.4,0.6),
				new ComplexNumber(0.282,0),
				new ComplexNumber(0.285,0.01),
				new ComplexNumber(0.45,0.1428),
				new ComplexNumber(-0.70176,-0.3842),
				new ComplexNumber(-0.835,-0.2321),
				new ComplexNumber(-0.8,+0.156),
		};
		
		return ns;
		
	}

}
