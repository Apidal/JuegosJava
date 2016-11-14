
package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioConecta4 extends JugadorConsola {
	
	public JugadorAleatorioConecta4(FactoriaTipoJuego factoria) {
		super(factoria);
	}

	/**
	 * Devuelve un movimiento válido
	 * @param tab
	 * @param color
	 * @return Momiento creado
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		int columna;
		
		//crea aleatoriamente un movimiento hasta que la casilla sea valida
		do{
			columna = randomCol(tab);
		}while(tab.getCasilla(columna, 1) != Ficha.VACIA);
				
		//Pasamos un 1 en la fila puesto que no vamos a utilizarla
		//para crear el movimiento, por lo que no nos importa su valor
		return this.factoria.creaMovimiento(columna, 1,color);
	}
	
	/**
	 * Devuelve una columna aleatoriamente 
	 * @param tab
	 * @return Un entero que representa una columna
	 */
	private int randomCol(Tablero tab){
		return (int)(Math.random()*tab.getAncho()+1);
	}
	
}
