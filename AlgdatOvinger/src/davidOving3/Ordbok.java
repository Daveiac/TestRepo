package davidOving3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class Ordbok{
	

    private static final ArrayList<Integer> empty = new ArrayList<Integer>();
    private static int pos = 0;

  
	public static Node bygg(String[] ordliste){
        Node rot = new Node();
        for (String s:ordliste) {
        	putBarn(rot, s, 0);
        	pos+= s.length()+1;
        }
    	return rot;
    }
    
	private static void putBarn(Node node, String s, int index) {
		char c = s.charAt(index);
		Node nextNode;
		if(!node.barn.containsKey(c)){
			nextNode = new Node();
			node.barn.put(c, nextNode);
		} else {
			nextNode = node.barn.get(c);
		}
		if (s.length() == index+1){
			nextNode.posisjoner.add(pos);
			return;
		}
		putBarn(nextNode,s, index+1);
	}

	public static ArrayList<Integer> posisjoner(String ord, int index, Node currentNode){
        for(int i = index; i < ord.length();i++) {
        	char c = ord.charAt(i);
        	if(c == '?') {
        		ArrayList<Integer> posisjoner = new ArrayList<Integer>();
        		for (Node node : currentNode.barn.values()) {
					posisjoner.addAll(posisjoner(ord, i+1, node));
				}
        		return posisjoner;
        	}
        	currentNode = currentNode.barn.get(c);
        	if(currentNode == null){
        		return empty;
        	}
        }
        return currentNode.posisjoner;
    }


    public static void main(String[]  args){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(in.readLine());
            String[] ord = new String[st.countTokens()];
            int i=0;
            while(st.hasMoreTokens()) ord[i++]=st.nextToken();
            Node rotNode = bygg(ord);
            String sokeord= in.readLine();
            while(sokeord!=null){
            	StringBuilder sb = new StringBuilder();
				sb.append(sokeord).append(":");
                ArrayList<Integer> pos = posisjoner(sokeord, 0, rotNode);
                int[] posi = new int[pos.size()];
                for(i=0;i<posi.length;i++)posi[i] = pos.get(i);
                Arrays.sort(posi);
                for(i=0;i<posi.length;i++) sb.append(" ").append(posi[i]);
                System.out.println(sb.toString());
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