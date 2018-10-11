
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 */
/**
 * @author Administrator
 *
 */
public class InputPanel extends JPanel implements ActionListener {

    private Function function;

    JTextField actin;
    JTextField durin;
    JTextField dependein;

    /**
     *
     */
    public InputPanel(Function _function) {
        this.setLayout(null);
        function = _function;

        JButton add = new JButton("add");
        add.addActionListener(this);

        JLabel name = new JLabel("Activity:");
        JLabel dur = new JLabel("Duration:");
        JLabel depende = new JLabel("Dependency:");
        JLabel List = new JLabel("Added list");
        JLabel notice = new JLabel("(Seperate by ',')");

        actin = new JTextField();
        durin = new JTextField();
        dependein = new JTextField();

        add.setBounds(150, 350, 100, 30);
        List.setBounds(450, 10, 100, 10);
        actin.setBounds(100, 70, 200, 30);
        durin.setBounds(100, 170, 200, 30);
        dependein.setBounds(100, 270, 200, 30);
        name.setBounds(30, 60, 100, 50);
        dur.setBounds(30, 160, 100, 50);
        depende.setBounds(20, 260, 100, 50);
        notice.setBounds(160, 290, 150, 50);
        JScrollPane jsp = new JScrollPane(this);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(notice);
        this.add(List);
        this.add(add);
        this.add(name);
        this.add(dur);
        this.add(depende);
        this.add(actin);
        this.add(durin);
        this.add(dependein);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Make an array out of Dependencies
        String[] depend = dependein.getText().split(",");

        // Check if durin is a number and not a texst
        if (!durin.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "duration must be integer");
            return;
        } 
        // Activity cannot be added twice
        if (function.activityExists(actin.getText())) {
            JOptionPane.showMessageDialog(this, "Activity already added");
            return;
        }     

        // Add new node to list of nodes
        function.addNode(actin.getText(), Integer.parseInt(durin.getText()), depend);
        JOptionPane.showMessageDialog(this, "Node added");
        

    }
}
