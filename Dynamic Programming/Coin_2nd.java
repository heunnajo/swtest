//BOJ #9084 동전
import java.util.*;
import java.io.*;
//Bottom-up
public class Coin_2nd {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TC = Integer.parseInt(br.readLine());
    	
    	StringTokenizer st = null;
    	StringBuilder sb = new StringBuilder();
    	
    	while(TC-- >0) {
    		int n = Integer.parseInt(br.readLine());
    		
    		st = new StringTokenizer(br.readLine());
    		
    		int[] coins = new int[n];
    		
    		for(int i=0;i<n;i++) {
    			coins[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		int m = Integer.parseInt(br.readLine());
    		
    		int[] d = new int[m+1];
    		d[0] = 1;
    		
    		for(int i=0;i<n;i++) {
    			for(int j=coins[i];j<=m;j++) {
    				d[j] += d[j-coins[i]];
    			}
    		}
    		sb.append(d[m]+"\n");
    	}
    	System.out.print(sb);
    }
}