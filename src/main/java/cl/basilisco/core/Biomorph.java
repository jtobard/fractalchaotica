package cl.basilisco.core;

import java.awt.Color;

public class Biomorph {
	
	public static Color itera(ComplexNumber z,int tope, boolean bn, float r, float g, float b){
		ComplexNumber C = new ComplexNumber(0.2,0);
		Color es = formula(z, C,0,tope, bn,r,g,b);
		return es;
	}
	
	private static Color formula(ComplexNumber Z, ComplexNumber C, int contador, int tope, boolean bn, float r, float g, float b){
		//Z = Z^1.5 + 0.2
		double magn = Z.getModulo();
		if(magn >= 2){
			return Mandelbrot.queColor(contador, magn, bn,r,g,b);//se pasa de dos, no es parte del conjunto
		}else if(contador>tope){
			return Color.BLACK;//si ya no se paso, es parte del conjunto
		}
		Z=Z.eleva(1.5);
		Z=Z.suma(C);
		
		return formula(Z, C,contador+1,tope, bn,r,g,b);
	}

}
