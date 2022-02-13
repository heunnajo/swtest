//6. 카드 짝 맞추기
import java.util.*;
class Solution {
    int INF = 987654321;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    int[][] Board;
    StringBuilder sb = new StringBuilder();
    class Point{
        int x,y,cnt;
        Point(int x,int y,int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    public int solution(int[][] board, int r, int c) {
        //변수 초기화, 깊은 복사
        //Board = new int[4][4];
        //for(int i=0;i<4;i++) for(int j=0;j<4;j++) Board[i][j] = board[i][j];
        Board = board;
        int answer = permu(new Point(r,c,0));
        return answer;
    }
    int permu(Point st){
        int ret=INF;
        for(int num =1; num<=6;num++){
            LinkedList<Point> list= new LinkedList<>();
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(Board[i][j] == num){
                        list.add(new Point(i,j,0));
                        
                    }
                }
            }
            if(list.size()==0) continue;//다음 num으로 컨티뉴처리
            //0,1번 방문  순서 정하고 각각마다 bfs 수행, 재귀호출&실행으로 그 때의 방문 횟수 계산
            
            int one = bfs(st,list.get(0))+bfs(list.get(0),list.get(1))+2;
            int two = bfs(st,list.get(1))+bfs(list.get(1),list.get(0))+2;
            //재귀함수로 카드 6개 모든 쌍을 방문!
            //현재 num 위치 방문 처리
            for(int i=0;i<2;i++){
                Point cur = list.get(i);
                Board[cur.x][cur.y] = 0;
            }
            //2가지 중 현재위치에서의 버튼 조작횟수 최솟값을 ret에 저장!
            //ret = one + permu(list.get(1));
            ret = Math.min(ret,one+permu(list.get(1)));
            ret = Math.min(ret,two+permu(list.get(0)));
            //재귀 리턴 후 원복 처리
            for(int i=0;i<2;i++){
                Point cur = list.get(i);
                Board[cur.x][cur.y] = num;
            }
        }
        if(ret == INF) {
            return 0;
            //sb.append("\n");//카드 선택을 끝낸 곳이라고 생각.
        }
        return ret;
    }
    int bfs(Point st,Point dest){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        visited[st.x][st.y] = true;
        q.add(st);
        while(!q.isEmpty()){
            Point cur = q.poll();
            if(cur.x == dest.x && cur.y == dest.y){
                return cur.cnt;
            }
            for(int d=0;d<4;d++){
                int nx = cur.x+dx[d],ny = cur.y+dy[d];
                //if(isOut(nx,ny) || visited[nx][ny]) continue;
                if(isOut(nx,ny)) continue;
                
                //1.일반 상하좌우 이동
                q.add(new Point(nx,ny,cur.cnt+1));
                //visited[nx][ny] = true;
                
                //2.ctrl+상하좌우 이동
                for(int i=0;i<2;i++){
                    if(Board[nx][ny] != 0) break;//카드에 도달했다면 반복 이동 멈춤!
                    //범위체크,방문체크 후 이동
                    if(isOut(nx+dx[d],ny+dy[d])) break;//현재 방향으로 범위초과한다면 반복 멈춤!
                    nx += dx[d]; ny += dy[d];
                }
                //이동할 만큼 이동하고 다음 이동할 좌표로 처리:큐삽입&방문체크!
                //if(!visited[nx][ny]){
                    //visited[nx][ny] = true;
                    q.add(new Point(nx,ny,cur.cnt+1));
                //}
            }
        }
        return INF;
    }
    boolean isOut(int x,int y){
        return x<0 || x>3 || y<0 || y>3;
    }
}
