package com.reprap.reprapgui.view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.GenericPrinter;
import com.reprap.reprapgui.view.components.BasicJTextField;
import com.reprap.reprapgui.view.config.PrinterButton;
import com.reprap.reprapgui.view.panels.FabricatorPrintPanel;

/**
 * The RepRapWindow is the main User Interface for controlling the printer,
 * defining the parameters, starting builds etc.
 */
public class FabricatorWindow extends AbstractFabricatorWindow {

	private static final long serialVersionUID = -7059521570271326438L;

	public static final String MAIN_WINDOW_NAME  = "Main";
	public static final String CONNECTION_LABEL_NAME  = "ConnectionLabel";
	public static final String CONNECTION_BUTTON_NAME = "ConnectionButton";
	
	@Autowired
	private GenericPrinter printer;

	private final JLabel portLabel = new JLabel("Port");
	private final JLabel baudSpeedLabel = new JLabel("Baudspeed");
	
	private final JTextField portPath1   = new JTextField(15);
	private final JTextField baudSpeed   = new JTextField(15);
	
	@Autowired
	private BasicJTextField portPath;
	
	private final JButton button = new JButton(PrinterButton.Connect.toString());
	
	public FabricatorWindow(final String name) throws Exception {
		super(name);
		
		setName(name);

		portPath1.setName("portentry");
		baudSpeed.setName("baudspeed");
		button.setName(CONNECTION_BUTTON_NAME);

		final JPanel panel = new FabricatorPrintPanel(new MigLayout());
		
		panel.add(portLabel);
	    panel.add(portPath1);
	    panel.add(baudSpeedLabel, "gap unrelated");
	    panel.add(baudSpeed, "wrap");
	    panel.add(button,"span, grow");

		add(panel);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				if(validated())
				printer.connect();
			}
		});
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	protected boolean validated() {
		if(portPath1.getText().equals("") || baudSpeed.getText().equals("")) {
			final JOptionPane pane = new JOptionPane();
			pane.showMessageDialog(this, "Connection settings incomplete", "Connection problem", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
		
	}
}
