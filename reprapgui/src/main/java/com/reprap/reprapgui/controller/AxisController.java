package com.reprap.reprapgui.controller;

import com.reprap.reprapgui.controller.utils.Axis;

public class AxisController extends AbstractController  {
	
	/**
	 * Move the axis up/down
	 * @param axis
	 */
	public void moveAxis(final Axis axis) {
		setModelProperty("Axis", axis);
	}
}
