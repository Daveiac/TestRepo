package labGen2;

import java.util.ArrayList;

public class Node2 {
	private int x, y;
	public Node2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private boolean visited;
	private ArrayList<Node2> children = new ArrayList<Node2>();
	
	public boolean getVisited() {
		return visited;
	}
	public void visit() {
		visited = true;
	}

	public ArrayList<Node2> getChildren() {
		return children;
	}
	public void addChild(Node2 node) {
		children.add(node);
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
