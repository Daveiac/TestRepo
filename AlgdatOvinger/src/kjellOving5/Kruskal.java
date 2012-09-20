import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Kruskal {

	public static int mst(Edge[] vertices, int nodeCount) {
		int max = Integer.MIN_VALUE;
		Tree[] trees = new Tree[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			trees[i] = new Tree(i);
		}
		for (Edge edge : vertices) {
			if (trees[edge.from].getNum() != trees[edge.to].getNum()) {
				trees[edge.to].setParent(trees[edge.from]);
				max = edge.weight;
			}
		}
		return max;
	}

	public static void main(String[]  args){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			ArrayList<String> input = new ArrayList<String>();
			String inp = in.readLine();
			while(inp!=null){
				input.add(inp);
				inp=in.readLine();
			}
			int nodeCount = input.size();
			ArrayList<Edge> verticesList = new ArrayList<Edge>(nodeCount*6);
			StringTokenizer st;
			StringTokenizer stSplitter;
			for(int i=0;i<nodeCount;i++){
				st = new StringTokenizer((String)input.get(i));
				while(st.hasMoreTokens()){
					stSplitter = new StringTokenizer(st.nextToken(), ":");
					verticesList.add(new Edge(i, Integer.parseInt(stSplitter.nextToken()), Integer.parseInt(stSplitter.nextToken())));
				}
			}
			int verticesLen = verticesList.size();
			Edge[] vertices = new Edge[verticesLen];
			for (int i = 0; i < verticesLen; i++) {
				vertices[i] = verticesList.get(i);
			}
			Arrays.sort(vertices);
			System.out.println(new StringBuilder().append(mst(vertices, nodeCount)).toString());
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