
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node implements Comparable {

    private String activity_name;
    private int duration;
    public String[] dependency;//temporary dependency from input
    private Set<Node> next;
    private Set<Node> previous;
    private boolean istail = true;
    private boolean isStart = false;
    private boolean ignored = false;

    public Node(String _activity_name, int _duration, String[] _dependency) {
        activity_name = _activity_name;
        duration = _duration;
        dependency = _dependency;
        next = new HashSet<Node>();
        previous = new HashSet<Node>();
    }


    public void setName(String activity_name) {
        this.activity_name = activity_name;
    }
    
    public void addNext(Node dependent) {
    	next.add(dependent);
    }
    
    public Set<Node> getNext() {
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


		/**
		 * @return the previous
		 */
		public Set<Node> getPrevious () {
			return previous;
		}


		/**
		 * @param previous the previous to set
		 */
		public void setPrevious ( Node p ) {
			previous.add(p);
		}


		/**
		 * @return the ignored
		 */
		public boolean isIgnored () {
			return ignored;
		}


		/**
		 * @param ignored the ignored to set
		 */
		public void setIgnored ( boolean ignored ) {
			this.ignored = ignored;
		}

}
