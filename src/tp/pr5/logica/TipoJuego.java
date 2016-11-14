package tp.pr5.logica;

public enum TipoJuego {
	CONECTA4("Conecta 4"),
	COMPLICA("Complica"),
	GRAVITY("Gravity"),
	REVERSI("Reversi");
	
	
	private String description;
	
	private TipoJuego(String desc){
		this.description = desc;
	}
	
	/**
	 * Indica si el enumerado de tipo juego actual en redimensionable
	 * @return
	 */
	public boolean esRedimensionable(){
		
		if(this == GRAVITY)
			return true;
		
		return false;
	}

	/**
	 * Indica mediante un Sring a que jeugo estamos jugando
	 * @return devuelve la descripcion del juego
	 */
	public String getDescription(){
		
		return this.description;
	}
}
