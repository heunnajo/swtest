//이항 계수1
package ss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class BOJ11050_DP_BottopUp {
 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
 
		long[][] dp = new long[N + 1][K + 1];
 
		// 2번 성질 (n == k)
		for (int i = 0; i <= K; i++) {
			dp[i][i] = 1;
		}
		
		// 2번 성질 (k == 0)
		for(int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}
		
 
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				// 1번 성질 
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]);
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
