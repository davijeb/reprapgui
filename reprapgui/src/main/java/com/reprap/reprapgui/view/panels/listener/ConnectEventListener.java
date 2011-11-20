package com.reprap.reprapgui.view.panels.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.PrusaPrinterController;

public class ConnectEventListener implements ActionListener {
	
	@Autowired
	private PrusaPrinterController printController;

	private final JTextField[] textFields;

	public ConnectEventListener(){textFields = null;}
	
	@Autowired
	public ConnectEventListener(final JTextField... fields) {
		textFields = fields;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		for (final JTextField jTextField : textFields) {
			if(jTextField.getText().equals(""))
				return;
		}
		printController.connect();
	}
}
