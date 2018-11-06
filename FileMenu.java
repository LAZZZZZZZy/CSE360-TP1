import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class FileMenu extends JMenu{

	/**
	 * @param frame 
	 * 
	 */
	public FileMenu (JFrame frame) {
		 super("File");
		 JMenuItem report = new JMenuItem("Create a file report", 'C');
		 
		 
		 
		 
		 this.add(report);
	}

}
