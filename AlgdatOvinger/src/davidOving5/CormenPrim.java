package davidOving5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class CormenPrim{


	private static final int INF = Integer.MAX_VALUE;

	public static int mst(Vertex[] noder){
		Vertex startNode = noder[0];
		Vertex[] noderSett = new Vertex[noder.length];
		int counter = 0;
		noderSett[counter++] = startNode;
		int max = Integer.MIN_VALUE;
		while(!Q.isEmpty()) {
			u = Q.poll();
			for (int i = 0; i < noder.length; i++) {
				int w = noder[u.getNode()][i];
				if(w == INF) continue;
				Vertex v = getVertex(Q, i);
				if(v != null && w < v.getKey()) {
					v.setParent(u);
					v.setKey(w);
				}
			}
		}
		return max;
	}


	public static boolean contains(Vertex[] array, Vertex v, int counter) {
		for (int j = 0; j < counter; j++) {
			if(array[j] == v) return true;
		}
		return false;
	}

	public static void main(String[]  args){
		try{
			BufferedReader in=null;
			if(args.length>0){
				in = new BufferedReader(new FileReader(args[0]));
			}
			else{
				in = new BufferedReader(new InputStreamReader(System.in));
			}
			//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			ArrayList<String> input = new ArrayList<String>();
			String inp = in.readLine();
			while(inp!=null){
				input.add(inp);
				inp=in.readLine();
			}
			StringTokenizer st;
			StringTokenizer st2;
			Vertex[] noder = new Vertex[input.size()];
			for(int i=0;i<input.size();i++){
				st = new StringTokenizer((String)input.get(i));
				Vertex vertex = new Vertex(i);
				noder[i] = vertex;

				while(st.hasMoreTokens()){
					st2 = new StringTokenizer(st.nextToken(), ":");
					int toVertex = Integer.parseInt(st2.nextToken());
					int weight = Integer.parseInt(st2.nextToken());
					vertex.addNeighbour(toVertex, weight);
				}
			}
			System.out.println(mst(noder));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

class Vertex implements Comparable<Vertex> {

	private int node;
	private int key;
	private Vertex parent;
	ArrayList<int[]> neighbours;

	public Vertex(int node, int key) {
		this.node = node;
		this.key = key;
		neighbours = new ArrayList<int[]>();
	}
	public Vertex(int node) {
		this(node,Integer.MAX_VALUE);
	}


	public int getNode() {
		return node;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public Vertex getParent() {
		return parent;
	}
	public void setParent(Vertex parent) {
		this.parent = parent;
	}
	public void addNeighbour(int neighbour, int weight) {
		neighbours.add(new int[] {neighbour,weight});
	}
	public ArrayList<int[]> getNeighbours() {
		return neighbours;
	}
	@Override
	public int compareTo(Vertex arg0) {
		return getKey()-arg0.getKey();
	}


}