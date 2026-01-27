package cl.basilisco.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import cl.basilisco.core.ComplexNumber;
import cl.basilisco.core.Julia;

/**
 * @author Jaime Tobar
 * 
 *         This corresponds to a Window that only collects events.
 */
public class GraphicUserInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBackground = null;
	private JPanel pnlOptions = null;
	private JPanel panel1 = null;
	private JPanel panel2 = null;
	private JPanel pnlColor = null;
	private MyCanvas pnlCanvas = null;

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
	private JComboBox<String> cmbSelectFormula = null;
	private JTextField txtR = null;
	private JTextField txtG = null;
	private JTextField txtB = null;
	private JButton btnSetColor = null;
	private JButton btnSetColorHelp = null;
	/*
	 * Removed unused generic file filter class if strictly needed, but kept for now
	 * as per instructions to only remove specific unused items.
	 * Actually, I will just remove the noted unused fields and method.
	 */

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
			pnlOptions.setPreferredSize(new Dimension(0, 80));
			pnlOptions.setLayout(new BoxLayout(getPnlOptions(), BoxLayout.Y_AXIS));
			pnlOptions.add(getPanel1(), null);
			pnlOptions.add(getPanel2(), null);
			pnlOptions.add(getPnlColor(), null);
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
					if (e.getButton() == MouseEvent.BUTTON2)
						pnlCanvas.pan(e.getX(), e.getY());

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					pnlCanvas.handleMouse(e.getX(), e.getY(), 2);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					pnlCanvas.handleMouse(e.getX(), e.getY(), 0);
				}
			});
			pnlCanvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					pnlCanvas.handleMouse(e.getX(), e.getY(), 1);
				}
			});
			pnlCanvas.addComponentListener(new java.awt.event.ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					if (cmbSelectFormula.getSelectedIndex() != 0)
						pnlCanvas.paintFractal();
				}

			});
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
			panel1.setPreferredSize(new Dimension(0, 20));
			panel1.setLayout(new BoxLayout(getPanel1(), BoxLayout.X_AXIS));

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
			panel2.setPreferredSize(new Dimension(0, 20));
			panel2.setLayout(new BoxLayout(getPanel2(), BoxLayout.X_AXIS));
			panel2.add(Box.createRigidArea(new Dimension(20, 20)));
			panel2.add(getJLabel(), null);
			panel2.add(getJTextField(), null);
			panel2.add(getJLabel2(), null);
			panel2.add(getJTextField1(), null);
			panel2.add(getJLabel3(), null);
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
			pnlColor.setPreferredSize(new Dimension(0, 20));
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

	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton();
			btnReset.setText("Reset");
			btnReset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pnlCanvas.reset();
				}
			});
		}
		return btnReset;
	}

	private JButton getBtnSwitchColor() {
		if (btnSwitchColor == null) {
			btnSwitchColor = new JButton();
			btnSwitchColor.setText("Grayscale Version");
			btnSwitchColor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pnlCanvas.toggleColorMode();
					if (btnSwitchColor.getText().equals("Grayscale Version"))
						btnSwitchColor.setText("Color Version");
					else
						btnSwitchColor.setText("Grayscale Version");
				}
			});
		}
		return btnSwitchColor;
	}

	private JButton getBtnRefresh() {
		if (btnRefresh == null) {
			btnRefresh = new JButton();
			btnRefresh.setText("Refresh");
			btnRefresh.setEnabled(false);
			btnRefresh.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						double r = Double.parseDouble(txtZr.getText());
						double i = Double.parseDouble(txtZi.getText());
						pnlCanvas.setComplexConstant(r, i);
						pnlCanvas.paintFractal();

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(pnlCanvas, "You are not entering a valid number",
								"Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnRefresh;
	}

	private JButton getBtnGivemeNumber() {
		if (btnGivemeNumber == null) {
			btnGivemeNumber = new JButton();
			btnGivemeNumber.setText("Get Number");
			btnGivemeNumber.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ComplexNumber[] val = Julia.famousValues();
					int valor = (int) (Math.random() * val.length);
					double r = val[valor].getReal();
					double i = val[valor].getImaginary();
					txtZr.setText(r + "");
					txtZi.setText(i + "");
				}
			});
		}
		return btnGivemeNumber;
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton();
			btnSave.setText("Save Image");
			btnSave.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser fc = new JFileChooser();
					fc.setDialogTitle("Save image");
					FileFilter jpegFilter = new ExtensionFileFilter("*.png", "png");
					fc.setFileFilter(jpegFilter);
					if (fc.showSaveDialog(pnlCanvas) == JFileChooser.APPROVE_OPTION) {
						String path = fc.getSelectedFile().getAbsolutePath();
						pnlCanvas.saveImage(path);
					}
				}
			});
		}
		return btnSave;
	}

	private JButton getBtnSetColor() {
		if (btnSetColor == null) {
			btnSetColor = new JButton();
			btnSetColor.setText("Modify Colors");
			btnSetColor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					float r, g, b;
					r = Float.parseFloat(txtR.getText());
					g = Float.parseFloat(txtG.getText());
					b = Float.parseFloat(txtB.getText());
					pnlCanvas.setColors(r, g, b);
					pnlCanvas.paintFractal();
				}
			});
		}
		return btnSetColor;
	}

	private JButton getBtnSetColorHelp() {
		if (btnSetColorHelp == null) {
			btnSetColorHelp = new JButton();
			btnSetColorHelp.setText("What is this");
			btnSetColorHelp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String msj = "Enter only positive numbers. Also, if you enter 0\n" +
							"that value will be replaced by the magnitude reached by the point.";
					JOptionPane.showMessageDialog(null, msj, "Help", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return btnSetColorHelp;
	}

	private JTextField getJTextField() {
		if (txtZr == null) {
			txtZr = new JTextField();
			txtZr.setText("-1");
			txtZr.setEnabled(false);
		}
		return txtZr;
	}

	private JTextField getJTextField1() {
		if (txtZi == null) {
			txtZi = new JTextField();
			txtZi.setText("0");
			txtZi.setEnabled(false);
		}
		return txtZi;
	}

	private JLabel getJLabel() {
		if (lblZ == null) {
			lblZ = new JLabel();
			lblZ.setText("Z=");
		}
		return lblZ;
	}

	private JLabel getJLabel2() {
		if (lblPlus == null) {
			lblPlus = new JLabel();
			lblPlus.setText("+");
		}
		return lblPlus;
	}

	private JLabel getJLabel3() {
		if (lbli == null) {
			lbli = new JLabel();
			lbli.setText("i");
		}
		return lbli;
	}

	private JComboBox<String> getCmbSelectFormula() {
		if (cmbSelectFormula == null) {
			String[] fractales = { "Select Formula", "Mandelbrot", "Julia", "Mandelbrot2",
					"Lambda", "Biomorph"
			};
			cmbSelectFormula = new JComboBox<>(fractales);
			cmbSelectFormula.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					float cr, cg, cb;
					try {
						cr = Float.parseFloat(txtR.getText());
						cg = Float.parseFloat(txtG.getText());
						cb = Float.parseFloat(txtB.getText());
						if (cr < 0 || cg < 0 || cb < 0)
							throw new NumberFormatException();
						pnlCanvas.setColors(cr, cg, cb);
					} catch (NumberFormatException exc) {
						JOptionPane.showMessageDialog(null, "Please enter a correct number", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					int s = cmbSelectFormula.getSelectedIndex();
					if (s > 0)
						pnlCanvas.resetCoords();
					switch (s) {
						case 1:// mandelbrot
							pnlCanvas.setMandelbrot();
							txtZr.setEnabled(false);
							txtZi.setEnabled(false);
							btnRefresh.setEnabled(false);
							pnlCanvas.paintFractal();
							break;
						case 2:
							try {
								double r = Double.parseDouble(txtZr.getText());
								double i = Double.parseDouble(txtZi.getText());
								pnlCanvas.setJulia(r, i);
								txtZr.setEnabled(true);
								txtZi.setEnabled(true);
								btnRefresh.setEnabled(true);
								pnlCanvas.paintFractal();

							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(pnlCanvas, "No esta ingresando un numero valido",
										"Error de ingreso", JOptionPane.ERROR_MESSAGE);
							}
							break;
						case 3:// mandelbrot2
							pnlCanvas.setMandelbrot2();
							txtZr.setEnabled(false);
							txtZi.setEnabled(false);
							btnRefresh.setEnabled(false);
							pnlCanvas.paintFractal();
							break;
						case 4:// lambda
							try {
								double r = Double.parseDouble(txtZr.getText());
								double i = Double.parseDouble(txtZi.getText());
								pnlCanvas.setLambda(r, i);
								txtZr.setEnabled(true);
								txtZi.setEnabled(true);
								btnRefresh.setEnabled(true);
								pnlCanvas.paintFractal();

							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(pnlCanvas, "No esta ingresando un numero valido",
										"Error de ingreso", JOptionPane.ERROR_MESSAGE);
							}
							break;
						case 5:// Biomorph
							pnlCanvas.setBiomorph();
							txtZr.setEnabled(false);
							txtZi.setEnabled(false);
							btnRefresh.setEnabled(false);
							pnlCanvas.paintFractal();
							break;
						default:// no selecciono nada, no hacemos nada
							break;
					}

				}
			});
		}

		return cmbSelectFormula;
	}

	private JTextField getJTxtR() {
		if (txtR == null) {
			txtR = new JTextField();
			txtR.setText("1");
		}
		return txtR;
	}

	private JTextField getJTxtG() {
		if (txtG == null) {
			txtG = new JTextField();
			txtG.setText("1");
		}
		return txtG;
	}

	private JTextField getJTxtB() {
		if (txtB == null) {
			txtB = new JTextField();
			txtB.setText("1");
		}
		return txtB;
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
