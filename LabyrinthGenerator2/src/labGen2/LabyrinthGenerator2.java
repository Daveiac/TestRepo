package labGen2;

import java.util.ArrayList;
import java.util.Collections;

public class LabyrinthGenerator2 {
	private int width;
	private int height;
	
	private Node[][] nodeNetwork;
	private Node root;
	
	public static void main(String[] args) {
		int initWidth, initHeight;
		if (args.length == 2) {
			initWidth = 20; initHeight = 10; //TODO
		} else {
			initWidth = 20; initHeight = 10;
		}
		new LabyrinthGenerator2(initWidth, initHeight);
	}
	
	public LabyrinthGenerator2(int width, int height) {
		this.width = width; this.height = height;
		nodeNetwork = new Node[width][height];
		initNodeNetwork();
		root = nodeNetwork[width/2][height/2];
		generateLabyrinth(root);
	}

	private void initNodeNetwork() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				nodeNetwork[i][j] = new Node(i, j);
//				System.out.println(nodeNetwork[i][j].getX() + ", " + nodeNetwork[i][j].getY() + " - " + nodeNetwork[i][j].getVisited());
			}
		}
	}

	private void generateLabyrinth(Node node) {
		node.visit();
		int x = node.getX();
		int y = node.getY();
		ArrayList<Node> surroundingNodes = new ArrayList<Node>();
		if (x > 0) surroundingNodes.add(nodeNetwork[x-1][y]);
		if (y > 0) surroundingNodes.add(nodeNetwork[x][y-1]);
		if (x < width - 1) surroundingNodes.add(nodeNetwork[x+1][y]);
		if (y < height - 1) surroundingNodes.add(nodeNetwork[x][y+1]);
		Collections.shuffle(surroundingNodes);
		for (Node nextNode : surroundingNodes) {
			if (!nextNode.getVisited()) {
				node.addChild(nextNode);
				generateLabyrinth(nextNode);
			}
		}
//		System.out.print(node.getX() + ", " + node.getY() + " - " + node.getVisited());
//		for (Node child : node.getChildren()) System.out.print(" (" + child.getX() + "," + child.getY() + ")");
//		System.out.println();
	}

	public Node[][] getNetwork() {
		return nodeNetwork;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	public void genNewLab() {
		nodeNetwork = new Node[width][height];
		initNodeNetwork();
		root = nodeNetwork[width/2][height/2];
		generateLabyrinth(root);
	}
	
	
}
