//4.합승 택시 요금 플로이드 와샬
import java.util.*;
class Solution {
    static final int INF = 199*100000+1;//덧셈에 있어서 overflow 방지하면서 최대한 큰값!
    static int[][] d = new int[205][205];
    public int solution(int V, int s, int a, int b, int[][] fares) {
        //1.변수 초기화
        int answer = INF;
        for(int i=0;i<205;i++) 
            for(int j=0;j<205;j++) d[i][j] = INF;
        
        for(int i=0;i<205;i++) d[i][i] = 0;//자기 자신과의 거리는 0으로 초기화!?
    
        //2.가중치 정보 저장!
        int len = fares.length;
        for(int i=0;i<len;i++){
            d[fares[i][0]][fares[i][1]] = fares[i][2];
            d[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        for(int mid = 1;mid<=V;mid++)
            for(int i=1;i<=V;i++)
                for(int j=1;j<=V;j++)
                    if(d[i][j] > d[i][mid]+d[mid][j]) d[i][j] = d[i][mid]+d[mid][j];
        
        //3.최소가 되는 i를 찾아서 정답 도출
        for(int i=1;i<=V;i++)
            if(answer> d[s][i]+d[i][a]+d[i][b]) answer = d[s][i]+d[i][a]+d[i][b];
        return answer;
    }
}
