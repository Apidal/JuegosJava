package tp.pr5.logica;

public abstract class Movimiento {

	protected int columna;
	protected int fila;
	protected Ficha jugador;
	
	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	
	/**
	 * Devuelve el jugador del movimiento
	 * @return Jugador
	 */
	public  Ficha getJugador(){
		return this.jugador;
	}
	
	/**
	 * Devuelve la columna del movimiento
	 * @return Columna
	 */
	public int getColumna(){
		return this.columna;
	}
	
	/**
	 * Devuelve la fila del movimiento
	 * @return Fila
	 */
	public int getFila(){
		return this.fila;
	}
	
	public abstract void undo(Tablero tab);

}