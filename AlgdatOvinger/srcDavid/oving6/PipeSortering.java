package oving6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PipeSortering {

	public static void sorter(int[] liste){
		// SKRIV DIN KODE HER
	}

	public static int[] finnMinMax(int[] sortert, int min, int max){
		int[] minMax = new int[2];
		// SKRIV DIN KODE HER
		return minMax;
	}

	public static void main(String args[]){
		try{
			BufferedReader in;
			if (args.length > 0) {
				try {
					in = new BufferedReader(new FileReader(args[0]));
				}
				catch (FileNotFoundException e) {
					System.out.println("Kunne ikke åpne filen " + args[0]);
					return;
				}
			}
			else {
				in = new BufferedReader(new InputStreamReader(System.in));
			}
			StringTokenizer st = new StringTokenizer(in.readLine());
			int numTokens=st.countTokens();
			int liste[] = new int[numTokens];
			for(int i=0;i<numTokens;i++){
				liste[i]=Integer.parseInt(st.nextToken());
			}
			sorter(liste);
			String linje = in.readLine();
			while(linje!=null){
				st = new StringTokenizer(linje);
				int[] ret = finnMinMax(liste, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				linje = in.readLine();
				System.out.println(ret[0]+" "+ret[1]);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}