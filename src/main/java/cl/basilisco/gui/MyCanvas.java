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
 * Panel para pintar nuestro fractal.
 * 
 * @author Jaime Tobar Diaz, jaime.tobar.diaz@gmail.com
 *
 */
public class MyCanvas extends JPanel {
	
	//variables para graficar el plano
	private int largo = 600; //largo de la ventana
	private double relPixPunto;//relacion Pixel - Punto en la recta
	private double esqx=-3, esqy=3;//esquinas en coordenadas
	private Image pintar; //para no ver el efecto pintura y guardar la imagen.
	
	//variables para el cuadrado
	private int xcuboi, ycuboi,xcubof,ycubof;
	//es blanco y negro?
	private boolean bn;
	
	//que fractal usa
	//0=Mandelbrot, 1=Julia
	private int fractal;
	private int tope=255;//numero de veces que iteraran los numeros
	
	//factor de Julia y Otros
	private ComplexNumber constante;
	
	//formula de usuario
	private String cabecera;
	private Object[] formulafinal;
	
	//colores
	float r=8;
	float g=2;
	float b=3;
	
	private static final long serialVersionUID = -8016335228852792096L;
	
	/**
	 * Constructor, inicializa las variables.
	 */
	public MyCanvas(){
		this.relPixPunto = (esqy*2)/this.getPreferredSize().width;
		this.bn=false;
		super.setDoubleBuffered(true);
		fractal=0;
	}
	
	public void acerca_area(){
		if(xcubof>xcuboi+5){//si el cubo se ha creado con intencion y no ha sido un pequeño movimiento de mouse.
			double esqxisel, esqyisel,esqxfsel;
			
			esqxisel=xcuboi;
			esqxisel = esqxisel*relPixPunto;
			esqxisel = esqx + esqxisel;
			
			esqyisel=ycuboi;
			esqyisel = esqyisel*relPixPunto;
			esqyisel = esqy - esqyisel;
			
			esqxfsel=xcubof;
			esqxfsel = esqxfsel*relPixPunto;
			esqxfsel = esqx + esqxfsel;
			
			esqx = esqxisel;
			esqy = esqyisel;
			relPixPunto = (esqxfsel-esqxisel)/this.getSize().width;
			this.pinta();
			
		}
	}
	
	/**
	 * Vuelve el fractal al estado inicial.
	 */
	public void resetea(){
			this.esqx = -3;
			this.esqy = 3;
			this.relPixPunto = (esqy*2)/this.getSize().height;
		this.pinta();
	}
	
	/**
	 * Mueve el plano para centrar el punto.
	 * @param x punto x.
	 * @param y punto y.
	 */
	public void desplaza(int x, int y){
		this.esqx = esqx - ((this.getSize().width/2 -x)*relPixPunto);
		this.esqy = esqy - ((y - this.getSize().height/2)*relPixPunto);
		pinta();
		
	}
	
	//efecto doble buffer.
	@Override
	public void paint(Graphics g) {
		if(this.pintar!=null)
			g.drawImage(this.pintar, 0, 0, this);
		else
			g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		//super.paint(g);
	}
	
	
	/**
	 * Metodo q busca un punto (x,y) y devuelve su numero complejo<br>
	 * correspondiente en el plano actual.
	 * @param x x en el plano.
	 * @param y y en el plano.
	 * @return numero complejo q representa el punto solicitado.
	 */
	public ComplexNumber coordenada(int x, int y){
		ComplexNumber c ;
		
		double cx=x;
		cx = cx*relPixPunto;
		cx = esqx + cx;
		
		double cy=y;
		cy = cy*relPixPunto;
		cy = esqy - cy;
		c = new ComplexNumber(cx,cy);
		return c;
	}
	
	/**
	 * Dibuja el cuadrado del mouse y setea las variables para su escalamiento.
	 * @param x x en el plano.
	 * @param y y en el plano.
	 * @param n 0 cuando el mouse baja, 1 cuando se mueve, 2 cuando se suelta.
	 */
	public void mouse(int x, int y,int n){
		if(this.pintar!=null){
			if(n==0){
				xcuboi=x;
				ycuboi=y;
				xcubof=x;
				ycubof=y;
			}
			if(n==2){
				//si no, sacamos la proporcion
				int ancho = this.getSize().width;
				int alto = this.getSize().height;
				if(x>xcuboi && y > ycuboi){//se creo un area hacia la derecha/abajo
					if((x-xcuboi)>(y-ycuboi)){//linea x es mayor q la linea y, usamos esa de referencia
						xcubof = (x);
						ycubof = ycuboi+(int)((x-xcuboi)*(float)alto/(float)ancho);
					}else{
						ycubof = y;
						xcubof = xcuboi+(int)((y-ycuboi)*(float)ancho/(float)alto);
					}
					
				}else if(x>xcuboi && y < ycuboi){//se creo un area hacia la derecha/arriba
					if((x-xcuboi)>(ycuboi-y)){//linea x es mayor q la linea y, usamos esa de referencia
						xcubof = (x);
						ycubof = ycuboi-(int)((x-xcuboi)*(float)alto/(float)ancho);
					}else{
						ycubof = y;
						xcubof = xcuboi+(int)((ycuboi-y)*(float)ancho/(float)alto);
					}
				}else if(x<xcuboi && y > ycuboi){//se creo un area hacia la izquierda/abajo
					if((xcuboi-x)>(y-ycuboi)){//linea x es mayor q la linea y, usamos esa de referencia
						xcubof = (x);
						ycubof = ycuboi+(int)((xcuboi-x)*(float)alto/(float)ancho);
					}else{
						ycubof = y;
						xcubof = xcuboi-(int)((y-ycuboi)*(float)ancho/(float)alto);
					}
				}else{//se creo un area hacia la izquierda/arriba
					if((xcuboi-x)>(ycuboi-y)){//linea x es mayor q la linea y, usamos esa de referencia
						xcubof = (x);
						ycubof = ycuboi-(int)((xcuboi-x)*(float)alto/(float)ancho);
					}else{
						ycubof = y;
						xcubof = xcuboi-(int)((ycuboi-y)*(float)ancho/(float)alto);
					}
				}
				
				//si selecciono el area en el sentido inverso			
				if(xcuboi>xcubof){
					int xresp = xcuboi;
					xcuboi=xcubof;
					xcubof=xresp;
				}
				if(ycuboi>ycubof){
					int yresp = ycuboi;
					ycuboi=ycubof;
					ycubof=yresp;
				}
			}
			Graphics g = this.getGraphics();
			g.drawImage(pintar, 0, 0, this);
			g.setColor(Color.YELLOW);
			if(n==2){
				g.drawLine(xcuboi, ycuboi, xcubof, ycuboi);
				g.drawLine(xcuboi, ycuboi, xcuboi, ycubof);
				g.drawLine(xcubof, ycuboi, xcubof, ycubof);
				g.drawLine(xcuboi, ycubof, xcubof, ycubof);
			}else{
				g.drawLine(xcuboi, ycuboi, x, ycuboi);
				g.drawLine(xcuboi, ycuboi, xcuboi, y);
				g.drawLine(x, ycuboi, x, y);
				g.drawLine(xcuboi, y, x, y);
			}
		}
	}
	
	/**
	 * Setea el grafico a blanco y negro binario.
	 */
	public void color_bn(){
		this.bn=!this.bn;
		this.pinta();
	}
	
	public void guardar(String path){
		BufferedImage bi = new BufferedImage(this.getSize().width,this.getSize().height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(this.pintar, 0, 0, this);
		g2.dispose();
		
		if(!path.endsWith("png") && !path.endsWith("PNG"))
			path=path+".png";
		try {
			ImageIO.write(bi, "PNG", new File(path));
		} catch (IOException e) {
			System.out.println("error al guardar");
		}
	}
	
	/**
	 * Setea el grafico a la formula de Mandelbrot.
	 */
	public void setMandelbrot(){
		this.fractal=1;
	}
	
	
	/**
	 * Setea el grafico a la formula de Julia.
	 * @param r parte real del numero a usar.
	 * @param i parte imaginaria del numero a usar.
	 */
	public void setJulia(double r,double i){
		this.fractal=2;
		this.constante= new ComplexNumber(r,i);
	}
	
	/**
	 * Otras formulas.
	 */
	public void setMandelbrot2(){
		this.fractal=3;
	}
	
	public void setLambda(double r,double i){
		this.fractal=4;
		this.constante= new ComplexNumber(r,i);
	}
	
	public void setBiomorph(){
		this.fractal=5;
	}
	public void setComplejo(double r,double i){
		ComplexNumber aux = this.constante;
		this.constante= new ComplexNumber(r,i);
		if(this.fractal==6){//si tenemos q cambiar la formula
			
			for (int j = 0; j < formulafinal.length; j++)
				if(formulafinal[j]!=null &&formulafinal[j].equals(aux))
					formulafinal[j]=this.constante;
		}
			
	}
	
	public void setColor(float r, float g, float b){
		this.r=r;
		this.g=g;
		this.b=b;
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension d = new Dimension(largo,largo);
		return d;
	}
	
	/**
	 * el metodo que pinta el fractal sobre una imagen.
	 */
	public void pinta(){
		//JOptionPane.showMessageDialog(this, "Calculando, esta operacion puede demorar", "Cargando...", JOptionPane.INFORMATION_MESSAGE);
		this.pintar = createImage(this.getSize().width, this.getSize().height);
		Graphics2D db = (Graphics2D)this.getGraphics();//pintar.getGraphics();
		Graphics2D db2 = (Graphics2D)this.pintar.getGraphics();//pintar.getGraphics();
		//x e y son los pixeles
		int x, y;
		//a y b recorren el eje
		double a,b;
		Color color = null;
		//mientras x recorre el eje x del lienzo, a entrega el valor real de este.
		int xfin = this.getSize().width;
		int yfin = this.getSize().height;
		for (x = 0,  a=esqx; x < xfin; x++,a = a+relPixPunto) {
			//mientras y recorre el eje y del lienzo, b entrega el valor imaginario de este.
			for (y = 0, b=esqy; y < yfin; y++,b = b-relPixPunto) {
				//creamos el numero complejo de esta coordenada (x,y)
				ComplexNumber c = new ComplexNumber(a,b);
				
				//dependiendo la formula fractal q usemos
				switch (this.fractal) {
				case 1:
					color = Mandelbrot.itera(c, tope,bn,this.r,this.g,this.b); //recogemos el color resultante de la formula
					break;
				case 2: //julia
					color = Julia.itera(c,constante, tope,bn,this.r,this.g,this.b);
					break;
				case 3: //madelbrot2
					color = Fractal.itera(c, tope,bn,this.r,this.g,this.b);
					break;
				case 4: //lambda
					color = Lambda.itera(c,constante, tope,bn,this.r,this.g,this.b);
					break;
				case 5: //biomorph
					color = Biomorph.itera(c,tope,bn,this.r,this.g,this.b);
					break;
				}
				
				db.setColor(color);// y pintamos el pixel
				db.drawLine(x, y, x, y);
				db2.setColor(color);// y pintamos el pixel
				db2.drawLine(x, y, x, y);
			}
		}
		//pintamos la imagen sobre el lienzo
		
		//this.repaint();
	}

}
