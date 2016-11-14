package tp.pr5.vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Coordenadas;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;


public class PanelIzq extends JPanel implements Observer{
	
	private JPanel jugada;
	private JButton aleatorio;
	private ControladorGUI co;
	private JFrame window;
	
	/**
	 * Constructor de la clase
	 * @param c
	 * @param ventana
	 */
	public PanelIzq(ControladorGUI c, JFrame ventana){
		
		this.co = c;
		this.window = ventana;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		this.jugada = new PanelJugada(this.co, this.window);
		
		creaBotonAleatorio();
		ponerEscuchaBotonAleatorio();
		
		this.add(this.jugada);
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(this.aleatorio);
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		this.setVisible(true);
		
		this.co.addObservador(this);
	}
	
	/**
	 * Crea el botón para poner de manera aleatoria
	 */
	private void creaBotonAleatorio(){
		this.aleatorio = new JButton("Poner Aleatorio");
		this.aleatorio.setAlignmentX(CENTER_ALIGNMENT);
		this.aleatorio.setIcon(new ImageIcon(PanelPartida.class.getResource("/tp/pr5/vistas/images/icons/random.png")));
	}

	/**
	 * Pone oyente al botón de aleatorio
	 */
	private void ponerEscuchaBotonAleatorio(){
		aleatorio.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              co.ponerAleatorio();
            }
        });
	}
	
	
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		// TODO Auto-generated method stub
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						aleatorio.setEnabled(true);
					}
			
				});
			}
		};
		
		worker.start();
	}

	
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		// TODO Auto-generated method stub
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						aleatorio.setEnabled(false);
					}
			
				});
			}
		};
		
		worker.start();
	}

	
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		// TODO Auto-generated method stub
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						aleatorio.setEnabled(true);
					}
			
				});
			}
		};
		
		worker.start();
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	
	public void onMovimientoIncorrecto(final MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						JOptionPane.showMessageDialog(null, movimientoException.getMensaje(), "Movimiento Invalido", JOptionPane.ERROR_MESSAGE);
					}
			
				});
			}
		};
		
		worker.start();
	}

	
	public void onMovimientoStart(final TipoTurno turno, TableroInmutable tab) {
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						aleatorio.setEnabled(turno==TipoTurno.HUMANO);
					}
			
				});
			}
		};
		
		worker.start();
		
	}

	@Override
	public void onPosibles(ArrayList<Coordenadas> posibles) {
		// TODO Auto-generated method stub
		
	}
}
