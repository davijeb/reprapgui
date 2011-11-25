package com.reprap.reprapgui.view.components;

import javax.swing.JTextField;

/**
 * This class is an abstraction above {@link IntegerTextField} and {@link StringTextField}
 * as they both need to set the name and define the number of columns used.
 */
public class AbstractAlphaNumericJTextField extends JTextField {

	private static final long serialVersionUID = 9119209479438090719L;

	/**
	 * C'tor to set the name and number of columns
	 * @param name the text field name
	 * @param columns the number of columns set
	 */
	public AbstractAlphaNumericJTextField(final String name, final int columns) {
		super();
		setName(name);
		setColumns(columns);
	}
}
