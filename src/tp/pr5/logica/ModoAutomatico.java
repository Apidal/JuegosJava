package tp.pr5.logica;

import tp.pr5.control.ControladorGUI;

public class ModoAutomatico implements Modo {

	private Thread hebra;
	private ControladorGUI control;


	/**
	 * Constructor de la clase  ModoAutomatico
	 * @param c
	 */
	public ModoAutomatico(ControladorGUI c){
		this.control = c;
	}
	
	
	/**
	 * Inicia una nueva hebra e implementa el metod run() de la misma
	 */
	public void comenzar() {
		this.hebra = new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					control.ponerAleatorio();
				} catch (InterruptedException e) {}
			};
		};
		this.hebra.start();
	}
	

	/**
	 * Interrumpe la hebra para finalizar su ejecucion
	 */
	public void terminar() {
		if(this.hebra != null){
			this.hebra.interrupt();
		}
	}
	
}
