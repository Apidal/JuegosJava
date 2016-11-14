package tp.pr5.logica;


public class Tablero implements TableroInmutable{
		
	private Ficha[][] tablero;
	private int ancho;
	private int alto;
	
	/**
	 * Constructor del tablero que le vienen por parametros
	 * las filas(ty) y las columnas(tx)
	 * @param tx
	 * @param ty
	 */
	public Tablero(int tx, int ty){
		//Si le llegan datos no válidos para crear un tablero
		//Por defecto creará un tablero de 1x1
		if(tx < 1){
			tx = 1;
		}
		if(ty < 1){
			ty = 1;
		}
		this.ancho = tx;
		this.alto = ty;		
		tablero = new Ficha[this.alto][this.ancho];
		reset();
	}
	
	/**
	 * Reinicia los valores del tablero colocando todas las casillas en VACIA
	 */
	public void reset(){
		for(int f = 0; f < this.alto; f++){
			for(int c = 0; c < this.ancho; c++){
				tablero[f][c] = Ficha.VACIA;
			}
		}
	}
	
	/**
	 * Devuelve el valor del alto del tablero 
	 * @return alto
	 */
	public int getAlto(){
		return this.alto;
	}
	
	/**
	 * Devuelve el valor del ancho del tablero 
	 * @return ancho
	 */
	public int getAncho(){
		return this.ancho;
	}

	/**
	 * Devuelve el valor de la casilla indicada
	 * @param x Columna
	 * @param y Fila
	 * @return ficha Color del la ficha
	 */
	public Ficha getCasilla(int x, int y){
		Ficha ficha = Ficha.VACIA;
		
		//Comprueba que la coordenada dada esta dentro del tablero
		if((x >= 1 && x <= this.ancho) && (y >= 1 && y <= this.alto))
			ficha = tablero[y-1][x-1];
		
		return ficha;
	}
	
	/**
	 * Coloca en el tablero la ficha en la coodenada indicada
	 * @param x columna
	 * @param y Fila
	 * @param color
	 */
	public void setCasilla(int x, int y, Ficha color){
		//Pone la ficha solo si está dentro del tablero
		if(x > 0 && x <= this.ancho && y > 0 && y <= this.alto)
			tablero[y-1][x-1] = color;
	}
	
	
	/**
	 * Transforma el tablero en un String
	 * @return tablero String con los caracteres que forman el tablero
	 */
	public String toString(){
		String tablero = "";
		
		for(int fila = 1; fila <= this.alto; fila++){
			tablero = tablero + "|";
			for(int col = 1; col <= this.ancho; col++){
				if(getCasilla(col, fila) == Ficha.BLANCA)
					tablero = tablero + "O";
				else if(getCasilla(col, fila) == Ficha.NEGRA)
					tablero = tablero + "X";
				else
					tablero = tablero +" ";
			}
			tablero = tablero + "|\n";
		}
		tablero = tablero +"+";
		for(int col = 0; col < this.ancho; col++){
			tablero = tablero + "-";
		}
		tablero = tablero +"+\n ";
		for(int col = 1; col <= this.ancho; col++){
			tablero = tablero + Integer.toString(col%10);
		}
		
		return tablero;
	}
	
}
