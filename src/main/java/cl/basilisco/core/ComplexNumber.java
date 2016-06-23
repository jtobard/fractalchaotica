package cl.basilisco.core;

/**
 * 
 * Representacion de un numero complejo<br>
 *    y algunas de sus operaciones.
 * @author Jaime Tobar Diaz, jaime.tobar.diaz@gmail.com
 * 
 *
 */
public class ComplexNumber implements Comparable{
	private double real;
	private double imaginario;
	
	/**
	 * Constructor vacio, crea un numero complejo en 0
	 */
	public ComplexNumber() {
		this.real=0;
		this.imaginario = 0;
	}
	/**
	 * Constructor con ambas partes ,real e imaginaria.
	 * @param r corresponde a la parte real, eje X en la grafica
	 * @param i corresponde a la parte imaginaria, eje Y en la grafica
	 */
	public ComplexNumber(double r, double i){
		this.real=r;
		this.imaginario = i;
	}
	
	//setters y getters
	
	public double getReal() {
		return real;
	}
	public void setReal(double real) {
		this.real = real;
	}
	public double getImaginario() {
		return imaginario;
	}
	public void setImaginario(double imaginario) {
		this.imaginario = imaginario;
	}

	//propiedades de numeros complejos
	/**
	 * El modulo tambien se conoce com magnitud o valor absoluto.<br>
	 * Corresponde a la formula siguiente:<br>
	 * |Z| = V¯¯(Re(Z^2)+Im(Z^2)¯¯
	 * 
	 * @return Valor de la magnitud.
	 */
	public double getModulo(){
		if(real!=0 || imaginario!=0)
			return Math.sqrt((real*real+imaginario*imaginario));
		else
			return 0d;
	}
	
	
	/**
	 * Entrega el argumento del numero
	 * atan(im/rea)
	 * @return
	 */
	public double getArgumento(){
		return Math.atan2(this.getImaginario(),this.getReal());
	}
	
	/**
	 * Conjugado del numero complejo<br>
	 * Corresponde a misma parte real + negativo parte imaginaria
	 * @return
	 */
	public ComplexNumber getConjugado(){
		return new ComplexNumber(real,imaginario*-1);
	}
	
	
	
	//operaciones matematicas
	
	/**
	 * Suma el numero actual mas el numero complejo pasado.
	 * @param b numero complejo a sumar.
	 * @return nuevo numero complejo.
	 */
	public ComplexNumber suma(ComplexNumber b){
		return new ComplexNumber(real+b.getReal(),imaginario+b.getImaginario());
	}
	
	/**
	 * Resta el numero pasado al numero actual
	 * @param b numero complejo a restar.
	 * @return nuevo numero complejo.
	 */
	public ComplexNumber resta(ComplexNumber b){
		return new ComplexNumber(real-b.getReal(),imaginario-b.getImaginario());
	}
	
	
	/**
	 * Multiplica el numero actual por el parametro.
	 * @param b numero complejo a multiplicar.
	 * @return nuevo numero complejo.
	 */
	public ComplexNumber multiplica(ComplexNumber b){
		double r = (this.real*b.getReal())-(this.imaginario*b.getImaginario());
		double i = (this.real*b.getImaginario())+(b.getReal()*this.imaginario);
		return new ComplexNumber(r,i);
	}

	/**
	 * Divide el numero actual por el parametro.
	 * @param b
	 * @return nuevo numero Complejo.
	 */
	public ComplexNumber divide(ComplexNumber b){
		double den=Math.pow(b.getModulo(),2);
		return new ComplexNumber((real*b.getReal()+imaginario*b.getImaginario())/den,(imaginario*b.getReal()-real*b.getImaginario())/den);

	}
	/**
	 * exp(Z), es decir el numero de Euler e^Z
	 * @return nuevo numero Complejo.
	 */
	public ComplexNumber exp(){
		double aux=Math.exp(real);
		return new ComplexNumber(aux*Math.cos(imaginario),aux*Math.sin(imaginario));
	}
	
	/**
	 * Logaritmo del numero o Log(Z)
	 * @return nuevo numero Complejo.
	 */
	public ComplexNumber log(){
		return new ComplexNumber(Math.log(this.getModulo()),this.getArgumento());

	}
	
	/**
	 * Raiz cuadrada o sqrt(Z)
	 * @return nuevo numero Complejo.
	 */
	public ComplexNumber sqrt(){
        double r=Math.sqrt(this.getModulo());
        double theta=this.getArgumento()/2;
		return new ComplexNumber(r*Math.cos(theta),r*Math.sin(theta));
	}
	
    // Real cosh function (used to compute complex trig functions)
    private double cosh(double theta) {
        return (Math.exp(theta)+Math.exp(-theta))/2;
    }
    
    // Real sinh function (used to compute complex trig functions)
    private double sinh(double theta) {
        return (Math.exp(theta)-Math.exp(-theta))/2;
    }
	
	
	/**
	 * Seno del numero o sin(Z)
	 * @return nuevo numero Complejo.
	 */
	public ComplexNumber sin(){
		return new ComplexNumber(cosh(imaginario)*Math.sin(real),sinh(imaginario)*Math.cos(real));
	}
	
	/**
	 * Coseno del numero o cos(Z)
	 * @return nuevo numero Complejo.
	 */
	public ComplexNumber cos() {
        return new ComplexNumber(cosh(imaginario)*Math.cos(real),-sinh(imaginario)*Math.sin(real));
    }

	/**
	 * Seno hiperbolico de Z o sinh(Z)
	 * @return nuevo numero Complejo.
	 */
    public ComplexNumber sinh() {
        return new ComplexNumber(sinh(real)*Math.cos(imaginario),cosh(real)*Math.sin(imaginario));
    }

    /**
     * Coseno Hiperbolico de Z o cosh(Z)
     * @return nuevo numero Complejo.
     */
    public ComplexNumber cosh() {
        return new ComplexNumber(cosh(real)*Math.cos(imaginario),sinh(real)*Math.sin(imaginario));
    }
	
    /**
     * Tangente de Z o tan(Z)
     * @return
     */
    public ComplexNumber tan() {
        return (this.sin()).divide(this.cos());
    }

    
    public ComplexNumber negativo(){
    	return new ComplexNumber(real*-1,imaginario*-1);
    }
	
	/**
	 * Implementando Teorema de Moivre
	 * z^n = r^n*(cos(n*rho) + i sin(n*rho))
	 * donde r = |z| es el módulo y rho es el argumento de z.
	 * @param n valor a elevar
	 * @return nuevo numero complejo.
	 */
	public ComplexNumber eleva(double n){
		double r = this.getModulo();
		double rho = this.getArgumento();
		double aux1 = Math.pow(r, n);
		double aux2= n*rho;
		double real= aux1*(Math.cos(aux2));
		double img = aux1*Math.sin(aux2);
		return new ComplexNumber(real,img);
	}
	
	/**
	 * Para mejorar la eficiencia version comun sin decimales de elevar
	 * @return
	 */
	public ComplexNumber eleva(int n){
		ComplexNumber z = new ComplexNumber();
		if(n!=0){
			z = this;
			for(int j=1; j<n; j++)
				z= z.multiplica(this);
		}
		return z;
	}
	
	public ComplexNumber eleva(Double n){
		double n2 = n.doubleValue();
		return eleva(n2);
		
	}
	//otros
	/**
	 * Metodo sobrescrito para mostrar la representacion tradicional<br>
	 * de un numero complejo.<br>
	 * parte Real + parte imaginaria + i
	 */
	@Override
	public String toString() {
		return this.real+" + "+this.imaginario+"i";
	}

	/**
	 * Compara con otro Complejo comparando solo sus valores.
	 * @param obj Object a comparar
	 * @return boolean es igual
	 */
	public boolean equalsM(Object obj) {
		if(obj.getClass().equals(ComplexNumber.class)){
			ComplexNumber aux = (ComplexNumber) obj;
			return (aux.getReal()==this.real && aux.getImaginario()==this.imaginario);
		}
		return false;
	}

	@Override
	public int compareTo(Object o) {
		if(o.getClass().equals(ComplexNumber.class)){
			ComplexNumber aux = (ComplexNumber) o;
			if (aux.getReal()==this.real && aux.getImaginario()==this.imaginario){
				return 0;
			}
		}
		return -1;
	}
}
