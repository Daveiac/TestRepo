package kjellOving5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Prim {

	public static int mst(ArrayList<Node> nodeList, int nodeCount) {
		int max = Integer.MIN_VALUE;
		
		
		
		return max;
	}

	public static void main(String[] args){
		try{
			BufferedReader in=null;
			if(args.length>0){
				in = new BufferedReader(new FileReader(args[0]));
			}
			else{
				in = new BufferedReader(new InputStreamReader(System.in));
			}			
			ArrayList<String> input = new ArrayList<String>(500);
			String inp = in.readLine();
			while(inp!=null){
				input.add(inp);
				inp=in.readLine();
			}
			int nodeCount = input.size();
			ArrayList<Node> nodeList = new ArrayList<Node>(nodeCount*3);
			StringTokenizer st;
			StringTokenizer stSplitter;
			for(int i=0;i<nodeCount;i++){
				nodeList.add(new Node(i));
				st = new StringTokenizer((String)input.get(i));
				while(st.hasMoreTokens()){
					stSplitter = new StringTokenizer(st.nextToken(), ":");
					nodeList.get(i).adjacentNodes.add(new int[] {Integer.parseInt(stSplitter.nextToken()), Integer.parseInt(stSplitter.nextToken())});
				}
			}
			System.out.println(new StringBuilder().append(mst(nodeList, nodeCount)).toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

class Node {
	final int num;
	int weight = Integer.MAX_VALUE;
	ArrayList<int[]> adjacentNodes = new ArrayList<int[]>();
	public Node(int num) {
		this.num = num;
	}

}