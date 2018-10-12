
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListPanel extends JPanel{
	private Function function;
	private JTextArea content;
	
	public ListPanel(Function _function) {
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
		for(Path p:function.getPath()) {
			content.append(p.toString());
			System.out.println(p.toString());
		}
		this.repaint();
		this.revalidate();
	}
	
}
