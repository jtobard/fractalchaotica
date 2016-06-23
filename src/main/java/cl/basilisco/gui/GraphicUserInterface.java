package cl.basilisco.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import cl.basilisco.core.ComplexNumber;
import cl.basilisco.core.Julia;



/**
 * @author Jaime Tobar
 * 
 * Esta es una Ventana que no hace nada mas que recoger eventos <br>
 * no necesita mayor explicacion
 */
public class GraphicUserInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackground = null;
	private JPanel pnlOptions = null;
	private JPanel panel1 = null;
	private JPanel panel2 = null;
	private JPanel pnlColor = null;
	private JPanel pnlFormula = null;
	private MyCanvas pnlCanvas = null;
	private JButton btnZoom = null;
	private JButton btnReset = null;
	private JButton btnSwitchColor = null;
	private JButton btnRefresh = null;
	private JButton btnGivemeNumber = null;
	private JButton btnSave = null;
	private JLabel lblZ = null;
	private JTextField txtZr = null;
	private JLabel lblPlus = null;
	private JTextField txtZi = null;
	private JLabel lbli = null;
	private JComboBox cmbSelectFormula = null;
	private JTextField txtR = null;
	private JTextField txtG = null;
	private JTextField txtB = null;
	private JButton btnSetColor = null;
	private JButton btnSetColorHelp = null;
	private JTextField txtFuncHeader = null;
	private JTextField txtFuncBody = null;
	private JButton btnGo = null;
	private JButton btnFuncHelp = null;
	
	
	
	/**
	 * This is the default constructor
	 */
	public GraphicUserInterface() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getPnlBackground());
		this.setTitle("Fractal Chaotica");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlBackground() {
		if (pnlBackground == null) {
			pnlBackground = new JPanel();
			pnlBackground.setBackground(Color.BLACK);
			pnlBackground.setLayout(new BorderLayout());
			pnlBackground.add(getPnlOptions(), BorderLayout.SOUTH);
			pnlBackground.add(getPnlCanvas(), BorderLayout.CENTER);
			
		}
		return pnlBackground;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlOptions() {
		if (pnlOptions == null) {
			pnlOptions = new JPanel();
			pnlOptions.setPreferredSize(new Dimension(0,80));
			pnlOptions.setLayout(new BoxLayout(getPnlOptions(), BoxLayout.Y_AXIS));
			pnlOptions.add(getPanel1(), null);
			pnlOptions.add(getPanel2(), null);
			pnlOptions.add(getPnlColor(), null);
			//pnlOptions.add(getPnlFormula(), null);
		}
		return pnlOptions;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private MyCanvas getPnlCanvas() {
		if (pnlCanvas == null) {
			pnlCanvas = new MyCanvas();
			pnlCanvas.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getButton()==MouseEvent.BUTTON2)
						pnlCanvas.desplaza(e.getX(), e.getY());
						
				}
				@Override
				public void mouseReleased(MouseEvent e) {
						pnlCanvas.mouse(e.getX(), e.getY(),2);
				}
				@Override
				public void mousePressed(MouseEvent e) {
						pnlCanvas.mouse(e.getX(), e.getY(),0);
				}
			});
			pnlCanvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
						pnlCanvas.mouse(e.getX(), e.getY(),1);
				}
			});
			pnlCanvas.addComponentListener( new java.awt.event.ComponentAdapter(){
				@Override
				public void componentResized(ComponentEvent e) {
					if(cmbSelectFormula.getSelectedIndex()!=0)
						pnlCanvas.pinta();
				}
				
				}		
			);
		}
		return pnlCanvas;
	}
	
	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel1() {
		if (panel1 == null) {
			panel1 = new JPanel();
			panel1.setPreferredSize(new Dimension(0,20));
			panel1.setLayout(new BoxLayout(getPanel1(), BoxLayout.X_AXIS));
			panel1.add(getBtnZoom(), null);
			panel1.add(getBtnReset(), null);
			panel1.add(getBtnSwitchColor(), null);
			panel1.add(getBtnSave(), null);
		}
		return panel1;
	}
	
	/**
	 * This method initializes jPanel3
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel2() {
		if (panel2 == null) {
			panel2 = new JPanel();
			panel2.setPreferredSize(new Dimension(0,20));
			panel2.setLayout(new BoxLayout(getPanel2(), BoxLayout.X_AXIS));
			panel2.add(Box.createRigidArea(new Dimension(20,20)));
			panel2.add(getJLabel(),null);
			panel2.add(getJTextField(), null);
			panel2.add(getJLabel2(),null);
			panel2.add(getJTextField1(), null);
			panel2.add(getJLabel3(),null);
			panel2.add(getBtnRefresh(), null);
			panel2.add(getBtnGivemeNumber(), null);
			panel2.add(getCmbSelectFormula(), null);
		}
		return panel2;
	}
	
	/**
	 * This method initializes jPanel4
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlColor() {
		if (pnlColor == null) {
			pnlColor = new JPanel();
			pnlColor.setPreferredSize(new Dimension(0,20));
			pnlColor.setLayout(new BoxLayout(getPnlColor(), BoxLayout.X_AXIS));
			pnlColor.add(new JLabel("Color"));
			pnlColor.add(getJTxtR());
			pnlColor.add(new JLabel("R"));
			pnlColor.add(getJTxtG());
			pnlColor.add(new JLabel("G"));
			pnlColor.add(getJTxtB());
			pnlColor.add(new JLabel("B"));
			pnlColor.add(getBtnSetColor());
			pnlColor.add(getBtnSetColorHelp());
			
		}
		return pnlColor;
	}

	/**
	 * This method initializes jPanel5
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlFormula() {
		if (pnlFormula == null) {
			pnlFormula = new JPanel();
			pnlFormula.setEnabled(false);
			pnlFormula.setPreferredSize(new Dimension(0,20));
			pnlFormula.setLayout(new BoxLayout(getPnlFormula(), BoxLayout.X_AXIS));
			pnlFormula.add(new JLabel("F("));
			pnlFormula.add(getJTxtCabecera());
			pnlFormula.add(new JLabel(")="));
			pnlFormula.add(getJTxtFormula());
			pnlFormula.add(getBtnGo());
			pnlFormula.add(getBtnFuncHelp());
		}
		return pnlFormula;
	}
	
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnZoom() {
		if (btnZoom == null) {
			btnZoom = new JButton();
			btnZoom.setText("Ver area seleccionada");
			btnZoom.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pnlCanvas.acerca_area();
				}
			});
		}
		return btnZoom;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton();
			btnReset.setText("Reset");
			btnReset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pnlCanvas.resetea();
				}
			});
		}
		return btnReset;
	}
	
	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSwitchColor() {
		if (btnSwitchColor == null) {
			btnSwitchColor = new JButton();
			btnSwitchColor.setText("Version Grises");
			btnSwitchColor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pnlCanvas.color_bn();
					if (btnSwitchColor.getText().equals("Version Grises"))
						btnSwitchColor.setText("Version Color");
					else
						btnSwitchColor.setText("Version Grises");
				}
			});
		}
		return btnSwitchColor;
	}
	
	/**
	 * This method initializes jButton3
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRefresh() {
		if (btnRefresh == null) {
			btnRefresh = new JButton();
			btnRefresh.setText("Actualizar");
			btnRefresh.setEnabled(false);
			btnRefresh.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						double r = Double.parseDouble(txtZr.getText());
						double i = Double.parseDouble(txtZi.getText());
						pnlCanvas.setComplejo(r,i);
						pnlCanvas.pinta();
						
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(pnlCanvas, "No esta ingresando un numero valido", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnRefresh;
	}
	
	/**
	 * This method initializes jButton4
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGivemeNumber() {
		if (btnGivemeNumber == null) {
			btnGivemeNumber = new JButton();
			btnGivemeNumber.setText("Obtener numero");
			btnGivemeNumber.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ComplexNumber[] val = Julia.valores();
					int valor = (int)(Math.random()*val.length);
					double r = val[valor].getReal();
					double i = val[valor].getImaginario();
					txtZr.setText(r+"");
					txtZi.setText(i+"");
				}
			});
		}
		return btnGivemeNumber;
	}
	
	/**
	 * This method initializes jButton5
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave= new JButton();
			btnSave.setText("Guardar Imagen");
			btnSave.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String path = "";
					JFileChooser fc = new JFileChooser();
					fc.setDialogTitle("Guardar imagen");
					FileFilter jpegFilter = new ExtensionFileFilter("*.png", "png" );
					fc.setFileFilter(jpegFilter);
					if(fc.showSaveDialog(pnlCanvas)== JFileChooser.APPROVE_OPTION){
						path = fc.getSelectedFile().getAbsolutePath();
						pnlCanvas.guardar(path);
					}
				}
			});
		}
		return btnSave;
	}
	/**
	 * This method initializes jbtncolor
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSetColor() {
		if (btnSetColor == null) {
			btnSetColor= new JButton();
			btnSetColor.setText("Modificar Colores");
			btnSetColor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					float r,g,b;
					r = Float.parseFloat(txtR.getText());
					g = Float.parseFloat(txtG.getText());
					b = Float.parseFloat(txtB.getText());
					pnlCanvas.setColor(r, g, b);
					pnlCanvas.pinta();
				}
			});
		}
		return btnSetColor;
	}
	
	/**
	 * This method initializes jbtncolorAyuda
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSetColorHelp() {
		if (btnSetColorHelp == null) {
			btnSetColorHelp= new JButton();
			btnSetColorHelp.setText("Que es esto");
			btnSetColorHelp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String msj = "Ingrese solo numeros positivos, ademas si ingresa 0\n" +
							"ese valor sera reemplazado por la magnitud que alcance el punto.";
					JOptionPane.showMessageDialog(null, msj, "Ayuda", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return btnSetColorHelp;
	}
	
	/**
	 * This method initializes jbtncolor
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGo() {
		if (btnGo == null) {
			btnGo= new JButton();
			btnGo.setText("Ver Resultado");
			btnGo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String formula = txtFuncBody.getText();
					String cabecera=  txtFuncHeader.getText();
					double r = Double.parseDouble(txtZr.getText());
					double i = Double.parseDouble(txtZi.getText());
					pnlCanvas.setNuevaFormula(cabecera, formula, r, i);
					pnlCanvas.pinta();
				}
			});
		}
		return btnGo;
	}
	
	/**
	 * This method initializes jbtncolorAyuda
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnFuncHelp() {
		if (btnFuncHelp == null) {
			btnFuncHelp= new JButton();
			btnFuncHelp.setText("Que es esto");
			btnFuncHelp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String msj = "------Formula----------------"+ "\n"+
					"Numeros complejos:"+"\n"+
					"Z = punto eje"+"\n"+
					"C = constante escrita en campos"+"\n"+
					"M = Numero complejo q parte siempre en cero (como Mandelbrot)"+"\n"+
					"x = Z.real"+"\n"+
					"y = Z.imaginario"+"\n"+
					"Operaciones:"+"\n"+
					//"( ) = parentesis"+"\n"+
					"+ , -, *, / = suma, resta, multiplicacion, division solo con complejos/"+"\n"+
					//"e = exp(Z) = e^Z"+"\n"+
					"^ = eleva solo n° reales"+"\n"+
					//"l = log(Z) = logaritmo"+"\n"+
					//"r = sqrt(Z) = raiz de Z"+"\n"+
					//"s = sin(Z) = seno de Z"+"\n"+
					//"c = cos(Z) = coseno de Z"+"\n"+
					//"t = tan(Z) = tangente"+
					"";
					JOptionPane.showMessageDialog(null, msj, "Ayuda", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return btnFuncHelp;
	}
	
	/**
	 * This method initializes jtextfield	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (txtZr == null) {
			txtZr = new JTextField();
			txtZr.setText("-1");
			txtZr.setEnabled(false);
		}
		return txtZr;
	}
	
	/**
	 * This method initializes jtextfield	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (txtZi == null) {
			txtZi = new JTextField();
			txtZi.setText("0");
			txtZi.setEnabled(false);
		}
		return txtZi;
	}
	
	/**
	 * This method initializes jlabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel() {
		if (lblZ == null) {
			lblZ = new JLabel();
			lblZ.setText("Z=");
		}
		return lblZ;
	}
	/**
	 * This method initializes jlabel2	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel2() {
		if (lblPlus == null) {
			lblPlus = new JLabel();
			lblPlus.setText("+");
		}
		return lblPlus;
	}
	/**
	 * This method initializes jlabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel3() {
		if (lbli == null) {
			lbli = new JLabel();
			lbli.setText("i");
		}
		return lbli;
	}
	
	/**
	 * This method initializes jcombo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbSelectFormula() {
		if (cmbSelectFormula == null) {
			String[] fractales={"Seleccione Formula","Mandelbrot","Julia","Mandelbrot2",
					"Lambda","Biomorph"
					//,"Escribir"
			};
			cmbSelectFormula = new JComboBox(fractales);
			cmbSelectFormula.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					float cr,cg,cb;
					try{
					cr = Float.parseFloat(txtR.getText());
					cg = Float.parseFloat(txtG.getText());
					cb = Float.parseFloat(txtB.getText());
					if(cr <0 || cg <0 || cb <0)
						throw new NumberFormatException();
					pnlCanvas.setColor(cr, cg, cb);
					}catch(NumberFormatException exc){
						JOptionPane.showMessageDialog(null, "Ingrese un numero correcto", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					int s =cmbSelectFormula.getSelectedIndex();
					switch (s) {
					case 1://mandelbrot
						pnlCanvas.setMandelbrot();
						txtZr.setEnabled(false);
						txtZi.setEnabled(false);
						btnRefresh.setEnabled(false);
						bloqueaFormula(true);
						pnlCanvas.pinta();
						break;
					case 2:
						try{
							double r = Double.parseDouble(txtZr.getText());
							double i = Double.parseDouble(txtZi.getText());
							pnlCanvas.setJulia(r,i);
							txtZr.setEnabled(true);
							txtZi.setEnabled(true);
							btnRefresh.setEnabled(true);
							bloqueaFormula(true);
							pnlCanvas.pinta();
							
						}catch(NumberFormatException ex){
							JOptionPane.showMessageDialog(pnlCanvas, "No esta ingresando un numero valido", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
						}
						break;
					case 3://mandelbrot2
						pnlCanvas.setMandelbrot2();
						txtZr.setEnabled(false);
						txtZi.setEnabled(false);
						btnRefresh.setEnabled(false);
						bloqueaFormula(true);
						pnlCanvas.pinta();
						break;
					case 4://lambda
						try{
							double r = Double.parseDouble(txtZr.getText());
							double i = Double.parseDouble(txtZi.getText());
							pnlCanvas.setLambda(r,i);
							txtZr.setEnabled(true);
							txtZi.setEnabled(true);
							btnRefresh.setEnabled(true);
							bloqueaFormula(true);
							pnlCanvas.pinta();
							
						}catch(NumberFormatException ex){
							JOptionPane.showMessageDialog(pnlCanvas, "No esta ingresando un numero valido", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
						}
						break;
					case 5://Biomorph
						pnlCanvas.setBiomorph();
						txtZr.setEnabled(false);
						txtZi.setEnabled(false);
						btnRefresh.setEnabled(false);
						bloqueaFormula(true);
						pnlCanvas.pinta();
						break;
					case 6:
						txtZr.setEnabled(true);
						txtZi.setEnabled(true);
						btnRefresh.setEnabled(true);
						bloqueaFormula(false);
						break;
					default://no selecciono nada, no hacemos nada
						break;
					}
					
				}
			});
		}
			
		return cmbSelectFormula;
	}
	
	/**
	 * This method initializes jtxtr	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTxtR() {
		if (txtR == null) {
			txtR = new JTextField();
			txtR.setText("1");
		}
		return txtR;
	}
	/**
	 * This method initializes jtxtg	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTxtG() {
		if (txtG == null) {
			txtG = new JTextField();
			txtG.setText("1");
		}
		return txtG;
	}
	/**
	 * This method initializes jtxtb	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTxtB() {
		if (txtB == null) {
			txtB = new JTextField();
			txtB.setText("1");
		}
		return txtB;
	}
	
	/**
	 * This method initializes jtxtformula
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTxtCabecera() {
		if (txtFuncHeader == null) {
			txtFuncHeader = new JTextField();
			txtFuncHeader.setText("Z");
			txtFuncHeader.setEnabled(false);
		}
		return txtFuncHeader;
	}
	
	/**
	 * This method initializes jtxtformula
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTxtFormula() {
		if (txtFuncBody == null) {
			txtFuncBody = new JTextField();
			txtFuncBody.setText("0");
			txtFuncBody.setEnabled(false);
		}
		return txtFuncBody;
	}
	
	private void bloqueaFormula(boolean bloquea){
		/*bloquea = !bloquea;
		pnlFormula.setEnabled(bloquea);
		txtFuncHeader.setEnabled(bloquea);
		txtFuncBody.setEnabled(bloquea);
		btnGo.setEnabled(bloquea);
		btnFuncHelp.setEnabled(bloquea);*/
	}
	
	/**
	 * generic filefilter
	 *
	 */
	class ExtensionFileFilter extends FileFilter {
		  String description;
		  String extensions[];

		  public ExtensionFileFilter(String description, String extension) {
		    this(description, new String[] { extension });
		  }

		  public ExtensionFileFilter(String description, String extensions[]) {
		    if (description == null) {
		      this.description = extensions[0] + "{ " + extensions.length + "} ";
		    } else {
		      this.description = description;
		    }
		    this.extensions = (String[]) extensions.clone();
		    toLower(this.extensions);
		  }

		  private void toLower(String array[]) {
		    for (int i = 0, n = array.length; i < n; i++) {
		      array[i] = array[i].toLowerCase();
		    }
		  }

		  public String getDescription() {
		    return description;
		  }

		  public boolean accept(File file) {
		    if (file.isDirectory()) {
		      return true;
		    } else {
		      String path = file.getAbsolutePath().toLowerCase();
		      for (int i = 0, n = extensions.length; i < n; i++) {
		        String extension = extensions[i];
		        if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
		          return true;
		        }
		      }
		    }
		    return false;
		  }
		}

}
