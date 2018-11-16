
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class will create a text file of the report.
 */
public class ReportPanel extends JPanel implements ActionListener {

    JTextField fileName;
    JButton createButton;
    Function function;

    public ReportPanel(Function function) {
        this.function = function;
        
        this.setLayout(null);
        // Add JTextField and JButton
        JLabel name = new JLabel("Report Name: ");

        fileName = new JTextField();
        createButton = new JButton("Create");
        createButton.addActionListener(this);
        
        name.setBounds(30,60,100,50);
        fileName.setBounds(150, 70, 400, 30);
        createButton.setBounds(250, 120, 100, 30);;
        
        this.add(name);
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
                printWriter.println(fileName.getText());

                // Write date and time
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                Date date = new Date();
                printWriter.println(dateFormat.format(date) + "\n");

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
