package labGen;

import java.util.Collections;

public class FindPath {
	
	public static Nodelist findPath(int startX, int startY, int endX, int endY, Boolean[][] walkways) {
		System.out.println("Start pathfind");
		if(!walkways[endX][endY]) {
			System.out.println("Target is not walkable");
			return null;
		}
		Node startNode = new Node(startX, startY);
		startNode.setgScore(0);
		Nodelist openSet = new Nodelist();
		Nodelist closedSet = new Nodelist();
		openSet.add(startNode);
		
		while(!openSet.isEmpty()) {
			Node currentNode = Collections.min(openSet);
			if (currentNode.getX() == endX && currentNode.getY() == endY) {
				return getPath(currentNode, walkways);
			}
			openSet.remove(currentNode);
			closedSet.add(currentNode);
			Nodelist adjacentNodes = new Nodelist();
			int[][] directions = new int[][] {new int[] {-1,0} ,new int[] {0,-1},new int[] {1,0},new int[] {0,1}};
			for (int[] is : directions) {
				int x = currentNode.getX()+is[0];
				int y = currentNode.getY()+is[1];
				Node node = new Node(x, y, currentNode);
				int gScore = currentNode.getgScore()+1;
				node.setgScore(gScore);
				node.setfScore(gScore+ Math.abs(endX-x) + Math.abs(endY-y));
				if(withinGrid(x, y, walkways) && walkways[x][y] && !closedSet.contains(node)) {
					adjacentNodes.add(node);
					Node existingNode = openSet.getNode(node);
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
	
	private static Nodelist getPath(Node currentNode,Boolean[][] walkways) {
		int length = currentNode.getgScore();
		Nodelist path = new Nodelist();
		path.add(currentNode);
		walkways[currentNode.getX()][currentNode.getY()] = null;		
		for (int i = 0; i < length-1; i++) {
			currentNode = currentNode.getParent();
			walkways[currentNode.getX()][currentNode.getY()] = null;
			path.add(0, currentNode);
		}
		System.out.println(path.toString());
		return path;
	}
	
	private static boolean withinGrid(int x, int y, Boolean[][] walkways) {
		return y >= 0 && x >= 0  && y < walkways.length && x < walkways[y].length;
	}

}
