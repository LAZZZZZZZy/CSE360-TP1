import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HelpMenu extends JMenu {

    private List<HelpTopic> topics;

    public HelpMenu(JFrame parent) {
        super("Help");
        this.topics = new ArrayList<>();

        JMenuItem header = new JMenuItem("Help Topics");
        header.setEnabled(false);
        super.add(header);
        super.add(new JSeparator());

        topics.add(new HelpTopic("Add Node",
                "To add a node to the activity diagram, click on the [INPUT] button on the toolbar, and " +
                "fill in the fields.  Finally, click the [ADD] button at the bottom of the window.  If the node is " +
                "already in the activity graph, an error message will be displayed."));
        topics.add(new HelpTopic("Process",
                "To process the nodes that have been entered into the program, press the [PROCESS] button " +
                "on the toolbar.  If the nodes are not properly connected, the program will issue an error message."));
        topics.add(new HelpTopic("View Nodes",
                "To view the nodes that have been added to the program, simply click the [ADDED] button on " +
                "the toolbar.  A textual output of all the nodes that have been added to the current process will be " +
                "shown.  This output is helpful for checking that information was entered correctly."));
        topics.add(new HelpTopic("Cycle Detected",
                "This error message will appear when nodes in the graph form an endless loop.  In this case, " +
                "the endered chart is invalid and must be fixed before the processing can occur."));
        topics.add(new HelpTopic("Integer Duration",
                "This error message will appear when the text entered in the 'Duration' field of the [INPUT] " +
                "tab is not a valid integer value.  The program will not be able to add the node until this field is " +
                "entered as an integer."));

        JMenuItem item;
        for(HelpTopic topic : topics) {
            item = new JMenuItem(topic.title);
            item.addActionListener(event -> {
                String html = "<html><body style='width:320px;'><h2>" + topic.title + "</h2><p>" + topic.description + "</p></body></html>";
                JOptionPane.showMessageDialog(parent, html, "Help Catalog", JOptionPane.QUESTION_MESSAGE);
            });
            super.add(item);
        }
    }

    private class HelpTopic {
        public String title;
        public String description;

        public HelpTopic(String title, String description) {
            this.title = title;
            this.description = description;
        }
    }

}
