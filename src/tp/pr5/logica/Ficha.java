package tp.pr5.logica;

import java.awt.Color;

import tp.pr5.control.ControladorGUI;

/**
 * Declaración del enumerado
 */
public enum Ficha {
	BLANCA(TipoTurno.HUMANO, Color.white, "Blancas", new ModoHumano()),
	NEGRA(TipoTurno.HUMANO, Color.black, "Negras", new ModoHumano()),
	VACIA(TipoTurno.HUMANO, new Color(102,204,102), "TABLAS", new ModoHumano());
	
	private TipoTurno turno;
	private Color color;
	private String description;
	private Modo modo;
	
	/**
	 * constructor del enumerado Ficha
	 * @param turno
	 * @param color
	 * @param desc
	 * @param modo
	 */
	private Ficha(TipoTurno turno, Color color, String desc, Modo modo){
		this.turno = turno;
		this.color = color;
		this.description = desc;
		this.modo = modo;
	}
	
	/**
	 * Informa del tipo de turno actual del enumerado
	 * @return devuelve el turno actual
	 */
	public TipoTurno getTurno(){
		
		return this.turno;
	}
	
	/**
	 * Establece el nuevo tipo de turno para el enumerado
	 * @param t
	 */
	public void setTurno(TipoTurno t){
		
		this.turno = t;
	}
	
	/**
	 * Informa del color actual del enumerado
	 * @return devuelve el color actual
	 */
	public Color getColor(){
		
		return this.color;
	}
	
	/**
	 * Establece el nuevo color para el enumerado
	 * @param c
	 */
	public void setColor(Color c){
		
		this.color = c;
	}
	
	/**
	 * Informa de la descripcion actual del enumerado
	 * @return devuelve la descripcion actual
	 */
	public String getDescription(){
		
		return this.description;
	}
	
	/**
	 * Establece la nueva descripcion para el enumerado
	 * @param d
	 */
	public void setDescription(String d){
		
		this.description = d;;
	}
	
	/**
	 * Informa del modo actual del enumerado
	 * @return devuelve el modo actual
	 */
	public Modo getModo(){
		
		return this.modo;
	}
	
	/**
	 * Establece el nuevo modo para el enumerado
	 * @param m
	 */
	public void setModo(Modo m){
		this.modo = m;
	}
	
	/**
	 * Indica el adversario de una ficha
	 * @return devuevlve el enumerado "contrario" al actual
	 */
	public Ficha adversario(){
		
		if(this == BLANCA)
			return NEGRA;
		else if(this == NEGRA)
			return BLANCA;
		else
			return VACIA;
	}
	
}
