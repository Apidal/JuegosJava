package tp.pr5.logica;



public class ReglasReversi extends ReglasGenerales {
	

	
	public ReglasReversi(){
		this.tx = 8;
		this.ty = 8;
		
	}
	
	
	/**
	 * Nos indica si existe un ganador en la partida
	 * @param ultimoMovimiento
	 * @param t Tablero
	 * @return Valor del jugador que genera grupo. Vacía si no hay grupo
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		int blancas = nFichas(Ficha.BLANCA, t);
		int negras = nFichas(Ficha.NEGRA, t);
		
		if(!puedePoner(Ficha.BLANCA, t) && !puedePoner(Ficha.NEGRA, t)){
			
			if(negras == blancas)
				return Ficha.VACIA;
			else if(blancas < negras)
				return Ficha.NEGRA;
			else
				return Ficha.BLANCA;
		}
		return Ficha.VACIA;
	}


	/**
	 * Nos devuelve el tablero inicializado con las reglas del juego
	 * @return Tablero inicializado
	 */
	public Tablero iniciaTablero() {
		Tablero tab = new Tablero(tx, ty);
		
		poner4casillasIniciales(tab);
		
		return tab;
	}
	
	/**
	 * Colocamos las 4 casillas iniciales del tablero
	 * @param tab
	 */
	private void poner4casillasIniciales(Tablero tab){
		tab.setCasilla(tab.getAncho()/2, tab.getAlto()/2, Ficha.BLANCA);
		tab.setCasilla((tab.getAncho()/2)+1, tab.getAlto()/2, Ficha.NEGRA);
		tab.setCasilla((tab.getAncho()/2)+1, (tab.getAlto()/2)+1, Ficha.BLANCA);
		tab.setCasilla(tab.getAncho()/2, (tab.getAlto()/2)+1, Ficha.NEGRA);
	}


	/**
	 * Nos devuelve el jugador inicial
	 * @return Ficha.NEGRA. En Reversi siempre empiezan las negras
	 */
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}

	/**
	 * Cambia el turno actual segun que jugador ha puesto en ultimo lugar, y en funcion de 
	 * si el openente puede o no poner
	 * @param ultimoEnPoner
	 * @param t Tablero
	 * @return turno
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		if(ultimoEnPoner == Ficha.BLANCA){
			if(puedePoner(Ficha.NEGRA, t))
				return Ficha.NEGRA;
			if(puedePoner(Ficha.BLANCA, t))
				return Ficha.BLANCA;
		}
		else{
			if(puedePoner(Ficha.BLANCA, t))
				return Ficha.BLANCA;
			if(puedePoner(Ficha.NEGRA, t))
				return Ficha.NEGRA;
		}
		//Nunca se va a dar, se utiliza para hallar errores en el cambio de turno
		return Ficha.VACIA;
	}

	
	/**
	 * Nos dice si hay posibilidad de que la partida termine en tablas
	 * Se utiliza en conjunto con hayGanador ya que el tablero puede llenarse y no acabar en tablas
	 * @param ultimoEnPoner
	 * @param t Tablero
	 * @return True si el tablero está lleno
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		if(!puedePoner(Ficha.BLANCA, t) && !puedePoner(Ficha.NEGRA, t)){
			if(nFichas(Ficha.BLANCA, t) == nFichas(Ficha.NEGRA, t))
				return true;
			return false;
		}
		else{
			return false;
		}
		
	}
	
	/**
	 * Indica el numero de fichas f que contiene el tablero t
	 * @param f
	 * @param t
	 * @return devuelve un entero con el numero de fichas del mismo tipo que f
	 */
	private int nFichas(Ficha f, Tablero t) {
		
		int ret = 0;
		
		for(int i = 1; i <= t.getAlto(); i++){
			for(int j = 1; j <= t.getAncho(); j++){
				if(f == t.getCasilla(j, i))
					ret++;
			}
		}
		return ret;
	}
	
	
	/**
	 * Indica si la ficha f tiene algun movimiento posible en el tablero t
	 * @param f
	 * @param t
	 * @return true si hay movimientos posibles, false en caso contrario
	 */
	private boolean puedePoner(Ficha f, Tablero t){
		//movimiento falso creado para no repetir 
		//el codigo de puedenPoner de MovimientoReversi
		MovimientoReversi m = new MovimientoReversi(0, 0, f);
		return m.puedePoner(f,t);
	}

	/**
	 * Nos indica a que juego estamos jugando en este momento
	 * @return devuelve el tipo de juego actual
	 */
	public TipoJuego getJuego() {
		return TipoJuego.REVERSI;
	}

}
