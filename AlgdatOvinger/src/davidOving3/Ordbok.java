package davidOving3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class Ordbok{
	

    public static Node bygg(String[] ordliste){
        Node rot = new Node();
        int pos = 0;
        for (String s:ordliste) {
        	putBarn(rot, s, pos);
        	pos+= s.length()+1;
        }
    	return rot;
    }
    
    

    private static void putBarn(Node node, String s, int index) {
		if (s.isEmpty()) {
			node.posisjoner.add(index);
		} else {
			char c = s.charAt(0);
        	if(!node.barn.containsKey(c)){
        		Node nextNode = new Node();
				node.barn.put(c, nextNode);
        		putBarn(nextNode,s.substring(1), index);
        	} else {
        		putBarn(node.barn.get(c),s.substring(1), index);
        	}
		}
	}


	public static ArrayList<Integer> posisjoner(String ord, int index, Node currentNode){
        for(int i = 0; i < ord.length();i++) {
        	char c = ord.charAt(i);
        	if(c == '?') {
        		ArrayList<Integer> posisjoner = new ArrayList<Integer>();
        		for (Node node : currentNode.barn.values()) {
					posisjoner.addAll(posisjoner(ord.substring(i+1), 0, node));
				}
        		Collections.sort(posisjoner);
        		return posisjoner;
        	}
        	currentNode = currentNode.barn.get(c);
        	if(currentNode == null){
        		return new ArrayList<Integer>();
        	}
        }
        return currentNode.posisjoner;
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
            int i=0;
            while(st.hasMoreTokens()) ord[i++]=st.nextToken();
            Node rotNode = bygg(ord);
            String sokeord= in.readLine();
            while(sokeord!=null){
                sokeord=sokeord.trim();
                System.out.print(sokeord+":");
                ArrayList<Integer> pos = posisjoner(sokeord, 0, rotNode);
                int[] posi = new int[pos.size()];
                for(i=0;i<posi.length;i++)posi[i]=((Integer)pos.get(i)).intValue();
                Arrays.sort(posi);
                for(i=0;i<posi.length;i++) System.out.print(" "+posi[i]);
                System.out.println();
                sokeord=in.readLine();
            }
        }
        catch(Exception e){
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