package cl.basilisco.core;

import java.awt.Color;

/**
 * W.I.P.
 *
 */
public class Formula{
	/*
	    Numeros complejos:
		Z= punto eje o PUNTO
		C= constante escrita en campos o KONSTANTE
		M= Numero complejo q parte siempre en cero (como Mandelbrot) o MANDELBROT
		x = Z.real
		y = Z.imaginario
		Operaciones:
		() = parentesis
		+,-,*,/
		^ = eleva solo n° reales
		pendientes:
		e = exp(Z) = e^Z
		l = log(Z) = logaritmo
		r = sqrt(Z) = raiz de Z
		s = sin(Z) = seno de Z
		c = cos(Z) = coseno de Z
		t = tan(Z) = tangente
	 */
	public static Color itera(String cabecera,Object[] formula,ComplexNumber z,int tope,boolean bn, float r, float g, float b){
		Object formula2[] = new Object[formula.length];
		for (int i = 0; i < formula.length; i++) 
			formula2[i]=formula[i];
		
		formula2=poneZ(formula2, z);
		if(cabecera.equals("M"))
			return formulaM(formula2,new ComplexNumber(),new ComplexNumber(),0,tope,bn,r,g,b);
		else
			return formula(formula2,z,z,0,tope,bn,r,g,b);
	}

	public static Object[] transforma(String formula,ComplexNumber M,ComplexNumber Z, ComplexNumber C){
		char[] c=formula.toCharArray();
		Object[] formula2 = new Object[c.length];
		String aux="";
		int j=0;
		for (int i = 0; i < formula2.length; i++) {
			if(c[i]=='+' || c[i]=='-' || c[i]=='*' || c[i]=='/' || c[i]=='^'
					|| c[i]=='(' || c[i]==')' || c[i]=='x' || c[i]=='y'
					|| c[i]=='l'|| c[i]=='r'){
				if(aux.length()!=0){//viene de un numero
					formula2[j]=new Double(aux);
					aux="";
					j++;
				}
				formula2[j]=c[i]+"";
				j++;
			}else{
				try{
					ComplexNumber num = getComplex(c[i], M, Z, C);
					formula2[j]= num;
					j++;
				}catch(Error e){
					aux += c[i]+"";
				}

			}

		}
		if(aux.length()>0){//aun queda un numero q esta al final
			formula2[j]=new Double(aux);
			j++;
		}

		Object[] formula3 = new Object[j];
		for (int i = 0; i < formula3.length; i++) {
			formula3[i]=formula2[i];
		}

		return formula3;

	}

	private static Color formula(Object[] formula, ComplexNumber originalZ,ComplexNumber Z,int contador, int tope, boolean bn, float r, float g, float b){
		for (int i = 0; i < formula.length; i++) {
			if(formula[i].equals(originalZ)){
				formula[i]=Z;
				originalZ=Z;
				break;
			}
		}
		double magn = Z.getModulo();
		if(magn >= 2){
			return Mandelbrot.queColor(contador,magn,bn,r,g,b);//si pasa de dos, no es parte del conjunto
		}else if(contador>tope){
			return Color.BLACK;//si ya no se paso, es parte del conjunto
		}
		//aqui va la formula
		try {
			Z=eval(formula);
		} catch (BadFormulaException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return formula(formula,originalZ,Z,contador+1,tope,bn,r,g,b);
	}

	private static Color formulaM(Object[] formula, ComplexNumber originalZ,ComplexNumber Z,int contador, int tope, boolean bn, float r, float g, float b){
		for (int i = 0; i < formula.length; i++) {
			if(formula[i].getClass().equals(ComplexNumber.class)){
				ComplexNumber aux=(ComplexNumber)formula[i];
				if(aux.equalsM(originalZ)){
					formula[i]=Z;
					originalZ=Z;
					break;
				}
			}
		}
		double magn = Z.getModulo();
		if(magn >= 2){
			return Mandelbrot.queColor(contador,magn,bn,r,g,b);//si pasa de dos, no es parte del conjunto
		}else if(contador>tope){
			return Color.BLACK;//si ya no se paso, es parte del conjunto
		}
		//aqui va la formula
		try {
			Z=eval(formula);
		} catch (BadFormulaException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return formula(formula,originalZ,Z,contador+1,tope,bn,r,g,b);
	}
	
	private static ComplexNumber getComplex(char compl,ComplexNumber M,ComplexNumber Z, ComplexNumber C) throws Error{
		if (compl == 'M'){
			return M;
		}
		if(compl == 'Z'){
			return Z;
		}
		if(compl == 'C'){
			return C;
		}
		throw new Error();
	}
	
	private static ComplexNumber eval(Object[] formula)throws BadFormulaException{
		ComplexNumber z = new ComplexNumber();
		//si no hay mas operaciones, terminamos
		try {
			if (formula.length == 1) {
				return (ComplexNumber) formula[0];
			} else if (formula.length == 3) {
				return expr(formula, 0, 2, 1);
			}
		}catch (ClassCastException ex){
			System.err.println(ex.getMessage());
		}

		int pi,pf,signo;
		signo=-1;
		int pe,ps,pr,pm,pd;
		int pl,psqrt;//operaciones mas complejas
		pe=ps=pr=pm=pd=-1;
		pl=psqrt=-1;
		for(int i=formula.length-1;i>=0;i--){
			if(formula[i].equals("^"))
				pe=i;
			if(formula[i].equals("/"))
				pd=i;
			if(formula[i].equals("*"))
				pm=i;
			if(formula[i].equals("-"))
				pr=i;
			if(formula[i].equals("+"))
				ps=i;
			if(formula[i].equals("l"))
				pl=i;
			if(formula[i].equals("r"))
				psqrt=i;
		}
				
		if(pe!=-1){
			signo=pe;;	
		}else if(pl!=-1){
			signo=pl;
		}else if(psqrt!=-1){
			signo=psqrt;
		}else if(pd!=-1){
			signo=pd;
		}else if(pm!=-1){
			signo=pm;
		}else if(pr!=-1){
			signo=pr;
		}else if(ps!=-1){
			signo=ps;
		}
		
		pi=signo-1;
		pf=signo+1;
		
		if(pl==signo || psqrt==signo){
			pi++;
		}
		
		ComplexNumber c = expr(formula, pi, pf, signo);
		
		
		formula = reemplaza(formula, pi, pf, c);
		
		z=eval(formula);
		
		return z;
	}
	
	private static Object[] poneZ(Object[] formula, ComplexNumber Z){
		
		for (int i = 0; i < formula.length; i++) {
			if(formula[i]==null)
				formula[i]=Z;
			else if(formula[i].equals("x"))
				formula[i]=Z.getReal();
			else if(formula[i].equals("y"))
				formula[i]=Z.getImaginario();
		}
		
		return formula;
	}
	
	private static Object[] reemplaza(Object[] formula, int pi,int pf,ComplexNumber C){

		int aux = (pf-pi);
		
		Object[] formula_final = new Object[formula.length-aux];
		for(int i=0;i<formula_final.length;i++)
			if(i<pi)
				formula_final[i]=formula[i];
			else if(i==pi)
				formula_final[i]=C;
			else
				formula_final[i]=formula[i+aux];
		
		return formula_final;
	}
	
	private static ComplexNumber expr(Object formula[], int pi, int pf, int signo) throws BadFormulaException{


		ComplexNumber a,b,c;
		c=null;
		if(pi == -1){
			throw new BadFormulaException();
		}
		if(formula[pi].equals("l")){
			a=(ComplexNumber)formula[pi+1];
			c = a.log();
		}else if(formula[pi].equals("r")){
			a=(ComplexNumber)formula[pi+1];
			c = a.sqrt();
		}else{
		
			a = (ComplexNumber) formula[pi];
			
	
			if(formula[signo].equals("^")){
				Double d = (Double) formula[pf];
				c=a.eleva(d);
			}else if(formula[signo].equals("/")){
				b = (ComplexNumber) formula[pf];
				c=a.divide(b);
			}else if(formula[signo].equals("*")){
				b = (ComplexNumber) formula[pf];
				c=a.multiplica(b);
			}else if(formula[signo].equals("-")){
				b = (ComplexNumber) formula[pf];
				c=a.resta(b);
			}else if(formula[signo].equals("+")){
				b = (ComplexNumber) formula[pf];
				c=a.suma(b);
			}else{
				c=new ComplexNumber();
			}
			
		}
		return c;
	}
}
