//Q43162 네트워크
import java.util.*;
class Solution {
    int Ans,N;
    boolean[] visited = new boolean[N];
    public int solution(int n, int[][] computers) {
        Ans = 0;
        N = n;
        visited = new boolean[N];
        
        for(int i=0;i<N;i++){
            
            if(!visited[i]){
                graphTraversal(i,computers);//탐색 시작 노드 번호,연결 정보 넘김
            }
        }
        
        return Ans;
    }
    void graphTraversal(int st,int[][] adj){
        Ans++;//1
        Queue<Integer> q = new LinkedList<>();
        q.offer(st);
        visited[st] = true;
        
        while(!q.isEmpty()){
            int cur = q.poll();//0->1
            
            for(int next=0;next<N;next++){
                if(st == next) continue;//next=0->1,cur=1,next=2
                if(!visited[next] && adj[cur][next] == 1){
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
       
        
    }
}