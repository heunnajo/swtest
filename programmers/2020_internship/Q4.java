//4. 경주로 건설
import java.util.*;
class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    int ans = Integer.MAX_VALUE;
    boolean[][][] v;//x,y,dir에 대해 방문체크를 수행
    int n;
    int[][] Board;
    public int solution(int[][] board) {
        n = board.length;
        Board = new int[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++) Board[i][j] = board[i][j];
        
        //4가지 방향에 대해 완전 탐색
        v = new boolean[n][n][4];
        bfs(0);
        v = new boolean[n][n][4];
        bfs(1);
        v = new boolean[n][n][4];
        bfs(2);
        v = new boolean[n][n][4];
        bfs(3);
        
        return ans;
    }
    void bfs(int dir){
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.add(new Point(0,0,0,dir));
        
        while(!q.isEmpty()){
            Point cur = q.poll();
            if(cur.x == n-1 && cur.y == n-1){
                ans = Math.min(ans,cur.dist);
                return;
            }
            //현재 좌표, 현재 방향에 대해 방문 체크
            if(v[cur.x][cur.y][cur.dir]) continue;
            v[cur.x][cur.y][cur.dir] = true;
            
            for(int d=0;d<4;d++){
                //현재 방향 cur.dir
                //현재 이동하려고 하는 방향 d
                
                int nx = cur.x+dx[d], ny = cur.y+dy[d];
                if(isOut(nx,ny)) continue;//범위를 벗어나면 컨티뉴 처리!
                if(Board[nx][ny] == 1) continue;//벽이면 이동 불가하므로 컨티뉴 처리!
                
                //일반적인 직선도로인지, 코너를 돌아서 직선도로인지 판단!
                if(cur.dir == d){
                    //q.add(new Point(nx,ny,cur.dist+100,cur.dir));
                    q.add(new Point(nx,ny,cur.dist+100,d));
                } else{
                    q.add(new Point(nx,ny,cur.dist+600,d));
                }
            }
        }
    }
    boolean isOut(int x,int y){
        return x<0 || x>n-1 || y<0 || y>n-1;
    }
    class Point implements Comparable<Point>{
        int x,y,dist,dir;
        Point(int x,int y,int dist,int dir){
            this.x = x; this.y = y; this.dist = dist; this.dir = dir;
        }
        public int compareTo(Point p){
            return this.dist-p.dist;
        }
    }
}