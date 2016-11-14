package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.ReglasJuego;

public interface FactoriaTipoJuego {

	ReglasJuego creaReglas();
	
	Movimiento creaMovimiento(int col, int fila,  Ficha color);
	
	Jugador creaJugadorHumanoConsola(java.util.Scanner in);
	
	Jugador creaJugadorAleatorio();
	
}
