/*
Autores: 
	Alejandro Pidal Gallego
	Sergio Freire Fernández
*/

package tp.pr5;


import java.util.Scanner;

import org.apache.commons.cli.*;

import tp.pr5.control.*;
import tp.pr5.logica.*;
import tp.pr5.vistas.*;

public class Main {
	private static final int DEFCOL = 10; 
	private static final int DEFFILA = 10;
	private static final String DEFVISTA = "console";
	private static int grCol = DEFCOL;
	private static int grFila = DEFFILA;
	
	

	public static void main(String[] args){
		//Declaraciones------------
		FactoriaTipoJuego f;
		Partida p;
		Scanner in;
		ControladorConsola game;
		ControladorGUI gameGUI;
		//datos[0] contiene tipoJuego, datos[1] contiene la vista
		String[] datos;  
		VistaConsola console;
		VistaGui window;
		//-------------------------
		
		
		//Analizamos los argumentos que nos pasan y los tratamos
		datos = analizaArgs(args);
		
		
		//Segun el tipo de juego creado creará esa factoría
		if(datos[0].equals("c4"))
    		f = new FactoriaConecta4();
    	else if(datos[0].equals("co"))
    		f = new FactoriaComplica();
    	else if(datos[0].equals("gr"))
    		f = new FactoriaGravity(grCol, grFila);
    	else
    		f = new FactoriaReversi();
		
       	ReglasJuego reglas = f.creaReglas();
		p = new Partida(reglas);
		//Si ha elegido vista consola
		if(datos[1].equals(DEFVISTA)){
			in = new Scanner(System.in);
			game = new ControladorConsola(f, p, in);
			console = new VistaConsola(game);
			game.run();
			System.exit(0);
		}
		else{
			gameGUI = new ControladorGUI(f, p);
			window = new VistaGui(gameGUI);
		}
		
	}
			
	/**
	 * Analiza los argumentos 
	 * @param args
	 * @param tipoJuego
	 * @return ret, vector de String con las opciones de tipo de juego y vista
	 */
	private static String[] analizaArgs(String args[]){
		CommandLineParser parser  = null;  
	    CommandLine cmdLine = null; 
	    Options options = null;
	    String[] ret = new String[2];
	    
	    //La inicializamos aqui por si no entra en la opcion de jugar o interfaz
	    ret[0] = "c4"; //Tipo Juego
	    ret[1] = DEFVISTA; // Vista
	    
	    //Configuramos las opciones de validación de entrada. 
	    options = configuracionOpciones();
        try {  

        	//Parseamos la entrada con la configuración establecida  
        	parser  = new BasicParser();  
        	cmdLine = parser.parse(options, args);  

        	//Analizamos los resultados y realizamos las tareas pertinentes  
        	// Si está la opción de ayuda, la imprimimos y salimos.  
        	if (cmdLine.hasOption("h")){  
        		opcionHelp(options);
		    }//Si ha puesto la opción para jugar o vista
		    else if (cmdLine.hasOption("g") || cmdLine.hasOption("u")){
		    	if(cmdLine.hasOption("g"))
			    	ret[0] = opcionGame(parser, cmdLine);
		    	if(cmdLine.hasOption("u"))
		    		ret[1] = opcionVista(parser, cmdLine);
		    	
		    }
		} 
        catch (ParseException ex){  
        	System.err.println("Uso incorrecto: " + ex.getMessage() + "\nUse -h|--help para más detalles.");  
		    System.exit(1); 
        }
	    catch(NumberFormatException g){
			System.err.println("No te entiendo.");
			System.exit(1); 
		};
		
		return ret;
	}
	
	
	/**
	 * Si elige la opción vista, comprueba que no haya argumentos incorrectos y prepara
	 * las variables para después crear la partida con la vista introducida
	 * @param parser
	 * @param cmdLine
	 * @throws ParseException
	 * @return vista
	 */
	private static String opcionVista(CommandLineParser parser, CommandLine cmdLine) throws ParseException {
		String vista, aux= "";
    	String[] extra;
    	//almacenamos en extra todos los argumentos no parseados
    	extra = cmdLine.getArgs();
    	if(extra.length != 0){
    	//Si extra no está vacio almacenamos lo de dentro en el String aux
	    	for(int i = 0; i < extra.length; i++){
	    		aux = aux + " " + extra[i];
		    }
		    //Y lanzamos la excepción con el mensaje construído
		    throw new ParseException("Argumentos no entendidos:" + aux);
	    }
    	else{
    		vista = cmdLine.getOptionValue("u");
        	//Si no ha introducido ni window ni console
        	if(!vista.equals("window") && !vista.equals("console")){
        		throw new ParseException("Valor de interfaz " + vista + " incorrecta." );
        	}
        	
    	}
    	
    	return vista;
    	
	}

	/**
	 * Crea las opciones que se pueden meter como parametros
	 * @return options
	 */
	private static Options configuracionOpciones(){
		Options options = new Options();
        options.addOption( OptionBuilder.withLongOpt( "game" )
                .withDescription( "Tipo de juego (c4, co, gr, rv). Por defecto, c4." )
                .hasArg()
                .withArgName("game")
                .create("g"));
        options.addOption( OptionBuilder.withLongOpt( "help" )
                .withDescription( "Muestra esta ayuda." )
                .withArgName("help")
                .create("h"));
        options.addOption( OptionBuilder.withLongOpt( "ui" )
                .withDescription( "Tipo de interfaz (console, window). Por\n"
                		+ "defecto, console." )
                .hasArg()
                .withArgName("tipo")
                .create("u"));
        options.addOption( OptionBuilder.withLongOpt( "tamX" )
                .withDescription( "Número de columnas del tablero (sólo para\n"
                		+ "Gravity). Por defecto, 10." )
                .hasArg()
                .withArgName("columnNumber")
                .create("x"));
        options.addOption( OptionBuilder.withLongOpt( "tamY" )
                .withDescription( "Número de filas del tablero (sólo para\n"
                		+ "Gravity). Por defecto, 10." )
                .hasArg()
                .withArgName("rowNumber")
                .create("y"));
        
        return options;
	}
	
	/**
	 * Si ha introducido la opción ayuda se le muestra
	 */
	private static void opcionHelp(Options options){
		String aux = "tp.pr4.Main [-g <game>] [-h] [-u <tipo>] [-x <columnNumber>] [-y <rowNumber>]";
		new HelpFormatter().printHelp(aux, options );
		System.exit(0);
	}
	
	
	/**
	 * Si elige la opción jugar, comprueba que no haya argumentos incorrectos y prepara
	 * las variables para después crear la partida con los parametros introducidos
	 * @param parser
	 * @param cmdLine
	 * @throws ParseException
	 * @return tipoJuego
	 */
	private static String opcionGame(CommandLineParser parser, CommandLine cmdLine) throws ParseException{
		String grColS,grFilaS, aux = "";
		String tipoJuego;
    	String[] extra;
    	//almacenamos en extra todos los argumentos no parseados
    	extra = cmdLine.getArgs();
    	if(extra.length != 0){
    	//Si extra no está vacio almacenamos lo de dentro en el String aux
	    	for(int i = 0; i < extra.length; i++){
	    		aux = aux + " " + extra[i];
		    }
		    //Y lanzamos la excepción con el mensaje construído
		    throw new ParseException("Argumentos no entendidos:" + aux);
	    }
    	else{
	    	tipoJuego = cmdLine.getOptionValue("g");
	    	if((!tipoJuego.equals("c4")) && (!tipoJuego.equals("co")) && (!tipoJuego.equals("gr") && (!tipoJuego.equals("rv"))))
	    		throw new ParseException("Juego '" + tipoJuego + "' incorrecto.");
    		else if(tipoJuego.equals("gr")){
	    		//Comprobamos que se ha introducido fila y columna
	    		if((cmdLine.hasOption("x")) && (cmdLine.hasOption("y"))){
	    			grColS = cmdLine.getOptionValue("x");
	    			grCol = Integer.parseInt(grColS);
	    			grFilaS = cmdLine.getOptionValue("y");
	    			grFila = Integer.parseInt(grFilaS);
	    		}
	    	}
    	}
    	return tipoJuego;
	}
	
	//end
}