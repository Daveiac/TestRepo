package oving6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PipeSortering {

	static Random rnd = new Random();

	public static void sorter(int[] liste){
		quicksort(liste, 0, liste.length-1);
	}

	private static void quicksort(int[] array, int start, int end) {
		if(end-start <= 0) return;
		int r = rnd.nextInt(end-start)+start;
		int pivotIndex = partition(array, start, end, r);
		quicksort(array, start, pivotIndex-1);
		quicksort(array, pivotIndex+1, end);
	}
	
	public static int partition(int[] array, int start, int end, int pivot) {
		swap(array, pivot, end);
		int storeIndex = start;
		for (int i = start; i < end; i++) {
			if(array[i] < array[end]) {
				swap(array, i, storeIndex);
				storeIndex++;
			}
		}
		swap(array, storeIndex, end);
		return storeIndex;
	}
	
	private static void swap(int[] liste, int i, int j) {
		int temp = liste[i];
		liste[i] = liste[j];
		liste[j] = temp;
	}

	public static int[] finnMinMax(int[] sortert, int min, int max){
		int[] minMax = new int[2];

		minMax[0] = (min < sortert[0]) ? sortert[0] : binSearch(sortert, min, 0,sortert.length-1, true);
		minMax[1] = (max > sortert[sortert.length-1]) ? sortert[sortert.length-1] : binSearch(sortert, max, 0,sortert.length-1, false);
		return minMax;
	}

	private static int binSearch(int[] sortert, int min, int start, int end, boolean isMin) {
		while(end-start >= 0) {
			int half = (end+start)/2;
			if(sortert[half] < min){
				start = half+1;
			} else if (sortert[half] > min){
				end = half-1;
			} else {
				return sortert[half];
			}
		}
		return (isMin) ? sortert[end]: sortert[end+1];
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
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(linje);
			int[] ret = finnMinMax(liste, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			linje = in.readLine();
			sb.append(ret[0]);sb.append(" ");sb.append(ret[1]);
			while(linje!=null){
				sb.append("\n");
				st = new StringTokenizer(linje);
				ret = finnMinMax(liste, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				linje = in.readLine();
				sb.append(ret[0]);sb.append(" ");sb.append(ret[1]);
			}
			System.out.println(sb.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}