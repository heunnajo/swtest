//6.외벽 점검
import java.util.*;

class Solution {
    static final int INF = 987654321;
    int N,MinCnt;
    int[] Weak;
    int[] Dist;
    
    void solve(int cnt,int pos,int visited){
        if(cnt > Dist.length) return;
        if(cnt >= MinCnt) return;
            
        for(int i=0;i<Weak.length;i++){
            int nextPos = (pos + i) % Weak.length;
            int diff = Weak[nextPos] - Weak[pos];
            
            if(nextPos < pos){
                //diff = n + Weak[nextPos] - Weak[pos];
                diff += N;
            }
            if(diff > Dist[Dist.length-cnt]){
                break;
            }
            visited |= 1 << nextPos;
        }
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