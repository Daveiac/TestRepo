package davidOving5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Kruskal {


	public static int mst(PriorityQueue<Edge> edges, Vertex[] noder) {
		ArrayList<Edge> A = new ArrayList<Edge>();
		Edge e = null;
		while(!edges.isEmpty()) {
			e = edges.poll();
			Vertex from = noder[e.getFrom()];
			Vertex fromsParent = from.getParent();
			Vertex to = noder[e.getTo()];
			Vertex tosParent = to.getParent();
			if(fromsParent != tosParent || fromsParent == null && tosParent == null) {
				A.add(e);
				if(tosParent == null) {
					if(fromsParent == null) {
						to.setParent(to);
					} else {
						to.setParent(fromsParent);
					}
				}
			}
		}
		return e.getWeight();
		
		
		
	}

	public static boolean containsVertex(int[] array, int v, int counter) {
		for (int j = 0; j < counter; j++) {
			if (array[j] == v)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		try {
			BufferedReader in = null;
			if (args.length > 0) {
				in = new BufferedReader(new FileReader(args[0]));
			} else {
				in = new BufferedReader(new InputStreamReader(System.in));
			}
			// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			ArrayList<String> input = new ArrayList<String>();
			String inp = in.readLine();
			while (inp != null) {
				input.add(inp);
				inp = in.readLine();
			}
			StringTokenizer st;
			StringTokenizer st2;
			PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
			Vertex[] noder = new Vertex[input.size()];
			for (int i = 0; i < input.size(); i++) {
				st = new StringTokenizer((String) input.get(i));
				Vertex vertex = new Vertex(i);
				noder[i] = vertex;
				while (st.hasMoreTokens()) {
					st2 = new StringTokenizer(st.nextToken(), ":");
					int to = Integer.parseInt(st2.nextToken());
					int weight = Integer.parseInt(st2.nextToken());
					edges.add(new Edge (i,to,weight));
				}
			}
			System.out.println(mst(edges, noder));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Edge implements Comparable<Edge>{
	private int from;
	private int to;
	private int weight;
	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	public int getFrom() {
		return from;
	}
	public int getTo() {
		return to;
	}
	public int getWeight() {
		return weight;
	}
	@Override
	public int compareTo(Edge o) {
		return weight-o.getWeight();
	}
	
	
}
//class Vertex implements Comparable<Vertex> {
//
//	private int node;
//
//	private int key;
//	private Vertex parent;
//	ArrayList<int[]> neighbours;
//
//	public Vertex(int node, int key) {
//		this.node = node;
//		this.key = key;
//		neighbours = new ArrayList<int[]>();
//	}
//
//	public Vertex(int node) {
//		this(node, Integer.MAX_VALUE);
//	}
//
//	public int getNode() {
//		return node;
//	}
//
//	public int getKey() {
//		return key;
//	}
//
//	public void setKey(int key) {
//		this.key = key;
//	}
//
//	public Vertex getParent() {
//		return parent;
//	}
//
//	public void setParent(Vertex parent) {
//		this.parent = parent;
//	}
//
//	public void addNeighbour(int neighbour, int weight) {
//		neighbours.add(new int[] { neighbour, weight });
//	}
//
//	public ArrayList<int[]> getNeighbours() {
//		return neighbours;
//	}
//
//	@Override
//	public int compareTo(Vertex arg0) {
//		return getKey() - arg0.getKey();
//	}
//
//}