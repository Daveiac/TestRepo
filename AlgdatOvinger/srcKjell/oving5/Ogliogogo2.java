package oving5;

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
					if (! settNode(j, noderSett) && nabomatrise[noderSett[i]][j] < nextWeight) {
						nextNode = j;
						nextWeight = nabomatrise[noderSett[i]][j];
					}
				}
			}
			noderSett[counter++] = nextNode;
			max = Math.max(max, nextWeight);
		}
		return max;
	}

	private static boolean settNode(int j, int[] noderSett) {
		for (int i : noderSett) {
			if (i == j) return true;
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
			ArrayList<String> input = new ArrayList<String>();
			String inp = in.readLine();
			while(inp!=null){
				input.add(inp);
				inp=in.readLine();
			}
			int len = input.size();
			int[][] nabomatrise = new int[len][len];
			for(int i=0; i<len;i++){ 
				Arrays.fill(nabomatrise[i], INF);
			}
			StringTokenizer st;
			StringTokenizer stSplitter;
			for(int i=0;i<len;i++){
				st = new StringTokenizer((String)input.get(i));
				while(st.hasMoreTokens()){
					stSplitter = new StringTokenizer(st.nextToken(), ":");
					nabomatrise[i][Integer.parseInt(stSplitter.nextToken())]=Integer.parseInt(stSplitter.nextToken());
				}
			}
			System.out.println(new StringBuilder().append(mst(nabomatrise)).toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}