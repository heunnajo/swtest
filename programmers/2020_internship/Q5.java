//5.동굴 탐험
import java.util.*;
class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        //0.초기화
        ArrayList<Integer>[] map = new ArrayList[n];
        boolean[] v = new boolean[n];
        int[] before = new int[n];
        int[] savePoint = new int[n];
        
        for(int i=0;i<n;i++) map[i] = new ArrayList<Integer>();
        
        //1.자료구조 : 간선 정보, 순서 정보 저장
        int p_len = path.length;
        for(int i=0;i<p_len;i++){
            //map[i].add(path[i][0]); map[i].add(path[i][1]);
            map[path[i][0]].add(path[i][1]);
            map[path[i][1]].add(path[i][0]);
        }
        int o_len = order.length;
        for(int i=0;i<o_len;i++){
            before[order[i][1]] = order[i][0];//[0]먼저 방문, [1] 방문
        }
        
        if(before[0]!=0) return false;
        
        //2.알고리즘 : BFS 수행:0번 노드부터 탐색 : 방문체크, 0번 인접 노드 큐에 삽입
        
        v[0] = true;
        Queue<Integer> q = new LinkedList<>();
        for(int i:map[0]) q.add(i);//0번 노드의 인접 노드
        
        while(!q.isEmpty()){
            int cur = q.poll();
            if(v[cur]) continue;
            
            //방문 순서 정보에 따른 이전 노드 방문 여부 확인
            //i.이전 노드 방문 X
            if(!v[before[cur]]){
                savePoint[before[cur]] = cur;
                continue;
            }
            //i.이전 노드 방문 O
            v[cur] = true;//큐에서 나온 노드에 대해 방문 체크를 수행.
            for(int i:map[cur]){
                q.add(i);
            }
            q.add(savePoint[cur]);//현재노드 방문 후 다음 노드 큐에 삽입. 만약 현재 노드에 대한 방문 순서가 없다면 savePoint[cur] = 0, 큐에서 뽑았을 때, cur==0이면 v[cur] = v[0] = true이므로 컨티뉴 처리되어 탐색 진행하지 않음.
            
        }
        //정답 판별
        for(int i=0;i<n;i++){
            if(!v[i]) return false;
        }
        return true;
    }
}