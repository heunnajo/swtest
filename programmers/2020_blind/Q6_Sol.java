//6.외벽 점검
import java.util.*;

class Solution {
    //재귀함수로 완전 탐색을 구현할 것. 가급적 로컬 변수를 줄이는 것이 좋기 때문에 전역변수로 선언합니다~
    static final int INF = 987654321;//코드리뷰Q. 왜 final로 선언했나요?
    int N,MinCnt;
    int[] Weak;
    int[] Dist;
    
    //cnt :사용할 친구 명수 pos : weak배열의 인덱스(탐색 시작 위치) visited : 2^15이므로 32비트 정수로 표현 가능!
    void solve(int cnt,int pos,int visited){
        //cnt는 우리가 이미 알고 있는 최솟보다 갖거나 고치면 더 이상 탐색 진행X!가지치기!
        if(cnt > Dist.length) return;
        if(cnt >= MinCnt) return;//작은 값을 찾는 것이 의미있기 때문!모ㅗ든 취약점 방문 방법x필요한 최소 친구수만 찾기 때문에!
            
            
        for(int i=0;i<Weak.length;i++){
            int nextPos = (pos + i) % Weak.length;//최초 nextPos = pos + 0 으로서, 시작위치부터 방문할 수 있다!!?
            //인덱스 초과 어떻게 핸들링할 것인가? 취약지점 갯수가 4라면 이 갯수를 넘어간다면 넘어가는 만큼 뒤로 rewind해줘야한다!=> 모듈러 연산. 내가 생각했던 부분임.
            //시작점부터의 거리 계산???왜?
            int diff = Weak[nextPos] - Weak[pos];//이동하고자 하는 취약점 위치값 - 시작 위치값.최초에는 동일하기 때문에 diff = 0이 됨.
            
            if(nextPos < pos){
                //diff = n + Weak[nextPos] - Weak[pos];
                diff += N;
            }
            if(diff > Dist[Dist.length-cnt]){//이동할 수 있는 거리보다 크면 break;거리는 가장 긴 것부터 쓴다!
                break;
            }
            visited |= 1 << nextPos;//해당하는 비트만 1로 켜지게 된다.
        }
        //다 마킹한 다음! 모든 취약점 다 방문했는지 확인!:취약점 갯수만큼의 비트가 다 1로 켜진다!
        //여기까지 왓따는 건 다 자기짝을 찾았다는 뜻
        if(visited == (1<<Weak.length)-1){
            MinCnt = Math.min(MinCnt,cnt);
            return ;
        }
        for(int i=0;i<Weak.length;i++){
            if((visited & (1<<i)) != 0) continue;//이미 방문. 시작 위치로 사용
            
            solve(cnt+1,i,visited);// 
        }
        
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        //0.변수 초기화, 정렬
        Arrays.sort(dist);
        N = n; Weak = weak; Dist = dist;
        MinCnt = INF;
        
        for(int i=0;i<Weak.length;i++){
            solve(1,i,0);//친구 마다 시작점을 다르게 해서 완전 탐색하는 건데! 2중 for문으로 친구 반복문, 시작점 반복문 구현해야되는 것 아닌가..??!  
        }
        if(MinCnt == INF) return -1;
        return MinCnt;
    }
}