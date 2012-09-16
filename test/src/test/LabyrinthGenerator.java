package test;

import java.util.ArrayList;
import java.util.Collections;

public class LabyrinthGenerator {
	private int width;
	private int height;
	private static boolean mainRun = false; 
	private boolean[][] walkways;
	
	public static void main(String[] args) {
		mainRun = true;
		new LabyrinthGenerator(20,10);
	}
	
	public LabyrinthGenerator(int width, int height) {
		this.width = width;
		this.height = height;
		walkways = new boolean[width][height];
		fillLevel();
		createLab(width/2, height/2);
		if (mainRun) {
			printLevel();
		}
	}

	public void genNewLab() {
		fillLevel();
		createLab(width/2, height/2);
	}
	
	private void createLab(int x, int y) {
		walkways[x][y] = true;
		ArrayList<Node> dirs = new ArrayList<Node>();
		if (x > 1) dirs.add(new Node(x-1, y));
		if (y > 1) dirs.add(new Node(x, y-1));
		if (x < width - 2) dirs.add(new Node(x+1, y));
		if (y < height - 2) dirs.add(new Node(x, y+1));
		
		Collections.shuffle(dirs);
		
		for (Node node : dirs) {
			if (walkways[node.x][node.y]) {
				continue;
			} else if (checkSurroundingTiles(x, y, node.x, node.y)) {
				createLab(node.x, node.y);
			}
			
		}
	}

	private boolean checkSurroundingTiles(int x, int y, int dx, int dy) {
		if (x > dx) {
			for (int i = -1; i < 2; i++) {
				if (walkways[dx-1][dy+i]) return false;
				else if(walkways[dx][dy+i] && dx != x) return false;
			}
		} else if (y > dy) {
			for (int i = -1; i < 2; i++) {
				if (walkways[dx+i][dy-1]) return false;
				else if (walkways[dx+i][dy] && dy != y) return false;
			}
		} else if (x < dx) {
			for (int i = -1; i < 2; i++) {
				if (walkways[dx+1][dy+i]) return false;
				else if (walkways[dx][dy+i] && dx != x) return false;
			}
		} else if (y < dy) {
			for (int i = -1; i < 2; i++) {
				if (walkways[dx+i][dy+1]) return false;
				else if (walkways[dx+i][dy] && dy != y) return false;
			}
		}
		return true;
	}

	private void printLevel() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(walkways[j][i] ? ' ' : '#');
			}
			System.out.println();
		}
	}

	public boolean[][] getWalkways() {
		return walkways;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	private void fillLevel() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				walkways[i][j] = false;
			}
		}
	}
}

class Node {
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
