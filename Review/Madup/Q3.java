/*
2차원 배열 완전 탐색
1. 모든 가능한 경로 구한다.
2. 1에서 구한 각 경로마다 시간 조건 판단 후 배달팁 합산
3. 정답 : 2값 중 최댓값
*/
import java.util.*;

class Solution {
    static int R,Ans;
    static int[][] Del;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Info{
        int x,y,t,m;

        Info(int x,int y,int t,int m){
            this.x = x;
            this.y = y;
            this.t = t;
            this.m = m;
        }
    }
    public int solution(int r, int[][] delivery) {
        R = r;
        Ans = 0;

        bfs(0,0,delivery);

        return Ans;
    }
    void bfs(int sx,int sy,int[][] del){
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(0,0,0,0));
        int[][] dp = new int[R][R];

        while(!q.isEmpty()){
            Info cur = q.poll();
            if(cur.m > Ans) Ans = cur.m;

            for(int d=0;d<4;d++){
                int nx = cur.x+dx[d]; int ny = cur.y+dy[d];
                if(isOut(nx,ny)) continue;

                int[] curInfo = del[nx*R+ny];
                int timeLimit = curInfo[0];
                int tip = curInfo[1];

                if(cur.t+1 <= timeLimit){
                    q.offer(new Info(nx,ny,cur.t+1,cur.m+tip));
                    dp[nx][ny] = cur.m+tip;
                } else{
                    q.offer(new Info(nx,ny,cur.t+1,cur.m));
                }
            }
        }    
    }
    boolean isOut(int x,int y){
        return x<0 || x>=R || y<0 || y>=R;
    }
}