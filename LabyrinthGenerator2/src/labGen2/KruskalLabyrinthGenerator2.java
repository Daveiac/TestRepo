package labGen2;

import java.util.PriorityQueue;
import java.util.Random;

public class KruskalLabyrinthGenerator2 implements LabGen {
	private int width;
	private int height;
	
	private Node2[][] nodeNetwork;
	PriorityQueue<Edge> verticesQueue;
	
	public static void main(String[] args) {
		int initWidth, initHeight;
		if (args.length == 2) {
			try {
				initWidth = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				initWidth = 28;
			}
			try {
				initHeight = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				initHeight = 16;
			}
		} else {
			initWidth = 10; initHeight = 10;
		}
		new KruskalLabyrinthGenerator2(initWidth, initHeight).printLab();
	}
	
	private void printLab() {
		for (Node2[] row : nodeNetwork) {
			for (Node2 node : row) {
				System.out.print("Node: (" + node.getX() + "," + node.getY() + ") Children:");
				for (Node2 child : node.getChildren()) System.out.print(" (" + child.getX() + "," + child.getY() + ")");
				System.out.println();
			}
		}
	}

	public KruskalLabyrinthGenerator2(int width, int height) {
		this.width = width; this.height = height;
		nodeNetwork = new Node2[width][height];
		initNodeNetwork();
		initVertices();
		generateLabyrinth();
	}

	private void initNodeNetwork() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				nodeNetwork[i][j] = new Node2(i, j);
			}
		}
	}

	private void initVertices() {
		verticesQueue = new PriorityQueue<Edge>();
		Random r = new Random();
		for(int i=0;i<width;i++){
			for (int j = 0; j < height; j++) {
				if (i > 0) verticesQueue.add(new Edge(new int[]{i, j}, new int[]{i-1, j}, r.nextInt(128)));
				if (j > 0) verticesQueue.add(new Edge(new int[]{i, j}, new int[]{i, j-1}, r.nextInt(128)));
				if (i < width - 1) verticesQueue.add(new Edge(new int[]{i, j}, new int[]{i+1, j}, r.nextInt(128)));
				if (j < height - 1) verticesQueue.add(new Edge(new int[]{i, j}, new int[]{i, j+1}, r.nextInt(128)));
			}
		}
	}

	private void generateLabyrinth() {
		int nodeCount = width * height;
		Tree[][] trees = new Tree[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				trees[i][j] = new Tree(new int[] {i,j});
			}
		}
		for (int i = 0, len = verticesQueue.size(); i < len; i++) {
			Edge edge = verticesQueue.poll();
			if (trees[edge.from[0]][edge.from[1]].getNum() != trees[edge.to[0]][edge.to[1]].getNum()) {
				trees[edge.to[0]][edge.to[1]].setParent(trees[edge.from[0]][edge.from[1]]);
				nodeNetwork[edge.from[0]][edge.from[1]].addChild(nodeNetwork[edge.to[0]][edge.to[1]]);
				nodeNetwork[edge.to[0]][edge.to[1]].addChild(nodeNetwork[edge.from[0]][edge.from[1]]);
				
			}
			if (i % nodeCount == 0) {
				boolean stop = true;
				int[] val = trees[0][0].getNum();
				for (Tree[] row : trees) {
					for (Tree tree : row) {
						if (tree.getNum() != val) {
							stop = false;
							break;
						}
					};
				}
				if(stop) break;
			}
		}
		
//		node.visit();
//		int x = node.getX();
//		int y = node.getY();
//		ArrayList<Node2> surroundingNodes = new ArrayList<Node2>();
//		if (x > 0) surroundingNodes.add(nodeNetwork[x-1][y]);
//		if (y > 0) surroundingNodes.add(nodeNetwork[x][y-1]);
//		if (x < width - 1) surroundingNodes.add(nodeNetwork[x+1][y]);
//		if (y < height - 1) surroundingNodes.add(nodeNetwork[x][y+1]);
//		Collections.shuffle(surroundingNodes);
//		for (Node2 nextNode : surroundingNodes) {
//			if (!nextNode.getVisited()) {
//				node.addChild(nextNode);
//				nextNode.setParent(node);
//				generateLabyrinth(nextNode);
//			}
//		}
	}

	public Node2[][] getNetwork() {
		return nodeNetwork;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	public void genNewLab(int startX, int startY) {
		nodeNetwork = new Node2[width][height];
		initNodeNetwork();
		initVertices();
		generateLabyrinth();
	}
}

class Tree {
	final int[] num;
	Tree parent = this;
	public Tree(int[] num) {
		this.num = num;
	}
	public int[] getNum() {
		if (!this.equals(parent)) return parent.getNum();
		else return num;
	}
	public void setParent(Tree node) {
		if (this.equals(parent)) parent = node;
		else parent.setParent(node);
	}
}

class Edge implements Comparable<Edge> {
	int weight;
	final int[] from, to;
	public Edge(int[] from, int[] to, int weight) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
	@Override
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}
	public String toString() {
		return "Edge - From:(" + from[0] + "," + from[1] + ") to:(" + to[0] + "," + to[1] + ") weight:" + weight;
	}
}