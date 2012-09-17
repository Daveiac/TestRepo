package labGen;

import java.util.ArrayList;
import java.util.Collections;

public class FindPath {
	
	public static ArrayList<Node> findPath(int startX, int startY, int endX, int endY, Boolean[][] walkways) {
		System.out.println("Start pathfind");
		if(!walkways[endX][endY]) {
			System.out.println("Target is not walkable");
			return null;
		}
		Node startNode = new Node(startX, startY);
		startNode.setgScore(0);
		ArrayList<Node> openSet = new ArrayList<Node>();
		ArrayList<Node> closedSet = new ArrayList<Node>();
		openSet.add(startNode);
		
		while(!openSet.isEmpty()) {
			Node currentNode = Collections.min(openSet);
			if (currentNode.getX() == endX && currentNode.getY() == endY) {
				return getPath(currentNode, walkways);
			}
			openSet.remove(currentNode);
			closedSet.add(currentNode);
			int[][] directions = new int[][] {new int[] {-1,0} ,new int[] {0,-1},new int[] {1,0},new int[] {0,1}};
			for (int[] is : directions) {
				int x = currentNode.getX()+is[0];
				int y = currentNode.getY()+is[1];
				Node node = new Node(x, y, currentNode);
				int gScore = currentNode.getgScore()+1;
				node.setgScore(gScore);
				node.setfScore(gScore+ Math.abs(endX-x) + Math.abs(endY-y));
				if(withinGrid(x, y, walkways) && walkways[x][y] && getNode(node,closedSet) == null) {
					Node existingNode = getNode(node, openSet);
					if(existingNode == null || node.getgScore() < existingNode.getgScore()) {
						openSet.remove(existingNode);
						openSet.add(node);
					}
				}
			}
		}
		System.out.println("No path found");
		return null;
	}
	
	private static ArrayList<Node> getPath(Node currentNode,Boolean[][] walkways) {
		ArrayList<Node> path = new ArrayList<Node>();
		while (currentNode.getParent() != null) {
			walkways[currentNode.getX()][currentNode.getY()] = null;
			path.add(0, currentNode);
			currentNode = currentNode.getParent();
		}
		walkways[currentNode.getX()][currentNode.getY()] = null;
		System.out.println(path.toString());
		return path;
	}
	
	private static boolean withinGrid(int x, int y, Boolean[][] walkways) {
		return y >= 0 && x >= 0  && x < walkways.length && y < walkways[x].length;
	}
	
	public static Node getNode(Node node, ArrayList<Node> list) {
		for (Node m : list) {
			if(m.equals(node)) {
				return m;
			}
		}
		return null;
	}
}
