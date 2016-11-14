package tp.pr5.control;


import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class JugadorAleatorioComplica extends JugadorConsola {
	
	/**
	 * constructor de la clase
	 * @param factoria
	 */
	public JugadorAleatorioComplica(FactoriaTipoJuego factoria) {
		super(factoria);
	}
	
	/**
	 * Devuelve un movimiento válido
	 * @param tab
	 * @param color
	 * @return Movimiento creado
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color)  {
		
		int columna = randomCol(tab);
		
		//Pasamos un 1 en la fila puesto que no vamos a utilizarla
		//para crear el movimiento, por lo que no nos importa su valor
		return this.factoria.creaMovimiento(columna, 1,color);
	}
	
	/**
	 * Genera una columna aleatoriamente 
	 * @param tab
	 * @return entero que representa una columna
	 */
	private int randomCol(Tablero tab){
		return (int)(Math.random()*tab.getAncho()+1);
	}
	
}
