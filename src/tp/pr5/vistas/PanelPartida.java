package tp.pr5.vistas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Coordenadas;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;


public class PanelPartida extends JPanel implements Observer {

	private JButton deshacer;
	private JButton reiniciar;
	private TitledBorder borde;
	private ControladorGUI co;
	
	/**
	 * Constructor de la clase
	 * @param c
	 */
	public PanelPartida(ControladorGUI c){	
		
		this.co = c;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		crearBotones();
        
        configuracionEventos();
        
        anadeBotones();
        
        this.borde = BorderFactory.createTitledBorder("Partida");
        this.setBorder(this.borde);
        co.addObservador(this);
        
	}
	
	/**
	 * Añade los botones a este panel
	 */
	private void anadeBotones(){
		this.add(this.deshacer);
        this.add(this.reiniciar);
	}
	
	/**
	 * Crea los botones
	 */
	private void crearBotones(){
		crearBotonDeshacer();
		crearBotonReiniciar(); 
	}
	
	/**
	 * Crea el botón para poder deshacer un movimiento
	 */
	private void crearBotonDeshacer(){
		this.deshacer = new JButton("Deshacer");
        this.deshacer.setIcon(new ImageIcon(PanelPartida.class.getResource("/tp/pr5/vistas/images/icons/undo.png")));
	}
	
	/**
	 * Crea el botón para poder reiniciar la partida
	 */
	private void crearBotonReiniciar(){
		this.reiniciar = new JButton("Reiniciar");
        this.reiniciar.setIcon(new ImageIcon(PanelPartida.class.getResource("/tp/pr5/vistas/images/icons/reset.png")));
	}
	
	/**
	 * Pone oyente al botón reiniciar y reinicia la partida cuando se pulsa el botón
	 */
	private void ponerEscuchaBotonReiniciar(){
		this.reiniciar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				co.reset();
				JOptionPane.showMessageDialog(null, "Partida reiniciada!");
			}
        });
	}
	
	/**
	 * Pone oyente al botón deshacer y deshace el movimiento cuando se pulsa el botón
	 */
	private void ponerEscuchaBotonDeshacer(){
		this.deshacer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				co.undo();
				
			}
        	
        });
	}
	
	/**
	 * Configura las escuchas de los botones
	 */
	private void configuracionEventos(){
		ponerEscuchaBotonDeshacer();
		ponerEscuchaBotonReiniciar();		
	}
	
	/**
	 * Desactiva el botón deshacer
	 */
	private void botonUndoOff(){
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						deshacer.setEnabled(false);
					}
			
				});
			}
		};
		
		worker.start();
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		// TODO Auto-generated method stub
		botonUndoOff();
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		// TODO Auto-generated method stub
		botonUndoOff();
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		// TODO Auto-generated method stub
		
		botonUndoOff();
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		if(!hayMas){
			botonUndoOff();
		}
	}

	
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador, Ficha turno) {
		// TODO Auto-generated method stub
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						deshacer.setEnabled(true);
					}
			
				});
			}
		};
		
		worker.start();
		
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}


	public void onMovimientoStart(TipoTurno turno, TableroInmutable tab) {
		if(turno == TipoTurno.AUTOMATICO)
			botonUndoOff();
		
	}

	@Override
	public void onPosibles(ArrayList<Coordenadas> posibles) {
		// TODO Auto-generated method stub
		
	}
		
}
