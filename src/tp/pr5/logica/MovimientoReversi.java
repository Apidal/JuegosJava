package tp.pr5.logica;

import java.util.ArrayList;


public class MovimientoReversi extends Movimiento{
	
	private ArrayList<Coordenadas> volteos;
	private ArrayList<Coordenadas> casillas;
	
	/**
	 * Constructor de la clase
	 * @param donde Columna
	 * @param fila Fila
	 * @param color Turno
	 */
	public MovimientoReversi(int columna, int fila, Ficha color){
		this.columna = columna;
		this.fila = fila;
		this.jugador = color;
		this.volteos = new ArrayList<Coordenadas>();
	}

	/**
	 * Si es posible realiza el movimiento
	 * @param tab
	 * @throws MovimientoInvalido
	 */
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido{
		
		int cont = 0;
		boolean encontrado = false;
		Coordenadas c = null;
		if(puedePoner(this.jugador, tab)){
			if(this.columna >= 1 && this.columna <= tab.getAncho()){
				if(this.fila >= 1 && this.fila <= tab.getAlto()){
					if(tab.getCasilla(this.columna, this.fila) == Ficha.VACIA){
						
						
						while(cont < casillas.size() && !encontrado){
							c = casillas.get(cont);
							if((c.getCol() == this.columna) && (c.getFila() == this.fila))
								encontrado = true;
							cont++;
						}
						
						if(encontrado){
							volteaFichas(this.jugador, tab, c);
							tab.setCasilla(columna, fila, jugador);
								
						}
						else{//Lanza excepción si la casilla no es valida
							throw new MovimientoInvalido("Casilla Invalida");
						}
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
		else{
					
			throw new MovimientoInvalido("No puede poner");
		}
		
				
	}
	

	/**
	 * Deshace un movimiento de tipo reversi
	 * @param tab
	 */
	public void undo(Tablero tab) {
		
		tab.setCasilla(this.columna, this.fila, Ficha.VACIA);
		
		for(Coordenadas c: this.volteos){
			int col = c.getCol();
			int f = c.getFila();
			Ficha t = tab.getCasilla(col, f);
			tab.setCasilla(col, f, t.adversario());
		}
		
	}
	
	/**
	 * Da la vuelta a las fichas segun el movimiento y las almacena en un ArrayList
	 * @param jugador
	 * @param tab
	 * @param c
	 */
	private void volteaFichas(Ficha jugador, Tablero tab, Coordenadas c) {
		// TODO Auto-generated method stub
		if(creaGruposN(jugador, tab, c))
			volteaFichasN(jugador, tab, c);
		if(creaGuposNE(jugador, tab, c))
			volteaFichasNE(jugador, tab, c);
		if(creaGruposE(jugador, tab, c))
			volteaFichasE(jugador, tab, c);
		if(creaGruposSE(jugador, tab, c))
			volteaFichasSE(jugador, tab, c);
		if(creaGruposS(jugador, tab, c))
			volteaFichasS(jugador, tab, c);
		if(creaGruposSO(jugador, tab, c))
			volteaFichasSO(jugador, tab, c);
		if(creaGruposO(jugador, tab, c))
			volteaFichasO(jugador, tab, c);
		if(creaGruposNO(jugador, tab, c))
			volteaFichasNO(jugador, tab, c);
		
		
	}

	/**
	 * Voltea las fichas si es posible en la direccion NorOeste
	 * @param jugador
	 * @param tab
	 * @param c
	 */
	private void volteaFichasNO(Ficha jugador, Tablero tab, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col;
		fila = c.getFila() - 1;
		col = c.getCol() - 1;
		while(tab.getCasilla(col, fila) != jugador){
			
			volteos.add(new Coordenadas(col, fila));
			tab.setCasilla(col, fila, jugador);
			
			fila--;
			col--;

		}
		
	}

	/**
	 * Voltea las fichas si es posible en la direccion Oeste
	 * @param jugador
	 * @param tab
	 * @param c
	 */
	private void volteaFichasO(Ficha jugador, Tablero tab, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col;
		fila = c.getFila();
		col = c.getCol() - 1;
		while(tab.getCasilla(col, fila) != jugador){
			
			volteos.add(new Coordenadas(col, fila));
			tab.setCasilla(col, fila, jugador);
			
			col--;

		}
		
	}

	/**
	 * Voltea las fichas si es posible en la direccion SurOeste
	 * @param jugador
	 * @param tab
	 * @param c
	 */
	private void volteaFichasSO(Ficha jugador, Tablero tab, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col;
		fila = c.getFila() + 1;
		col = c.getCol() - 1;
		while(tab.getCasilla(col, fila) != jugador){
			
			volteos.add(new Coordenadas(col, fila));
			tab.setCasilla(col, fila, jugador);
			
			fila++;
			col--;

		}
		
	}

	/**
	 * Voltea las fichas si es posible en la direccion Sur
	 * @param jugador
	 * @param tab
	 * @param c
	 */
	private void volteaFichasS(Ficha jugador, Tablero tab, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col;
		fila = c.getFila() + 1;
		col = c.getCol();
		while(tab.getCasilla(col, fila) != jugador){
			
			volteos.add(new Coordenadas(col, fila));
			tab.setCasilla(col, fila, jugador);
			
			fila++;

		}
		
	}

	/**
	 * Voltea las fichas si es posible en la direccion SurEste
	 * @param jugador
	 * @param tab
	 * @param c
	 */
	private void volteaFichasSE(Ficha jugador, Tablero tab, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col;
		fila = c.getFila() + 1;
		col = c.getCol() + 1;
		while(tab.getCasilla(col, fila) != jugador){
			
			volteos.add(new Coordenadas(col, fila));
			tab.setCasilla(col, fila, jugador);
			
			fila++;
			col++;

		}
		
	}

	/**
	 * Voltea las fichas si es posible en la direccion Este
	 * @param jugador
	 * @param tab
	 * @param c
	 */
	private void volteaFichasE(Ficha jugador, Tablero tab, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col;
		fila = c.getFila();
		col = c.getCol() + 1;
		while(tab.getCasilla(col, fila) != jugador){
			
			volteos.add(new Coordenadas(col, fila));
			tab.setCasilla(col, fila, jugador);
			
			col++;

		}
		
	}

	/**
	 * Voltea las fichas si es posible en la direccion NorEste
	 * @param jugador
	 * @param tab
	 * @param c
	 */
	private void volteaFichasNE(Ficha jugador, Tablero tab, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col;
		fila = c.getFila() - 1;
		col = c.getCol() + 1;
		while(tab.getCasilla(col, fila) != jugador){
			
			volteos.add(new Coordenadas(col, fila));
			tab.setCasilla(col, fila, jugador);
			
			fila--;
			col++;

		}
		
	}

	/**
	 * Voltea las fichas si es posible en la direccion Norte
	 * @param jugador
	 * @param tab
	 * @param c
	 */
	private void volteaFichasN(Ficha jugador, Tablero tab, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col;
		fila = c.getFila() - 1;
		col = c.getCol();
		while(tab.getCasilla(col, fila) != jugador){
			
			volteos.add(new Coordenadas(col, fila));
			tab.setCasilla(col, fila, jugador);
			
			fila--;

		}
		
	}
	

	/**
	 * Nos indica si la ficha f puede poner en el tablero t
	 * @param f
	 * @param t
	 * @return devuelve true si f puede poner y false en caso contrario
	 */
	public boolean puedePoner(Ficha f, TableroInmutable t) {
		// TODO Auto-generated method stub
		
		Coordenadas c;
		boolean puede = false;
		casillas = new ArrayList<Coordenadas>();
		for(int i = 1; i <= t.getAncho(); i++){
			for(int j = 1; j <= t.getAlto(); j++){
				if(t.getCasilla(i, j) == Ficha.VACIA){
					c = new Coordenadas(i, j);
					if(creaGrupos(f, t, c)){
						casillas.add(c);
						puede = true;
					}
				}	
			}
		}
		return puede;
	}

	/**
	 * Nos indica si se crea algun grupo de fichas para voltear en la coordenada c
	 * @param f
	 * @param t
	 * @param c
	 * @return devuelve true si c tiene algun grupo para voltear y false en caso contrario
	 */
	private boolean creaGrupos(Ficha f, TableroInmutable t, Coordenadas c) {
		// TODO Auto-generated method stub
		if(creaGruposN(f,t,c))
			return true;
		if(creaGuposNE(f,t,c))
			return true;
		if(creaGruposE(f,t,c))
			return true;
		if(creaGruposSE(f,t,c))
			return true;
		if(creaGruposS(f,t,c))
			return true;
		if(creaGruposSO(f,t,c))
			return true;
		if(creaGruposO(f,t,c))
			return true;
		if(creaGruposNO(f,t,c))
			return true;
		else
			return false;
	}


	/**
	 * Nos indica si se crean grupos en la direccion Este
	 * @param f
	 * @param t
	 * @param c
	 * @return true si se crean grupos, false en caso contrario
	 */
	private boolean creaGruposE(Ficha f, TableroInmutable t, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col, cont;
		fila = c.getFila();
		col = c.getCol();
		cont = col + 1;
		if(col < t.getAncho() - 1){
			if(t.getCasilla(cont, fila).adversario() == f){
				cont++;
				while(cont <= t.getAncho()){
					if(t.getCasilla(cont, fila) == f)
						return true;
					if(t.getCasilla(cont, fila) == Ficha.VACIA)
						return false;
					
					cont++;
				}
			}
		}
		return false;
	}


	/**
	 * Nos indica si se crean grupos en la direccion NorOeste
	 * @param f
	 * @param t
	 * @param c
	 * @return true si se crean grupos, false en caso contrario
	 */
	private boolean creaGruposNO(Ficha f, TableroInmutable t, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col, contX, contY;
		fila = c.getFila();
		col = c.getCol();
		contX = col - 1;
		contY = fila - 1;
		if(col > 2  && fila > 2 ){
			if(t.getCasilla(contX, contY).adversario() == f){
				contX--;
				contY--;
				while(contX > 0 && contY > 0){
					if(t.getCasilla(contX, contY) == f)
						return true;
					if(t.getCasilla(contX, contY) == Ficha.VACIA)
						return false;
					
					contX--;
					contY--;
				}
			}
		}
		return false;
	}


	/**
	 * Nos indica si se crean grupos en la direccion Oeste
	 * @param f
	 * @param t
	 * @param c
	 * @return true si se crean grupos, false en caso contrario
	 */
	private boolean creaGruposO(Ficha f, TableroInmutable t, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col, cont;
		fila = c.getFila();
		col = c.getCol();
		cont = col - 1;
		if(col > 2){
			if(t.getCasilla(cont, fila).adversario() == f){
				cont--;
				while(cont > 0){
					if(t.getCasilla(cont, fila) == f)
						return true;
					if(t.getCasilla(cont, fila) == Ficha.VACIA)
						return false;
					
					cont--;
				}
				return false;
			}
			return false;
		}
		return false;
	}


	/**
	 * Nos indica si se crean grupos en la direccion SurOeste
	 * @param f
	 * @param t
	 * @param c
	 * @return true si se crean grupos, false en caso contrario
	 */
	private boolean creaGruposSO(Ficha f, TableroInmutable t, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col, contX, contY;
		fila = c.getFila();
		col = c.getCol();
		contX = col - 1;
		contY = fila + 1;
		if(col > 2  && (fila < t.getAlto() - 1)){
			if(t.getCasilla(contX, contY).adversario() == f){
				contX--;
				contY++;
				while(contX > 0 && contY <= t.getAlto()){
					if(t.getCasilla(contX, contY) == f)
						return true;
					if(t.getCasilla(contX, contY) == Ficha.VACIA)
						return false;
					
					contX--;
					contY++;
				}
			}
		}
		return false;
	}


	/**
	 * Nos indica si se crean grupos en la direccion Sur
	 * @param f
	 * @param t
	 * @param c
	 * @return true si se crean grupos, false en caso contrario
	 */
	private boolean creaGruposS(Ficha f, TableroInmutable t, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col, cont;
		fila = c.getFila();
		col = c.getCol();
		cont = fila + 1;
		if(fila < t.getAlto() - 1){
			if(t.getCasilla(col, cont).adversario() == f){
				cont++;
				while(cont <= t.getAlto()){
					if(t.getCasilla(col, cont) == f)
						return true;
					if(t.getCasilla(col, cont) == Ficha.VACIA)
						return false;
					
					cont++;
				}
				return false;
			}
			return false;
		}
		
		return false;
		
	}


	/**
	 * Nos indica si se crean grupos en la direccion SurEste
	 * @param f
	 * @param t
	 * @param c
	 * @return true si se crean grupos, false en caso contrario
	 */
	private boolean creaGruposSE(Ficha f, TableroInmutable t, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col, contX, contY;
		fila = c.getFila();
		col = c.getCol();
		contX = col + 1;
		contY = fila + 1;
		if((col < t.getAncho() - 1)  && (fila < t.getAlto() - 1)){
			if(t.getCasilla(contX, contY).adversario() == f){
				contX++;
				contY++;
				while(contX <= t.getAncho() && contY <= t.getAlto()){
					if(t.getCasilla(contX, contY) == f)
						return true;
					if(t.getCasilla(contX, contY) == Ficha.VACIA)
						return false;
					
					contX++;
					contY++;
				}
			}
		}
		return false;
	}


	/**
	 * Nos indica si se crean grupos en la direccion NorEste
	 * @param f
	 * @param t
	 * @param c
	 * @return true si se crean grupos, false en caso contrario
	 */
	private boolean creaGuposNE(Ficha f, TableroInmutable t, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col, contX, contY;
		fila = c.getFila();
		col = c.getCol();
		contX = col + 1;
		contY = fila - 1;
		if((col < t.getAncho() - 1)  && fila > 2 ){
			if(t.getCasilla(contX, contY).adversario() == f){
				contX++;
				contY--;
				while(contX <= t.getAncho() && contY > 0){
					if(t.getCasilla(contX, contY) == f)
						return true;
					if(t.getCasilla(contX, contY) == Ficha.VACIA)
						return false;
					
					contX++;
					contY--;
				}
			}
		}
		return false;
	}

	
	/**
	 * Nos indica si se crean grupos en la direccion Norte
	 * @param f
	 * @param t
	 * @param c
	 * @return true si se crean grupos, false en caso contrario
	 */
	private boolean creaGruposN(Ficha f, TableroInmutable t, Coordenadas c) {
		// TODO Auto-generated method stub
		int fila, col, cont;
		fila = c.getFila();
		col = c.getCol();
		cont = fila - 1;
		if(fila > 2){
			if(t.getCasilla(col, cont).adversario() == f){
				cont--;
				while(cont > 0){
					if(t.getCasilla(col, cont) == f)
						return true;
					if(t.getCasilla(col, cont) == Ficha.VACIA)
						return false;
					
					cont--;
				}
				return false;
			}
			return false;
		}
		
		return false;
	}
	
	
	/**
	 * Devulve un ArrayList que nos indica los movimientos posibles para el jugador actual del
	 * movimiento, lo devuelve vacio en caso de no ecistir ningun movimiento posible
	 * @param t
	 * @return devuelve un ArrayList de Coordenadas con los posibles movimientos
	 */
	public ArrayList<Coordenadas> movPosibles(TableroInmutable t){
		
		
		if(!puedePoner(this.jugador, t)){
			//lo limpiamos para asegurarnos de que no contenga valores extraños
			casillas.clear();
		}
		
		return casillas;
		
	}
	

	

}
