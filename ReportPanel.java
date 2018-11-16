
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class will create a text file of the report.
 */
public class ReportPanel extends JPanel implements ActionListener {

    JTextField fileName;
    JButton createButton;

    public ReportPanel() {
        // Add JTextField and JButton
        fileName = new JTextField();
        createButton = new JButton("Create Report");
        createButton.addActionListener(this);

        this.add(fileName);
        this.add(createButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == createButton) {
            if (fileName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Enter Name");
                return;
            }

            try {
                FileWriter fileWriter = new FileWriter(fileName.getText() + ".txt");
                BufferedWriter bufferedWritter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWritter);
                
                // Write title of file
                printWriter.println();
                
                // Write date and time
                
                /////
                System.out.println(fileName + " was written");

                printWriter.close();
                bufferedWritter.close();
                fileWriter.close();
            } catch (IOException e) {
            }

            JOptionPane.showMessageDialog(this, "File Created");
        }

    }
}
