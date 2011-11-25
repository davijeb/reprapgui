package com.reprap.reprapgui.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.AxisController;
import com.reprap.reprapgui.controller.utils.Axis;

@SuppressWarnings("serial")
public class AxisButton extends JButton {
	
	@Autowired
	private AxisController axisController;
	
	private final Axis axis;

	public AxisButton(final Axis axis) {
		super(axis.toString());
		initialise();
		
		setName(axis.toString());
		this.axis = axis;
		
	}
	
	public void initialise() {
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent evt) {
				axisController.moveAxis(axis);
			}
		});
	}
}