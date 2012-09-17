package labGen2;

import java.util.ArrayList;

public class Node {
	private int x, y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private boolean visited;
	private ArrayList<Node> children = new ArrayList<Node>();
	
	public boolean getVisited() {
		return visited;
	}
	public void visit() {
		visited = true;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}
	public void addChild(Node node) {
		children.add(node);
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
