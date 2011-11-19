package com.reprap.reprapgui.gui;

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

import com.reprap.reprapgui.config.PrinterButton;
import com.reprap.reprapgui.printer.GenericPrinter;

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
	
	private final JTextField portEntryField   = new JTextField(15);
	private final JTextField baudspeed   = new JTextField(15);
	
	private final JButton button = new JButton(PrinterButton.Connect.toString());
	
	public FabricatorWindow(final String name) throws Exception {
		super(name);
		
		setName(name);

		portEntryField.setName("portentry");
		baudspeed.setName("baudspeed");
		button.setName(CONNECTION_BUTTON_NAME);

		final JPanel panel = new JPanel(new MigLayout());
		
		panel.add(portLabel);
	    panel.add(portEntryField);
	    panel.add(baudSpeedLabel, "gap unrelated");
	    panel.add(baudspeed, "wrap");
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
		if(portEntryField.getText().equals("") || baudspeed.getText().equals("")) {
			final JOptionPane pane = new JOptionPane();
			pane.showMessageDialog(this, "Connection settings incomplete", "Connection problem", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
		
	}
}
