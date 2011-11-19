package com.reprap.reprapgui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.reprap.reprapgui.config.PrinterButton;
import com.reprap.reprapgui.config.PrinterStatus;
import com.reprap.reprapgui.printer.PrusaPrinter;

/**
 * The RepRapWindow is the main User Interface for controlling the printer,
 * defining the parameters, starting builds etc.
 */
public class FabricatorWindow extends JFrame {

	private static final long serialVersionUID = -7059521570271326438L;

	public static final String MAIN_WINDOW_NAME  = "Main";
	public static final String CONNECTION_LABEL_NAME  = "ConnectionLabel";
	public static final String CONNECTION_BUTTON_NAME = "ConnectionButton";
	
	@Autowired
	private PrusaPrinter printer;

	private final JLabel label   = new JLabel(PrinterStatus.Disconnected.toString());
	private final JButton button = new JButton(PrinterButton.Connect.toString());
	
	public FabricatorWindow(final String name) {
		super(name);
		
		setName(name);
		
		label.setName(CONNECTION_LABEL_NAME);
		button.setName(CONNECTION_BUTTON_NAME);

		final JPanel panel = new JPanel(new MigLayout());

		panel.add(button);
		panel.add(label);
		add(panel);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				JOptionPane.showMessageDialog(getFrame(), printer);
			}
		});
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	JFrame getFrame() {
		return this;
	}

	/**
	 * Entry point to application
	 * 
	 * @param args the runtime arg array
	 */
	public static void main(final String[] args) {new ClassPathXmlApplicationContext("application-context.xml").getBean("MainApplication");}
}
