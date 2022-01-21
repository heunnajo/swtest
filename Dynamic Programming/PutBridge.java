package ss;
import java.io.*;
import java.util.*;
public class PutBridge {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] dp = new int [30][30];
		for(int i=0;i<30;i++) {
			dp[i][1] = 1;
			dp[i][0] = 1;
		}
		
		for(int i=2;i<30;i++) {
			for(int j=1;j<30;j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			System.out.println(dp[m][n]);
		}

	}

}
