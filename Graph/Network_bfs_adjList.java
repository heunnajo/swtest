import java.util.*;
class Network_bfs_adjList {
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int len = computers.length;
        visited = new boolean[len];
        
        list = new ArrayList[len];
        for(int i=0;i<len;i++){//0번~n-1번 컴 존재하기 때문에
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(i==j) continue;
                //인접행렬로 인접리스트 완성
                if(computers[i][j]==1){
                    list[i].add(j);
                    list[j].add(i);
                    computers[j][i] = -1;//중복 방지
                }
            }
        }
        //n개의 노드에 대해 탐색!
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<len;i++){
            if(!visited[i]){
                q.add(i);visited[i] = true;//0
                while(!q.isEmpty()){
                    int cur = q.remove();
                    //if(visited[cur]) continue;
                    for(int a:list[cur]){//0의 인접 노드 탐색!
                        if(!visited[a]){//방문하지 않았다면 방문하고, 방문 체크, 큐에 넣넣
                            q.add(a); visited[a] = true;
                        }
                    }
                }
                answer++;
            }
            
        }
        return answer;
    }
    
}