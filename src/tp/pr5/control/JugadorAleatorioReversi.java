package tp.pr5.control;

import java.util.ArrayList;

import tp.pr5.logica.Coordenadas;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.ReglasReversi;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioReversi extends JugadorConsola {
	
	private ArrayList<Coordenadas> movs;
	
	/**
	 * Constructor de la clase 
	 * @param factoria
	 */
	public JugadorAleatorioReversi(FactoriaTipoJuego factoria) {
		super(factoria);
	}
	
	/**
	 * Devuelve un movimiento válido
	 * @param tab
	 * @param color
	 * @return Movimiento creado
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		int columna;
		int fila;
		Coordenadas c;
		//Creamos un movimiento falso para que nos diga en que casillas se puede poner
		MovimientoReversi m = new MovimientoReversi(0,0,color);
		//crea aleatoriamente un movimiento hasta que la casilla sea valida
		movs = m.movPosibles(tab);
		c = movs.get(randomPos());
		columna = c.getCol();
		fila = c.getFila();

		return this.factoria.creaMovimiento(columna, fila,color);
	}
	
	/**
	 * Devuelve una posicion aleatoriamente
	 * @return Un entero que representa una posicion del array movs(movimientos Posibles)
	 */
	private int randomPos(){
		return (int)(Math.random()*movs.size());
	}
	
	
}
