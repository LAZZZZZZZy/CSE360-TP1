
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTextArea;

public class Function {

	private List<Node> nodes;
	private Set<Node> startNodes;
	private Set<Node> tailNodes;
	private List<Path> path;
	private List<Path> criticalPath;
	private Set<Node> containNode;
	HashMap<Node,Integer> early;
	HashMap<Node,Integer> late; 
	
	private AddedPanel addedPanel;

	public Function ( List<Node> nodes, AddedPanel addedPanel ) {
		this.nodes = nodes;
		this.addedPanel = addedPanel;
		startNodes = new HashSet<Node>();
		path = new ArrayList<Path>();
		criticalPath = new ArrayList<Path>();
		tailNodes = new HashSet<Node>();
		containNode = new HashSet<Node>();
		early = new HashMap<Node,Integer>();
		late  = new HashMap<Node,Integer>();
	}
	
	//connect all nodes in the node list
	public boolean ConnectNodes() {

		for ( Node n : nodes ) {
			//n is the start node if there is no dependencies
			if(n.getDependency()[0].equals("")) {
				n.setStart(true);
				startNodes.add(n);
				continue;
			}
			
			for ( String temp : n.getDependency() ) {
				if(dependenciesNode(temp)!=null) {
					dependenciesNode(temp).addNext(n);
					n.setPrevious(dependenciesNode(temp));
					dependenciesNode(temp).setTail(false);
				} else {
					return false;
				}
			}
		}

		return true;
	}
	// check all nodes are connected.
	// if connected return true, else return false;
	public boolean errorCheckingCycle () {

		boolean hastail = false;
		for ( Node n : nodes ) {
			if(n.istail()) {
				hastail = true;
				break;
			}
		}
		return hastail;
	}
	
	// check all nodes are connected.
	// if connected return true, else return false;
	public boolean errorCheckingConnect () {
		
		List<Node> l = new ArrayList<Node>();
		l.addAll(nodes);
		//check multiple start nodes
		if(startNodes.size()>1) {
			int count =0 ;
			for(Node start:startNodes) {
				if(errorCheckingMulConnect(start,containNode)) {
					count++;
				}
			}
			if(count>1) {
				return false;
			} else {
				return true;
			}
		}
		
		//only one node
		if(nodes.size()==1) {
			return true;
		}
		
		//check one start node
		for ( Node n : nodes ) {			
			for(Node next : n.getNext()) {
				containNode.add(n);
				containNode.add(next);
			}
		}
		l.removeAll(containNode);
		if(l.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean errorCheckingMulConnect(Node n,Set<Node> s) {

		for(Node next:n.getNext()) {
			if(s.contains(next)) {
				return false;
			}
			s.add(next);
			if(!errorCheckingMulConnect(next,s)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 */
	public void process () {

		processini();
		
		for(Node n: nodes) {
			if(n.isStart()) {
				createPath(n,0,"");
			}
		}
		createCritical();
		descendSortList();
	}
	
	/**
	 * @param n
	 */
	private void createCritical () {
		for(Node start:startNodes) {
			early.put(start,start.getDuration());
			Criticalearly(start);
		}
		
		int max = 0;
		for(Node tail:tailNodes) {
			if(early.get(tail)>max) {
				max = early.get(tail);
			}
		}
		//ignore some tails path that smaller than the longest tail path
		List<Node> remove = new ArrayList<Node>();
		for(Node tail:tailNodes) {
			if(early.get(tail)<max) {
				tail.setIgnored(true);
			}
		}
		
		for(Node tail:tailNodes) {
			late.put(tail,early.get(tail)-tail.getDuration());
			Criticallate(tail);
		}
		
		for(Node start:startNodes) {
			createCriticalPath(start,0,"");
		}

	}
	
	/**
	 * @param start 
	 * 
	 */
	private void createCriticalPath (Node n,int dur,String name) {
		if(n.istail()&&n.isIgnored()!=true) {
			dur += n.getDuration();
			name += n.getName();
			criticalPath.add(new Path(name,dur));
			return;
		}
		//since the late is late start time and early is early finished time
		if(late.get(n)!=early.get(n)-n.getDuration()) {
			return;
		}
		dur += n.getDuration();
		name += n.getName()+"-->";
		for(Node next:n.getNext()) {
			createCriticalPath(next,dur,name);
		}
		return;
	}

	/**
	 * @param start
	 * late start time table
	 */
	private void Criticallate ( Node n ) {
		if(n.isStart()) {
			return;
		}
		for(Node pre:n.getPrevious()) {
			if(late.containsKey(pre)){
				if(late.get(pre)>late.get(n)-pre.getDuration()) {
					late.put(pre,late.get(n)-pre.getDuration());
				}
			}else {
				late.put(pre,late.get(n)-pre.getDuration());
			}
			Criticallate(pre);
		}
		return;
		
	}
	//early finished time table
	private void Criticalearly(Node n) {
		if(n.istail()) {
			return;	
		}
		for(Node next:n.getNext()) {
			if(next.istail()) {
				tailNodes.add(next);
			}
			if(early.containsKey(next)){
				if(early.get(next)<early.get(n)+next.getDuration()) {
					early.put(next,early.get(n)+next.getDuration());
				}
			}else {
				early.put(next,early.get(n)+next.getDuration());
			}
			Criticalearly(next);
		}
		return;
	}
	
	//n is the start node
	public void createPath(Node n,int dur,String name) {
		
		if(n.istail()) {
			dur += n.getDuration();
			name += n.getName();
			path.add(new Path(name,dur));
			System.out.println(name+"  "+dur);
			System.out.println(path.size());
			return;
		} else {
			dur += n.getDuration();
			name += n.getName()+ "-->";
		}

		for(Node next:n.getNext()) {
			createPath(next,dur,name);
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
	 * @param dependency 
	 * @return
	 */
	public boolean activityExists ( String activityName) {
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
	
	public void processini() {
		path.clear();
		containNode.clear();
		criticalPath.clear();
		tailNodes.clear();
		early.clear();
		late.clear();
	}
	
	public void initial() {
		nodes.clear();
		path.clear();
		containNode.clear();
		criticalPath.clear();
		startNodes.clear();
		tailNodes.clear();
		early.clear();
		late.clear();
		
		updateList(addedPanel);
	}


	/**
	 * @return the path
	 */
	public List<Path> getPath () {
		return path;
	}

	/**
	 * @return the nodes
	 */
	public List<Node> getNodes () {
		return nodes;
	}

	/**
	 * @return the criticalPath
	 */
	public List<Path> getCriticalPath () {
		return criticalPath;
	}
        
        /**
         * Updates activity duration
         * @param activity
         * @param newDuration 
         */
        public void updateDuraton(String activity, int newDuration){
           for (Node n : nodes) {
               if (n.getName().equals(activity)) {
                   n.setDuration(newDuration);
                   break;
               }
           }
           this.updateList(addedPanel);
        }

}
