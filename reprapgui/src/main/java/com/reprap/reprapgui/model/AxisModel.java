package com.reprap.reprapgui.model;

import com.reprap.reprapgui.controller.utils.Axis;
import com.reprap.reprapgui.controller.utils.StaticConstants;


/**
 * This class holds onto the printer x,y and z coordinate values plus
 * the current print rate (speed).
 */
public class AxisModel extends AbstractModel {
	
	private int x;
	private int y;
	private int z;
	private int rate;
	
	public AxisModel(){}

	public int getX() {
		return x;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(final int z) {
		this.z = z;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(final int rate) {
		this.rate = rate;
	}
	
	public void setAxis(final Axis axis) {
		firePropertyChange(StaticConstants.AXIS_COMMAND, null, axis);
	}

}
