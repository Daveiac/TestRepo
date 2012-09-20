package davidOving5;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Ogliogogo{

	public static int INF = Integer.MAX_VALUE;

	public static int mst(int[][] nabomatrise){
		//SKRIV DIN KODE HER
		return INF;
	}


	public static void main(String[]  args){
		try{
			BufferedReader in=null;
			if(args.length>0){
				in = new BufferedReader(new FileReader(args[0]));
			}
			else{
				in = new BufferedReader(new InputStreamReader(System.in));
			}
			ArrayList<String> input = new ArrayList<String>();
			String inp = in.readLine();
			while(inp!=null){
				input.add(inp);
				inp=in.readLine();
			}
			int[][] nabomatrise = new int[input.size()][input.size()];
			for(int i=0; i<nabomatrise.length;i++){
				for(int j=0; j<nabomatrise.length;j++){
					nabomatrise[i][j]=INF;
				}	 
			}
			StringTokenizer st;
			for(int i=0;i<input.size();i++){
				st = new StringTokenizer((String)input.get(i));
				while(st.hasMoreTokens()){
					String[] oneEdge = st.nextToken().split(":");
					nabomatrise[i][Integer.parseInt(oneEdge[0])]=Integer.parseInt(oneEdge[1]);
				}
			}
			System.out.println(""+mst(nabomatrise));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}