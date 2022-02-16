//4. 미로 탈출_다익스트라(인접리스트)
import java.util.*;
class Solution {
    //인접리스트는 Point 타입, PQ는 Tuple(val,idx,STATE) 타입
    class Tuple implements Comparable<Tuple>{
        int val,idx,state;
        public Tuple(int val, int idx, int state){
            this.val = val; this.idx = idx; this.state = state;
        }
        @Override
        public int compareTo(Tuple o){
            if(val>o.val) return 1;
            if(val == o.val) return 0;
            return -1;
        }
    }
    class Point{
        int idx,w;
        Point(int idx,int w){
            this.idx = idx; this.w = w;
        }
    }
    final int INF = 987654321;
    int[][] d = new int[1001][1024];//d[node][state], node = 1000개, state = 1023개(함정노드갯수에 따름)
    List<Point>[] adj = new ArrayList[1001];
    List<Point>[] adjrev = new ArrayList[1001];
    int trapIdx[] = new int[1001];
    
    boolean bitmask(int state, int idx){
        return ((1 << trapIdx[idx]) & state) != 0;
    }
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        //1.변수 초기화
        //1-1.d를 INF로 초기화
        for(int i=1;i<=n;i++)
            for(int j=0;j<1024;j++) d[i][j] = INF;
        //1-2.adj, adjrev 초기화
        for(int i=1;i<=n;i++){
            adj[i] = new ArrayList<>(); adjrev[i] = new ArrayList<>();
        }
        //1-3.trapIdx 초기화
        for(int i=1;i<=n;i++) trapIdx[i] = -1;
        for(int i=0;i<traps.length;i++){
            trapIdx[traps[i]] = i;//함정 번호 0,1,2, 를 저장!
        }
        //1-4.간선 정보 저장
        for(int i=0;i<roads.length;i++){
            int u = roads[i][0]; int v = roads[i][1]; int val = roads[i][2];
            adj[u].add(new Point(v,val)); adjrev[v].add(new Point(u,val));
        }
        
        //다익스트라
        d[start][0] = 0;//d[node][state]
        PriorityQueue<Tuple> pq = new PriorityQueue<>();//Tuple(val,idx,state)
        pq.add(new Tuple(d[start][0],start,0));
        while(!pq.isEmpty()){
            Tuple cur = pq.poll();
            if(cur.idx == end) return cur.val;
            if(d[cur.idx][cur.state] != cur.val) continue;
            
            //1.정방향 간선 확인 : 둘다 ON 또는 둘다 OFF
            for(int i=0;i<adj[cur.idx].size();i++){
                Point next = adj[cur.idx].get(i);
                int rev = 0;
                if(trapIdx[cur.idx] != -1 && bitmask(cur.state,cur.idx)) rev ^= 1;
                if(trapIdx[next.idx] != -1 && bitmask(cur.state,next.idx)) rev ^= 1;
                if(rev != 0) continue;
                int next_state = cur.state;
                if(trapIdx[next.idx] != -1) next_state ^= (1<<trapIdx[next.idx]);//next 노드 번호로 함정 번호를 조회해서 비트 토글(ON->OFF, OFF->ON)
                //대소 비교 후 작은 값으로 갱신!
                if(d[next.idx][next_state]>next.w+cur.val){
                    d[next.idx][next_state] = next.w+cur.val;
                    pq.add(new Tuple(d[next.idx][next_state],next.idx,next_state));//val,idx,state
                }
            }
            //2.역방향 간선 확인 : 둘 중 하나만 ON
            for(int i=0;i<adjrev[cur.idx].size();i++){
                Point next = adjrev[cur.idx].get(i);
                int rev = 0;
                if(trapIdx[cur.idx] != -1 && bitmask(cur.state,cur.idx)) rev ^= 1;
                if(trapIdx[next.idx] != -1 && bitmask(cur.state,next.idx)) rev ^= 1;
                if(rev != 1) continue;
                int next_state = cur.state;
                if(trapIdx[next.idx] != -1) next_state ^= (1<<trapIdx[next.idx]);//next 노드 번호로 함정 번호를 조회해서 비트 토글(ON->OFF, OFF->ON)
                //대소 비교 후 작은 값으로 갱신!
                if(d[next.idx][next_state]>next.w+cur.val){//!!!
                    d[next.idx][next_state] = next.w+cur.val;
                    pq.add(new Tuple(d[next.idx][next_state],next.idx,next_state));//val,idx,state
                }
            }
        }
        return -1;//항상 미로 탈출하는 경우만 주어지므로 여기까지 도달하지 않는다."unreachable"
    }
}