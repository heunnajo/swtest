//6. 카드 짝 맞추기
import java.util.*;
class Solution {
    static final int INF = 987654321;
    static int[][] Board = new int[4][4];
    static class Point{
        int x,y,cnt;
        Point(int x,int y,int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int bfs(Point src,Point dest){
        Queue<Point> q = new LinkedList<Point>();
        boolean[][] visited = new boolean[4][4];
        q.add(src);
        visited[src.x][src.y] = true;
        
        while(!q.isEmpty()){
            //방향키, Ctrl+방향키 같으므로 함께 큐에 넣어준다!
            Point cur = q.remove();
            if(cur.x == dest.x && cur.y == dest.y){
                return cur.cnt;
            }
            //델타어레이
            for(int d=0;d<4;d++){
                int nx = cur.x+dx[d];
                int ny = cur.y+dy[d];
                    
                if(check(nx,ny)) continue;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny,cur.cnt+1));
                }
                //Ctrl+방향키 처리.더 이동가능하다면 1칸 더 이동한 위치에서 더 이동.
                for(int j=0;j<2;j++){
                    if(Board[nx][ny] != 0) break;//숫자칸 도착하면 종료!
                    //동일한 d방향으로 더 이동한 좌표 범위  체크 후 이동시킨다!
                    if(check(nx+dx[d],ny+dy[d])) break;
                    nx += dx[d];
                    ny += dy[d];
                   
                }
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny,cur.cnt+1));
                }
                
            }
            
        }
        return INF;//그럴일 없겠지만 여기까지왔다는 건 큐가 비었고, 탐색 끝났다는 뜻.
    }
    //범위 초과하면 true를 리턴.
    static boolean check(int x,int y){
        return x<0 || x>3 || y<0 || y>3;
    }
    
    //재귀함수로 각 순자카드마다 고르는 순서를 선택한다!
    static int permutate(Point src){
        int ret = INF;
        for(int num = 1;num<=6;num++){
            List<Point> cards = new ArrayList<>();
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(Board[i][j] == num){
                        cards.add(new Point(i,j,0));
                    }
                }
            }
            if(cards.isEmpty()) continue;
            int one = bfs(src,cards.get(0)) + bfs(cards.get(0),cards.get(1))+2;
            int two = bfs(src,cards.get(1)) + bfs(cards.get(1),cards.get(0))+2;
            
            for(int i=0;i<2;i++)
                Board[cards.get(i).x][cards.get(i).y] = 0;
            
            ret = Math.min(ret,one+permutate(cards.get(1)));
            ret = Math.min(ret,two+permutate(cards.get(0)));
            
            for(int i=0;i<2;i++)
                Board[cards.get(i).x][cards.get(i).y] = num;
        }
        if(ret == INF) return 0;
        return ret;
    }
    public int solution(int[][] board, int r, int c) {
        Board = board;
        return permutate(new Point(r,c,0));
    }
}
