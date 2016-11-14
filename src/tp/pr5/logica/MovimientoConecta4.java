package tp.pr5.logica;

public class MovimientoConecta4 extends Movimiento {
		
	/**
	 * Constructor de la clase
	 * @param donde Columna
	 * @param color Turno
	 */
	public MovimientoConecta4(int donde, Ficha color){
		this.columna = donde;
		this.jugador = color;
	}
	
	
	/**
	 * Si es posible realiza el movimiento
	 * @param tab
	 * @throws MovimientoInvalido
	 */
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido{
		boolean ok = false;
		int fila;
		if(this.columna >= 1 && this.columna <= tab.getAncho()){
			fila = tab.getAlto();
			//Buscamos la primera casilla de la columna que este vacia
			while(fila >= 1 && !ok){
				if(tab.getCasilla(this.columna, fila) == Ficha.VACIA){
					
					tab.setCasilla(this.columna, fila, this.jugador);
					
					ok = true;
				}
				
				fila--;
			}
			if(!ok) throw new MovimientoInvalido("Columna llena.");
		}
		else{
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y "+tab.getAncho()+".");
		}	
	}
	
	/**
	 * "Quita" la última ficha puesta
	 * @param tab
	 */
	public void undo(Tablero tab){
		
		tab.setCasilla(this.columna, getUltimaFila(this.columna, tab), Ficha.VACIA);
	}
	

	/**
	 * Devuelve el número de la última fila ocupada de una columna
	 * @param col
	 * @param tab
	 * @return Última fila ocupada
	 */
	private int getUltimaFila(int col, Tablero tab){
		int fila = tab.getAlto();
		while((fila > 0) && (tab.getCasilla(col, fila) != Ficha.VACIA)){
			fila--;
		}
		return fila + 1;
	}
		
}

