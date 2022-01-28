import java.util.*;
class Solution {
    int n;//정점 갯수
    int answer = 1;//0번 루트노드는 항상 양이기 때문에 1로 초기화한다!
    int[] val = new int[20];
    int[] left = new int[20];
    int[] right = new int[20];
    int[] visited = new int[1<<17];
    public int solution(int[] info, int[][] edges) {
        //그래프 정보 저장
        //1.정점 정보 저장
        n = info.length;
        for(int i=0;i<n;i++) val[i] = info[i];
        Arrays.fill(left,-1);
        Arrays.fill(right,-1);
        //2.연결 정보 저장
        for(int i=0;i<n-1;i++){
            int a = edges[i][0];
            int b = edges[i][1];
            
            if(left[a] == -1) left[a] = b;
            else right[a] = b;
        }
        dfs(1);//0번 노드에서 dfs 시작!
        return answer;
    }
    void dfs(int state){
        //이미 방문한 경우 리턴
        if(visited[state] == 1) return;
        //방문 처리
        visited[state] = 1;
        //현재 상태의 전체 방문 정점 수, 늑대 수 카운팅
        int total = 0, wolf = 0;
        for(int i=0;i<n;i++){
            if((state & (1<<i)) != 0){
                total++;
                wolf += val[i];
            }
        }
        //현재 상태 늑대 수에 따라 처리
        //1.불가능한 경우 : 늑대 수가 과반수 이상일 때
        if(wolf*2>=total) return;
        //2.정답 찾은 경우 : 현재 상태의 양 수가 더 많다면 answer를 갱신
        if(answer<total-wolf) answer = total-wolf;
        //3.다음 경우 호출(다음 상태로 이동) : 현재 상태에서 비트 1인 정점의 자식노드가 있다면 그 노드로 이동
        for(int i=0;i<n;i++){
            if((state & (1<<i)) != 0){
                if(left[i] != -1) dfs(state | (1<<(left[i])));
                if(right[i] != -1) dfs(state | (1<<(right[i])));
            }
        }
    }
}