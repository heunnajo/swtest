//Q118668 코딩 테스트 공부
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int targetA = -1; int targetC = -1;
        
        for(int i=0;i<problems.length;i++){
            if(targetA < problems[i][0]){targetA = problems[i][0];}
            if(targetC < problems[i][1]){targetC = problems[i][1];}
        }
        if(targetA <= alp && targetC <= cop){return 0;}
        
        if(targetA <= alp){alp = targetA;}
        if(targetC <= cop){cop = targetC;}
        
        int[][] dp = new int[targetA+2][targetC+2];
        
        for(int i=alp;i<=targetA;i++){
            for(int j=cop;j<=targetC;j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp;i<=targetA;i++){
            for(int j=cop;j<=targetC;j++){
                //1.시간+1로 알고력+1
                dp[i+1][j] = Math.min(dp[i+1][j],dp[i][j]+1);
                //2.시간+1로 코딩력+1
                dp[i][j+1] = Math.min(dp[i][j+1],dp[i][j]+1);
                
                //3.문제 풀이로 알고력, 코딩력 증가 : 문제 풀이 시 요구되는 알고력, 코딩력 조건 판단!
                for(int[] p:problems){
                    if(i >= p[0] && j >= p[1]){
                        if(i+p[2] <= targetA && j+p[3] <= targetC){
                            dp[i+p[2]][j+p[3]] = Math.min(dp[i+p[2]][j+p[3]],dp[i][j]+p[4]);
                        } else if(i+p[2] > targetA && j+p[3] > targetC){
                            dp[targetA][targetC] = Math.min(dp[targetA][targetC],dp[i][j]+p[4]);
                        } else if(i+p[2] > targetA){
                            dp[targetA][j+p[3]] = Math.min(dp[targetA][j+p[3]],dp[i][j]+p[4]);
                        } else if(j+p[3] > targetC){
                            dp[i+p[2]][targetC] = Math.min(dp[i+p[2]][targetC],dp[i][j]+p[4]);
                        }
                    }
                    
                }
            }
        }
        
        int answer = dp[targetA][targetC];
        return answer;
    }
}