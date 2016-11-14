package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.*;

public class FactoriaReversi implements FactoriaTipoJuego {

	/**
	 * Crea las nuevas reglas
	 * @return devuelve las nuevas reglas de tipo Reversi
	 */
	public ReglasJuego creaReglas() {
		// TODO Auto-generated method stub
		return new ReglasReversi();
	}

	/**
	 * Crea un nuevo movimiento de tipo reversi
	 * @return devuelve el movimiento creado
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		// TODO Auto-generated method stub
		return new MovimientoReversi(col, fila, color);
	}

	/**
	 * Crea un nuevo jugador humano de tipo reversi
	 * @retrun devuelve el jugador creado
	 */
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		// TODO Auto-generated method stub
		return new JugadorHumanoReversi(in, this);
	}

	/**
	 * Crea un nuevo jugador aleatorio de tipo reversi
	 * @return devuelve el jugador creado
	 */
	public Jugador creaJugadorAleatorio() {
		// TODO Auto-generated method stub
		return new JugadorAleatorioReversi(this);
	}

}
