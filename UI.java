
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class UI extends JFrame implements ActionListener {
	// UI Component

	private JMenuBar menubar;
	private JMenu helpm, aboutm,filem;
	private JToolBar toolbar;
	private JButton input, process, restart, quit, added,critical;

	private JPanel mainpanel;
	private ListPanel listpanel;
	private InputPanel inputpanel;
	private AddedPanel addedpanel;
	private CriticalPanel criticalpanel;
	
	private CardLayout card;

	private Function function;
	private List<Node> nodes;// store all input nodes

	public UI () {
		// initialize
		card = new CardLayout();
		mainpanel = new JPanel(card);
		nodes = new ArrayList<Node>();
		addedpanel = new AddedPanel();
		function = new Function(nodes,addedpanel);
		inputpanel = new InputPanel(function);
		listpanel = new ListPanel(function);// paint panel
		criticalpanel = new CriticalPanel();
		// menu bar
		menubar = new JMenuBar();

		helpm = new HelpMenu(this);
		aboutm = new AboutMenu(this);
		filem = new FileMenu(this);
		
		menubar.add(filem);
		menubar.add(helpm);
		menubar.add(aboutm);
		// tool bar
		toolbar = new JToolBar();

		input = new JButton("Input");
		added = new JButton("Added");
		critical =new JButton("Critical");
		process = new JButton("Process");
		restart = new JButton("Restart");
		quit = new JButton("Quit");
		input.addActionListener(this);
		process.addActionListener(this);
		restart.addActionListener(this);
		quit.addActionListener(this);
		added.addActionListener(this);

		toolbar.add(input);
		toolbar.addSeparator();
		toolbar.add(added);
		toolbar.addSeparator();
		toolbar.add(critical);
		toolbar.addSeparator();
		toolbar.add(process);
		toolbar.addSeparator();
		toolbar.add(restart);
		toolbar.addSeparator();
		toolbar.add(quit);

		// graphic area
		// add component
		this.setJMenuBar(menubar);
		this.add(toolbar,BorderLayout.NORTH);

		mainpanel.add(inputpanel,"input");
		mainpanel.add(listpanel,"list");
		mainpanel.add(new JScrollPane(addedpanel),"added");
		this.add(mainpanel,BorderLayout.CENTER);

		// property of Jframe
		this.setTitle("Path Analyzer");
		this.setSize(600,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed ( ActionEvent e ) {
		// TODO Auto-generated method stub
		if ( e.getSource() == input ) {
			card.show(mainpanel,"input");
		}
		if ( e.getSource() == restart ) {
			function.initial();
			card.show(mainpanel,"input");
			JOptionPane.showMessageDialog(this,"reset");
		}
		if ( e.getSource() == added ) {
			card.show(mainpanel,"added");
		}

		if ( e.getSource() == process ) {
			if(!function.ConnectNodes()) {
				JOptionPane.showMessageDialog(this,"Some dependencies do not exist");
				return;
			}
			if ( function.getNodes().isEmpty() ) {
				JOptionPane.showMessageDialog(this,"No input node");
				return;
			}

			if ( !function.errorCheckingCycle() ) {
				JOptionPane.showMessageDialog(this,"It has a cycle");
				return;
			}

			if ( !function.errorCheckingConnect() ) {
				JOptionPane.showMessageDialog(this,"some nodes are not connected");
				return;
			}
			function.process();
			listpanel.Output();
			card.show(mainpanel,"list");
		}
		
		if(e.getSource()==quit) {
			System.exit(0);
		}

	}

}
