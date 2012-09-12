package davidOving4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.math.BigDecimal;

public class Prinsessejakt{

    public static BigDecimal subgraftetthet(boolean[][] nabomatrise, 
                                                int startnode){
        //SKRIV DIN KODE HER
        return new BigDecimal(antallKanter).divide(
                        new BigDecimal(antallNoderMed*antallNoderMed),
                        3, BigDecimal.ROUND_HALF_UP);
    }


    public static void main(String[]  args) {
        try {
            BufferedReader in;
            if(args.length>0){
                in = new BufferedReader(new FileReader(args[0]));
            }
            else{
                in= new BufferedReader(new InputStreamReader(System.in));
            }
            int n = Integer.parseInt(in.readLine());    
            boolean[][] nabomatrise = new boolean[n][n];
            String naboRad;
            for(int i=0;i<n;i++){
                naboRad=in.readLine();
                for(int j=0;j<n;j++) 
                    if(naboRad.charAt(j)=='1')nabomatrise[i][j]=true;
            }
            String linje = in.readLine();
            while(linje!=null && linje.length()>0){
                int startnode= Integer.parseInt(linje);
                System.out.println(subgraftetthet(nabomatrise, startnode));                                                        
                linje=in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
