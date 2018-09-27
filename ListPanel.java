import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListPanel extends JPanel{
	private List<Node> nodes;
	private Function function;
	
	public ListPanel(Function _function) {
		function = _function;
		
		JScrollPane jsp = new JScrollPane(this);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
	
	public void inputNodes(List<Node> _nodes) {
		nodes = _nodes;
		this.repaint();
	}
}
