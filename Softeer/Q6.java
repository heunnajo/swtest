//지도 자동 구축
import java.util.*;
import java.io.*;

public class Q6 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		long[] dp = new long[n+1];
		dp[0] = 4; dp[1] = 9;
		
		for(int i=2;i<=n;i++) {
			int x = (int)Math.sqrt(dp[i-1]);
			int k = (int)Math.pow(2, i-1);
			dp[i] = (long)Math.pow(x+k,2);
		}
		System.out.println(dp[n]);
	}

}
