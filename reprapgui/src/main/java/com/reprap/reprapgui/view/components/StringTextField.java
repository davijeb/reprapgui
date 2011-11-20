package com.reprap.reprapgui.view.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.PrusaPrinterController;

@SuppressWarnings("serial")
public class StringTextField extends JTextField {

	@Autowired
	private PrusaPrinterController printController;

	public StringTextField() {
		super();
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(final KeyEvent arg0) {
			}

			@Override
			public void keyReleased(final KeyEvent arg0) {
				printController.printParametersChanged(getName(), getText());
			}

			@Override
			public void keyTyped(final KeyEvent arg0) {
			}
		});
	}
}
