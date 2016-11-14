package tp.pr5.control;

import java.util.Scanner;


public class JugadorHumanoComplica extends JugadorConsola {
	
	/**
	 * Constructor de la clase
	 * @param in
	 * @param factoria
	 */
	public JugadorHumanoComplica(Scanner in, FactoriaTipoJuego factoria) {
		super(factoria);
		this.sc = in;
	}
		
}
