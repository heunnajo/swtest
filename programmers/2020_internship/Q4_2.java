//4.경주로 건설(다익스트라)
import java.util.*;
class Solution {
    int[][] Board;
    int N;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    int[][] dp;
    int INF = 987654321;
    class Point implements Comparable<Point> {
        int x,y,cost,dir;
        Point(int x,int y,int cost,int dir){
            this.x = x; this.y = y; this.cost = cost; this.dir = dir;
        }
        public int compareTo(Point p){
            return this.cost-p.cost;
        }
    }
    public int solution(int[][] board) {
        //0.변수 초기화
        N = board.length;
        Board = new int[N][N];
        Board = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++) {
                Board[i][j] = board[i][j];
                dp[i][j] = INF;
            }
        }
        //(0,0)->(n-1,n-1) 최소 비용 도출:다익스트라 수행
        return dijkstra();
    }
    int dijkstra(){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0,0,0,0));//(x,y,cost,dir)
        dp[0][0] = 0;
        //"방문 체크".....이미 방문한 칸도 방문을 해야할까?
        while(!pq.isEmpty()){
            Point cur = pq.poll();
            //탐색 종료
            if(cur.x == N-1 && cur.y == N-1){
                return cur.cost;
            }
            //1.현재 정점 기준 최소 비용 정점 구한다 = 큐에서 뽑은 노드
            //2.원래 거리값과 대소 비교 후 거리값 갱신!
            for(int d=0;d<4;d++){
                //다음으로 이동할 좌표 (nx,ny)
                int nx = cur.x+dx[d],ny = cur.y+dy[d];
                if(isOut(nx,ny) || Board[nx][ny] == 1) continue;
                
                //일반적인 직선도로인지, 코너를 돌아서 직선도로인지 판단!
                if(cur.dir == d){
                    pq.add(new Point(nx,ny,cur.cost+100,d));
                    if(dx[nx][ny] > d[cur.x][cur.y]+)
                } else{
                    pq.add(new Point(nx,ny,cur.cost+600,d));
                }
            }
        }
        return 0;//unreachable
    }
    boolean isOut(int x,int y){
        return x<0 || x>N-1 || y<0 || y>N-1;
    }
}