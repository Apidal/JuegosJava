package tp.pr5.vistas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Coordenadas;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.ReglasReversi;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;

public class PanelTablero extends JPanel implements Observer{
	private MiBoton[][] botones;
	private ControladorGUI ca;
	private JFrame window;
	
	/**
	 * Constructor de la clase
	 * @param c
	 * @param ventana
	 */
	public PanelTablero(ControladorGUI c, JFrame ventana){
		this.ca = c;
		this.window = ventana;
		this.ca.addObservador(this);
	}
	
	/**
	 *Clase interna para tratar el botón
	 */
	public class MiBoton extends JButton{
		
		private int fila;
		private int col;
		
		/**
		 * Constructor de la clase interna
		 * @param filas
		 * @param columnas
		 */
		public MiBoton(int filas, int columnas){
			
			this.fila = filas;
			this.col = columnas;
		}
		
		public int getFila(){
			
			return fila;
		}
		public int getCol(){
			return col;
		}
		
	}
	
	/**
	 * Comprueba el color de la casilla para decidir el color del botón
	 * @param tab
	 * @param b
	 */
	private void pintaBoton(final TableroInmutable tab, final MiBoton b){
				
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						Ficha color;
						color = tab.getCasilla(b.getCol()+1, b.getFila()+1);
						b.setBackground(color.getColor());
					}
			
				});
			}
		};
		
		worker.start();
	
	}
	
	/**
	 * Pinta el boton que le llega por parametro para que el usuario vea
	 * que es un movimiento posible
	 * @param b
	 */
	private void pintaPosible(final MiBoton b){
	
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						b.setBackground(new Color(140, 190, 255));
					}
			
				});
			}
		};
		
		worker.start();
	}
	
	
	/**
	 * Recorre el tablero para decidir el color del botón
	 * @param tab
	 */
	private void pintaBotones(TableroInmutable tab, Ficha f){
		
		for(int i = 0; i < tab.getAlto(); i++){
			for(int j = 0; j < tab.getAncho(); j++){
				pintaBoton(tab, botones[i][j]);
			}
		}
		
	}
	
	
	/**
	 * Descativa los botones del tablero
	 * @param tab
	 */
	private void botonesOff(final TableroInmutable tab){
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						for(int i = 0; i < tab.getAlto(); i++){
							for(int j = 0; j < tab.getAncho(); j++){
								botones[i][j].setEnabled(false);
							}
						}
					}
			
				});
			}
		};
		
		worker.start();
	}
	
	/**
	 * Activa los botones del tablero
	 * @param tab
	 */
	private void botonesOn(final TableroInmutable tab){
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						for(int i = 0; i < tab.getAlto(); i++){
							for(int j = 0; j < tab.getAncho(); j++){
								botones[i][j].setEnabled(true);
							}
						}
					}
			
				});
			}
		};
		
		worker.start();
	}
	
	
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		// TODO Auto-generated method stub
		pintaBotones(tab, turno);
		botonesOn(tab);	
	}

	
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		// TODO Auto-generated method stub
		pintaBotones(tablero, ganador);
		botonesOff(tablero);
	}


	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		// TODO Auto-generated method stub
		
		int a, b;
		a = tab.getAlto();
		b = tab.getAncho();
		
		this.removeAll();
		this.setLayout(new GridLayout(a, b));
		botones = new MiBoton[a][b];
		
		for(int fila = 0; fila < a ; fila++){
			for(int col = 0; col < b; col++){
				botones[fila][col] = new MiBoton(fila, col);
				configuraBoton(tab, fila, col);
				this.add(botones[fila][col]);
			}
		}
		this.window.pack();
	}
	
	/**
	 * Configura el botón añadiendole un tamaño preferido, poniendole color y agregandole 
	 * el oyente
	 * @param tab
	 * @param fila
	 * @param col
	 */
	private void configuraBoton(TableroInmutable tab, int fila, int col){
		botones[fila][col].setPreferredSize(new Dimension(30,30));
		pintaBoton(tab, botones[fila][col]);
		ponerEscuchaBoton(fila, col);
		
	}
	
	/**
	 * Añade una escucha al botón y recoge el la posición del botón pulsado
	 * @param fila
	 * @param col
	 */
	private void ponerEscuchaBoton(int fila, int col){
		botones[fila][col].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MiBoton b = (MiBoton) e.getSource();
				ca.poner(b.getCol()+1, b.getFila()+1);		
			}
			
		});//Fin clase anonima
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		pintaBotones(tablero, turno);
	}


	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		// TODO Auto-generated method stub
		pintaBotones(tablero, turno);
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}

	
	public void onMovimientoStart(TipoTurno turno, TableroInmutable tab) {
		
		if(turno == TipoTurno.AUTOMATICO)
			botonesOff(tab);
		else
			botonesOn(tab);
	}


	public void onPosibles(ArrayList<Coordenadas> posibles){
		for(Coordenadas c: posibles){
			pintaPosible(botones[c.getFila()-1][c.getCol()-1]);
		}
	}

}
