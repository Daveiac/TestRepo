import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class OrdbokKjell{
	static int pos = 0;
	static ArrayList<Integer> returnList = new ArrayList<Integer>();

	private static void bygg(String[] ordliste, Node rot){
		for (String ord : ordliste) {
			createNode(rot, ord, ord.length(), 0);
		}
		return;
	}

	private static void createNode(Node node, String ord, int len, int index) {
		if (len > index) {
			if (node.barn == null) {
				node.barn = new HashMap<Character, Node>();
			}
			char c = ord.charAt(index);
			HashMap<Character, Node> tempMap = node.barn;
			if (! tempMap.containsKey(c))
				tempMap.put(c, new Node());
			index++;
			if (len == index) {
				if(tempMap.get(c).posisjoner == null) {
					tempMap.get(c).posisjoner = new ArrayList<Integer>();
				}
				tempMap.get(c).posisjoner.add(pos);
				pos += len + 1;
			} else {
				createNode(tempMap.get(c), ord, len, index);
			}
		}
	}

	private static void posisjoner(String ord, Node currentNode, int len, int index) {
		if (currentNode == null) {
			return;
		} else if (len == index) {
			ArrayList<Integer> ppos = currentNode.posisjoner;
			if (ppos != null) {
				returnList.addAll(ppos);
			}
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
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(in.readLine());
			String[] ord = new String[st.countTokens()];
			int i=0, stop = 0;
			while(st.hasMoreTokens()) ord[i++]=st.nextToken();
			Node rotNode = new Node();
			bygg(ord, rotNode);
			String sokeord= in.readLine();
			StringBuilder sbuf = new StringBuilder();
			ArrayList<Integer> intList = returnList;
			while(sokeord!=null){
				intList.clear();
				sbuf.delete(0, sbuf.length());
				sokeord=sokeord.trim();
				sbuf.append(sokeord).append(":");
				posisjoner(sokeord, rotNode, sokeord.length(), 0);
				ArrayList<Integer> pos = intList;
				stop = pos.size();
				int[] posi = new int[stop];
				for(i=0;i<stop;i++)posi[i]=pos.get(i).intValue();
				Arrays.sort(posi);
				for (i=0; i<stop; i++) {
					sbuf.append(" ").append(posi[i]);
				}
				System.out.println(sbuf.toString());
				sokeord=in.readLine();
			}
		} catch(Exception e){
			System.out.println();
			e.printStackTrace();
		}
	}
}

class Node{
	public ArrayList<Integer> posisjoner;
	public HashMap<Character, Node> barn;
}