package com.reprap.reprapgui.view.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

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
	
	@Override
	protected Document createDefaultModel() {
		return new StringDocument();
	}

	/**
	 * This document only allows integral values to be added to it.
	 */
	class StringDocument extends PlainDocument {

		@Override
		protected void removeUpdate(final DefaultDocumentEvent arg0) {
			printController.printParametersChanged(getName(), "");
		}
	}
}