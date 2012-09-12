//package kjellOving3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class OrdbokKjell{
	static int pos = 0;
	static ArrayList<Integer> returnList = new ArrayList<Integer>();
	static HashMap<Character, Node> tempMap = new HashMap<Character, Node>();

	public static Node bygg(String[] ordliste){
		Node rot = new Node();
		for (String ord : ordliste) {
			createNode(rot, ord);
		}
		return rot;
	}

	private static void createNode(Node node, String ord) {
		if (! ord.isEmpty()) {
			char c = ord.charAt(0);
			tempMap = node.barn;
			if (! tempMap.containsKey(c))
				tempMap.put(c, new Node());
			if (ord.length() == 1)
				tempMap.get(c).posisjoner.add(pos);
			createNode(tempMap.get(c), ord.substring(1));
		}
		pos++;
	}
	
	public static void posisjoner(String ord, Node currentNode){
		if (currentNode == null) {
			return;
		} else if (ord.length() == 0) {
			returnList.addAll(currentNode.posisjoner);
		} else if (ord.charAt(0) == '?') {
			for (Node node : currentNode.barn.values()) {
				posisjoner(ord.substring(1), node);
			}
		} else if (! currentNode.barn.containsKey(ord.charAt(0))) {
			return;
		} else {
			posisjoner(ord.substring(1), currentNode.barn.get(ord.charAt(0)));
		}
	}

	public static void main(String[]  args){
		try{
			BufferedReader in;
//            if (args.length > 0) {
//                try {
//                    in = new BufferedReader(new FileReader(args[0]));
//                }
//                catch (FileNotFoundException e) {
//                    System.out.println("Kunne ikke åpne filen " + args[0]);
//                    return;
//                }
//            }
//            else {
                in = new BufferedReader(new InputStreamReader(System.in));
//            }
			StringTokenizer st = new StringTokenizer(in.readLine());
			String[] ord = new String[st.countTokens()];
			int i=0;
			while(st.hasMoreTokens()) ord[i++]=st.nextToken();
			Node rotNode = bygg(ord);
			String sokeord= in.readLine();
			while(sokeord!=null){
				returnList.clear();
				sokeord=sokeord.trim();
				System.out.print(sokeord+":");
				posisjoner(sokeord, rotNode);
				ArrayList<Integer> pos = returnList;
				int[] posi = new int[pos.size()];
				for(i=0;i<posi.length;i++)posi[i]=((Integer)pos.get(i)).intValue();
				Arrays.sort(posi);
				for(i=0;i<posi.length;i++) System.out.print(" "+posi[i]);
				System.out.println();
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

    public Node(){
        posisjoner=new ArrayList<Integer>();
        barn=new HashMap<Character, Node>();
    }
}