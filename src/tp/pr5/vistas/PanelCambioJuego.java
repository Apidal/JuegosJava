package tp.pr5.vistas;

import java.awt.*;
import java.awt.event.*;
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

public class PanelCambioJuego extends JPanel implements Observer{
	
	private JComboBox comboBox;
	private PanelDimension dimension ;
	private JButton cambiar;
	private TitledBorder borde;
	private ControladorGUI co;
	private JFrame window;
	
	/**
	 * Constructor de la clase
	 * @param c
	 * @param ventana
	 */
	public PanelCambioJuego(ControladorGUI c, JFrame ventana){
		
		this.co = c;
		this.window = ventana;
		
		configuraPanel();		
        
        configuraEventos();
        this.co.addObservador(this);
	}
	
	/**
	 * Configura el panel cambioJuego
	 */
	private void configuraPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		creaListaJuegos();
		 
		this.add(this.comboBox);
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		this.dimension = new PanelDimension(this.co, this.window);
		this.add(this.dimension);
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
		
		crearBotonCambioJuego();
		
		this.add(this.cambiar);
		
		this.add(Box.createRigidArea(new Dimension(0,10)));
        
		ponerBorde();
	}
	
	/**
	 * Crea el botón para poder cambiar el juego
	 */
	private void crearBotonCambioJuego(){
		this.cambiar = new JButton("Cambiar Juego");
		this.cambiar.setAlignmentX(CENTER_ALIGNMENT);
		this.cambiar.setIcon(new ImageIcon(PanelPartida.class.getResource("/tp/pr5/vistas/images/icons/check.png")));
	}
	
	/**
	 * Pone un borde al panel
	 */
	private void ponerBorde(){
		this.borde = BorderFactory.createTitledBorder("Cambio de Juego");
        this.setBorder(this.borde);
	}
	
	/**
	 * Llama a los metodos para añadir oyentes a los componentes
	 */
	private void configuraEventos(){
		ponerEscuchaBotonCambiarJuego();
		ponerEscuchaComboBox();
	}
	
	/**
	 * Crea el JComboBox con los juegos
	 */
	private void creaListaJuegos(){
		comboBox = new JComboBox(TipoJuego.values());
		
	}
	
	/**
	 * Pone oyente al boton de cambiar el juego
	 */
	private void ponerEscuchaBotonCambiarJuego(){
		cambiar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					TipoJuego j = (TipoJuego) comboBox.getSelectedItem();
					if(j.esRedimensionable()){
						co.cambioJuego(j, dimension.getColumnas(), dimension.getFilas());
					}
					else{
						co.cambioJuego(j, 0, 0);
					}
					JOptionPane.showMessageDialog(null, "Juego cambiado a "+ j.getDescription() + "!");
				}
				catch(NumberFormatException n){
					JOptionPane.showMessageDialog(null, "Error! Debe introducir valores enteros!", "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				window.pack();
			}
		});//Fin clase anonima
	}
	
	/**
	 * Pone oyente a lista desplegable para saber que opción se escoge
	 */
	private void ponerEscuchaComboBox(){
		comboBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){              
              
            	dimension.activar(((TipoJuego) comboBox.getSelectedItem()).esRedimensionable());
            }
			
        });
	}


	
	public void onReset(TableroInmutable tab, Ficha turno, final TipoJuego juego) {
		
		Thread worker = new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						cambiar.setEnabled(true);
						comboBox.setSelectedItem(juego);
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
						cambiar.setEnabled(false);
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
						cambiar.setEnabled(true);
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
