package com.reprap.reprapgui.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.PrinterController;
import com.reprap.reprapgui.controller.utils.StaticConstants;

/**
 * This class is a JButton that has been modified to start life as being disabled.
 * 
 * It has an autowired controller which is connected to the click event and will
 * instruct the controller to either connect or disconnect from the printer.
 *
 */
@SuppressWarnings("serial")
public class PrinterConnectButton extends JButton {

	@Autowired
	private PrinterController printController;
	
	public PrinterConnectButton(final String title, final String name) {
		super(title);
		setName(name);
		setEnabled(false);
		
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