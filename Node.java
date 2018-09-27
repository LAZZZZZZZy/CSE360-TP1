import java.util.List;

public class Node {
	private String activity_name;
	private int duration;
	private List<Node> dependencies;
	
	public Node(String _activity_name, int _duration, List<Node> _dependencies) {
		activity_name = _activity_name;
		duration = _duration;
		dependencies = _dependencies;
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
	
	public List<Node> getDependencies(){
		return dependencies;
	}
}
