package com.reprap.reprapgui.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.PrusaPrinterController;
import com.reprap.reprapgui.controller.utils.StaticConstants;

@SuppressWarnings("serial")
public class PrinterConnectButton extends JButton {

	@Autowired
	private PrusaPrinterController printController;
	
	public PrinterConnectButton(final String name) {
		super(name);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				if(getText().equals(StaticConstants.CONNECT_BUTTON)) {
					printController.connect();
				} else {
					printController.disconnect();
				}
			}
		});
	}
}