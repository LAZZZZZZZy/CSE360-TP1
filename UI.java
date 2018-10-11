
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
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class UI extends JFrame implements ActionListener {
    //UI Component

    private JMenuBar menubar;
    private JMenu helpm, aboutm;
    private JToolBar toolbar;
    private JButton input, process, restart, quit;
    
    private JPanel mainpanel;
    private ListPanel listpanel;
    private InputPanel inputpanel;

    private Function function;
    private List<Node> nodes;//store all input nodes

    public UI() {
        //initialize
        nodes = new ArrayList<Node>();
        function = new Function(nodes);
        inputpanel = new InputPanel(function);
        listpanel = new ListPanel(function);//paint panel

        // menu bar
        menubar = new JMenuBar();

        helpm = new JMenu("Help");
        aboutm = new JMenu("About");

        menubar.add(helpm);
        menubar.add(aboutm);
        //tool bar
        toolbar = new JToolBar();

        input = new JButton("Input");
        process = new JButton("Process");
        restart = new JButton("Restart");
        quit = new JButton("Quit");
        input.addActionListener(this);
        process.addActionListener(this);
        restart.addActionListener(this);
        quit.addActionListener(this);

        toolbar.add(input);
        toolbar.addSeparator();
        toolbar.add(process);
        toolbar.addSeparator();
        toolbar.add(restart);
        toolbar.addSeparator();
        toolbar.add(quit);

        //graphic area
        //add component
        this.setJMenuBar(menubar);
        this.add(toolbar, BorderLayout.NORTH);
        inputpanel.setEnabled(false);
        inputpanel.setVisible(false);
        this.add(listpanel);
        this.add(inputpanel);


        //property of Jframe
        this.setTitle("Path Analyzer");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

    }

    /* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == input) {
            inputpanel.setEnabled(true);
            inputpanel.setVisible(true);
        }
        if (e.getSource() == restart) {
            nodes.clear();
            JOptionPane.showMessageDialog(this, "reset");
        }
        if (e.getSource() == process) {
            function.inputNodes(nodes);
            if (function.errorChecking()) {
                listpanel.inputNodes(nodes);
                inputpanel.setVisible(false);
                listpanel.setVisible(true);
                System.out.println("2");
            } else {
                JOptionPane.showMessageDialog(this, "some of activies are not connected");
            }
        }

        if (e.getSource() == helpm) {
            System.out.println("1");
            JOptionPane.showMessageDialog(this, "help");
        }
    }

}
