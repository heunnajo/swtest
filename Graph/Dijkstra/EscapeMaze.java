class EscapeMaze {
    static int Start,N;
    static final int INF = 5000000;//500만
    static int cur,edges[][],D[];
    static boolean[] visited;
//     static void Dijkstra(){
//         //최소 비용 노드 curNode를 찾는다.
//         //curNode의 인접노드 순회해서 이를 고려하여 D 배열 최솟값 갱신한다.
//         for(int i=1;i<n;i++){
//             if(D[i]>D[i+1]) cur = i+1;//curNode를 찾음
//             //curNode의 인접노드들 순회
            
//         }
//     }
    //Start에서 이르는 최소 거리 노드 찾는 메서드
    int ShortestVertex(){
        int v = 0;//최소 거리 노드 번호 저장
        int minDist = INF;
        for(int i=1;i<=N;i++){
            if(D[i]<minDist && !visited[i]){//아직 방문하지 않은 노드 중에서 최소 거리 노드 번호 찾는다!
                minDist = D[i];
                v = i;
            }
        }
    }
    void Dijkstra(){
        for(int i=1;i<N-1;i++){
            int cur = ShortestVertex(start);
            visited[cur] = true;
            for(int j=1;j<N;j++){
                if(!visited[j]){//1~N-1
                    if(D[cur]+edges[cur][j]<D[j]){//최솟값 비교, 갱신
                        D[j] = D[cur]+edges[cur][j];
                    }
                }
            }
        }
    }
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        //초기화
        Start = start;
        N = n;
        
        edges = new int[n+1][n+1];//정점의 갯수만큼
        D = new int[n+1];
        Arrays.fill(D,INF);
        D[start] = 0;
        cur = start;
        
        //간선 정보 edges에 저장, 출발점이 start라면 D 배열에도 저장
        for(int i=0;i<roads.length;i++){
            edges[roads[i][0]][roads[i][1]] = roads[i][2];
            if(roads[i][0] == start){
                D[roads[i][1]] = roads[i][2];
            }
        }
        //다익스트라 알고리즘 수행, D 배열 완성
        Dijkstra();
        return D[end];
    }
}