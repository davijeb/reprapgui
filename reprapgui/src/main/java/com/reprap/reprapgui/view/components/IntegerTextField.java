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

import com.reprap.reprapgui.controller.PrusaPrinterController;

@SuppressWarnings("serial")
public class IntegerTextField extends JTextField {

	@Autowired
	private PrusaPrinterController printController;

	public IntegerTextField() {
		super();
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(final KeyEvent evt) {
			}

			@Override
			public void keyReleased(final KeyEvent evt) {
				if (getText().length() > 0)
					printController.printParametersChanged(getName(),
							Integer.parseInt(getText()));
			}

			@Override
			public void keyTyped(final KeyEvent evt) {
			}
		});
	}

	@Override
	protected Document createDefaultModel() {
		return new IntegerDocument();
	}

	/**
	 * This document only allows integral values to be added to it.
	 */
	static class IntegerDocument extends PlainDocument {
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
