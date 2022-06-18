package ss;
//피보나치 수3
import java.io.*;
import java.util.*;

public class BOJ2749 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long n = Long.parseLong(br.readLine());
		int pisano = 1500000;//k : 10^6,  pisano : 15*10^(k-1)
		long[] dp = new long[pisano];//pisano만큼 반복됨.
		
		dp[0] = 0; dp[1] = 1;
		
		for(int i=2;i<pisano && i<=n;i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 1000000;
		}
		if(n>=pisano) {
			n %= pisano;
		}
		System.out.println(dp[(int)n]);
	}

}
