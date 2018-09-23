import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;


public class UI extends JFrame{
	//UI Component
	JMenuBar menubar;
	JMenu help,about,quit;
	
	JToolBar toolbar;
	JButton input,process;
	
	ListPanel graph;
	
	private List<Node> nodes;//store all input nodes
	
	public UI() {
		nodes = new ArrayList<Node>();

		// menu bar
		menubar = new JMenuBar();
		
		help = new JMenu("Help");
		about = new JMenu("About");
		quit = new JMenu("Quit");
		
		menubar.add(help);
		menubar.add(about);
		menubar.add(quit);
		//tool bar
		toolbar = new JToolBar();

		input = new JButton("Input");
		process = new JButton("Process");
		
		toolbar.add(input);
		toolbar.addSeparator();
		toolbar.add(process);
		//graphic area
		graph = new ListPanel(nodes);//paint panel
		JScrollPane jsp = new JScrollPane(graph);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//add component
		this.setJMenuBar(menubar);
		this.add(toolbar,BorderLayout.NORTH);
		this.add(graph, BorderLayout.CENTER);
		
		//property of Jframe
		this.setTitle("Project 1");
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
	}
}
