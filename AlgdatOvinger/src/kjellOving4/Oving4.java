package kjellOving4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Oving4 {
	private static boolean[] noderMed;

	public static BigDecimal subgraftetthet(boolean[][] nabomatrise, int startnode, int len){
		int antallKanter = 0;
		int antallNoderMed = 0;
		
		noderMed = new boolean[len];
		
		dfs(nabomatrise, startnode, len);
				
		for (int i = 0; i < len; i++) {
			if (noderMed[i]) continue;
			antallNoderMed++;
			boolean[] row = nabomatrise[i];
			for (int j = 0; j < len; j++) {
				if (noderMed[j]) continue;
				if (row[j]) antallKanter++;
			}
		}
		
		if (antallNoderMed == 0) {
			antallNoderMed = 1;
			antallKanter = 0;
		}
		return new BigDecimal(antallKanter).divide(new BigDecimal(antallNoderMed*antallNoderMed), 3, BigDecimal.ROUND_HALF_UP);
	}
	
	private static void dfs(boolean[][] nabomatrise, int start, int len) {
		noderMed[start] = true;
		for (int i = 0; i < len; i++) {
			if (nabomatrise[start][i] && !noderMed[i]) {
				dfs(nabomatrise, i, len);
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
					if(naboRad.charAt(j)=='1') nabomatrise[i][j]=true;
			}
			String linje = in.readLine();
			StringBuilder sb = new StringBuilder();
			int startnode= Integer.parseInt(linje);
			sb.append(subgraftetthet(nabomatrise, startnode, n));
			linje = in.readLine();
			while(linje!=null && linje.length()>0){
				startnode = Integer.parseInt(linje);
				sb.append('\n').append(subgraftetthet(nabomatrise, startnode, n));
				linje=in.readLine();
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}