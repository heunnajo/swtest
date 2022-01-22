package ss;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClimbStairs {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[10001];
		
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int[] dp = new int[10001];
		//1.초기해 셋팅
		dp[1] = arr[1];
		dp[2] = arr[1] + arr[2];
		
		//2.dp 완성
		for(int i=3;i<=N;i++) {
			dp[i] = Math.max(dp[i-3]+arr[i-1], dp[i-2])+arr[i];
		}
		System.out.println(dp[N]);
	}

}
