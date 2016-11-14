package tp.pr5.control;

import tp.pr5.logica.*;
import tp.pr5.vistas.Observer;

public class ControladorGUI {
	private Partida p;
	private FactoriaTipoJuego f;
	
	/**
	 * Constructor del controlador de la vista gráfica
	 * @param fac
	 * @param par
	 */
	public ControladorGUI(FactoriaTipoJuego fac, Partida par){
		this.p = par;
		this.f = fac;
		Ficha.BLANCA.setModo(new ModoHumano());
		Ficha.NEGRA.setModo(new ModoHumano());
		Ficha.BLANCA.setTurno(TipoTurno.HUMANO);
		Ficha.NEGRA.setTurno(TipoTurno.HUMANO);
	}
	
	/**
	 * Llama cambiaJuego para iniciar la partida
	 */
	public void start(){
		this.p.cambioJuego(this.f.creaReglas());
		this.p.reinicia(this.f.creaReglas());
	}

	/**
	 * Crea un movimiento con la fila y la columna que le llega
	 * @param i, columna
	 * @param j, fila
	 */
	public void poner(int i, int j) {
		
		this.p.detenerPartida();
		Movimiento mov = f.creaMovimiento(i, j, this.p.getTurno());
		this.p.ejecutaMovimiento(mov);
		this.p.continuarPartida();
	}
	
	
	/**
	 * Reinicia la partida
	 */
	public void reset(){
		this.p.detenerPartida();
		this.p.reinicia(this.f.creaReglas());
		new ControladorGUI(this.f, this.p);
		this.p.continuarPartida();
		
	}

	/**
	 * Según el juego que le llegue crea una partida de un juego u otro
	 * @param g
	 * @param filas
	 * @param cols
	 */
	public void cambioJuego(TipoJuego g, int cols, int filas) {
		this.p.detenerPartida();
		if(g == TipoJuego.CONECTA4){
			this.f = new FactoriaConecta4();
			this.p.cambioJuego(this.f.creaReglas());
		}
		else if(g == TipoJuego.COMPLICA){
			this.f = new FactoriaComplica();
			this.p.cambioJuego(this.f.creaReglas());
		}
		else if(g == TipoJuego.GRAVITY){
			this.f = new FactoriaGravity(cols, filas);
			this.p.cambioJuego(this.f.creaReglas());
		}
		else if(g == TipoJuego.REVERSI){
			this.f = new FactoriaReversi();
			this.p.cambioJuego(this.f.creaReglas());
		}
		
		this.p.continuarPartida();
	}
	
	/**
	 * Llama a deshacer para deshacer un movimiento
	 */
	public void undo(){
		
		this.p.undo();
		
	}
	
	/**
	 * Añade el observador que le llega por parametro al ArrayList de observadores
	 * @param o
	 */
	public void addObservador(Observer o){
		this.p.addObserver(o);
	}

	/**
	 * Crea un jugador aleatorio para crear el movimiento aleatorio
	 */
	public void ponerAleatorio() {
		
		this.p.detenerPartida();
		Jugador j = this.f.creaJugadorAleatorio();
		this.p.ejecutaAleatorio(j);
		this.p.continuarPartida();
	}
	
	
	/**
	 * Cambia el modo de juego de f segun el valor de t
	 * @param f
	 * @param t
	 */
	public void cambiarJugador(Ficha f, TipoTurno t){
		this.p.detenerPartida();
		
		
		if(t == TipoTurno.AUTOMATICO)
			f.setModo(new ModoAutomatico(this));
		else
			f.setModo(new ModoHumano());
		
		f.setTurno(t);
		
		
		this.p.continuarPartida();
	}
}

