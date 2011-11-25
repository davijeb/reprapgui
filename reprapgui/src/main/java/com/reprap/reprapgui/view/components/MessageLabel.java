package com.reprap.reprapgui.view.components;

import javax.swing.JLabel;

/**
 * This class is basically a {@link JLabel} but is also sets the 
 * name upon construction. Sadly fluid interfaces were not used
 * when the JDK was created. 
 */
public class MessageLabel extends JLabel {

	private static final long serialVersionUID = 7104462770533831913L;

	public MessageLabel(final String title, final String name) {
		super(title);
		setName(name);
	}
}
