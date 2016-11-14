package tp.pr5.vistas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGUI;

public class PanelDerNorte extends JPanel{
	
	private PanelPartida pPartida;
	private PanelCambioJuego pCambioJuego;
	private PanelJugadores pJugadores;
	private ControladorGUI co;
	private JFrame window;
	
	/**
	 * Constructor de la clase
	 * @param c
	 * @param ventana
	 */
	public PanelDerNorte(ControladorGUI c, JFrame ventana){
		this.co = c;
		this.window = ventana;
		
		creaPaneles();
		
		this.setLayout(new BorderLayout());
		
		anadirPaneles();
		
		this.setVisible(true);
	}
	
	/**
	 * Crea los paneles partida y cambioJuego
	 */
	private void creaPaneles(){
		pPartida = new PanelPartida(this.co);
		pCambioJuego = new PanelCambioJuego(this.co, this.window);
		pJugadores = new PanelJugadores(this.co);
	}
	
	/**
	 * Añade los paneles a este panel
	 */
	private void anadirPaneles(){
		this.add(pPartida, BorderLayout.NORTH);
		this.add(pCambioJuego, BorderLayout.SOUTH);
		this.add(pJugadores, BorderLayout.CENTER);
	}
}
