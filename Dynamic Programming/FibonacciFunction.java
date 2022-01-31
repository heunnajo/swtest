import java.io.*;
import java.util.*;
public class FibonacciFunction {
	static int dp[],cntZero[],cntOne[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		dp = new int[41];
		Arrays.fill(dp, -1);
		dp[0] = 0;dp[1] = 1;
		fibo(40);
		while(T-->0) {
			int n = Integer.parseInt(br.readLine());
			//n-1에 따라 분기처리
			if(n==0) sb.append(1+" "+0+"\n");
			else if(n==1) sb.append(0+" "+1+"\n");
			else sb.append(dp[n-1]+" "+dp[n]+"\n");
		}
		System.out.println(sb);
				
	}
	static int fibo(int n) {
		//이미 구한 것은 다시 구하지 않는다!
		if(dp[n] != -1) return dp[n];
		if(n == 0) {
			//cntZero[n]++;
			return 0;
		}
		if(n == 1) {
			//cntOne[n]++;
			return 1;
		}
		return dp[n] = fibo(n-1)+fibo(n-2);
	}

}