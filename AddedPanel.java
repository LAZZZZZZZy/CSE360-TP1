
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AddedPanel extends JPanel {

    private Function function;
    private JTextArea content;

    public AddedPanel(Function function) {
        this.setLayout(new BorderLayout());
        this.function = function;

        content = new JTextArea();
        content.setEditable(false);

        JScrollPane jsp = new JScrollPane(content);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        content.setEditable(false);
        
        this.add(jsp, BorderLayout.CENTER);
    }

    public void Output() {
        content.setText("");
        for (Node n : function.getNodes()) {
            content.append(n.toString());
            this.repaint();
            this.revalidate();
        }
    }
}
