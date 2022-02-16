//4. 미로 탈출_다익스트라(인접행렬)
import java.util.*;

class Solution {
    private final static int MAX_N = 1001;//1부터 인덱스 사용하기 위해
    private final static int INF = Integer.MAX_VALUE;
    int[][] Graph = new int[MAX_N][MAX_N];

    int dijkstra(int n,int src,int dst,int[] traps){//n:정점 갯수, src:출발점, dst:도착점, traps : 함정 노드들
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);//와 람다식으로 한 라인에 바로 정렬 기준 선언 가능.......미쳤....배열의 0번 값을 가중치라고 설정할 것이기 때문에 가중치인 0번 값을 기준으로 오름차순 정렬해준다! 0번:node번호, 1번:가중치, 2번:상태
        boolean[][] visited = new boolean[MAX_N][1<<10];//[node][state]
        pq.add(new int[]{src,0,0});//(node,가중치,state) 초기 함정 노드가 발동된 상태state = 0
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int u = curr[0];//노드번호
            int w = curr[1];//가중치
            int state = curr[2];//상태

            if(u == dst) return w;
            if(visited[u][state]) continue;
            visited[u][state] = true;
            //현재 노드에서 다음 이동할 노드 확인 : 이동하려는 노드의 함정발동된 상태 확인해야함:HashMap을 이용해 빠르게 확인한다.
            boolean curTrapped = false;//초기값은 false
            HashMap<Integer,Boolean> trapped = new HashMap<>();//함정이 발동된 노드는 value=true;
            //함정에 해당하는 비트를 먼저 만든다.
            for(int i=0;i<traps.length;++i){
                int bit = 1<<i;//0:왼쪽으로 0번 시프트, 1:왼쪽으로 1번 시프트,... 그러면 함정에 해당하는 비트가 1로 켜진다! 2,3이 함정비트일 때, 이 2, 3 노드 번호와는 상관없이 인덱스이자 반복변수인 i값에 따라 비트가 켜지도록?!
                if((state & bit)!= 0) {//0이 아니라는 건 그 비트가 켜져있다는 뜻.
                     //현재 도착한 노드가 비트가 켜진 함정 노드라면 다시 반전시킨다(원래대로 돌아가야함) 
                    //즉, 현재 함정 발동되있는데 다시 온것을 의미=>이 때는 해당 비트 꺼줘야함
                    if(traps[i] ==u) {//비트를 끄는 방법은 비트 반전시킨다음 &연산하면 된다.
                        state &= ~bit;//~bit는 해당 비트만 0이다. 따라서 현재 상태 state와 &연산하면 해당 비트만 0이 된다.
                    } else{//아니라면 반전 시킬 필요없음
                        trapped.put(traps[i],true);
                    }
                } else{//함정 발동X 상태 : 함정 발동 비트 ON 한다!
                    if(traps[i] == u){
                        state |= bit;//함정에 해당하는 비트 켠다!
                        trapped.put(traps[i],true);//함정이 발동되있다는 뜻.
                        curTrapped = true;
                    }
                }
            }//한마디로 현재 state에 따라 ON/OFF로 토글된다! 끝나고 나면 trapped에는 함정 발동된 노드들이 들어가있을 것.
            //인접 노드로 이동
            for(int v = 1;v<=n;v++){
                if(v==u) continue;
                //이동할 때 확인해야하는 것 : 이동하고자 하는 노드가 함정 노드 발동되있는지 : HashMap을 이용
                boolean nextTrapped = trapped.containsKey(v) ? true : false;//v로 key를 조회
                //이로써 현재 노드의 함정 발동 여부 curTrapped, 다음 노도의 함정 발동 여부 nextTrapped
                if(curTrapped == nextTrapped){//둘다 함정발동O 또는 둘다 함정발동 OFF로 같으면 원래 간선 정보 사용
                    if(Graph[u][v] != INF)
                        pq.add(new int[]{v,w+Graph[u][v],state});
                } else{//두개가 다르면 반전된 간선 정보를 이용
                    if(Graph[v][u] != INF)
                        pq.add(new int[]{v,w+Graph[v][u],state});
                }
            }
        }
        return INF;//문제에서 도착점이 존재하도록 간선을 주지만, INF를 리턴하도록 한다.
    }
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        //인접행렬 초기화
        for(int i=1;i<=n;++i){
            for(int j=1;j<=n;++j){
                if(i==j) Graph[i][j] = 0;//i: 출발점, j:도착점
                else Graph[i][j] = INF;
            }
        }
        //roads 간선정보 저장
        for(int[] data : roads){
            int u = data[0],v = data[1], w = data[2];//u:출발점,v:도착점,w:비용
            //동일한 두 정점 사이에 여러 간선이 존재 가능하다고 했다. 인접 리스트라면 모두 저장하면 되지만 우리는 여기서 인접행렬을 사용할 것이기 때문에 최솟값 하나만을 저장할 수 있다. 그리고 어차피 최소 비용을 구하는 것이기 때문에 최솟값을 저장한다.
            if(w<Graph[u][v]) Graph[u][v] = w;
        }
        return dijkstra(n,start,end,traps);
    }
}
