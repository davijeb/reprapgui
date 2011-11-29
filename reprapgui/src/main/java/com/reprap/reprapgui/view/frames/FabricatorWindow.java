package com.reprap.reprapgui.view.frames;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.reprap.reprapgui.view.panels.FabricatorAxisViewPanel;
import com.reprap.reprapgui.view.panels.FabricatorEventViewPanel;
import com.reprap.reprapgui.view.panels.FabricatorPrintPanel;

/**
 * This class is the main frame for the GUI component. The panels are added using
 * the Spring {@link Autowired} mechanism. The frame properties itself are set
 * in an external Spring configuration file.
 */
@SuppressWarnings("serial")
public class FabricatorWindow extends JFrame {
	
	@Autowired
	public FabricatorWindow(
			final String name, 
			final FabricatorPrintPanel panel, 
			final FabricatorAxisViewPanel axisPanel,
			final FabricatorEventViewPanel eventLogPanel) throws Exception {
		super(name);
		
		setLayout(new MigLayout());
		
		add(panel,    "wrap");
		add(axisPanel,"wrap");
		add(eventLogPanel);

		setName(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		pack();
	}
	
	/**
	 * Spring is used to control the mechanism by which this class is created on the EDT.
	 * @param args <not used>
	 */
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ClassPathXmlApplicationContext("classpath:/application-context.xml").getBean(FabricatorWindow.class);
			}
		});
	}
}
