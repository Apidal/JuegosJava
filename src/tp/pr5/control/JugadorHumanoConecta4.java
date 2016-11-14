package tp.pr5.control;

import java.util.Scanner;


public class JugadorHumanoConecta4 extends JugadorConsola {

	/**
	 * Constructor de la clase
	 * @param in
	 * @param factoria
	 */
	public JugadorHumanoConecta4(Scanner in, FactoriaTipoJuego factoria) {
		super(factoria);
		this.sc = in;
	}

}
