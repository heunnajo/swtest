//6.파괴되지 않은  건물
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int row = board.length;
        int col = board[0].length;
        int[][] dp = new int[1003][1003];
        
        //1.dp완성
        int len = skill.length;
        for(int i=0;i<len;i++){
            int type = skill[i][0], x1 = skill[i][1], y1 = skill[i][2], x2 = skill[i][3], y2 = skill[i][4], degree = skill[i][5];
            if(type == 1) degree = -degree;
            //백준 문제와 다른 점 : degree 만큼 변화하는 것이기 때문에,"변화량" 테이블에 해당 구간에 대해 degree만큼 +-해주면 됨!
            dp[x1][y1] += degree;
            dp[x1][y2+1] -= degree;//오른쪽
            dp[x2+1][y1] -= degree;//아래쪽
            dp[x2+1][y2+1] += degree;//중복해서 빼준 대각선 영역
        }
        //2.오른쪽, 아래 방향 누적합 계산
        //2-1.오른쪽
        for(int i=0;i<row;i++)
            for(int j=1;j<col;j++) dp[i][j] += dp[i][j-1];
        //2-2.아래
        for(int j=0;j<col;j++)
            for(int i=1;i<row;i++) dp[i][j] += dp[i-1][j];
        
        //3.정답 도출
        int answer = 0;
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++) if(dp[i][j]+board[i][j]>0) answer++;
        
        return answer;
    }
}