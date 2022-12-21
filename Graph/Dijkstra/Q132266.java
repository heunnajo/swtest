//Q132266 부대복귀
import java.util.*;
class Solution {
    int[] dp;
    class Pair implements Comparable<Pair>{
        int node,time;
        
        Pair(int node,int time){
            this.node = node;
            this.time = time;
        }
        public int compareTo(Pair p){
            return this.time - p.time;
        }
    }
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int INF = 987654321;
        int k = sources.length;
        int len = roads.length;
        
        ArrayList<Pair>[] adj = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            adj[i] = new ArrayList<>();
        }
        
        for(int i=0;i<len;i++){
            int u = roads[i][0];
            int v = roads[i][1];
            
            adj[u].add(new Pair(v,1));
            adj[v].add(new Pair(u,1));
            
        }
        
        dp = new int[n+1];
        Arrays.fill(dp,INF);
        dijkstra(n,destination,adj);//정점갯수,출발점, 연결정보
        
        //총 k명의 도착시간 구한다.
        int[] answer = new int[k];
        for(int i=0;i<k;i++){
            if(dp[sources[i]] == INF) answer[i] = -1;
            else answer[i] = dp[sources[i]];
        }
        // for(int i=1;i<=n;i++){
        //     System.out.println(i+"번 정점까지 최단거리 : "+dp[i]);
        // }
        return answer;
    }
    void dijkstra(int n,int st,ArrayList<Pair>[] adj){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] v = new boolean[n+1];
        pq.offer(new Pair(st,0));
        dp[st] = 0;
        
        while(!pq.isEmpty()){
            Pair cur = pq.poll();
            
            for(Pair next:adj[cur.node]){
                if(dp[next.node] > dp[cur.node]+1){
                    //최단 거리 구할 때
                    //st->next vs st->cur + cur->next
                    dp[next.node] = dp[cur.node]+1;
                    pq.offer(new Pair(next.node,dp[next.node]));
                }
            }
        }
    }
}