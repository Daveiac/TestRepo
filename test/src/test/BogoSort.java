package test;

import java.util.ArrayList;
import java.util.Collections;

public class BogoSort {

	ArrayList<Integer> list;
	public static void main(String[] args) {
		new BogoSort().run();
	}
	
	private void run() {
		list = new ArrayList<Integer>();
		for (int i = 0; i < 12; i++) {
			list.add(i);
		}
		bogosort(list);
		int i = 0;
		while(!isSorted(list)) {
			bogosort(list);
			i++;
		}
		System.out.println(i);
	}

	@SuppressWarnings("unchecked")
	private boolean isSorted(ArrayList<Integer> list) {
		ArrayList<Integer> sorted = (ArrayList<Integer>) list.clone();
		Collections.sort(sorted);
		for (int i = 0; i < sorted.size(); i++) {
			if(sorted.get(i) != list.get(i)){
				return false;
			}
		}
		return true;
	}

	public void bogosort(ArrayList<Integer> list) {
		Collections.shuffle(list);
	}

}
