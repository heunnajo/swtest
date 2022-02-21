//6.외벽 점검
import java.util.*;

class Solution {
    //재귀함수로 완전 탐색을 구현할 것. 가급적 로컬 변수를 줄이는 것이 좋기 때문에 전역변수로 선언
    static final int INF = 987654321;
    int N,MinCnt;
    int[] Weak;
    int[] Dist;
    
    //cnt :사용할 친구 명수 pos : weak배열의 인덱스(탐색 시작 위치) visited : 2^15이므로 32비트 정수로 표현 가능!
    void solve(int cnt,int pos,int visited){
        //cnt는 우리가 이미 알고 있는 최솟보다 갖거나 고치면 더 이상 탐색 진행X!가지치기!
        if(cnt > Dist.length) return;
        if(cnt >= MinCnt) return;//작은 값을 찾는 것이 의미있기 때문!모ㅗ든 취약점 방문 방법x필요한 최소 친구수만 찾기 때문에!
            
        for(int i=0;i<Weak.length;i++){
            int nextPos = (pos + i) % Weak.length;//최초 nextPos = pos + 0 으로서, 시작위치부터 방문할 수 있다!
            int diff = Weak[nextPos] - Weak[pos];//이동하고자 하는 취약점 위치값 - 시작 위치값.최초에는 동일하기 때문에 diff = 0이 됨.
            
            if(nextPos < pos){
                //diff = n + Weak[nextPos] - Weak[pos];
                diff += N;
            }
            if(diff > Dist[Dist.length-cnt]){//이동할 수 있는 거리보다 크면 break;거리는 가장 긴 것부터 쓴다!
                break;
            }
            visited |= 1 << nextPos;
        }
        //다 마킹한 다음! 모든 취약점 다 방문했는지 확인!:취약점 갯수만큼의 비트가 다 1로 켜진다!
        if(visited == (1<<Weak.length)-1){
            MinCnt = Math.min(MinCnt,cnt);
            return ;
        }
        for(int i=0;i<Weak.length;i++){
            if((visited & (1<<i)) != 0) continue;
            solve(cnt+1,i,visited);
        }
        
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        //0.변수 초기화, 정렬
        Arrays.sort(dist);
        N = n; Weak = weak; Dist = dist;
        MinCnt = INF;
        
        for(int i=0;i<Weak.length;i++){
            solve(1,i,0);
        }
        if(MinCnt == INF) return -1;
        return MinCnt;
    }
}