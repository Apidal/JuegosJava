package tp.pr5.logica;


public class MovimientoComplica extends Movimiento {
	
	private Ficha excedente;
	
	/**
	 * Constructor de la clase
	 * @param donde Columna
	 * @param color Turno
	 */
	public MovimientoComplica(int donde, Ficha color){
		this.columna = donde;
		this.jugador = color;
		this.excedente = Ficha.VACIA;
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
			if(!ok){
				//En caso de que la columna este llena almacenamos la ficha excedente 
				this.excedente = tab.getCasilla(this.columna,tab.getAlto());
				//Y a continuación desplazamos toda la columna una posición hace abajo
				desplazarHaciaAbajo(tab);
				//Y colocamos la nueva ficha el la fila superior
				tab.setCasilla(this.columna, 1, this.jugador);
				ok = true;
			}
		}
		else{
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y "+tab.getAncho()+".");
		}
	}
	
	/**
	 * Desplaza todas las fichas de la columna una posición hacia abajo
	 * @param tab
	 */
	private void desplazarHaciaAbajo(Tablero tab){
		for(int i= tab.getAlto(); i > 1; i--){
			tab.setCasilla(this.columna, i, tab.getCasilla(this.columna, i-1));
		}
	}
	
	/**
	 * Desplaza todas las fichas de la columna una posición hacia arriba
	 * @param tab
	 */
	private void desplazarHaciaArriba(Tablero tab){
		for(int i = 1; i < tab.getAlto(); i++){
			tab.setCasilla(this.columna, i, tab.getCasilla(this.columna, i+1));
		}
	}
		
	/**
	 * Deshace el último moviemiento ejecutado
	 * @param tab
	 */
	public void undo(Tablero tab){
		
		if(!excedente.equals(Ficha.VACIA)){
			//Si tenemos ficha excedente subimos la columna una posición
			desplazarHaciaArriba(tab);
			//Y colocamos la ficha excedente en la fila inferior
			tab.setCasilla(this.columna, tab.getAlto(), excedente);
		}
		else {
			//Si no hay excedennte simplemente eliminamos la última ficha colocada
			tab.setCasilla(this.columna, getUltimaFila(this.columna, tab), Ficha.VACIA);
		}
		
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
