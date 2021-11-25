import java.util.*;
class Network_bfs_adjMatrix {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int len = computers.length;
        visited = new boolean[len];
        
        //n개의 노드에 대해 탐색!
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<len;i++){
            if(!visited[i]){
                for(int j=0;j<len;j++){
                    if(i==j) continue;
                    if(computers[i][j] == 1){
                        q.add(i);visited[i] = true;
                        while(!q.isEmpty()){
                            int cur = q.remove();
                            for(int a=0;a<len;a++){
                                if(a==cur)continue;
                                if(visited[a]) continue;
                                if(computers[cur][a]==1){
                                    q.add(a);visited[a] = true;
                                }
                            }
                        }
                    }
                }
                answer++;
            }
            
        }
        return answer;
    }
    
}