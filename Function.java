
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTextArea;

public class Function {

	private List<Node> nodes;
	private List<Path> path;
	private AddedPanel addedPanel;

	public Function ( List<Node> nodes, AddedPanel addedPanel ) {
		this.nodes = nodes;
		this.addedPanel = addedPanel;
		path = new ArrayList<Path>();
	}
	
	//connect all nodes in the node list
	public void ConnectNodes() {
		for ( Node n : nodes ) {
			//n is the start node if there is no dependencies
			if(n.getDependency()[0].equals("")) {
				n.setStart(true);
			}
			
			for ( String temp : n.getDependency() ) {
				if(dependenciesNode(temp)!=null) {
					dependenciesNode(temp).addNext(n);
					dependenciesNode(temp).setTail(false);
				}
			}
		}
	}
	// check all nodes are connected.
	// if connected return true, else return false;
	public boolean errorChecking () {
		ConnectNodes();
		
		boolean connect = true;
		boolean hastail = false;
		for ( Node n : nodes ) {
			if(n.getNext().isEmpty()&&!n.isStart()) {
				connect = false;
				break;
			}
			
			if(n.istail()) {
				hastail = true;
				break;
			}
		}
		return connect&&hastail;
	}

	//n is the start node
	public void formPath(Node n,double dur,String name) {
		dur += n.getDuration();
		name = name+ "-->" +n.getName();
		
		if(n.istail()) {
			path.add(new Path(name,dur));
			return;
		}

		for(Node next:n.getNext()) {
			formPath(next,dur,name);
		}
		
	}
	
	// sort the List order by duration descend
	public void descendSortList () {
		Collections.sort(path, new Comparator<Path>() {

      @Override
      public int compare(Path p1, Path p2) {
          if (p1.getDuration()>p2.getDuration()) {
              return 1;
          } else if(p1.getDuration()<p2.getDuration()){
              return -1;
          } else {
          	return 0;
          }
      }

  });
	}

	public void inputNodes ( List<Node> _nodes ) {
		nodes = _nodes;
	}

	public void addNode ( String activityName, int duration,
	                      String[] Dependencies ) {
		Node newNode = new Node(activityName,duration,Dependencies);

		nodes.add(newNode);

		this.updateList(addedPanel);
		printList();
	}

	/**
	 * If the dependencies node exist, return it. If not, return null
	 *
	 * @param list
	 * @return node
	 */
	public Node dependenciesNode ( String s ) {
		for ( Node n : nodes ) {
			if ( n.getName().equals(s) ) {
				return n;
			}
		}
		return null;
	}
	
	/**
	 * Similarly to dependenciesExists, this method checks if the given node
	 * exists
	 *
	 * @param activityName
	 * @return
	 */
	public boolean activityExists ( String activityName ) {
		for ( Node n : nodes ) {
			if ( n.getName().equals(activityName) ) {
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
	public List<Node> getListOFDepndencies ( String[] strArr ) {
		List<Node> list = new ArrayList<Node>();
		for ( String str : strArr ) {
			for ( Node node : nodes ) {
				if ( node.getName().equals(str) ) {
					list.add(node);
				}
			}
		}
		return list;
	}

	public void printList () {
		for ( Node n : nodes ) {
			System.out.println(n);
		}
	}

	public boolean hasNode ( String name ) {
		return false;
	}

	public void updateList ( JTextArea textField ) {
		textField.setText("");

		for ( Node n : nodes ) {
			textField.append(n.toString());
		}
	}
	
	public void initial() {
		nodes.clear();
		path.clear();
	}

	/**
	 * 
	 */
	public void process () {
		ConnectNodes();
		for(Node n: nodes) {
			if(n.isStart()) {
				formPath(n,n.getDuration(),n.getName());
			}
		}
	}
}
