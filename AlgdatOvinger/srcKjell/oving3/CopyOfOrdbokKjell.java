package oving3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class CopyOfOrdbokKjell{
	static int pos = 0;
	static ArrayList<Integer> returnList = new ArrayList<Integer>();

	private static Node bygg(String[] ordliste, Node rot){
		for (String ord : ordliste) {
			lagNode(rot, ord, ord.length(), 0);
		}
		return rot;
	}
	
	private static void build(String ord, Node node, int len, int index) {
		if (len > index) {
			char c = ord.charAt(index);
			HashMap<Character, Node> tempMap = node.barn;
			if (! tempMap.containsKey(c))
				tempMap.put(c, new Node());
			if (len > index + 1)
				build(ord, tempMap.get(c), len, index + 1);
		}
	}
	
	private static void lagNode(Node node, String ord, int len, int index) {
		while (len > index) {
			char c = ord.charAt(index);
			if (node != null){
				HashMap<Character, Node> tempMap = node.barn;
				if (!tempMap.containsKey(c) && tempMap.containsKey('?'))
					tempMap.put(c, new Node());
				if (len == index + 1) {
					if (tempMap != null && tempMap.get(c) != null)
						tempMap.get(c).posisjoner.add(pos);
					pos += len + 1;
				}
				if (len > index + 1)
					node = tempMap.get(c);
			}
			index++;
		}
		
//		if (len > index && node != null) {
//			char c = ord.charAt(index);
//			HashMap<Character, Node> tempMap = node.barn;
//			if (!tempMap.isEmpty()) {
//				if (!tempMap.containsKey(c) && tempMap.containsKey('?'))
//					tempMap.put(c, new Node());
//				if (len == index + 1)
//					tempMap.get(c).posisjoner.add(pos);
//				if (len > index + 1)
//					lagNode(tempMap.get(c), ord, len, index + 1);
//			}
//		}
//		pos++;
	}
	
	private static void posisjoner(String ord, Node currentNode, int len, int index) {
		if (currentNode == null) {
			return;
		} else if (len == index) {
			returnList.addAll(currentNode.posisjoner);
			return;
		} 
		char c = ord.charAt(index);
		if (c == '?') {
			for (Node node : currentNode.barn.values()) {
				posisjoner(ord, node, len, index + 1);
			}
			return;
		} else if (! currentNode.barn.containsKey(c)) {
			return;
		} else {
			posisjoner(ord, currentNode.barn.get(c), len, index + 1);
		}
	}

	public static void main(String[]  args){
		try{
			BufferedReader in;
            if (args.length > 0) {
                try {
                    in = new BufferedReader(new FileReader(args[0]));
                }
                catch (FileNotFoundException e) {
                    System.out.println("Kunne ikke �pne filen " + args[0]);
                    return;
                }
            }
            else {
                in = new BufferedReader(new InputStreamReader(System.in));
            }
			StringTokenizer st = new StringTokenizer(in.readLine());
			String[] ord = new String[st.countTokens()];
			int i=0, stop = 0;
			while(st.hasMoreTokens()) ord[i++]=st.nextToken();
			ArrayList<String> sord = new ArrayList<String>();
			String sokeord= in.readLine();
			Node rot = new Node();
			while(sokeord!=null){
				sokeord=sokeord.trim();
				sord.add(sokeord);
				build(sokeord, rot, sokeord.length(), 0);
				sokeord=in.readLine();
			}
			Node rotNode = bygg(ord, rot);
			for (String sok : sord) {
				returnList.clear();
				StringBuilder sbuf = new StringBuilder();
				sbuf.append(sok).append(":");
				posisjoner(sok, rotNode, sok.length(), 0);
				ArrayList<Integer> pos = returnList;
				stop = pos.size();
				int[] posi = new int[stop];
				for(i=0;i<stop;i++)posi[i]=pos.get(i).intValue();
				Arrays.sort(posi);
				for (i=0; i<stop; i++) {
					sbuf.append(" ").append(posi[i]);
				}
				System.out.println(sbuf.toString());
			}
		} catch(Exception e){
			System.out.println();
			e.printStackTrace();
		}
	}
}

//class Node{
//    public ArrayList<Integer> posisjoner;
//    public HashMap<Character, Node> barn;
//
//    public Node(){
//        posisjoner=new ArrayList<Integer>();
//        barn=new HashMap<Character, Node>();
//    }
//}