package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.vistas.Observer;

public class ControladorConsola {
	
	private Partida partida;
	private Scanner in;
	private FactoriaTipoJuego factoria;
	//Se crea para saber si el usuario introduce Salir
	private boolean salir;
	private ReglasJuego reglas;
	private Jugador jugador1;
	private Jugador jugador2;
	
	/**
	 * Constructor
	 * @param factoria
	 * @param p
	 * @param in
	 */
	public ControladorConsola(FactoriaTipoJuego factoria, Partida p, Scanner in){
		this.factoria = factoria;
		this.partida = p;
		this.in = in;	
	}
	
	/**
	 * Ejecuta el desarrollo del juego
	 */
	public void run() {
		
		this.salir = false;
		this.reglas = this.factoria.creaReglas();
		//------
		this.jugador1 = this.factoria.creaJugadorHumanoConsola(this.in);
		this.jugador2 = this.factoria.creaJugadorHumanoConsola(this.in);
		//------
		
		this.partida.iniciar();
		//Mientras la partida no acabe o uno de los usuarios salga pide opciones
 		while(!partida.isTerminada() && !salir){
 			
			String option;
			option = this.in.nextLine();
			option = option.toLowerCase();
			//Introduce palabra por palabra en un array
			String[] optionArray = option.split(" ");
			
			opciones(optionArray);
		}
	}
	
	/**
	 * Ejecuta la opción elegida
	 * @param option
	 */
	private void opciones(String[] option){
		Movimiento mov;

		if(option.length == 1){
			switch(option[0]){
				case "salir":{
					this.salir = true;
					break;
				}
				
				//Opción para colocar una nueva ficha en el tablero
				case "poner":{
					if(this.partida.getTurno() == Ficha.BLANCA)
						mov = this.partida.dameMov(this.jugador1);
					else
						mov = this.partida.dameMov(this.jugador2);
					
					if(mov!= null){
						this.partida.ejecutaMovimiento(mov);
					}
					break;
				}
				
				//Opción para deshacer el último movimiento
				case "deshacer":{
						this.partida.undo();
						break;
				}
				
				//Se reinicia la partida con las reglas actuales
				case "reiniciar":{
					this.partida.reinicia(reglas);
					break;
				}
				
				case "ayuda":{
					System.out.print(ayuda());
					break;
				}
								
				default: System.err.println("No te entiendo.");
			}
		}
		else{
			if(option[0].equals("jugar")){
				opcionesJugar(option);
			}
			else if(option[0].equals("jugador")){
				cambiaJugador(option);
			}
			else{
				System.err.println("No te entiendo.");
			}
			
		}
					
	}
	
	/**
	 * Crea la ayuda a mostrar
	 * @return
	 */
	public String ayuda() {
		String ayuda = null;
		
		ayuda = "Los comandos disponibles son:\n\n"
		+	"PONER: utilizalo para poner la siguiente ficha.\n"
		+ 	"DESHACER: deshace el último movimiento hecho en la partida.\n"
		+	"REINICIAR: reinicia la partida.\n"
		+	"JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego.\n"
		+	"JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.\n"
		+	"SALIR: termina la aplicación.\n"
		+	"AYUDA: muestra esta ayuda.\n\n";
		return ayuda;
	}
	
	/**
	 * Cambia de juego segun el juego escogido
	 * @param option
	 */
	private void opcionesJugar(String[] option){
		int col = 10;
		int fil = 10;
		String columna = null;
		String fila = null;
		try{
			if(option.length >= 2){
				switch(option[1]){
					//Cambia el tipo de juego a Conecta4
					case "c4":{
						if(option.length == 2){
							this.factoria = new FactoriaConecta4();
							inicializaPartida();
						}
						else{
							System.err.println("No te entiendo.");
						}
						break;
					}
					//Cambia el tipo de juego a complica
					case "co":{
						if(option.length == 2){
							this.factoria = new FactoriaComplica();
							inicializaPartida();
						}
						else{
							System.err.println("No te entiendo.");
						}
						break;
					}
					//Cambia el tipo de juego a gravity
					case "gr":{
						if(option.length == 4){
							columna = option[2];
							col = Integer.parseInt(columna);
							fila = option[3];
							fil = Integer.parseInt(fila);
							this.factoria = new FactoriaGravity(col, fil);
							inicializaPartida();				
						}
						else{
							System.err.println("No te entiendo.");
						}
						break;
					}
					case "rv":{
						if(option.length == 2){
							this.factoria = new FactoriaReversi();
							inicializaPartida();
						}
						else{
							System.err.println("No te entiendo.");
						}
						break;
					}
					
					default: System.err.println("No te entiendo.");
				}
			}
			else{
				System.err.println("No te entiendo.");
			}
		}
		catch(NumberFormatException g){
			System.err.println("No te entiendo.");
		}
	}
	
	/**
	 * Crea una partida 
	 */
	private void inicializaPartida(){
		this.reglas = this.factoria.creaReglas();
		this.partida.cambioJuego(this.reglas);
		this.jugador1 = this.factoria.creaJugadorHumanoConsola(this.in);
		this.jugador2 = this.factoria.creaJugadorHumanoConsola(this.in);
	}
	
	/**
	 * Cambia al tipo de jugador que se especifique 
	 * @param option
	 * @throws Exception
	 */
	private void cambiaJugador(String[] option){
		
		if(option[1].equals("blancas")){
			if(option[2].equals("humano")){
				this.jugador1 = this.factoria.creaJugadorHumanoConsola(this.in); 
			}
			else if(option[2].equals("aleatorio")){
				this.jugador1 = this.factoria.creaJugadorAleatorio();
			}
			else{//Si no coincide
				System.err.println("No te entiendo.");
			}
		}
		else if(option[1].equals("negras")){
			if(option[2].equals("humano")){
				this.jugador2 = this.factoria.creaJugadorHumanoConsola(this.in); 
			}
			else if(option[2].equals("aleatorio")){
				this.jugador2 = this.factoria.creaJugadorAleatorio();
			}
			else{//Si no coincide
				System.err.println("No te entiendo.");
			}
		}
		else{//Si no coincide
			System.err.println("No te entiendo.");
		}
	}
	
	/**
	 * Añade el observador al ArrayList de observadores
	 * @param o
	 */
	public void addObserver(Observer o){
		this.partida.addObserver(o);
	}

}

