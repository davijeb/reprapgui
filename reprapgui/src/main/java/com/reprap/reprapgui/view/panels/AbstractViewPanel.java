package com.reprap.reprapgui.view.panels;

import java.awt.LayoutManager;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractViewPanel extends JPanel {

	public AbstractViewPanel(final LayoutManager layout) {
		super(layout);
	}

}
