package labGen2;

import java.util.ArrayList;

public class FindPath2 {
	private static ArrayList<Node2> path = new ArrayList<Node2>();
	
	public static ArrayList<Node2> findPath(Node2[][] network, int startX, int startY, int endX, int endY) {
		for (Node2[] row : network) {
			for (Node2 node : row) {
				node.setSearched(false);
			}
		}
		Node2 start = network[startX][startY];
		Node2 end = network[endX][endY];
		dfs(start, end);
		return path;
	}

	private static boolean dfs(Node2 currentNode, Node2 end) {
		currentNode.setSearched(true);
		if (currentNode.equals(end)) {
			path.add(currentNode);
			return true;
		} else {
			if (currentNode.getChildren() != null) {
				for (Node2 child : currentNode.getChildren()) {
					if (!child.isSearched()) {
						if (dfs(child, end)) {
							path.add(currentNode);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
