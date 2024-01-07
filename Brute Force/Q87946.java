//Q87946 피로도
class Solution {
    int N,K,Ans;
    int[] Order;
    boolean[] Visited;
    int[][] D;
    public int solution(int k, int[][] dungeons) {
        Ans = -1;
        K = k;
        N = dungeons.length;
        D = new int[N][2];
        for(int i=0;i<N;i++) {
            D[i][0] = dungeons[i][0];
            D[i][1] = dungeons[i][1];
        }
        Order = new int[N];
        Visited = new boolean[N];
        
        go(0);
        
        return Ans;
    }
    void go(int idx){
        //1.종료 : N개의 던전 순서 다 정한 경우
        if(idx == N){
            //Order의 순서대로 던전 수행!
            int cur = simulation();
            if(cur > Ans) Ans = cur;
            return;
        }
        //2.현재 순서 선택, 다음 순서 재귀로 선택
        for(int i=0;i<N;i++){
            if(Visited[i]) continue;
            Visited[i] = true;
            Order[idx] = i;
            go(idx+1);
            Visited[i] = false;
        }
    }
    int simulation(){
        int curEngegy = K;
        int count = 0;
        for(int i=0;i<N;i++){
            if(curEngegy >= D[Order[i]][0]){
                curEngegy -= D[Order[i]][1];
                count++;
            } else{break;}
        }
        return count;
    }
}