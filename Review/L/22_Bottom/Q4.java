import java.util.*;

class Solution {
    static int[][] ans;
    static int R,C;
    static char[][] Map;
    static final int INF = Integer.MAX_VALUE;

    static class P{
        int x,y,dist;

        P(int x,int y,int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int[][] solution(String[] wall) {
        R = wall.length;
        C = wall[0].length();

        ans = new int[R][C];

        Map = new char[R][C];
        
        for(int i=0;i<R;i++){
            Map[i] = wall[i].toCharArray();
        }
        
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(wall[i].charAt(j) == 'H') ans[i][j] = INF;//방문한 H : 그 때의 홀드 수, 미방문 H : INF
                else ans[i][j] = 0;
            }
        }
        
        solve();

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(ans[i][j] == INF) ans[i][j] = -1;
            }
        }
        return ans;
    }
    void solve(){
        Queue<P> q = new LinkedList<>();
        boolean[][] v = new boolean[R][C];

        //시작
        q.offer(new P(R-1,0,1));
        v[R-1][0] = true;
        ans[q.peek().x][q.peek().y] = 1;

        int nx,ny;
        while(!q.isEmpty()){
            P cur = q.poll();
            //조건1 : 미방문 H인칸 방문 => ans[nx][ny] == INF

            //조건2 : 좌우 2칸 또는 3칸 이동 => 3가지 조건 모두 빈칸
            
            //조건3
            nx = cur.x-2; ny = cur.y;
            //if(isOut(nx,ny) || v[nx][ny] || ans[nx][ny] != INF) continue;
            //if(isOut(nx,ny) || ans[nx][ny] != INF) continue;
            if(isIn(nx,ny) && ans[nx][ny] == INF && Map[cur.x-1][cur.y] == '.' && ans[nx][ny] == INF) {
                q.offer(new P(nx,ny,cur.dist+1));
                v[nx][ny] = true;
                ans[nx][ny] = cur.dist+1;
                //continue;
            }
            //조건4
            nx = cur.x-1; ny = cur.y-1;
            //if(isOut(nx,ny) || ans[nx][ny] != INF) continue;
            if(isIn(nx,ny) && ans[nx][ny] == INF && Map[cur.x-1][cur.y] == '.' && Map[cur.x][cur.y-1] == '.'){
                q.offer(new P(nx,ny,cur.dist+1));
                v[nx][ny] = true;
                ans[nx][ny] = cur.dist+1;
                //continue;
            }
            //조건5 : 오른쪽 위
            nx = cur.x-1; ny = cur.y+1;
            //if(isOut(nx,ny) || ans[nx][ny] != INF) continue;
            if(isIn(nx,ny) && ans[nx][ny] == INF && Map[cur.x-1][cur.y] == '.' && Map[cur.x-1][cur.y+1] == '.'){
                //System.out.println("들어오니?");
                q.offer(new P(nx,ny,cur.dist+1));
                v[nx][ny] = true;
                ans[nx][ny] = cur.dist+1;
                //continue;
            }
        }
    }
    boolean isOut(int x,int y){
        return x<0 || x>=R || y<0 || y>=C;
    }
    boolean isIn(int x,int y){
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
//확인
        // for(int i=0;i<R;i++){
        //     for(int j=0;j<C;j++){
        //         System.out.print(Map[i][j]+" ");
        //     }
        //     System.out.println();
        // }