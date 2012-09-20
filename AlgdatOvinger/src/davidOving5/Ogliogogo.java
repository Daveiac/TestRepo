package davidOving5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Ogliogogo{

	public static int INF = Integer.MAX_VALUE;

	public static int mst(int[][] nabomatrise){
		int startNode = 0;
		ArrayList<Integer> noder = new ArrayList<Integer>();
		noder.add(startNode);
		int max = Integer.MIN_VALUE;
		while(noder.size() < nabomatrise.length) {
			int[] shortestEdge = getShortestEdge(nabomatrise, noder); 
			noder.add(shortestEdge[0]);
			max = Math.max(max, shortestEdge[1]);
		}
		return max;
}
	public static int[] getShortestEdge(int[][] nabomatrise, ArrayList<Integer> noder) {
		int[] shortest = new int[] {-1, INF};
		for (int fra = 0; fra < noder.size() ;fra++ ) {
			for (int til = 0; til < nabomatrise.length; til++) {
				int w = nabomatrise[noder.get(fra)][til];
				if(w != INF && w < shortest[1] && !noder.contains(til)) {
					shortest[0] = til;
					shortest[1] = w;
				}
			}
		}
		return shortest;
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
		int[][] nabomatrise = new int[input.size()][input.size()];
		for(int i=0; i<nabomatrise.length;i++){
			for(int j=0; j<nabomatrise.length;j++){
				nabomatrise[i][j]=INF;
			}	 
		}
		StringTokenizer st;
		StringTokenizer st2;
		for(int i=0;i<input.size();i++){
			st = new StringTokenizer((String)input.get(i));
			while(st.hasMoreTokens()){
				st2 = new StringTokenizer(st.nextToken(), ":");
				int j = 0;
				String[] oneEdge = new String[st2.countTokens()];
				while(st2.hasMoreTokens()) oneEdge[j++] = st2.nextToken();
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