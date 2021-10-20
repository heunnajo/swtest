import java.util.*;
class EscapeMaze_Solution_pureCode {
    private final static int MAX_N = 1001;//1부터 인덱스 사용하기 위해
    private final static int INF = Integer.MAX_VALUE;
    int[][] Graph = new int[MAX_N][MAX_N];
    
    int dijkstra(int n,int src,int dst,int[] traps){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        boolean[][] visited = new boolean[MAX_N][1<<10];
        pq.add(new int[]{src,0,0});
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int u = curr[0];
            int w = curr[1];
            int state = curr[2];
            
            if(u == dst) return w;
            if(visited[u][state]) continue;
            visited[u][state] = true;
            boolean curTrapped = false;
            HashMap<Integer,Boolean> trapped = new HashMap<>();
            for(int i=0;i<traps.length;++i){
                int bit = 1<<i;
                if((state & bit)!= 0) {
                    if(traps[i] ==u) {
                        state &= ~bit;
                    } else{
                        trapped.put(traps[i],true);
                    }
                } else{
                    if(traps[i] == u){
                        state |= bit;
                        trapped.put(traps[i],true);
                        curTrapped = true;
                    }
                }
            }
            
            for(int v = 1;v<=n;v++){
                if(v==u) continue;
                boolean nextTrapped = trapped.containsKey(v) ? true : false;
                if(curTrapped == nextTrapped){
                    if(Graph[u][v] != INF)
                        pq.add(new int[]{v,w+Graph[u][v],state});
                } else{
                    if(Graph[v][u] != INF)
                        pq.add(new int[]{v,w+Graph[v][u],state});
                }
            }
        }
        return INF;
    }
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        for(int i=1;i<=n;++i){
            for(int j=1;j<=n;++j){
                if(i==j) Graph[i][j] = 0;
                else Graph[i][j] = INF;
            }
        }
        for(int[] data : roads){
            int u = data[0],v = data[1], w = data[2];
            if(w<Graph[u][v]) Graph[u][v] = w;
        }
        return dijkstra(n,start,end,traps);
    }
}