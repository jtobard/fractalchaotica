package cl.basilisco.run;

import javax.swing.JFrame;

import cl.basilisco.gui.GraphicUserInterface;
/**
 *   Programa para graficar fractales.
 *   Copyright (C) 2008  Jaime Tobar Diaz
 *
 *    Este programa es software libre: usted puede redistribuirlo y/o modificarlo 
 *    bajo los términos de la Licencia Pública General GNU publicada 
 *    por la Fundación para el Software Libre, ya sea la versión 3 
 *    de la Licencia, o (a su elección) cualquier versión posterior.
 *
 *    Este programa se distribuye con la esperanza de que sea útil, pero 
 *    SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita 
 *    MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO. 
 *    Consulte los detalles de la Licencia Pública General GNU para obtener 
 *    una información más detallada. 
 *
 *    Debería haber recibido una copia de la Licencia Pública General GNU 
 *    junto a este programa. 
 *    En caso contrario, consulte <a href='http://www.gnu.org/licenses/'>http://www.gnu.org/licenses/</a>.
 *
 *
 */
public class Main {
	public static void main(String[] args) {
		GraphicUserInterface v = new GraphicUserInterface();
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.pack();
		v.setVisible(true);
	}

}
