package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.ReglasComplica;
import tp.pr5.logica.ReglasJuego;

public class FactoriaComplica implements FactoriaTipoJuego{

	/**
	 * Devuelve unas nuevas reglas
	 * @return Nuevas reglas
	 */
	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

	/**
	 * Devuelve un nuevo movimiento 
	 * @param col
	 * @param fila
	 * @param color
	 * @return Nuevo movimiento
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col, color);
	}
	
	/**
	 * Devuelve un jugador humano nuevo
	 * @param in
	 * @return Nuevo jugador Humano
	 */
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoComplica(in, this);
	}

	/**
	 * Devuelve un jugador aleatorio nuevo 
	 * @return Nuevo jugador aleatorio
	 */
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica(this);
	}

}
