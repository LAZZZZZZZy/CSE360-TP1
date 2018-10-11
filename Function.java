
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTextArea;

public class Function {

    private List<Node> nodes;
    private AddedPanel addedPanel;

    public Function(List<Node> nodes, AddedPanel addedPanel) {
        this.nodes = nodes;
        this.addedPanel = addedPanel;
    }

    //check all nodes are connected. 
    //if connected return true, else return false;
    public boolean errorChecking() {
        return false;
    }

    //sort the List order by duration descend
    public List<Node> descendSortList() {
        return nodes;
    }

    public void inputNodes(List<Node> _nodes) {
        nodes = _nodes;
    }

    public void addNode(String activityName, int duration, String[] Dependencies) {
        Node newNode = new Node(activityName,duration,Dependencies);
        nodes.add(newNode);
        
        // Add Dependencies
        for (Node n : getListOFDepndencies(Dependencies)) {
            newNode.addDependent(n);
        }
        
        Collections.sort(nodes, Collections.reverseOrder());
        this.updateList(addedPanel);
    }

    /**
     * Check if each dependency exists
     *
     * @param list
     * @return True if each dependency exits
     */
    public boolean dependenciesExists(String[] list) {
        for (String s : list) {
            boolean exists = false;
            for (Node n : nodes) {
                if (n.getName().equals(s)) {
                    exists = true;
                }
            }
            // If dependency does not exists, return false
            if (!exists) {
                return false;
            }
        }

        // All dependencies exits
        return true;
    }

    /**
     * Similarly to dependenciesExists, this method checks if the given node
     * exists
     *
     * @param activityName
     * @return
     */
    public boolean activityExists(String activityName) {
        for (Node n : nodes) {
            if (n.getName().equals(activityName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns List of Activities with the same name
     *
     * @param strArr
     * @return
     */
    public List<Node> getListOFDepndencies(String[] strArr) {
        List<Node> list = new ArrayList<Node>();
        for (String str : strArr) {
            for (Node node : nodes) {
                if (node.getName().equals(str)) {
                    list.add(node);
                }
            }
        }
        return list;
    }

    public void printList() {
        for (Node n : nodes) {
            System.out.println(n);
        }
    }

    public boolean hasNode(String name) {
        return false;
    }
    
        public void updateList(JTextArea textField) {
        textField.setText("");
        
        for (Node n : nodes) {
            textField.append(n.toString());
        }
    }
}
