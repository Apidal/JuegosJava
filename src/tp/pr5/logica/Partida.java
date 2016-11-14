package tp.pr5.logica;

import java.util.ArrayList;

import tp.pr5.control.Jugador;
import tp.pr5.vistas.Observer;

public class Partida {
	
	
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private ReglasJuego reglas;
	//Nos indica el mÃ¡ximo de veces que podemos deshacer
	private static final int UNDO = 10;
	//Nos indica el numero de veces que podemos deshacer
	private int numUndo;
	//Nos indica el numero de movimiento que llevamos
	//Se utiliza para calcular la posicion del ultimo movimiento en el array
	private int numJugadas;
	//Array de los ultimos movimientos
	private Movimiento[] undoStackMov;
	//ArrayList con los observadores
	private ArrayList<Observer> observadores;
	//ArrayList con los movimientos posibles
	private ArrayList<Coordenadas> posibles;

	
	
	
	/**
	 * Constructor de la partida, se llama al reset puesto que inicializa todos los valores
	 * @param reglas
	 */
	public Partida(ReglasJuego reglas){
		
		this.observadores = new ArrayList<Observer>();
		reset(reglas);
	}
	
	/**
	 * Devuelve el valor de ganador
	 * @return ganador
	 */
	public Ficha getGanador(){
		return this.ganador;
	}
	
	/**
	 * Devuelve el tablero
	 * @return tablero
	 */
	public Tablero getTablero(){
		return this.tablero;
	}
	
	/**
	 * Devuelve el turno
	 * @return turno
	 */
	public Ficha getTurno(){
		return this.turno;
	}
	
	/**
	 * Devuelve si la partida ha terminado 
	 * @return terminada
	 */
	public boolean isTerminada(){
		return this.terminada;
	}
	
	/**
	 * LLama a reset y notifica onCambioJuego
	 * @param reglas
	 */
	public void cambioJuego(ReglasJuego reglas){
		
		reset(reglas);
		
		for (Observer o: observadores){
			o.onCambioJuego(this.tablero, this.turno);
		}
		onReversi();
	}
	
	/**
	 * LLama a reset y notifica onReset
	 * @param reglas
	 */
	public void reinicia(ReglasJuego reglas){
		reset(reglas);
		
		for (Observer o: observadores){
			o.onReset(this.tablero, this.turno, this.reglas.getJuego());
		}
		onReversi();

	}
	
	/**
	 * Inicializa los valores predeterminados de la partida
	 * @param reglas
	 */
	public void reset(ReglasJuego reglas){
		this.tablero = reglas.iniciaTablero();
		this.turno = reglas.jugadorInicial();
		this.terminada = false;
		this.ganador = null;
		this.reglas = reglas;
		this.numUndo = 0;
		this.numJugadas = 0;
		this.undoStackMov = new Movimiento [UNDO];
	}
	
	/**
	 * Recibe un movimiento y lo ejecuta
	 * @param mov
	 */
	public void ejecutaMovimiento(Movimiento mov){
		try{
			//Nos aseguramos de que el jugador del movimiento corresponde con el del turno actual
			if(mov.getJugador() == this.turno){
				mov.ejecutaMovimiento(this.tablero);
				//Almacenamos los movimientos que podemos deshacer 
				//así como el número de veces que podemos deshacerlos
				push(mov);
				
				
				//Si ha introducido la ficha, comprueba si ha habido grupo	
				this.ganador = this.reglas.hayGanador(mov, this.tablero);
				if(this.ganador != Ficha.VACIA){
					this.terminada = true;
					this.turno = Ficha.VACIA;
					for (Observer o: observadores){
						o.onPartidaTerminada(this.tablero, this.ganador);
					}
				}
				//Si ha introducido la ficha y no ha habido grupo, comprueba si el tablero está lleno
				else if(reglas.tablas(this.turno, this.tablero)){
					this.terminada = true;
					this.ganador = Ficha.VACIA;
					this.turno = Ficha.VACIA;
					for (Observer o: observadores){
						o.onPartidaTerminada(this.tablero, this.ganador);
					}
				}
				//Y si ha introducido ficha, pero no ha habido grupo ni esta el tablero lleno
				//cambia el turno
				else{				
					this.turno = this.reglas.siguienteTurno(this.turno, this.tablero);
					//Llamamos a los observadores de onMovimientoEnd
					for (Observer o: observadores){
						o.onMovimientoEnd(this.tablero, mov.getJugador(), this.turno);
					}
					
					
				}
			}
			else {
				throw new MovimientoInvalido("El jugador no coincide con el turno.");
			}
		}
		catch(MovimientoInvalido m){
			for (Observer o: observadores){
				o.onMovimientoIncorrecto(m);
			}
			
		}	
		onReversi();
	}
	
	/**
	 * Almacenamos los movimientos que podemos deshacer
	 * así como el número de veces que podemos deshacerlos
	 * @param mov
	 */
	private void push(Movimiento mov){
		if (this.numUndo < UNDO) 
			this.numUndo++;
		this.numJugadas++;
		this.undoStackMov[(numJugadas%UNDO)] = mov;
	}
	
	/**
	 * Función auxiliar que quita de la pila el último movimiento almacenado
	 * @return ok True si se ha ejecutado bien
	 */
	private boolean pop(){
		boolean ok = false; 
		//Comprobamos que no excedemos el límite que podemos deshacer
		if(this.numUndo > 0){
			detenerPartida();
			//Deshacemos el movimiento
			this.undoStackMov[(this.numJugadas%UNDO)].undo(this.tablero);
			//Devolvemos el turno al jugador anterior
			this.turno = this.reglas.siguienteTurno(this.turno, this.tablero);
			ok = true;
			//Decrementamos el número de jugadas 
			//y el numero de movimientos posibles para deshacer
			this.numUndo--;
			this.numJugadas--;
			continuarPartida();
		}
		
		return ok;
	}
	
	/**
	 * Deshace el ultimo movimiento
	 * @return pop() Es un boolean para indicar si se ha efectuado
	 */
	public void undo(){
		
		if(pop()){
			for (Observer o: observadores){
				o.onUndo(this.tablero,this.turno,(this.numUndo>0));
			}
			onReversi();
		}
		else{
			for (Observer o: observadores){
				o.onUndoNotPossible(this.tablero,this.turno);
			}
		}
	}

	/**
	 * Muestra el tablero de la partida
	 */
	public void mostrarTablero(){
		System.out.print(this.tablero.toString() + "\n");
	}
	
	/**
	 * Dibuja el primer tablero
	 */
	public void iniciar(){
		for(Observer o: observadores){
			o.onCambioJuego(this.tablero, this.turno);
		}
	}

	/**
	 * Devuelve el movimiento del jugador
	 * @param jugador
	 * @return
	 */
	public Movimiento dameMov(Jugador jugador){
		try{
			return jugador.getMovimiento(this.tablero, this.turno);
		}
		catch(MovimientoInvalido m){
			if(m.getMensaje() != null){
				System.err.println(m.getMensaje());
				for (Observer o: observadores){
					o.onMovimientoIncorrecto(m);
				}
			}
			
			return null;
		}
		
	}

	/**
	 * Añade un observador a la lsita de observadores
	 * @param o
	 */
	public void addObserver(Observer o) {
		this.observadores.add(o);
	}

	/**
	 * Recibe un jugador y crea un movimiento aleatorio
	 * @param j
	 */
	public void ejecutaAleatorio(Jugador j) {
		// TODO Auto-generated method stub
			Movimiento mov = dameMov(j);
			ejecutaMovimiento(mov);		
			
	}
	
	/**
	 * Detiene la ejecucion de la hebra en caso de que el modo actual sea automatico,
	 * si el modo actual es humano no hace nada
	 */
	public void detenerPartida(){
		Modo modo;
		modo = turno.getModo();
		modo.terminar();
	}
	
	/**
	 * Comprueba si la partida no esta terminada y notifica al obvservador de onMovimientoStart
	 * en caso afirmativo y crea una nueva hebra de ejcucion en caso de que el modo actual sea 
	 * ModoAutomatico
	 */
	public void continuarPartida(){
		Modo modo;
		if(!terminada){
			for (Observer o: observadores){
				o.onMovimientoStart(this.turno.getTurno(), this.tablero);
			}
			modo = this.turno.getModo();
			modo.comenzar();
		}
	}
	
	private void onReversi(){
		
		if(this.reglas.getJuego() == TipoJuego.REVERSI){
			MovimientoReversi m = new MovimientoReversi(0,0,this.turno);
			posibles = m.movPosibles(this.tablero);
			if(posibles.size() !=0){
				for (Observer o: observadores){
					o.onPosibles(posibles);
				}
			}
			
		}
	}
	
		
}
