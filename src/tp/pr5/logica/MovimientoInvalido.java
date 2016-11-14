package tp.pr5.logica;

public class MovimientoInvalido extends Exception{
	
	private String mensaje;
	public MovimientoInvalido(){
		
	}
	
	/**
	 * Constructor que recibe el mensaje de la excepción
	 * @param msg
	 */
	public MovimientoInvalido(java.lang.String msg){
		this.mensaje = msg;
	}
	
	public MovimientoInvalido(java.lang.String msg, java.lang.Throwable arg){
		
	}
	
	public MovimientoInvalido(java.lang.Throwable arg){
		
	}
	
	/**
	 * Devuelve el mensaje de la excepción
	 * @return mensaje
	 */
	public String getMensaje(){
		return this.mensaje;
	}
}
