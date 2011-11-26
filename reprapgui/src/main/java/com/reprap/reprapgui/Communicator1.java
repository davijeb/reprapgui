/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reprap.reprapgui;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;

public class Communicator1 implements SerialPortEventListener
{

    //for containing the ports that will be found
    private Enumeration ports = null;
    //map the port names to CommPortIdentifiers
    private final HashMap portMap = new HashMap();

    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;

    //input and output streams for sending and receiving data
    private InputStream input = null;
    private OutputStream output = null;

    //just a boolean flag that i use for enabling
    //and disabling buttons depending on whether the program
    //is connected to a serial port or not
    private boolean bConnected = false;

    //the timeout value for connecting with the port
    final static int TIMEOUT = 2000;

    //some ascii values for for certain things
    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;

    //a string for recording what goes on in the program
    //this string is written to the GUI
    String logText = "";
    
    
    String selectedPort = null;

    //search for all the serial ports
    //pre: none
    //post: adds all the found ports to a combo box on the GUI
    public void searchForPorts()
    {
        ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements())
        {
            final CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

            //get only serial ports
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
                portMap.put(curPort.getName(), curPort);
                selectedPort = curPort.getName();
                break;
            }
        }
    }

    //connect to the selected port in the combo box
    //pre: ports are already found by using the searchForPorts method
    //post: the connected comm port is stored in commPort, otherwise,
    //an exception is generated
    public void connect()
    {
        selectedPortIdentifier = (CommPortIdentifier)portMap.get(selectedPort);

        CommPort commPort = null;

        try
        {
            //the method below returns an object of type CommPort
            commPort = selectedPortIdentifier.open("TigerControlPanel", TIMEOUT);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort)commPort;

            //for controlling GUI elements
            setConnected(true);

            //logging
            System.out.println(selectedPort + " opened successfully.");
        }
        catch (final PortInUseException e)
        {
            logText = selectedPort + " is in use. (" + e.toString() + ")";
        }
        catch (final Exception e)
        {
            logText = "Failed to open " + selectedPort + "(" + e.toString() + ")";
        }
    }

    //open the input and output streams
    //pre: an open port
    //post: initialized intput and output streams for use to communicate data
    public boolean initIOStream()
    {
        //return value for whather opening the streams is successful or not
        boolean successful = false;

        try {
            //
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();
            writeData(0, 0);
            
            successful = true;
            return successful;
        }
        catch (final IOException e) {
            logText = "I/O Streams failed to open. (" + e.toString() + ")";
            return successful;
        }
    }

    //starts the event listener that knows whenever data is available to be read
    //pre: an open serial port
    //post: an event listener for the serial port that knows when data is recieved
    public void initListener()
    {
        try
        {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (final TooManyListenersException e)
        {
            logText = "Too many listeners. (" + e.toString() + ")";
        }
    }

    //disconnect the serial port
    //pre: an open serial port
    //post: clsoed serial port
    public void disconnect()
    {
        //close the serial port
        try
        {
            writeData(0, 0);

            serialPort.removeEventListener();
            serialPort.close();
            input.close();
            output.close();
            setConnected(false);

            logText = "Disconnected.";
        }
        catch (final Exception e)
        {
            logText = "Failed to close " + serialPort.getName() + "(" + e.toString() + ")";
        }
    }

    final public boolean getConnected()
    {
        return bConnected;
    }

    public void setConnected(final boolean bConnected)
    {
        this.bConnected = bConnected;
    }

    //what happens when data is received
    //pre: serial event is triggered
    //post: processing on the data it reads
    @Override
	public void serialEvent(final SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE)
        {
            try
            {
                final byte singleData = (byte)input.read();

                if (singleData != NEW_LINE_ASCII)
                {
                    logText = new String(new byte[] {singleData});
                    System.out.println(evt.getEventType() + " -> " + logText);
                }
                else
                {
                }
            }
            catch (final Exception e)
            {
                System.out.println("Failed to read data. (" + e.toString() + ")");
            }
        }
    }

    //method that can be called to send data
    //pre: open serial port
    //post: data sent to the other device
    public void writeData(final int leftThrottle, final int rightThrottle)
    {
        try
        {
            output.write(leftThrottle);
            output.flush();
            //this is a delimiter for the data
            output.write(DASH_ASCII);
            output.flush();
            
            output.write(rightThrottle);
            output.flush();
            //will be read as a byte so it is a space key
            output.write(SPACE_ASCII);
            output.flush();
        }
        catch (final Exception e)
        {
            logText = "Failed to write data. (" + e.toString() + ")";
        }
    }
    
    public static void main(final String[] args) {
		final Communicator1 c1 = new Communicator1();
		c1.searchForPorts();
		c1.connect();
		c1.initIOStream();
		c1.initListener();
		
		try {
			c1.writeData(5, 0);
			Thread.sleep(2000);
			c1.writeData(10, 0);
			Thread.sleep(2000);
			c1.writeData(15, 0);
			Thread.sleep(2000);
			c1.writeData(20, 0);
			Thread.sleep(2000);
			c1.writeData(15, 0);
			Thread.sleep(2000);
			c1.writeData(10, 0);
			Thread.sleep(2000);
			c1.writeData(5, 0);
			System.out.println("Communicator1.main()");
			Thread.sleep(2000);
			c1.writeData(5, 0);
			Thread.sleep(2000);
			c1.writeData(10, 0);
			Thread.sleep(2000);
			c1.writeData(15, 0);
			Thread.sleep(2000);
			c1.writeData(20, 0);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
