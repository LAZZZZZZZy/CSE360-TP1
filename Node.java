
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Node implements Comparable {

    private String activity_name;
    private int duration;
    public String[] dependency;//temporary dependency from input
    private List<Node> next;
    private boolean istail = true;
    private boolean isStart = false;

    public Node(String _activity_name, int _duration, String[] _dependency) {
        activity_name = _activity_name;
        duration = _duration;
        dependency = _dependency;
        next = new ArrayList<Node>();
    }


    public void setName(String activity_name) {
        this.activity_name = activity_name;
    }
    
    public void addNext(Node dependent) {
    	next.add(dependent);
    }
    
    public List<Node> getNext() {
    	return next;
    }
    
    public boolean istail() {
    	return istail;
    }
    
    public boolean isStart() {
    	return isStart;
    }
    
    public void setTail(boolean b) {
    	istail = b;
    }
    
    public void setStart(boolean b) {
    	isStart = b;
    }

    public void setDuration(int val) {
        duration = val;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return activity_name;
    }
    
		/**
		 * @return the temp_dependency
		 */
		public String[] getDependency () {
			return dependency;
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
        for (String depend : dependency) {
                dependenciesNames += depend+"   ";
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
