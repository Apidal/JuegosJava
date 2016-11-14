package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Tablero;

public class JugadorHumanoGravity extends JugadorConsola {

	/**
	 * Constructor de la clase
	 * @param in
	 * @param factoria
	 */
	public JugadorHumanoGravity(Scanner in, FactoriaTipoJuego factoria){
		super(factoria);
		this.sc = in;
	}

	/**
	 * Crea un movimiento válido
	 * @param tab
	 * @param color
	 * @throws MovimientoInvalido
	 * @return Movimiento nuevo con la fila y la columna introducida por el jugador
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) throws MovimientoInvalido {
		
		System.out.print("Introduce la columna: ");
		int columna = sc.nextInt();
		
		System.out.print("Introduce la fila: ");
		int fila = sc.nextInt();
		sc.nextLine();
			
		return this.factoria.creaMovimiento(columna, fila,color);
				
	}

}