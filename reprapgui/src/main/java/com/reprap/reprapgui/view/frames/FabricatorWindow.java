package com.reprap.reprapgui.view.frames;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.reprap.reprapgui.view.panels.FabricatorPrintPanel;

/**
 * The RepRapWindow is the main User Interface for controlling the printer,
 * defining the parameters, starting builds etc.
 */
@SuppressWarnings("serial")
public class FabricatorWindow extends AbstractFabricatorWindow {

	public static final String MAIN_WINDOW_NAME  = "Main";
	
	@Autowired
	public FabricatorWindow(final String name, final FabricatorPrintPanel panel) throws Exception {
		super(name);
		
		add(panel);

		setName(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		pack();
	}
	
	/**
	 * Spring is used to control the mechanism by which this class is created.
	 * @param args <not used>
	 */
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ClassPathXmlApplicationContext("application-context.xml").getBean(FabricatorWindow.class);
			}
		});
	}
}
