package ss;
import java.util.Scanner;
public class Tiling_2N {
	static int dp[];
	static int go(int N) {
		if(N==0) dp[N]=1;
		else if(N==1) dp[N]=1;
		if(dp[N]>0) return dp[N];//이미 채워진 경우
		
		dp[N] = go(N-1)%10007 + go(N-2)%10007;
		return dp[N];
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new int[1001];
		
		System.out.println(go(N)%10007);
	}

}
