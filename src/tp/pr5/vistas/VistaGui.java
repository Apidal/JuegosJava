package tp.pr5.vistas;

import java.awt.*;

import javax.swing.*;

import tp.pr5.control.ControladorGUI;


public class VistaGui extends JFrame{

	private Container panelPrincipal;
	private JPanel panelIzq;
	private JPanel panelDer;
	private ControladorGUI co;
	
	/**
	 * Constructor de la clase
	 * @param c
	 */
	public VistaGui(ControladorGUI c){
		super("Practica 5 - TP");
		this.co = c;
		initGUI();
	}
	
	/**
	 * Centra la ventana
	 */
	private void centrarVentana() {
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
	    this.setLocation(screenWidth / 4, screenHeight / 4);		
	}
	
	/**
	 * Inicializa la vista creando el contenedor principal, los paneles derecha e izquierda
	 * y añadiendonolos a la ventana principal
	 */
	private void initGUI(){
		
		this.panelPrincipal = this.getContentPane();
		
		this.panelPrincipal.setLayout(new BoxLayout(this.panelPrincipal,BoxLayout.X_AXIS));
			
		this.panelIzq = new PanelIzq(co, this);
		this.panelDer = new PanelDer(co, this);
				
		this.panelPrincipal.add(this.panelIzq);
		this.add(Box.createRigidArea(new Dimension(30,0)));
		this.panelPrincipal.add(this.panelDer);
		
		centrarVentana();
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.co.start();
		
	}
	
}
