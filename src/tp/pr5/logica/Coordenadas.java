package tp.pr5.logica;

public class Coordenadas {
	
	private int col;
	private int fil;
	
	/**
	 * constructor de la clase Coordenadas
	 * @param columna
	 * @param fila
	 */
	public Coordenadas(int columna, int fila){
		this.col = columna;
		this.fil = fila;
	}
	
	/**
	 * Devuelve la componente vertical de una coordenada
	 * @return devuelve la fila
	 */
	public int getFila(){
		return this.fil;
	}
	
	/**
	 * Devuelve la componente horizontal de una coordenada
	 * @return devuelve la columna
	 */
	public int getCol(){
		return this.col;
	}
}
