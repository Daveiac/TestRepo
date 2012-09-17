package labGen2;

import java.util.ArrayList;

public class FindPath2 {
	private static ArrayList<Node2> path = new ArrayList<Node2>();
	
	public static ArrayList<Node2> findPath(Node2 start, Node2 end) {
		dfs(start, end);
		return path;
	}

	private static boolean dfs(Node2 currentNode, Node2 end) {
		currentNode.search();
		if (currentNode.equals(end)) {
			path.add(currentNode);
			return true;
		} else if (currentNode.getParent() != null && !currentNode.getParent().isSearched() && dfs(currentNode.getParent(), end)) {
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
