package labGen2;

import java.util.ArrayList;

public class Node2 {
	private int x, y;
	private boolean visited;
	private boolean searched;
	private ArrayList<Node2> children = new ArrayList<Node2>();
	private Node2 parent;
	
	public Node2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node2 getParent() {
		return parent;
	}
	public void setParent(Node2 parent) {
		this.parent = parent;
	}
	
	public boolean isSearched() {
		return searched;
	}
	public void search() {
		searched = true;
	}
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
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
