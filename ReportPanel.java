import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class will create a text file of the report.
 */
public class ReportPanel extends JPanel implements ActionListener{
    JTextField fileName;
    JButton createButton;

    public ReportPanel() {
        // Add JTextField and JButton
        fileName = new JTextField();
        createButton = new JButton("Create Report");
    
        this.add(fileName);
        this.add(createButton);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == createButton)
        {
        }
        
    }
}
