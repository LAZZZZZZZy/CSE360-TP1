import javax.swing.*;

public class AboutMenu extends JMenu {

    public AboutMenu(JFrame parent) {
        super("About");

        // The single menu item.
        JMenuItem about = new JMenuItem("About Path Analyzer...", 'A');
        about.addActionListener(event -> {
            String html =
            "<html><body style='width:320px;'>" +
            "<h2>Path Analyzer</h2>" +
            "<h3>Version: " + Main.VERSION + "</h3>" +
            "<p>Path Analyzer is a simple program that can be used to display the critical paths through an activity diagram.</p>" +
            "<ul>" +
            "<li>Curtis Anderson</li>" +
            "<li>Leonel Higuera</li>" +
            "<li>David Powell</li>" +
            "<li>Jiayan Wang</li>" +
            "</ul>" +
            "</body></html>";
            JOptionPane.showMessageDialog(parent, html, "About", JOptionPane.INFORMATION_MESSAGE);
        });

        super.add(about);
    }

}
