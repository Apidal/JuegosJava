package tp.pr5.logica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class ReglasGravity extends ReglasGenerales {

	
	/**
	 * Constructor de la clase
	 * @param numCols
	 * @param numFilas
	 */
	public ReglasGravity(int numCols, int numFilas){
		this.tx = numCols;
		this.ty = numFilas;
		
	}
 
	/**
	 * Nos indica si existe un ganador en la partida
	 * @param ultimoMovimiento
	 * @param t Tablero
	 * @return Valor del jugador que genera grupo. Vacía si no hay grupo
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t){
		int col = ultimoMovimiento.getColumna();
		int fila = ultimoMovimiento.getFila();
		Ficha jugador = ultimoMovimiento.getJugador();
		return compruebaGrupos(col, fila ,jugador, t);
		
	}
	
	/**
	 * Nos dice si hay posibilidad de que la partida termine en tablas
	 * Se utiliza en conjunto con hayGanador ya que el tablero puede llenarse y no acabar en tablas
	 * @param ultimoEnPoner
	 * @param t Tablero
	 * @return True si el tablero está lleno
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t){
		boolean tablas = false;	
		//En Gravity la partida termina si se llena el tablero
		if(tableroFull(t))
			tablas = true;
		return tablas;
	}
	
	
	/**
	 * Esta función comprueba si el tablero esta lleno
	 * @param t Tablero
	 * @return True si el tablero esta lleno
	 */
	private boolean tableroFull(Tablero t){
		boolean full = true;
		int fila;
		int col = 1;
		while(full && col <= t.getAncho()){
			fila = 1;
			while(full && fila <= t.getAlto()){
				if(t.getCasilla(col, fila) == Ficha.VACIA){
					full = false;
				}
				fila++;
			}
			col++;
		}
		return full;
	}

	
	/**
	 * Esta función comprueba los ditintos grupos posibles
	 * Y devuelve el color de la ficha que ha generado grupo
	 * @param col
	 * @param fila
	 * @param jugador
	 * @param tab
	 * @return True si se ha generado grupo
	 */
	private Ficha compruebaGrupos(int col, int fila, Ficha jugador, Tablero tab){
		boolean grupo = true;
		Ficha win = Ficha.VACIA;
		//Solo se comprueba si la última ficha ha generado grupos
		//Puesto que las anteriores ya habran sido revisadas
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
	 * Esta función comprueba los ditintos grupos verticales posibles
	 * @param col
	 * @param fila
	 * @param color
	 * @param tab
	 * @return True si se ha generado grupo
	 */
	private boolean compruebaVertical(int col, int fila, Ficha color, Tablero tab){
		boolean grupo = false;
		int aux; // guardará el valor de fila
		aux = fila - 1;
		fila = fila + 1;
		int cont = 1;
		
		while((fila <= tab.getAlto()) && (tab.getCasilla(col, fila) == color) && (cont < 4)){
			cont++;
			fila++;
		}
		fila = aux;
		while((fila >= 1) && (tab.getCasilla(col, fila) == color) && (cont < 4)){
			cont++;
			fila--;
		}
		if(cont == 4){
			grupo = true;
		}
		
		return grupo;
	}

	/**
	 * Nos indica a que juego estamos jugando en este momento
	 * @return devuelve el tipo de juego actual
	 */
	public TipoJuego getJuego() {
		// TODO Auto-generated method stub
		return TipoJuego.GRAVITY;
	}

		
}