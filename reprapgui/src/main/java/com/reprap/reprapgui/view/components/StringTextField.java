package com.reprap.reprapgui.view.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.interfaces.PrinterController;

/**
 * This class is just a standard {@link JTextField} that has a key listener
 * looking out for <code>keyPressed</code> events. When it finds one it tells the controller
 * to update the model.
 *  
 *  Additionally it holds on to the default model and when all the text is cleared from
 *  this field it tells the controller that the particular model property value is not
 *  the empty string.
 *
 */
@SuppressWarnings("serial")
public class StringTextField extends AbstractAlphaNumericTextField {

	@Autowired
	private PrinterController printController;

	public StringTextField(final String name, final int columns) {
		super(name, columns);
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(final KeyEvent arg0) {
				printController.printParametersChanged(getName(), getText());
			}

			@Override
			public void keyPressed(final KeyEvent arg0) {}
			@Override
			public void keyTyped(final KeyEvent arg0) {}
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