import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class DefaultPanel extends JPanel{
	/**
	 * 
	 */
	public DefaultPanel () {
		this.setLayout(null);
		
		JLabel label = new JLabel("Path Analyzer");
		JLabel version = new JLabel("ver.0.2");

		label.setFont(new Font("Chiller",Font.BOLD,40));
		label.setBounds(180,80,200,50);
		version.setFont(new Font("Chiller",Font.BOLD,18));
		version.setBounds(240,150,100,50);
		this.setBackground(Color.WHITE);
		this.add(label);
		this.add(version);
	}

}
