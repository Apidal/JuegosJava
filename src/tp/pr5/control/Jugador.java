package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Tablero;

public interface Jugador {

	Movimiento getMovimiento(Tablero tab, Ficha color) throws MovimientoInvalido ;
}
