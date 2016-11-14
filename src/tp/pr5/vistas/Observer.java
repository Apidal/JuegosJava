package tp.pr5.vistas;

import java.util.ArrayList;

import tp.pr5.logica.*;

public interface Observer {
	
	// Gestión de partida
	
	/**
	 * Notifica a la vista que se ha reiniciado la partida, pasandole el tablero para
	 * mostrarlo
	 * @param tab
	 * @param turno
	 */
	void onReset(TableroInmutable tab, Ficha turno, TipoJuego juego);
	
	/**
	 * Notifica a la vista que se ha terminado la partida
	 * @param tablero
	 * @param ganador
	 */
	void onPartidaTerminada(TableroInmutable tablero, Ficha ganador);
	
	/**
	 * Notifica a la vista de que ha habido un cambio de juego y que posiblemente
	 * haya cambiado el tablero y el turno
	 * @param tab
	 * @param turno
	 */
	void onCambioJuego(TableroInmutable tab, Ficha turno);
	
	// Gestión de movimientos
	
	/**
	 * Notifica a la vista que no se puede deshacer un movimiento
	 * @param tablero
	 * @param turno
	 */
	void onUndoNotPossible(TableroInmutable tablero, Ficha turno);
	
	/**
	 * Notifica a la vista que se ha deshecho un movimiento y refresca el tablero.
	 * Además indica si es posible volver a deshacer
	 * @param tablero
	 * @param turno
	 * @param hayMas
	 */
	void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas);
	
	/**
	 * Notifica a la vista que ha terminado el movimiento y refresca el tablero
	 * @param tablero
	 * @param jugador
	 * @param turno
	 */
	void onMovimientoEnd(TableroInmutable tablero, Ficha jugador, Ficha turno);
	
	/**
	 * Notifica a la vista que se ha creado un movimiento inválido
	 * @param movimientoException
	 */
	void onMovimientoIncorrecto(MovimientoInvalido movimientoException);
	
	/**
	 * Activa o desactiva componentes dependiendo del TipoTurno que este jugando
	 * @param turno
	 * @param tab
	 */
	void onMovimientoStart(TipoTurno turno, TableroInmutable tab);
	
	/**
	 * Nos informa de que el juego actual es reversi y colorea de forma llamativa los movimientos posibles
	 * @param tab
	 * @param f
	 */
	void onPosibles(ArrayList<Coordenadas> posibles);

}
