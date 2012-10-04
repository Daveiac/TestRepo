package oving6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PipeSortering {

	public static void sorter(int[] liste){
		quicksort(liste, 0, liste.length-1);
	}

	private static void quicksort(int[] liste, int start, int end) {
		if(end-start <= 0) return;
		Random rnd = new Random();
		int r = rnd.nextInt(end-start)+start;
		swap(liste, r, end);
		int i = start, j = start;
		for (int k = start; k < end+1; k++) {
			if (liste[j] <= liste[end]) {
				swap(liste, i, j);
				i++;
			}
			j++;
		}
		quicksort(liste, 0, i-2);
		quicksort(liste, i, end);
	}
	
	private static void swap(int[] liste, int i, int j) {
		int temp = liste[i];
		liste[i] = liste[j];
		liste[j] = temp;
	}

	public static int[] finnMinMax(int[] sortert, int min, int max){
		int[] minMax = new int[2];
		minMax[0] = minSearch(sortert, min, 0,sortert.length-1);
		minMax[1] = maxSearch(sortert, max, 0,sortert.length-1);
		
		return minMax;
	}

	private static int minSearch(int[] sortert, int min, int start, int end) {
		if(end-start <= 1) return sortert[start];
		int half = (end+start)/2;
		if(sortert[half] <= min){
			return minSearch(sortert, min, half, end);
		} else {
			return minSearch(sortert, min, start, half);
		}
	}
	private static int maxSearch(int[] sortert, int min, int start, int end) {
		if(end-start <= 1) return sortert[end];
		int half = (end+start)/2;
		if(sortert[half] >= min){
			return maxSearch(sortert, min, start, half);
		} else {
			return maxSearch(sortert, min, half, end);
		}
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