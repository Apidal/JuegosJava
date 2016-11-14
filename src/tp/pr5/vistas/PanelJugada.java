package tp.pr5.vistas;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import tp.pr5.control.ControladorGUI;
import tp.pr5.logica.Coordenadas;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;


public class PanelJugada extends JPanel implements Observer{

	private JPanel tablero;
	private JPanel central;
	private JTextField tTurno;
	private JLabel gif;
	private Border raisedbevel;
	private Border loweredbevel;
	private Border compound;
	private ControladorGUI co;
	private JFrame window;
	
	/**
	 * Constructor de la clase
	 * @param c
	 * @param ventana
	 */
	public PanelJugada(ControladorGUI c, JFrame ventana){
		
		this.co = c;
		this.window = ventana;
		
		this.setLayout(new BorderLayout());
		
		creaComponentes();
		configuraPanelTurno();
		crearGif();
		anadirComponentes();
		
		ponerBordes(this.tTurno);

		this.setVisible(true);
		this.co.addObservador(this);
	}
	
	/**
	 * Crea el panel tablero, el panel central y el campo de texto para mostrar el turno.
	 */
	private void creaComponentes(){
		this.tablero = new PanelTablero(this.co, this.window);
		this.tTurno = new JTextField("\nJuegan Blancas");
		this.central = new JPanel();
	}
	
	/**
	 * Configura el panel turno
	 */
	private void configuraPanelTurno(){
		this.tTurno.setMinimumSize(new Dimension(500, 0));
		this.tTurno.setHorizontalAlignment(SwingConstants.CENTER);
		this.tTurno.setEditable(false);
		this.tTurno.setForeground(new Color(0, 51, 255));
		this.tTurno.setBackground(new Color(255, 255, 255));
		this.tTurno.setFont(new Font("Magneto", Font.BOLD | Font.ITALIC, 14));
	}
	
	/**
	 * Crea un gif
	 */
	private void crearGif(){
		this.gif = new JLabel();
		this.gif.setIcon(new ImageIcon(PanelJugada.class.getResource("/tp/pr5/vistas/images/game.gif")));
		this.central.setVisible(true);
	}
	
	/**
	 * Añade los componentes al panel
	 */
	private void anadirComponentes(){
		this.central.add(this.gif);
		this.add(this.tablero, BorderLayout.NORTH);
		this.add(this.central, BorderLayout.CENTER);
		this.add(this.tTurno, BorderLayout.SOUTH);
	}
	
	/**
	 * Pone bordes a los componentes
	 * @param tTurno
	 */
	private void ponerBordes(JTextField tTurno){
		
		this.raisedbevel = BorderFactory.createRaisedBevelBorder();
		this.loweredbevel = BorderFactory.createLoweredBevelBorder();
		this.compound = BorderFactory.createCompoundBorder(this.raisedbevel, this.loweredbevel);
		this.setBorder(this.compound);
		this.tTurno.setBorder(this.compound);
	}

	/**
	 * Establece una imagen al reiniciar la partida y modifica el campo tTurno para que indique
	 * el turno actual
	 */
	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		// TODO Auto-generated method stub
		this.tTurno.setText("\nJuegan " + turno.getDescription());
		gif.setIcon(new ImageIcon(PanelJugada.class.getResource("/tp/pr5/vistas/images/game.gif")));
		this.window.pack();
	}

	/**
	 * Modifica la etiqueta del turno para indicar el ganador y establece una imagen dependiendo 
	 * de si hay o no ganador al terminar la partida
	 */
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		// TODO Auto-generated method stub
		if(ganador == Ficha.VACIA){
			this.tTurno.setText("\n" + ganador.getDescription());
			gif.setIcon(new ImageIcon(PanelJugada.class.getResource("/tp/pr5/vistas/images/tablas.gif")));
		}
		else{
			this.tTurno.setText("\nGanan las " + ganador.getDescription());
			gif.setIcon(new ImageIcon(PanelJugada.class.getResource("/tp/pr5/vistas/images/gameover.gif")));
		}
		this.window.pack();	
	}

	
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		// TODO Auto-generated method stub
		this.tTurno.setText("\nJuegan " + turno.getDescription());
		gif.setIcon(new ImageIcon(PanelJugada.class.getResource("/tp/pr5/vistas/images/game.gif")));
		this.window.pack();
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	
	public void onUndo(TableroInmutable tablero, final Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						tTurno.setText("\nJuegan " + turno.getDescription());
					}
			
				});
			}
		};
		
		worker.start();
		
	}

	
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador, final Ficha turno) {
		// TODO Auto-generated method stub
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						if(turno != Ficha.VACIA)
							tTurno.setText("\nJuegan " + turno.getDescription());
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

	@Override
	public void onMovimientoStart(TipoTurno turno, TableroInmutable tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPosibles(ArrayList<Coordenadas> posibles) {
		// TODO Auto-generated method stub
		
	}
}
