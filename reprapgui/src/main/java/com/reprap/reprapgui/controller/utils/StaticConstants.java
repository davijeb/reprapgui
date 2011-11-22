package com.reprap.reprapgui.controller.utils;

/**
 * This class defined the model parameter names the controller will reflect to set
 * the actual model properties. It also contains the GUI labels and visual component 
 * names. This is also referenced in the Spring configuration to tie both worlds together.
 */
public class StaticConstants {
	
	/**
	 * Print [model] static constants
	 */
	public static final String PORT	        		 = "Port";
	public static final String BAUD_SPEED   		 = "BaudSpeed";
	public static final String ALLOW_CONNECT	     = "ConnectState";
	
	/**
	 * Connect GUI labels
	 */
	public static final String PORT_LABEL_NAME	     = "portLabel";
	public static final String PORT_LABEL_TEXT	     = "Port:";
	public static final String BAUD_SPEED_LABEL_NAME = "baudSpeedLabel";
	public static final String BAUD_SPEED_LABEL_TEXT = "Baud speed:";
	public static final String CONNECT_STATE_LABEL   = "connectStateLabel";
	
	/**
	 * Connect GUI text fields
	 */
	public static final String PORT_TEXT_FIELD       = PORT;
	public static final String BAUD_SPEED_TEXT_FIELD = BAUD_SPEED;
	
	/**
	 * Connect GUI button
	 */
	public static final String CONNECT_BUTTON      	 = "Connect";
	public static final String DISCONNECT_BUTTON     = "Disconnect";

}
