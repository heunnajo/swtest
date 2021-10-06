package ss;
import java.util.Scanner;
public class Tiling_2N {
	static int dp[];
	static int go(int N) {
		if(N<=0) return 0;
		if(dp[N]>0) return 0;//이미 채워진 경우
		
		//dp[N] = dp[N-1] + dp[N-2];
		dp[N] = go(N-1) + go(N-2);
		return dp[N];
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new int[1001];
		
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		go(N);
		System.out.println(dp[N]);
	}

}
