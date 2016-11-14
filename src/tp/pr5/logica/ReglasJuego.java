package tp.pr5.logica;

public interface ReglasJuego {

	Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);
	
	Tablero iniciaTablero();
	
	Ficha jugadorInicial();
	
	Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);
	
	boolean tablas(Ficha ultimoEnPoner, Tablero t);
	
	TipoJuego getJuego();
}