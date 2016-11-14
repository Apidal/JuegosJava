package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.ReglasConecta4;
import tp.pr5.logica.ReglasJuego;

public class FactoriaConecta4 implements FactoriaTipoJuego{

	/**
	 * Devuelve unas nuevas reglas
	 * @return Nuevas reglas
	 */
	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}
	
	/**
	 * Devuelve un nuevo movimiento
	 * @param col
	 * @param fila
	 * @param color
	 * @return Nuevo movimiento
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoConecta4(col, color);
	}

	/**
	 * Devuelve un jugador humano nuevo
	 * @param in
	 * @return Nuevo jugador humano
	 */
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoConecta4(in, this);
	}

	/**
	 * Devuelve un jugador aleatorio nuevo 
	 * @return Nuevo jugador aleatorio
	 */
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4(this);
	}

}
