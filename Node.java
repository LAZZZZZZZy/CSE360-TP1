
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Node implements Comparable {

    private String activity_name;
    private int duration;
    public String[] temp_dependency;//temporary dependency from input
    private List<Node> dependencies;//exact dependencies

    public Node(String _activity_name, int _duration, String[] _temp_dependency) {
        activity_name = _activity_name;
        duration = _duration;
        temp_dependency = _temp_dependency;
        dependencies = new ArrayList<Node>();
    }

    public void setName(String activity_name) {
        this.activity_name = activity_name;
    }

    public void addDependent(Node dependent) {
        dependencies.add(dependent);
    }

    public void setDuration(int val) {
        duration = val;
    }

    public void setDependencies(List<Node> dependent) {
        dependencies = dependent;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return activity_name;
    }

    public List<Node> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return "Name:\t" + activity_name + "\n"
                + "Duration:\t" + duration + "\n"
                + "Dependencies:\t" + this.getDependenciesNames() + "\n";
    }
    
    /**
     * This method will return a string of the dependencies names.
     * This is a helper method for the toString method.
     * @return 
     */
    public String getDependenciesNames() {
        String dependenciesNames = "";
        if(this.dependencies.isEmpty()) {
            dependenciesNames = "No dependencies";
        } else {
            for (Node depend : this.dependencies) {
                dependenciesNames += depend.getName() + " ";
            }
        }
        
        return dependenciesNames + "\n";
    }

    @Override
    public int compareTo(Object t) {
        Node otherNode = (Node) t;
        if (this == otherNode) {
            return 0;
        }

        if (this.duration < otherNode.duration) {
            return 1;
        } else if (this.duration == otherNode.duration) {
            return 0;
        } else {
            return -1;
        }
    }
}
