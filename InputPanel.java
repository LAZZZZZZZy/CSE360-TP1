import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class InputPanel extends JPanel{
	private String activity_name;
	private int duration;
	private List<Node> dependencies;
	/**
	 * 
	 */
	public InputPanel () {
		this.setLayout(null);
		
		JLabel name = new JLabel("Activity:");
		JLabel dur = new JLabel("Duration:");
		JLabel depende = new JLabel("Dependency:");		
		
		JTextField actin = new JTextField();
		JTextField durin = new JTextField();
		JTextField dependein = new JTextField();
		
		actin.setBounds(r);
		
		this.add(name);
		this.add(dur);
		this.add(depende);
		name.setBounds(50,60,50,50);
		dur.setBounds(50,160,50,50);
		depende.setBounds(50,260,50,50);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
