package tp.pr5.logica;


public class ReglasConecta4 extends ReglasGenerales {
	
	/**
	 * Constructor de la clase
	 */
	public ReglasConecta4(){
			this.tx = 7;
			this.ty = 6;
	}
	
	/**
	 * Devuelve el valor del ganador
	 * @param ultimoMovimiento
	 * @param t Tablero
	 * @return Color del ganador. Vacía si no hay grupo
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t){
		int col = ultimoMovimiento.getColumna();
		Ficha jugador = ultimoMovimiento.getJugador();
		return compruebaGrupos(col, jugador, t);
	}
	
	
	/**
	 * Nos dice si hay posibilidad de que la partida termine en tablas
	 * Se utiliza en conjunto con hayGanador ya que el tablero puede llenarse y no acabar en tablas
	 * @param ultimoEnPoner
	 * @param t Tablero
	 * @return tablas True si ha habido tablas
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t){
		boolean tablas = false;	
		//En Conecta4 la partida termina si se llena el tablero
		if(tableroFull(t))
			tablas = true;
		return tablas;
	}

	/**
	 * Esta función comprueba si el tablero esta lleno
	 * @param t Tablero
	 * @return full True si está lleno
	 */
	private boolean tableroFull(Tablero t){
		boolean full = true;
		int col = 1;
		while(full && col <= t.getAncho()){
			if(t.getCasilla(col, 1) == Ficha.VACIA){
				full = false;
			}
			col++;
		}
		return full;
	}
	
	 /**
	 * Devuelve el valor de la última fila ocupada de una columna en concreto
	 * @param col
	 * @param tab
	 * @return Última fila ocupada de la columna
	 */
	private int getUltimaFila(int col, Tablero tab){
		int fila = tab.getAlto();
		while((fila > 0) && (tab.getCasilla(col, fila) != Ficha.VACIA)){
			fila--;
		}
		return fila + 1;
	}

	
	/**
	 * Esta función comprueba los distintos grupos posibles
	 * y devuelve el color de la ficha que ha generado grupo
	 * @param col
	 * @param jugador
	 * @param tab
	 * @return
	 */
	private Ficha compruebaGrupos(int col, Ficha jugador, Tablero tab){
		boolean grupo = true;
		Ficha win = Ficha.VACIA;
		//Solo se comprueba si la ultima ficha ha generado grupos
		//Puesto que las anteriores ya habran sido revisadas
		int fila = getUltimaFila(col, tab);
		if(!compruebaVertical(col, fila, jugador, tab)){
			if(!compruebaHorizontal(col, fila, jugador, tab)){
				if(!comprobarDiagonales(col, fila, jugador, tab)){
					grupo = false;
				}
			}
		}
		if(grupo){
			win = jugador;
		}
		return win;
	}
	
	/**
	 * Esta función comprueba los distintos grupos verticales posibles
	 * @param col
	 * @param fila
	 * @param color
	 * @param tab
	 * @return True si hay grupo
	 */
	private boolean compruebaVertical(int col, int fila, Ficha color, Tablero tab){
		boolean grupo = false;
		fila = fila + 1;
		int cont = 1;
		//Hara la comprobacion solo si hay 3 casillas mínimo por debajo
		if(fila <= tab.getAlto() - 2){
			while((fila <= tab.getAlto()) && (tab.getCasilla(col, fila) == color) && (cont < 4)){
				cont++;
				fila++;
			}
			if(cont == 4){
				grupo = true;
			}
		}
		return grupo;
	}

	/**
	 * Nos indica a que juego estamos jugando en este momento
	 * @return devuelve el tipo de juego actual
	 */
	public TipoJuego getJuego() {
		// TODO Auto-generated method stub
		return TipoJuego.CONECTA4;
	}

}