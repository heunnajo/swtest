//4. 미로 탈출_다익스트라(인접행렬)
import java.util.*;

class Solution {
    private final static int MAX_N = 1001;//1부터 인덱스 사용하기 위해
    private final static int INF = Integer.MAX_VALUE;
    int[][] Graph = new int[MAX_N][MAX_N];

    int dijkstra(int n,int src,int dst,int[] traps){//n:정점 갯수, src:출발점, dst:도착점, traps : 함정 노드들
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        boolean[][] visited = new boolean[MAX_N][1<<10];//[node][state]
        pq.add(new int[]{src,0,0});//(node,가중치,state) 초기 함정 노드가 발동된 상태state = 0
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int u = curr[0];//노드번호
            int w = curr[1];//가중치
            int state = curr[2];//상태

            if(u == dst) return w;
            if(visited[u][state]) continue;
            visited[u][state] = true;
            //현재 노드에서 다음 이동할 노드 확인 : 이동하려는 노드의 함정발동된 상태 확인해야함:HashMap을 이용해 빠르게 확인한다.
            boolean curTrapped = false;//초기값은 false
            HashMap<Integer,Boolean> trapped = new HashMap<>();//함정이 발동된 노드는 value=true;

            //함정 노드 순회하면서 현재 상태 state에서 몇번째 함정노드가 켜져있는지 확인, 아래 3가지 업데이트
            //1. 현재 상태 state
            //2. 현재 노드의 함정 발동 여부 curTrapped
            //3. 함정 노드 저장 Map인 trapped
            for(int i=0;i<traps.length;++i){
                int bit = 1<<i;
                if((state & bit)!= 0) {//함정 발동O 상태
                    if(traps[i] ==u) state &= ~bit;//현재 노드가 i번째 함정노드인 경우:해당 비트 OFF
                    else trapped.put(traps[i],true);//현재 노드가 일반 노드인 경우:Map만 업데이트
                } else{//함정 발동X 상태
                    if(traps[i] == u){//현재 노드가 i번째 함정노드인 경우:해당 비트 ON
                        state |= bit;
                        trapped.put(traps[i],true);
                        curTrapped = true;
                    }
                }
            }
            //인접 노드로 이동 : 현재 노드, 이동하려는 노드의 함정 발동 여부 확인(trapped Map을 이용하여 확인)
            //현재 노드의 함정 발동 여부 curTrapped, 다음 노도의 함정 발동 여부 nextTrapped
            for(int next = 1;next<=n;next++){
                if(next==u) continue;
                boolean nextTrapped = trapped.containsKey(next) ? true : false;
                
                if(curTrapped == nextTrapped){//둘다 함정발동O 또는 둘다 함정발동 OFF로 같으면 원래 간선 정보 사용
                    if(Graph[u][next] != INF) pq.add(new int[]{next,w+Graph[u][next],state});
                } else{//두개가 다르면 반전된 간선 정보를 이용
                    if(Graph[next][u] != INF) pq.add(new int[]{next,w+Graph[next][u],state});
                }
            }
        }
        return INF;//문제에서 도착점이 존재하도록 간선을 주지만, INF를 리턴하도록 한다.
    }
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        //인접행렬 초기화
        for(int i=1;i<=n;++i){
            for(int j=1;j<=n;++j){
                if(i==j) Graph[i][j] = 0;//i: 출발점, j:도착점
                else Graph[i][j] = INF;
            }
        }
        //roads 간선정보 저장
        for(int[] data : roads){
            int u = data[0],v = data[1], w = data[2];//u:출발점,v:도착점,w:비용
            //동일한 두 정점 사이에 여러 간선이 존재 가능. 인접 리스트라면 모두 저장하면 되지만 인접행렬을 사용할 경우, 최소 비용을 구하는 것이기 때문에 최솟값을 저장한다.
            if(w<Graph[u][v]) Graph[u][v] = w;
        }
        return dijkstra(n,start,end,traps);
    }
}
