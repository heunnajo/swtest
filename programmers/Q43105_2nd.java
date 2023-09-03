import java.util.Arrays;

//정수 삼각형 : debugging
public class Q43105_2nd {

	public static void main(String[] args) {
		int[][] triangle = {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
		
		int n = triangle.length;
		int[][] dp = new int[n][n];
		dp[0][0] = triangle[0][0];
		
		for(int i=1;i<n;i++) {
			for(int j=0;j<=i;j++) {
				if(j==0) {dp[i][j] = dp[i-1][j];}
				else if(j == i) {dp[i][j] = dp[i-1][j-1];}
				else {dp[i][j] = Math.max(triangle[i][j] + dp[i-1][j-1], triangle[i][j]+dp[i-1][j]);}
			}
		}
		
		Arrays.sort(dp[n-1]);
		int answer = dp[n-1][0];
		System.out.println(answer);
	}
}
