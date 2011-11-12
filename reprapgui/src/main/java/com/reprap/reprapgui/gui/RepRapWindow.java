package com.reprap.reprapgui.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.reprap.reprapgui.Main;
import com.reprap.reprapgui.config.PrinterStatus;

/**
 * The RepRapWindow is the main User Interface for controlling
 * the printer, defining the parameters, starting builds etc.
 */
public class RepRapWindow extends JFrame implements Observer {
	
	private static final long serialVersionUID = -7059521570271326438L;

	public static final String CONNECTION_LABEL_NAME = "ConnectionLabel";
	
	private final JLabel label = new JLabel(PrinterStatus.Disconnected.toString());
	
	public RepRapWindow() {
		super("RepRap GUI");
		setName(Main.MAIN_WINDOW_NAME);
		label.setName(CONNECTION_LABEL_NAME);
		add(label);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Update the printer status messages when observable changes are detected.
	 */
	@Override
	public void update(final Observable observable, final Object observerValue) {
		label.setText(observerValue.toString());
	}
}
