import java.util.*;
class Network_dfs_adjList {
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int len = computers.length;
        visited = new boolean[len];
        
        //list = new ArrayList[len][len];
        //list = (ArrayList<Integer>[]) new ArrayList[len];
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
                    list[j].add(i);//중복해서 들어가진 않을까....i<=>j
                    computers[j][i] = -1;//(j,i)에 대해서는 임의로 값을 변경한다. 원래 데이터 변경하는 방법은 별로일 것 같긴 하지만...
                }
            }
        }
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
        for(int a:list[node]){
            if(!visited[a]){
                dfs(a);
            }
        }
    }
}