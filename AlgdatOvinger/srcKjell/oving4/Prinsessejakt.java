package oving4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.math.BigDecimal;

public class Prinsessejakt{

	static ArrayList<Node> noderMed = new ArrayList<Node>();
	
	public static BigDecimal subgraftetthet(boolean[][] nabomatrise, int startnode, int len){
		Node[] noder = buildNodes(nabomatrise, len);
		
		finnNoderMed(noder[startnode]);
		int antallNoderMed = len - noderMed.size();
		ArrayList<Integer> antallKanterFraNoder = new ArrayList<Integer>(len);
		for (int i = 0; i < len; i++) {
			antallKanterFraNoder.add(finnKanterIkkeMed(noder[i], i));
		}
		
		int antallKanter = Collections.max(antallKanterFraNoder);
		
		for (Node node : noder) {
			System.out.println(node);
		}
		System.out.println(antallKanter);
		System.out.println(antallNoderMed);
		
		if (antallNoderMed == 0) {
			return new BigDecimal(0).divide(new BigDecimal(1), 3, BigDecimal.ROUND_HALF_UP);
		} else {
			return new BigDecimal(antallKanter).divide(new BigDecimal(antallNoderMed*antallNoderMed), 3, BigDecimal.ROUND_HALF_UP);
		}
	}

	private static Integer finnKanterIkkeMed(Node node, int start) {
		if (noderMed.contains(node)) {
			return 0;
		}
		node.visited = start;
		int antall = 0; 
		for (Node child : node.naboer) {
			if (child.visited != start && !noderMed.contains(child)) {
				antall++;
				antall += finnKanterIkkeMed(child, start);
			} else if (!noderMed.contains(child)) {
				antall++;
			}
		}
		return antall;
	}

	private static void finnNoderMed(Node node) {
		node.visited = -1;
		noderMed.add(node);
		for (Node child : node.naboer) {
			if (child.visited != -1) {
				finnNoderMed(child);
			}
		}
	}

	private static Node[] buildNodes(boolean[][] nabomatrise, int len) {
		Node[] noder = new Node[len];
		for (int i = 0; i < len; i++) {
			noder[i] = new Node();
		}
		for (int i = 0; i < len; i++) {
			boolean[] row = nabomatrise[i];
			for (int j = 0; j < len; j++) {
				if (row[j]) {
					noder[i].naboer.add(noder[j]);
				}
			}
		}
		return noder;
	}


	public static void main(String[]  args) {
		try {
			BufferedReader in;
			if(args.length>0){
				in = new BufferedReader(new FileReader(args[0]));
			}
			else{
				in= new BufferedReader(new InputStreamReader(System.in));
			}
			int n = Integer.parseInt(in.readLine());    
			boolean[][] nabomatrise = new boolean[n][n];
			String naboRad;
			for(int i=0;i<n;i++){
				naboRad=in.readLine();
				for(int j=0;j<n;j++) 
					if(naboRad.charAt(j)=='1')nabomatrise[i][j]=true;
			}
			String linje = in.readLine();
			while(linje!=null && linje.length()>0){
				int startnode= Integer.parseInt(linje);
				System.out.println(subgraftetthet(nabomatrise, startnode, n));                                                        
				linje=in.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Node {
	int visited = -2;
	ArrayList<Node> naboer = new ArrayList<Node>();
	public String toString() {return "(" + this.hashCode() + ")";}
}
