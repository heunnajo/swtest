//Q43105 정수 삼각형

class Solution {
	public int solution(int[][] triangle) {
        int row = triangle.length;
        int col = triangle[triangle.length-1].length;
        
        int[][] dp = new int[row][col];
        for(int i=0;i<col;i++){
            dp[row-1][i] = triangle[row-1][i];
        }
        
        int offset = 1;
        for(int i=row-2;i>=0;i--){
            for(int j=0;j<=col-1-offset;j++){
                dp[i][j] = Math.max(dp[i+1][j],dp[i+1][j+1])+triangle[i][j];
            }
            offset++;
        }
        
        int answer = dp[0][0];
        return answer;
    }
    
}