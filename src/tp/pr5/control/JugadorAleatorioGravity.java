package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioGravity extends JugadorConsola {
	
	/**
	 * Constructor de la clase 
	 * @param factoria
	 */
	public JugadorAleatorioGravity(FactoriaTipoJuego factoria) {
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
		
		//crea aleatoriamente un movimiento hasta que la casilla sea valida
		do{
			columna = randomCol(tab);
			fila = randomFila(tab);
			
		}while(tab.getCasilla(columna, fila) != Ficha.VACIA);

		return this.factoria.creaMovimiento(columna, fila,color);
	}
	
	/**
	 * Devuelve una columna aleatoriamente
	 * @param tab
	 * @return Un entero que representa una columna
	 */
	private int randomCol(Tablero tab){
		return (int)(Math.random()*tab.getAncho()+1);
	}
	
	/**
	 * Devuelve una fila aleatoriamente
	 * @param tab
	 * @return Un entero que representa una fila
	 */
	private int randomFila(Tablero tab){
		return (int)(Math.random()*tab.getAlto()+1);
	}
	
}
