package labGen2;

import java.util.ArrayList;
import java.util.Collections;

public class LabyrinthGenerator2 {
	private int width;
	private int height;
	
	private Node2[][] nodeNetwork;
	private Node2 root;
	
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
			initWidth = 20; initHeight = 10;
		}
		new LabyrinthGenerator2(initWidth, initHeight).printLab();
	}
	
	private void printLab() {
		for (Node2[] row : nodeNetwork) {
			for (Node2 node : row) {
				System.out.print("Node: (" + node.getX() + "," + node.getY() + ") Parent: ");
				System.out.print((node.getParent() != null ? "(" + node.getParent().getX() + "," + node.getParent().getY() + ")" : "null ") + " Children:");
				for (Node2 child : node.getChildren()) System.out.print(" (" + child.getX() + "," + child.getY() + ")");
				System.out.println();
			}
		}
	}

	public LabyrinthGenerator2(int width, int height) {
		this.width = width; this.height = height;
		nodeNetwork = new Node2[width][height];
		initNodeNetwork();
		root = nodeNetwork[0][0];
		generateLabyrinth(root);
	}

	private void initNodeNetwork() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				nodeNetwork[i][j] = new Node2(i, j);
//				System.out.println(nodeNetwork[i][j].getX() + ", " + nodeNetwork[i][j].getY() + " - " + nodeNetwork[i][j].getVisited());
			}
		}
	}

	private void generateLabyrinth(Node2 node) {
		node.visit();
		int x = node.getX();
		int y = node.getY();
		ArrayList<Node2> surroundingNodes = new ArrayList<Node2>();
		if (x > 0) surroundingNodes.add(nodeNetwork[x-1][y]);
		if (y > 0) surroundingNodes.add(nodeNetwork[x][y-1]);
		if (x < width - 1) surroundingNodes.add(nodeNetwork[x+1][y]);
		if (y < height - 1) surroundingNodes.add(nodeNetwork[x][y+1]);
		Collections.shuffle(surroundingNodes);
		for (Node2 nextNode : surroundingNodes) {
			if (!nextNode.getVisited()) {
				node.addChild(nextNode);
				nextNode.setParent(node);
				generateLabyrinth(nextNode);
			}
		}
//		System.out.print(node.getX() + ", " + node.getY() + " - " + node.getVisited());
//		for (Node child : node.getChildren()) System.out.print(" (" + child.getX() + "," + child.getY() + ")");
//		System.out.println();
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
		root = nodeNetwork[startX][startY];
		generateLabyrinth(root);
	}
	
	
}