package davidOving4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Prinsessejakt{
	static int antallNoderMed;
	

	public static BigDecimal subgraftetthet(boolean[][] nabomatrise, int startnode){
		boolean[] visited = new boolean[nabomatrise.length];
		int antallKanter = 0;
		antallNoderMed = 0;
		dfs(nabomatrise, visited, startnode);
		antallKanter = connectedEdges(nabomatrise, visited);
		if(antallNoderMed == 0) {
			antallNoderMed = 1;
			antallKanter = 0;
		}
		return new BigDecimal(antallKanter).divide(new BigDecimal(antallNoderMed*antallNoderMed), 3, BigDecimal.ROUND_HALF_UP);
	}
	
	private static int connectedEdges(boolean[][] nabomatrise, boolean[] visited) {
		int antallKanter = 0;
		for (int i = 0; i < visited.length; i++) {
			if(!visited[i]){
				antallNoderMed++;
				for (int j = 0; j < nabomatrise.length; j++) {
					if(nabomatrise[i][j] && !visited[j]) {
						antallKanter++;
					}
				}
			}
		}
		return antallKanter;
	}

	public static void dfs(boolean[][] nabomatrise, boolean[] visited, int node) {
		visited[node] = true;
		for (int i = 0; i < nabomatrise[node].length; i++) {
			if(nabomatrise[node][i] == true && !visited[i]) {
				dfs(nabomatrise, visited, i);
			}
		}
	}
	

	public static void main(String[]  args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(in.readLine());    
			boolean[][] nabomatrise = new boolean[n][n];
			String naboRad;
			for(int i=0;i<n;i++){
				naboRad=in.readLine();
				for(int j=0;j<n;j++) 
					if(naboRad.charAt(j)=='1')nabomatrise[i][j]=true;
			}
			String linje = in.readLine();
			StringBuilder sb = new StringBuilder();
			int startnode= Integer.parseInt(linje);
			sb.append((subgraftetthet(nabomatrise, startnode)));
			linje=in.readLine();
			while(linje!=null && linje.length()>0){
				sb.append("\n");
				startnode= Integer.parseInt(linje);
				sb.append((subgraftetthet(nabomatrise, startnode)));
				linje=in.readLine();
			}
			System.out.println(sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
