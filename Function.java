import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Function {
	private List<Node> nodes;
	
	public Function() {
		nodes = new ArrayList<Node>();
	}
	
	//check all nodes are connected. 
	//if connected return true, else return false;
	public boolean errorChecking() {
		return false;
	}
	
	//sort the List order by duration descend
	public List<Node> descendSortList(){
		return nodes;
	}
	
	public void inputNodes(List<Node> _nodes) {
		nodes = _nodes;
	}
	
	public boolean hasNode(String name) {
		return false;
	}
}