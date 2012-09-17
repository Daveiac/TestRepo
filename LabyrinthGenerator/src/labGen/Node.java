package labGen;

class Node implements Comparable<Node>{
	private int x;
	private int y;
	private int fScore;
	private int gScore;
	private Node parent;
	
	public Node(int x, int y, Node parent)  {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
	
	public Node(int x, int y) {
		this(x,y,null);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getfScore() {
		return fScore;
	}

	public void setfScore(int fScore) {
		this.fScore = fScore;
	}

	public int getgScore() {
		return gScore;
	}

	public void setgScore(int gScore) {
		this.gScore = gScore;
	}

	public Node getParent() {
		return parent;
	}
	
	@Override
	public int compareTo(Node other) {
		return getfScore()-other.getfScore();
	}
	
	public boolean equals(Node other) {
		return other != null && x == other.getX() && y == other.getY();
	}
	
	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}
	
}