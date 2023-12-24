//거스름돈
import java.util.*;
import java.io.*;
public class BOJ14916_dp {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[100001];
		
		//0.-1로 초기화
		Arrays.fill(dp, -1);
		//1.초기해 셋팅
		dp[2] = dp[5] = 1;
		//2.점화식 완성
		for(int i=3;i<=n;i++) {
			if(dp[i-2] != -1) {
				dp[i] = dp[i-2] + 1;
			}
			if(i-5 >= 0 && dp[i-5] != -1) {
				dp[i] = Math.min(dp[i],dp[i-5] + 1);
			}
		}
//		for(int i=1;i<=n;i++) {
//			System.out.println(i+": "+dp[i]);
//		}
		System.out.println(dp[n]);
	}

}
