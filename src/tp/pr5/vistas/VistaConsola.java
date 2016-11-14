package tp.pr5.vistas;


import java.util.ArrayList;

import tp.pr5.control.ControladorConsola;
import tp.pr5.logica.Coordenadas;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.TipoTurno;

public class VistaConsola implements Observer{
	
	private ControladorConsola co;
	
	/**
	 * Constructor de la clase
	 * @param c
	 */
	public VistaConsola(ControladorConsola c){
		this.co = c;
		this.co.addObserver(this);
	}


	public void onReset(TableroInmutable tab, Ficha turno, TipoJuego juego) {
		// TODO Auto-generated method stub
		System.out.println("Partida reiniciada.");
		cabecera(tab, turno);
	}

	
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		// TODO Auto-generated method stub
		tablero.toString();
		if(ganador != Ficha.VACIA){
			System.out.print("\nGanan las " + ganador.toString() + ".");
		}
		else{
			System.out.print("\n" + ganador.toString());
		}
	}

	
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		// TODO Auto-generated method stub
		cabecera(tab, turno);
	}


	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		System.err.print("Imposible deshacer.\n");
		cabecera(tablero, turno);
	}

	
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		cabecera(tablero, turno);
	}

	
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador, Ficha turno) {
		// TODO Auto-generated method stub
		cabecera(tablero, turno);
		
	}

	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		System.err.print(movimientoException.getMensaje() + "\n");
	}
	
	/**
	 * Muestra el tablero, el turno y pregunta al usuario que quiere hacer
	 * @param tablero
	 * @param turno
	 */
	private void cabecera(TableroInmutable tablero, Ficha turno){
		System.out.print(tablero.toString());
		System.out.print("\n\nJuegan las " + turno.getDescription());
		System.out.print("\n¿Qué quieres hacer? ");
	}


	@Override
	public void onMovimientoStart(TipoTurno turno, TableroInmutable tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPosibles(ArrayList<Coordenadas> posibles) {
		// TODO Auto-generated method stub
		
	}
	
}
