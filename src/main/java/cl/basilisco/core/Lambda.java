package cl.basilisco.core;

import java.awt.Color;

public class Lambda {
	
	public static Color itera(ComplexNumber z, ComplexNumber c,int tope, boolean bn, float r, float g, float b){
		Color es = formula(z, c,0,tope,bn,r,g,b);
		return es;
	}
	
	
	private static Color formula(ComplexNumber Z, ComplexNumber C, int contador, int tope, boolean bn, float r, float g, float b){
		//z = c*z(1-z)
		double magn = Z.getModulo();
		if(magn >= 2){
			return Mandelbrot.queColor(contador,magn,bn,r,g,b);//se pasa de dos, no es parte del conjunto
		}else if(contador>tope){
			return Color.BLACK;//si ya no se paso, es parte del conjunto
		}
		

		double x,y,a,b2,tmp;
		x=Z.getReal();
		y = Z.getImaginario();
		a = C.getReal();
		b2=C.getImaginario();
		//aun no entiendo este trozo, lo copie descaradamente
		tmp=x;
		x=(x-x*x+y*y)*a-(y-2*x*y)*b2;
		y=(y-2*tmp*y)*a+(tmp-tmp*tmp+y*y)*b2;
		
		return formula(new ComplexNumber(x,y), C,contador+1,tope, bn,r,g,b);
	}

}
