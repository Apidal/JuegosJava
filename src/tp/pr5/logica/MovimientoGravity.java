package tp.pr5.logica;

public class MovimientoGravity extends Movimiento{
	
	private int dy = 0;
	private int dx = 0;
	
	/**
	 * Constructor de la clase
	 * @param donde Columna
	 * @param fila Fila
	 * @param color Turno
	 */
	public MovimientoGravity(int columna, int fila, Ficha color){
		this.columna = columna;
		this.fila = fila;
		this.jugador = color;
	}

	/**
	 * Si es posible realiza el movimiento
	 * @param tab
	 * @throws MovimientoInvalido
	 */
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido{
		
		int dA; //distancia al borde superior
		int dB; //distancia al borde inferior
		int dD; //distancia al borde derecho
		int dI; //distancia al borde izquierdo
		
		if(this.columna >= 1 && this.columna <= tab.getAncho()){
			if(this.fila >= 1 && this.fila <= tab.getAlto()){
				if(tab.getCasilla(this.columna, this.fila) == Ficha.VACIA){
					
					dA = this.fila - 1;
					dB = tab.getAlto() - this.fila;
					dD = tab.getAncho() - this.columna;
					dI = this.columna - 1;
					
					//CRUZ
					comprobarCruz(dA, dB, dD, dI);
					//Cuadrantes
					if((dD != dI) && (dA != dB)){
						//Comprueba cuadrante arriba-izquierda
						if((dI < dD) && (dA < dB)){
							cuadrante1(dA, dI);
						}
						//Comprueba cuadrante abajo-izquierda
						else if((dI < dD) && (dA > dB)){
							cuadrante2(dB, dI);
						}
						//Comprueba cuadrante abajo-derecha
						else if((dI > dD) && (dA > dB)){
							cuadrante3(dB, dD);
						}
						//Comprueba cuadrante arriba-derecha
						else if((dI > dD) && (dA < dB)){
							cuadrante4(dA, dD);
						}
					}
					if((this.dx != 0) || (this.dy != 0)){
						moverFilaYColumna(tab);
					}
					tab.setCasilla(this.columna, this.fila, this.jugador);
					
				}
				else{//Lanza excepción si la casilla está ocupada
					throw new MovimientoInvalido("Casilla ocupada.");
				}
				
			}
			else{//Lanza excepción si la posición es incorrecta
				throw new MovimientoInvalido("Posición incorrecta.");
			}
		}
		else{//Lanza excepción si la posición es incorrecta
			throw new MovimientoInvalido("Posición incorrecta.");
		}
		
	}
	
	/**
	 * "Quita" la última ficha puesta
	 * @param tab
	 */
	public void undo(Tablero tab){
		
		tab.setCasilla(this.columna, this.fila, Ficha.VACIA);
	}
	
	/**
	 * Comprueba si esta en las medianas del tablero
	 * @param dA
	 * @param dB
	 * @param dD
	 * @param dI
	 */
	private void comprobarCruz(int dA, int dB, int dD, int dI){
		if(dD == dI){
			if(dA > dB){
				this.dy = 1;
			}
			if(dA < dB){
				this.dy = -1;
			}
		}
		if(dA == dB){
			if(dI > dD){
				this.dx = 1;
			}
			if(dD > dI){
				this.dx = -1;
			}
		}
	}
	
	/**
	 * Modifica la fila y la columna hasta encontrar una ficha o el borde del tablero
	 * @param tab
	 */
	private void moverFilaYColumna(Tablero tab){
		while((tab.getCasilla(this.columna + this.dx, this.fila + this.dy) == Ficha.VACIA) &&
		(this.columna != 1) && (this.columna != tab.getAncho()) && (this.fila != 1) && (this.fila != tab.getAlto())){
			this.columna = this.columna + this.dx;
			this.fila = this.fila + this.dy;
		}
	}
	
	/**
	 * Comprueba cuadrante arriba-izquierda 
	 * @param dA
	 * @param dI
	 */
	private void cuadrante1(int dA, int dI){
		if(dA == dI){
			this.dx = -1;
			this.dy = -1;
		}
		else if (dA < dI){
			this.dy = -1;
		}
		else {
			this.dx = -1;
		}
	}
	
	/**
	 * Comprueba cuadrante abajo-izquierda
	 * @param dB
	 * @param dI
	 */
	private void cuadrante2(int dB, int dI){
		if(dB == dI){
			this.dx = -1;
			this.dy = 1;
		}
		else if (dB < dI){
			this.dy = 1;
		}
		else {
			this.dx = -1;
		}
	}
	
	/**
	 * Comprueba cuadrante abajo-derecha
	 * @param dB
	 * @param dD
	 */
	private void cuadrante3(int dB, int dD){
		if(dB == dD){
			this.dx = 1;
			this.dy = 1;
		}
		else if (dB < dD){
			this.dy = 1;
		}
		else {
			this.dx = 1;
		}
	}
	
	/**
	 * Comprueba cuadrante arriba-derecha
	 * @param dA
	 * @param dD
	 */
	private void cuadrante4(int dA, int dD){
		if(dA == dD){
			this.dx = 1;
			this.dy = -1;
		}
		else if (dA < dD){
			this.dy = -1;
		}
		else {
			this.dx = 1;
		}
	}

}
