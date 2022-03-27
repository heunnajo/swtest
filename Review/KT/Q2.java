//2.1->n으로 가는 버스 최소 시간
import java.util.*;
class Solution {
    class Node{
        int idx,w;
        Node(int idx,int w){
            this.idx = idx;
            this.w = w;
        }
    }
    ArrayList<Node>[] adj;
    int N,ans;
    public int solution(int n, int[][] info) {
        N = n;
        adj = new ArrayList[n+1];

        for(int i=0;i<=n;i++){
            adj[i] = new ArrayList<Node>();
        }

        for(int i=0;i<info.length;i++){
            int[] cur = info[i];
            adj[cur[0]].add(new Node(cur[1],cur[2]));
            adj[cur[1]].add(new Node(cur[0],cur[2]));
        }

        int INF = 987654321;
        ans = INF;

        //1번 노드부터 DFS 탐색!
        dfs(1,0);

        if(ans == INF) ans = -1;
        return ans;
    }
    void dfs(int st,int time){
        //1. 탐색 종료
        if(st == N){
            ans = Math.min(time,ans);
            return;
        }
        //2.인접 노드 탐색
        for(Node nd:adj[st]){
            //이동 가능한 경우 : 현재 시간에 st->nd로 가는 버스가 있는 경우
            int curBusTime = nd.w*2;
            if(time % curBusTime == 0){
                dfs(nd.idx,time+nd.w);
            } else{//이동 불가능한 경우
                continue;//다음 인접 노드로 이동 시도!
            }
        }
    }
}
// for(int i=0;i<=n;i++){
//             if(adj[i] != null){
//                 System.out.print("초기화잘됨!"+ " ");
//             }
//             System.out.println();
//         }
 //간선 정보 저장 확인
// for(int i=1;i<=n;i++){
//     for(Node nd:adj[i]){
//         System.out.print(nd.idx+", "+nd.w);
//     }
//     System.out.println();
// }