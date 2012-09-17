package labGen;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Nodelist extends ArrayList<Node>{
	
	
	public boolean contains(Node node) {
		return getNode(node) != null;
	}
	
	public Node getNode(Node node) {
		for (Node m : this) {
			if(m.equals(node)) {
				return m;
			}
		}
		return null;
	}
}