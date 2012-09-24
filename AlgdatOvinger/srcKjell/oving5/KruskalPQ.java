package oving5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class KruskalPQ {

	public static int mst(PriorityQueue<Edge> verticesList, int nodeCount) {
		int max = Integer.MIN_VALUE;
		
		Tree[] trees = new Tree[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			trees[i] = new Tree(i);
		}
		for (int i = 0, len = verticesList.size(); i < len; i++) {
			Edge edge = verticesList.poll();
			if (trees[edge.from].getNum() != trees[edge.to].getNum()) {
				trees[edge.to].setParent(trees[edge.from]);
				max = edge.weight;
			}
			if (i % nodeCount == 0) {
				boolean stop = true;
				int val = trees[0].getNum();
				for (Tree tree : trees) {
					if (tree.getNum() != val) {
						stop = false;
						break;
					}
				};
				if(stop) break;
			}
		}
		return max;
	}
	
	public static void main(String[] args){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			ArrayList<String> input = new ArrayList<String>(500);
			String inp = in.readLine();
			while(inp!=null){
				input.add(inp);
				inp=in.readLine();
			}
			int nodeCount = input.size();
			PriorityQueue<Edge> verticesQueue = new PriorityQueue<Edge>(nodeCount*3);
			StringTokenizer st;
			StringTokenizer stSplitter;
			for(int i=0;i<nodeCount;i++){
				st = new StringTokenizer(input.get(i));
				while(st.hasMoreTokens()){
					stSplitter = new StringTokenizer(st.nextToken(), ":");
					verticesQueue.add(new Edge(i, Integer.parseInt(stSplitter.nextToken()), Integer.parseInt(stSplitter.nextToken())));
				}
			}
			System.out.println(new StringBuilder().append(mst(verticesQueue, nodeCount)).toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

class Tree {
	final int num;
	Tree parent = this;
	public Tree(int num) {
		this.num = num;
	}
	public int getNum() {
		if (!this.equals(parent)) {
			return parent.getNum();
		} else {
			return num;
		}
	}
	public void setParent(Tree node) {
		if (this.equals(parent)) {
			parent = node;
		} else {
			parent.setParent(node);
		}
	}
}

class Edge implements Comparable<Edge> {
	int weight;
	final int from, to;
	public Edge(int from, int to, int weight) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
	@Override
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}
}