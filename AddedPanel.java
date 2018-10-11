import java.awt.Panel;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class AddedPanel extends Panel{

  private Function function;

  JTextField actin;
  JTextField durin;
  JTextField dependein;
	/**
	 * 
	 */
	public AddedPanel (Function _function) {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
    function = _function;
    
    JLabel List = new JLabel("Added list");
    List.setBounds(250, 10, 100, 10);
    this.add(List);
	}

}
