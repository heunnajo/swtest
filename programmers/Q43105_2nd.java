//정수 삼각형
import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
		int[][] dp = new int[n][n];
		dp[0][0] = triangle[0][0];
		
		for(int i=1;i<n;i++) {
			for(int j=0;j<=i;j++) {
				if(j==0) {dp[i][j] = triangle[i][j] + dp[i-1][j];}
				else if(j == i) {dp[i][j] = triangle[i][j] + dp[i-1][j-1];}
				else {dp[i][j] = Math.max(triangle[i][j] + dp[i-1][j-1], triangle[i][j]+dp[i-1][j]);}
			}
		}
		
		Arrays.sort(dp[n-1]);
		answer = dp[n-1][n-1];
        return answer;
    }
}