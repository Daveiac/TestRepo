package oving6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Oving6 {

	private static int[] mergeSort(int[] list) {
		if (list.length == 1) {
			return list;
		}
		int len = list.length;
		int leftLen = len / 2;
		int middle = len - leftLen;
		int[] left = new int[leftLen];
		int[] right = new int[middle];
		for (int i = 0; i < leftLen; i++) {
			left[i] = list[i];
			right[i] = list[i + leftLen];
		}
		if (len % 2 == 1) {
			right[middle - 1] = list[len - 1];
		}
		left = mergeSort(left);
		right = mergeSort(right);
		return merge(left, right);
	}

	private static int[] merge(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];
		int iLeft = 0, iRight = 0, iResult = 0;
		while (left.length > iLeft || right.length > iRight) {
			if (left.length > iLeft && right.length > iRight) {
				if (left[iLeft] <= right[iRight]) {
					result[iResult++] = left[iLeft++];
				} else {
					result[iResult++] = right[iRight++];
				}
			} else if (left.length > iLeft) {
				result[iResult++] = left[iLeft++];
			} else if (right.length > iRight) {
				result[iResult++] = right[iRight++];
			}
		}
		return result;
	}

	public static int[] findMinMax(int[] sorted, int min, int max) {
		int[] minMax = new int[2];
		if (min < sorted[0]) {
			minMax[0] = sorted[0];
		} else {
			minMax[0] = binarySearch(sorted, min, 0, sorted.length, 0);
		}
		if (max > sorted[sorted.length - 1]) {
			minMax[1] = sorted[sorted.length - 1];
		} else {
			minMax[1] = binarySearch(sorted, max, 0, sorted.length, 1);
		}
		return minMax;
	}

	private static int binarySearch(int[] sorted, int key, int imin, int imax, int buffer) {
		int imid = 0;
		while (imax >= imin) {
			imid = (imin + imax) / 2;
			if (sorted[imid] < key) {
				imin = imid + 1;
			} else if (sorted[imid] > key) {
				imax = imid - 1;
			} else {
				return sorted[imid];
			}
		}
		return sorted[imax + buffer];
	}

	public static void main(String args[]) {
		try {
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader in;
			if (args.length > 0) {
				in = new BufferedReader(new FileReader(args[0]));
			} else {
				in = new BufferedReader(new InputStreamReader(System.in));
			}
			StringTokenizer st = new StringTokenizer(in.readLine());
			int numTokens = st.countTokens();
			int liste[] = new int[numTokens];
			for (int i = 0; i < numTokens; i++) {
				liste[i] = Integer.parseInt(st.nextToken());
			}
			liste = mergeSort(liste);
			String linje = in.readLine();
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(linje);
			int[] ret = findMinMax(liste, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			linje = in.readLine();
			sb.append(ret[0]).append(" ").append(ret[1]);
			while (linje != null) {
				st = new StringTokenizer(linje);
				ret = findMinMax(liste, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				sb.append('\n').append(ret[0]).append(" ").append(ret[1]);
				linje = in.readLine();
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}