package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Tablero;

public class JugadorHumanoReversi extends JugadorConsola{
	
	/**
	 * Constructor del jugador humano de tipo reversi
	 * @param in
	 * @param factoria
	 */
	public JugadorHumanoReversi(Scanner in, FactoriaTipoJuego factoria){
		super(factoria);
		this.sc = in;
	}
	
	/**
	 * Captura un movimiento de la consola
	 * @param tab
	 * @param color
	 * @throws MovimientoInvalido
	 * @return devuelve un movimiento de tipo reversi
	 */
	public Movimiento getMovimiento(Tablero tab, Ficha color) throws MovimientoInvalido{
		
		System.out.print("Introduce la columna: ");
		int columna = sc.nextInt();
		
		System.out.print("Introduce la fila: ");
		int fila = sc.nextInt();
		sc.nextLine();
			
		return this.factoria.creaMovimiento(columna, fila,color);
	}

}
