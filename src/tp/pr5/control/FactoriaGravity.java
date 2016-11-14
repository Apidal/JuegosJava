package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.ReglasGravity;
import tp.pr5.logica.ReglasJuego;

public class FactoriaGravity implements FactoriaTipoJuego{

	private int tx;
	private int ty;
	
	/**
	 * Constructor de la clase
	 * @param cols
	 * @param filas
	 */
	public FactoriaGravity(int cols, int filas){
		this.tx = cols;
		this.ty = filas;
	}
	
	/**
	 * Devuelve unas nuevas reglas 
	 * @return Nuevas reglas
	 */
	public ReglasJuego creaReglas() {
		return new ReglasGravity(this.tx, this.ty);
	}

	/**
	 * Devuelve un nuevo movimiento
	 * @param col
	 * @param fila
	 * @param color
	 * @return Nuevo movimiento
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	/**
	 * Devuelve un jugador humano nuevo
	 * @param in
	 * @return Nuevo jugador humano
	 */
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoGravity(in, this);
	}

	/**
	 * Devuelve un jugador aleatorio nuevo
	 * @return Nuevo jugador aleatorio
	 */
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity(this);
	}

}
