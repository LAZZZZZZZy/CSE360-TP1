import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;


public class UI extends JFrame implements ActionListener{
	//UI Component
	private JMenuBar menubar;
	private JMenu help,about,quit;	
	private JToolBar toolbar;
	private JButton input,process,restart;
	
	private ListPanel listpanel;
	private InputPanel inputpanel;
	
	private Function function;
	private List<Node> nodes;//store all input nodes

	
	public UI() {
		//initialize
		nodes = new ArrayList<Node>();
		inputpanel = new InputPanel();
		listpanel = new ListPanel();//paint panel
		function = new Function();

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
		restart = new JButton("Restart");
		input.addActionListener(this);
		process.addActionListener(this);
		restart.addActionListener(this);
		
		toolbar.add(input);
		toolbar.addSeparator();
		toolbar.add(process);
		toolbar.addSeparator();
		toolbar.add(restart);
		//graphic area

		JScrollPane jsp = new JScrollPane(listpanel);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//add component
		this.setJMenuBar(menubar);
		this.add(toolbar,BorderLayout.NORTH);
		
		this.add(listpanel);
		this.add(inputpanel);
		listpanel.setVisible(false);
		inputpanel.setVisible(false);
		
		//property of Jframe
		this.setTitle("Project 1");
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed ( ActionEvent e ) {
		// TODO Auto-generated method stub
		if(e.getSource()==input) {
			inputpanel.setVisible(true);
		}
		if(e.getSource()==restart) {
			nodes.clear();
			JOptionPane.showMessageDialog(this,"reset");
		}
		if(e.getSource()==process) {
			function.inputNodes(nodes);
			if(function.errorChecking()) {
				listpanel.inputNodes(nodes);
				inputpanel.setVisible(false);
				listpanel.setVisible(true);
				System.out.println("2");
			} else {
				JOptionPane.showMessageDialog(this,"some of activies are not connected");
			}
		}
	}
	
}
