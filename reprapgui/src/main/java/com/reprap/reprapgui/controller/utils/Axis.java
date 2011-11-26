package com.reprap.reprapgui.controller.utils;

/**
 * This class describes the 3 axis we will be moving the machine.
 * Each axis understands the concept of up/down.
 */
public enum Axis {
	
	XUp(true), XDown(false), // x
	YUp(true), YDown(false), // y 
	ZUp(true), ZDown(false); // z
	
	private final boolean isUp;

	Axis(final boolean isUp) {
		this.isUp = isUp;
	}

	public boolean isUp() {
		return isUp;
	}
}
