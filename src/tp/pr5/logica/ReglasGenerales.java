package tp.pr5.logica;

public abstract class ReglasGenerales implements ReglasJuego{

	protected int tx;
	protected int ty;
	
    public abstract Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);

    /**
	 * Nos devuelve el tablero inicializado con las reglas del juego
	 * @return Tablero inicializado
	 */
	public Tablero iniciaTablero(){
		
		return new Tablero(tx,ty);
	}
	
	/**
	 * Nos devuelve el jugador inicial
	 * @return Ficha.BLANCA Siempre inician partida las blancas
	 */
	public Ficha jugadorInicial(){
		
		return Ficha.BLANCA;
	}
	
	/**
	 * Cambia el turno actual segun que jugador ha puesto en ultimo lugar
	 * @param ultimoEnPoner
	 * @param t Tablero
	 * @return turno
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t){
		Ficha turno = Ficha.NEGRA;
		
			if(ultimoEnPoner == Ficha.NEGRA)
				turno = Ficha.BLANCA;
			
		return turno;
	}
	
	 public abstract boolean tablas(Ficha ultimoEnPoner, Tablero t);
	 
	
		/**
		 * Esta función comprueba los distintos grupos horizontales posibles
		 * @param col
		 * @param fila
		 * @param color
		 * @param tab
		 * @return True si hay grupo
		 */
		protected boolean compruebaHorizontal(int col, int fila, Ficha color, Tablero tab){
			boolean grupo = false;
			int cont = 1;
			int colAux = col - 1;
			//comprueba desde la ficha hacia la izquierda
			while((colAux > 0) && (tab.getCasilla(colAux, fila) == color) && (cont < 4)){
				cont++;
				colAux--;
			}
			colAux = col + 1;
			//comprueba desde la ficha hacia la derecha
			while((colAux <= tab.getAncho()) && (tab.getCasilla(colAux, fila) == color) && (cont < 4)){
				cont++;
				colAux++;
			}
			if(cont == 4){
				grupo = true;
			}
			return grupo;
		}

		/**
		 * Esta función comprueba los distintos grupos diagonales posibles
		 * @param col
		 * @param fila
		 * @param color
		 * @param tab
		 * @return grupo True si hay un grupo generado
		 */
		protected boolean comprobarDiagonales(int col, int fila, Ficha color, Tablero tab){
			boolean grupo = true;
			//Primero comprueba si hay grupo en la diagonalA, que es
			//la que va desde abajo a la izquierda a arriba a la derecha
			if(!diagonalA(col, fila, color,tab)){
				//Y si no hay grupo, comprueba si hay grupo en la otra diagonal
				if(!diagonalB(col, fila, color, tab)){
					grupo = false;
				}
			}
			return grupo;
		}
		
		/**
		 * Comprueba si hubiera grupo en la diagonal
		 * que va desde abajo a la izquierda a arriba a la derecha
		 * @param col
		 * @param fila
		 * @param color
		 * @param tab
		 * @return True si hay grupo
		 */
		private boolean diagonalA(int col, int fila, Ficha color, Tablero tab){
			boolean grupo = false;
			int cont = 1;
			int colAux = col - 1;
			int filaAux = fila + 1;
			//Comprueba desde la ultima ficha puesta hacia abajo a la izquierda
			while((colAux > 0) && (filaAux <= tab.getAlto()) && (tab.getCasilla(colAux, filaAux) == color) && (cont < 4)){
				cont++;
				colAux--;
				filaAux++;
			}
			colAux = col + 1;
			filaAux = fila - 1;
			//Comprueba desde la última ficha puesta hacia arriba a la derecha
			while((colAux <= tab.getAncho()) && (filaAux > 0) && (tab.getCasilla(colAux, filaAux) == color) && (cont < 4)){
				cont++;
				colAux++;
				filaAux--;
			}
			if(cont == 4){
				grupo = true;
			}
			return grupo;
		}
		
		/**
		 * Comprueba si hubiera grupo en la diagonal 
		 * que va desde arriba a la izquierda a abajo a la derecha
		 * @param col
		 * @param fila
		 * @param color
		 * @param tab
		 * @return True si hay grupo
		 */
		private boolean diagonalB(int col, int fila, Ficha color, Tablero tab){
			boolean grupo = false;
			int cont = 1;
			int colAux = col - 1;
			int filaAux = fila - 1;
			
			//Comprueba desde la última ficha puesta hacia arriba a la izquierda
			while((colAux > 0) && (filaAux > 0) && (tab.getCasilla(colAux, filaAux) == color) && (cont < 4)){
				cont++;
				colAux--;
				filaAux--;
			}
			colAux = col + 1;
			filaAux = fila + 1;
			//Comprueba desde la última ficha puesta hacia abajo a la derecha
			while((colAux <= tab.getAncho()) && (filaAux <= tab.getAlto()) && (tab.getCasilla(colAux, filaAux) == color) && (cont < 4)){
				cont++;
				colAux++;
				filaAux++;
			}
			if(cont == 4){
				grupo = true;
			}
			return grupo;
		}
}
