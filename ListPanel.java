import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class ListPanel extends JPanel{
	private List<Node> nodes;
	
	public ListPanel() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
	
	public void inputNodes(List<Node> _nodes) {
		nodes = _nodes;
		this.repaint();
	}
}
