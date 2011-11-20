package com.reprap.reprapgui.view.buttons;

import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PrinterConnectButton extends JButton {

	public PrinterConnectButton(final String name, final ActionListener listener) {
		super(name);
		addActionListener(listener);
	}
}
