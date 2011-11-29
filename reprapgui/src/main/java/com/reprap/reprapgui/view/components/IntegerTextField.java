package com.reprap.reprapgui.view.components;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.interfaces.PrinterController;

/**
 * This class is a specialization over the standard {@link JTextField}. It differs in that
 * it will only allow the users to enter integer values. Anything else will be ignored.
 * 
 * It achieves this by overriding the {@link PlainDocument} behavior when text is entered.
 * When the <code>insertString</code> method is called the contents of the text field is analysed
 * and if a {@link NumberFormatException} is found then the system will give an audible beep, otherwise
 * the new (positive) integer compatible string is entered.
 */
@SuppressWarnings("serial")
public class IntegerTextField extends AbstractAlphaNumericTextField {

	@Autowired
	private PrinterController printController;

	public IntegerTextField(final String name, final int columns) {
		super(name, columns);
		
		addKeyListener(new KeyListener() {

			/**
			 * Text has been entered and the field is now empty. Tell the controller
			 * about this change.
			 */
			@Override
			public void keyReleased(final KeyEvent evt) {
				if (getText().length() > 0)
				printController.printParametersChanged(getName(),Integer.parseInt(getText()));
			}

			@Override
			public void keyPressed(final KeyEvent evt) {}
			@Override
			public void keyTyped(final KeyEvent evt) {}
		});
	}

	/**
	 * Get the default model from the field.
	 */
	@Override
	protected Document createDefaultModel() {
		return new IntegerDocument();
	}

	/**
	 * This document only allows integer values to be added to it.
	 */
	class IntegerDocument extends PlainDocument {
		
		/**
		 * When all the contents of the field have been removed then tell the
		 * controller that the model property is now 0 (0 is empty in this case).
		 */
		@Override
		protected void removeUpdate(final DefaultDocumentEvent arg0) {
			printController.printParametersChanged(getName(),0);
		}

		/**
		 * Attempt to add the new string value but first check that it
		 * conforms o a valid numeric format.
		 */
		@Override
		public void insertString(final int offs, final String str,
				final AttributeSet a) throws BadLocationException {
			if (str != null) {
				try {
					Integer.decode(str);
					super.insertString(offs, str, a);
				} catch (final NumberFormatException ex) {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		}
	}
}
