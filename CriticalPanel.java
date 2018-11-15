import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class CriticalPanel extends JPanel{
	private Function function;
	private JTextArea content;
	/**
	 * 
	 */
	public CriticalPanel (Function _function) {
		this.setLayout(new BorderLayout());
		function = _function;
		content = new JTextArea();
		content.setText("");	
		
		JScrollPane jsp = new JScrollPane(content);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		content.setEditable(false);
		
		this.add(jsp,BorderLayout.CENTER);
		
	}
	
	public void Output() {
		content.setText("");
		for(Path p:function.getCriticalPath()) {
			content.append(p.toString());
		}
		this.repaint();
		this.revalidate();
	}

}
