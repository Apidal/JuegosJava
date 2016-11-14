package tp.pr5.control;

import java.util.InputMismatchException;
import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Tablero;

public class JugadorConsola implements Jugador {

protected Scanner sc;
protected FactoriaTipoJuego factoria;
	
	/**
	 * Constructor de la clase
	 * @param factoria
	 */
	public JugadorConsola(FactoriaTipoJuego factoria) {
		this.factoria = factoria;
	}
	
	/**
	 * Devuelve un movimiento válido
	 * @param tab
	 * @param color
	 * @throws MovimientoInvalido
	 * @return Movimiento con la columna introducida por el jugador
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) throws MovimientoInvalido {
		
		try{
			System.out.print("Introduce la columna: ");
			String col = sc.nextLine();
			int columna = Integer.parseInt(col);
			
			
			//Pasamos un 1 en la fila puesto que no vamos a utilizarla
			//para crear el movimiento de Conecta4 y Complica,
			//por lo que no nos importa su valor
			return this.factoria.creaMovimiento(columna, 1, color);
		}
		catch(NumberFormatException n){
			System.err.println("No te entiendo.");
			throw new MovimientoInvalido();
		}
			
		
	}
	
}
