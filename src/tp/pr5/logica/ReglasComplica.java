package tp.pr5.logica;


public class ReglasComplica extends ReglasGenerales {
	
	private boolean grupoB;
	private boolean grupoN;
	
	
	/**
	 * Constructor de la clase
	 */
	public ReglasComplica(){
		this.tx = 4;
		this.ty = 7;
	}
	
	/**
	 * Devuelve el valor del ganador
	 * @param ultimoMovimiento
	 * @param t Tablero
	 * @return compruebaGrupos(t) Valor del ganador
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t){
		
		return compruebaGrupos(t);
	}
	
	/**
	 * Devuelve false. ya que en complica no pueden quedar en tablas
	 * @return false
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t){
		return false;
	}
	
	/**
	 * Esta función comprueba los distintos grupos posibles
	 * y devuelve la ficha que ha generado grupo
	 * @param tab
	 * @return grupo El color del jugador que haya generado grupo. Vacía si no hay grupo
	 */
	private Ficha compruebaGrupos(Tablero tab){
		Ficha grupo = Ficha.VACIA;
		this.grupoB = false;
		this.grupoN = false;
		//Se comprueba todo el tablero puesto que pueden generase grupos
		//en distintos lugares por desplazamiento de las columnas
		//sin ser necesariamente en el ultimo lugar donde se coloca la ficha
		for(int col = 1; col <= tab.getAncho(); col++){
			compruebaVertical(col, tab);
			if(!this.grupoB || !this.grupoN){
				compruebaHorizontal(col, tab);
				if(!this.grupoB || !this.grupoN)
					comprobarDiagonales(col, tab);
			}
		}
		
		//Si ha encontrado grupos de ambos colores devolvera VACIA
		//En caso contrario devolvera la ficha correspondiente al grupo ganador
		if(this.grupoB && !this.grupoN)
			grupo = Ficha.BLANCA;
		else if(this.grupoN && !this.grupoB)
			grupo = Ficha.NEGRA;
		
		return grupo;
	}
	
	
	/**
	 * Comprueba los posibles grupos verticales
	 * @param col
	 * @param tab
	 */
	private void compruebaVertical(int col, Tablero tab){
		int fila = tab.getAlto();
		int filaAux = tab.getAlto()-1;
		Ficha color;
		int cont = 1;
		while((filaAux >= 1) && (tab.getCasilla(col, filaAux) != Ficha.VACIA) && (!this.grupoB || !this.grupoN)){
			color = tab.getCasilla(col, fila);
			if(tab.getCasilla(col, filaAux) == color){
				filaAux--;
				cont++;	
				if(cont == 4){
					if(color == Ficha.BLANCA)
						this.grupoB = true;
					else
						this.grupoN = true;
				}
			}
			else{
				cont = 1;
				fila = filaAux;
				filaAux--;
			}				
		}
	}

	/**
	 * Esta función comprueba los distintos grupos horizontales posibles
	 * @param col
	 * @param tab
	 */
	private void compruebaHorizontal(int col, Tablero tab){
		int cont;
		int colAux;
		Ficha color;
		for(int fila = 1;fila <= tab.getAlto() && (!this.grupoB || !this.grupoN); fila++){
			color = tab.getCasilla(col, fila);
			if(color != Ficha.VACIA){
				cont = 1;
				colAux = col - 1;
				//Comprueba desde la ficha hacia la izquierda
				while((colAux > 0) && (tab.getCasilla(colAux, fila) == color) && (cont < 4)){
					cont++;
					colAux--;
				}
				colAux = col + 1;
				//Comprueba desde la ficha hacia la derecha
				while((colAux <= tab.getAncho()) && (tab.getCasilla(colAux, fila) == color) && (cont < 4)){
					cont++;
					colAux++;
				}
				if(cont == 4){
					if(color == Ficha.BLANCA)
						this.grupoB = true;
					else
						this.grupoN = true;
				}
			}
		}
	}
	

	/**
	 * Esta función comprueba los distintos grupos diagonales posibles
	 * @param col
	 * @param tab
	 */
	private void comprobarDiagonales(int col, Tablero tab){
		//Primero comprueba si hay grupos en la diagonalA, que es
		//la que va desde abajo a la izquierda a arriba a la derecha
		diagonalA(col, tab);
		//Y si no hay grupos, comprueba si hay grupos en la otra diagonal
		if(!grupoN || !grupoB)
			diagonalB(col, tab);
	}	
	
	
	/**
	 * Comprueba si hubiera grupo en la diagonal
	 * que va desde abajo a la izquierda a arriba a la derecha
	 * @param col
	 * @param tab
	 */
	private void diagonalA(int col, Tablero tab){
		int cont, colAux, filaAux;
		Ficha color;
		for(int fila = 1;fila <=tab.getAlto() && (!this.grupoB || !this.grupoN); fila++){
			color = tab.getCasilla(col, fila);
			if(color != Ficha.VACIA){
				cont = 1;
				colAux = col - 1;
				filaAux = fila + 1;
				
				//Comprueba desde la última columna puesta hacia abajo a la izquierda
				while((colAux > 0) && (filaAux <= tab.getAlto()) && (tab.getCasilla(colAux, filaAux) == color) && (cont < 4)){
					cont++;
					colAux--;
					filaAux++;
				}
				colAux = col + 1;
				filaAux = fila - 1;
				//Comprueba desde la última columna puesta hacia arriba a la derecha
				while((colAux <= tab.getAncho()) && (filaAux > 0) && (tab.getCasilla(colAux, filaAux) == color) && (cont < 4)){
					cont++;
					colAux++;
					filaAux--;
				}
				if(cont == 4){
					if(color == Ficha.BLANCA)
						this.grupoB = true;
					else
						this.grupoN = true;
				}
			}
		}
	}

	/**
	 * Comprueba si hubiera grupo en la diagonal 
	 * que va desde arriba a la izquierda a abajo a la derecha
	 * @param col
	 * @param tab
	 */
	private void diagonalB(int col, Tablero tab){
		int cont, colAux, filaAux;
		Ficha color;
		
		for(int fila = 1;fila <=tab.getAlto() && (!this.grupoB || !this.grupoN); fila++){
			color = tab.getCasilla(col, fila);
			if(color != Ficha.VACIA){
				cont = 1;
				colAux = col - 1;
				filaAux = fila - 1;
				
				//Comprueba desde la última columna puesta hacia arriba a la izquierda
				while((colAux > 0) && (filaAux > 0) && (tab.getCasilla(colAux, filaAux) == color) && (cont < 4)){
					cont++;
					colAux--;
					filaAux--;
				}
				colAux = col + 1;
				filaAux = fila + 1;
				//Comprueba desde la última columna puesta hacia abajo a la derecha
				while((colAux <= tab.getAncho()) && (filaAux <= tab.getAlto()) && (tab.getCasilla(colAux, filaAux) == color) && (cont < 4)){
					cont++;
					colAux++;
					filaAux++;
				}
				if(cont == 4){
					if(color == Ficha.BLANCA)
						this.grupoB = true;
					else
						this.grupoN = true;
				}
			}
		}
	}

	/**
	 * Nos indica a que juego estamos jugando en este momento
	 * @return devuelve el tipo de juego actual
	 */
	public TipoJuego getJuego() {
		
		return TipoJuego.COMPLICA;
	}
	
}
