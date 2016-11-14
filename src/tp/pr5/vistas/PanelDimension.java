package tp.pr5.vistas;

import java.awt.FlowLayout;

import javax.swing.*;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.TipoTurno;

public class PanelDimension extends JPanel {
	private JTextField tFilas;
	private JLabel filas;
	private JTextField tColumnas;
	private JLabel columnas;
	private JFrame ventana;
	private ControladorGUI ca;
	
	/**
	 * Constructor de la clase
	 */
	public PanelDimension(ControladorGUI c, JFrame ventana){
		
		this.ventana = ventana;
		this.ca = c;
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
			
		creaEtiquetas();
		creaCamposDeTexto();
	
		anadeComponentes();
		
		this.setVisible(false);
				
	}
	
	/**
	 * Crea las etiquetas de filas y columnas
	 */
	private void creaEtiquetas(){
		this.filas = new JLabel("Filas: ");
		this.columnas = new JLabel("Columnas: ");
	}
	
	/**
	 * Crea los campos de texto para las filas y las columnas
	 */
	private void creaCamposDeTexto(){
		this.tFilas = new JTextField(4);
		this.tColumnas = new JTextField(4);
	}

	/**
	 * Añade a este panel las etiquetas de filas y columnas y los campos de texto 
	 * para poder "recogerlos"
	 */
	private void anadeComponentes(){
		this.add(filas);
		this.add(tFilas);
		this.add(columnas);
		this.add(tColumnas);
	}
	
	/**
	 * @return Devuelve la fila que ha introducido el usuario
	 * @throws NumberFormatException
	 */
	public int getFilas() throws NumberFormatException{
		
		return Integer.parseInt(tFilas.getText());
		
	}
	
	/**
	 * @return Devuelve la columna que se introduce el usuario
	 * @throws NumberFormatException
	 */
	public int getColumnas() throws NumberFormatException{
		
		return Integer.parseInt(tColumnas.getText());
		
	}
	
	/**
	 * Activa o desactiva según el valor de la variable
	 * @param b
	 */
	public void activar(final boolean b) {
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {
						if(b){
							
							tFilas.setText("10");
							tColumnas.setText("10");
							setVisible(true);
							ventana.pack();
							
						}
						else{
							setVisible(false);
						}
					}
			
				});
			}
		};
		
		worker.start();
	}
	
}