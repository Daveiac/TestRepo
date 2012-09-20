package kjellOving5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Ogliogogo2 {

	public static int INF = Integer.MAX_VALUE;

	public static int mst(int[][] nabomatrise){
		int currentNode = 0;
		int counter = 0;
		int len = nabomatrise.length;
		int max = Integer.MIN_VALUE;

		int[] noderSett = new int[len];
		noderSett[counter] = currentNode;

		while (counter < len - 1) {
			int nextNode = -1;
			int nextWeight = Integer.MAX_VALUE;

			for (int i = 0; i < counter + 1; i++) {
				for (int j = 0; j < len; j++) {
					if (nabomatrise[noderSett[i]][j] < nextWeight && ! settNode(j, noderSett)) {
						nextNode = j;
						nextWeight = nabomatrise[noderSett[i]][j];
					}
				}
			}
			noderSett[counter] = nextNode;
			max = Math.max(max, nextWeight);
			counter++;
		}
		return max;
	}

	private static boolean settNode(int j, int[] noderSett) {
		for (int i : noderSett) {
			if (i == j) return true;
		}
		return false;
	}


	//		int start = 0;
	//		ArrayList<int[]> edgeList = findEdges(nabomatrise[start], start);
	//		ArrayList<Integer> noderSett = new ArrayList<Integer>();
	//		int max = Integer.MIN_VALUE;
	//		noderSett.add(start);
	//		while (noderSett.size() < nabomatrise.length) {
	//			int[] currentEdge = new int[] {-1, INF};
	//			for (int[] edge : edgeList) {
	//				if (edge[1] < currentEdge[1] && !noderSett.contains(edge[0])) {
	//					currentEdge = edge;
	//				}
	//			}
	//			max = Math.max(max, currentEdge[1]);
	//			int node = currentEdge[0];
	//			noderSett.add(node);
	//			edgeList.addAll(findEdges(nabomatrise[node], node));
	//		}

	public static void main(String[]  args){
		try{
			BufferedReader in=null;
			if(args.length>0){
				in = new BufferedReader(new FileReader(args[0]));
			}
			else{
				in = new BufferedReader(new InputStreamReader(System.in));
			}
			ArrayList<String> input = new ArrayList<String>();
			String inp = in.readLine();
			while(inp!=null){
				input.add(inp);
				inp=in.readLine();
			}
			int[][] nabomatrise = new int[input.size()][input.size()];
			for(int i=0; i<nabomatrise.length;i++){ 
				Arrays.fill(nabomatrise[i], INF);
			}
			StringTokenizer st;
			for(int i=0;i<input.size();i++){
				st = new StringTokenizer((String)input.get(i));
				while(st.hasMoreTokens()){
					String[] oneEdge = st.nextToken().split(":");
					nabomatrise[i][Integer.parseInt(oneEdge[0])]=Integer.parseInt(oneEdge[1]);
				}
			}
			System.out.println(mst(nabomatrise));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}