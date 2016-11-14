package tp.pr5.vistas;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

public class PanelDerSur extends JPanel {
	
	private JButton salir;
	private JOptionPane panelConfimacion;
	
	/**
	 * Constructor de la clase
	 */
	public PanelDerSur(){
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		crearBotonSalir();		
		configuraEscuchaBotonSalir();
		
		this.add(salir);
		this.setVisible(true);
	}
	
	/**
	 * Crea el botón para poder cerrar la ventana.
	 */
	private void crearBotonSalir(){
		salir = new JButton("Salir");
		this.salir.setIcon(new ImageIcon(PanelPartida.class.getResource("/tp/pr5/vistas/images/icons/exit.png")));
	}
	
	/**
	 * Pone oyente al botón de salir y muestra un popup cuando lo pulsa
	 */
	private void configuraEscuchaBotonSalir(){
		salir.addActionListener(new ActionListener(){
			public void  actionPerformed(ActionEvent e){
				panelConfimacion = new JOptionPane();
				if(JOptionPane.showConfirmDialog(null, "¿Realmente quieres abandonar?", "Confirmar Abandono", panelConfimacion.YES_NO_OPTION)==0)
					System.exit(0);
			}
		});//Fin clase anonima
	}

}
