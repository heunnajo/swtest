//BOJ #9461 파도반 수열
package ss;
import java.io.*;
import java.util.*;

public class PadovanSequence {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		long[] dp = new long[101];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		//dp[6] = dp[1]+dp[5]=1+2 = 3;
		//dp[n] = dp[n-5] + dp[n-1];인덱스 초과 주의!
		for(int i=5;i<=100;i++) {
			dp[i] = dp[i-5] + dp[i-1];
		}
		StringBuilder sb = new StringBuilder();
		while(tc-->0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]+"\n");
		}
//		for(int i=1;i<=100;i++) {
//			System.out.println(i+": "+dp[i]+" ");
//		}
		System.out.print(sb);
	}

}
