package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//동물원
public class BOJ1309 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int [N][3];
		dp[0][0] = dp[0][1] = dp[0][2] = 1;
		
		for(int i=1;i<N;i++) {
			dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
			dp[i][1] = dp[i-1][0] + dp[i-1][2];
			dp[i][2] = dp[i-1][0] + dp[i-1][1];
			
			dp[i][0] %= 9901;
			dp[i][1] %= 9901;
			dp[i][2] %= 9901;
		}
		int ans = 0;
		for(int i=0;i<3;i++) {
			ans += dp[N-1][i];
		}
		ans %= 9901;
		System.out.println(ans);
	}

}
