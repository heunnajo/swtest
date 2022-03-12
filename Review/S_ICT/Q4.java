//4.트리 DP
import java.util.*;
class Solution {
    int N;
    ArrayList<Node>[] adj;
    long ans;
    int[] dp;
    class Node{
        int idx,w;
        Node(int idx,int w){
            this.idx = idx; this.w = w;
        }
    }
    public long solution(int n, int[][] edges) {
        //0.변수 초기화, 간선 정보 저장
        N = n; dp = new int[n];
        adj = new ArrayList[n+1];
        for(int i=0;i<n;i++){
            adj[i] = new ArrayList<Node>();
        }
        for(int i=0;i<n-1;i++){
            int u = edges[i][0];int v = edges[i][1];
            adj[u].add(new Node(v,1)); adj[v].add(new Node(u,1));
        }
        for(int i=0;i<n;i++){
        }
        //dfs로 트리 탐색하면서 메모이제이션
        ans = 0;
        dfs(0,-1);
        return ans;
    }
    int dfs(int cur,int p){
        if(dp[cur] != 0) return dp[cur];
        dp[cur] = 1;
        for(Node child:adj[cur]){
            if(child.idx != p){
                dp[cur] += dfs(child.idx,cur);
            }
        }
        return dp[cur];
    }
}
// for(int i=0;i<n;i++){
//             ArrayList<Node> cur = adj[i];
//             System.out.println(i+"번 노드의 인접 노드");
//             for(Node k:cur){
//                 System.out.print(k.idx+" ");
//             }
//             System.out.println();
//         }