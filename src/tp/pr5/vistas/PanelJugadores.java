package tp.pr5.vistas;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.*;


public class PanelJugadores extends JPanel implements Observer {
	
	private ControladorGUI co;
	private JComboBox jugador1;
	private JComboBox jugador2;
	private JLabel blancas;
	private JLabel negras;
	private JPanel norte;
	private JPanel sur;
	private TitledBorder borde;
	private Object c;

	public PanelJugadores(ControladorGUI c) {
		this.co = c;
		
		configuraPanel();		
        
        configuraEventos();
        this.co.addObservador(this);
	}
	
	private void configuraPanel() {
			
		this.setLayout(new BorderLayout());
		
		norte = new JPanel();
		norte.setLayout(new BoxLayout(norte, BoxLayout.X_AXIS));
		sur = new JPanel();
		sur.setLayout(new BoxLayout(sur, BoxLayout.X_AXIS));
		
		
		
		blancas = new JLabel();
		blancas.setText("Jugador de blancas");
		negras = new JLabel();
		negras.setText("Jugador de negras");
		
		jugador1 = new JComboBox(TipoTurno.values());
		jugador2 = new JComboBox(TipoTurno.values());
		
		norte.add(blancas);
		norte.add(Box.createRigidArea(new Dimension(45,0)));
		norte.add(jugador1);
		sur.add(negras);
		sur.add(Box.createRigidArea(new Dimension(50,0)));
		sur.add(jugador2);
		
		
		this.add(norte, BorderLayout.NORTH);
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(sur, BorderLayout.SOUTH);
		
		ponerBorde();
	}
	
	private void ponerBorde(){

		this.borde = BorderFactory.createTitledBorder("Gestión de jugadores");
        this.setBorder(this.borde);
	}

	private void configuraEventos() {
		ponerEscuchaModoBlancas();
		ponerEscuchaModoNegras();
		
	}
	
	/**
	 * Pone oyente al comboBox de las blancas
	 */
	private void ponerEscuchaModoBlancas(){
		jugador1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				TipoTurno blancas = (TipoTurno) jugador1.getSelectedItem();
				co.cambiarJugador(Ficha.BLANCA, blancas);
				
			}
		});//Fin clase anonima
	}
	
	/**
	 * Pone oyente al comboBox de las negras
	 */
	private void ponerEscuchaModoNegras(){
		jugador2.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				TipoTurno negras = (TipoTurno) jugador2.getSelectedItem();
				co.cambiarJugador(Ficha.NEGRA, negras);
				
			}
		});//Fin clase anonima
	}

	
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						jugador1.setSelectedIndex(0);
						jugador2.setSelectedIndex(0);
					}
			
				});
			}
		};
		
		worker.start();
	}

	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		
	}

	
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
			
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						jugador1.setSelectedIndex(0);
						jugador2.setSelectedIndex(0);
					}
			
				});
			}
		};
		
		worker.start();

	}

	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		
	}

	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}

	public void onEstado(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMovimientoStart(TipoTurno turno, TableroInmutable tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPosibles(ArrayList<Coordenadas> posibles) {
		// TODO Auto-generated method stub
		
	}
	
}
