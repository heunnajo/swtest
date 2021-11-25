import java.util.*;
class Network_dfs_adjMatrix {
    static boolean[] visited;
    static int[][] com;
    static int len;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        len = computers.length;
        visited = new boolean[len];
        com = computers;
        //n개의 노드에 대해 탐색!
        
        for(int i=0;i<len;i++){
            if(!visited[i]){
                dfs(i);
                answer++;
            }
            
        }
        return answer;
    }
    static void dfs(int node){
        visited[node] = true;
        for(int j=0;j<len;j++){
            if(node==j) continue;
            if(visited[j]) continue;
            if(com[node][j] == 1){
                dfs(j);
            }
        }
    }
    static void bfs(int node){
        Queue<Integer> q = new LinkedList<>();
        for(int j=0;j<len;j++){
            if(node==j) continue;
            if(com[node][j] == 1){
                q.add(node);visited[node] = true;
                while(!q.isEmpty()){
                    int cur = q.remove();
                    for(int a=0;a<len;a++){
                        if(a==cur)continue;
                        if(visited[a]) continue;
                        if(com[cur][a]==1){
                            q.add(a);visited[a] = true;
                        }
                    }
                }
            }
        }
    }
    
}