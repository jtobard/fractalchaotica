package cl.basilisco.run;

import javax.swing.JFrame;

import cl.basilisco.gui.GraphicUserInterface;

/**
 * Program to plot fractals.
 * Copyright (C) 2008 Jaime Tobar Diaz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.
 * If not, see
 * <a href='http://www.gnu.org/licenses/'>http://www.gnu.org/licenses/</a>.
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
