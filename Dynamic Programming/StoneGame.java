//BOJ #9655 돌게임
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		//dp[1] = 1;//dp[1] = 1-dp[0] = 1-0 = 1
		for(int i=1;i<=n;i++) {
			dp[i] = 1-dp[i-1];
		}
		if(dp[n] == 1) System.out.println("SK");
		else System.out.println("CY");
	}

}