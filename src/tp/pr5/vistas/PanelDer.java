package tp.pr5.vistas;

import java.awt.*;


import javax.swing.*;

import tp.pr5.control.ControladorGUI;

public class PanelDer extends JPanel {

	private JPanel norte;
	private JPanel sur;
	private ControladorGUI co;
	private JFrame window;
	
	/**
	 * Constructor de la clase
	 * @param c
	 * @param ventana
	 */
	public PanelDer(ControladorGUI c, JFrame ventana){
		this.co = c;
		this.window = ventana;
		
		creaPaneles();
		
		this.setLayout(new BorderLayout());
				
		anadirPaneles();
				
		this.setVisible(true);
	}
	
	/**
	 * Crea los paneles
	 */
	private void creaPaneles(){
		this.norte = new PanelDerNorte(this.co, this.window);
		this.sur = new PanelDerSur();
	}
	
	/**
	 * Añade los paneles a este panel
	 */
	private void anadirPaneles(){
		this.add(norte, BorderLayout.NORTH);
		this.add(sur, BorderLayout.SOUTH);
	}
	
}
