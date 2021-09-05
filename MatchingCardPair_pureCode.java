import java.util.*;
class MatchingCardPair_pureCode {
    class Point{
        Point(int r,int c,int t){
            row = r;
            col = c;
            cnt = t;
        }
        int row,col,cnt;
    }
    static final int INF = 987654321;
    int[][] Board;
    static final int[][] D = {{-1,0},{1,0},{0,-1},{0,1}};
    int bfs(Point src,Point dst){//출발지 src와 목적지 dst
        boolean[][] visited = new boolean[4][4];
        Queue<Point> q = new LinkedList<>();
        q.add(src);
        while(!q.isEmpty()){
            Point curr = q.poll();
            if(curr.row == dst.row && curr.col == dst.col){
                return curr.cnt;
            }
            for(int i=0;i<4;i++){
                int nx = curr.row + D[i][0], ny = curr.col + D[i][1];
                if(nx<0 || nx>3 ||ny<0 || ny>3) continue;
                
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny,curr.cnt+1));//조작횟수 1 증가.
                }
                //[Ctrl] + 방향키 처리
                for(int j=0;j<2;j++){//어차피 위에서 범위체크하는 것이 있다.
                    if(Board[nx][ny] != 0) break;//숫자 카드라면 더 이동X이므로 이동 반복 종료.
                    if(nx + D[i][0] <0 || nx + D[i][0] > 3
                      || ny +D[i][1]<0 || ny + D[i][1] > 3) break;
                    //이까지 왔다는 것은 이동할 수 있다는 뜻:한번 더 이동시킨다=>한칸 더 이동시킨다?!
                    nx += D[i][0];
                    ny += D[i][1];
                    //j-for문으로 한번더 반복해서 이동한 칸에서 다음 칸으로 더 이동가능한지 조건 검사하고 더 이동.
                }
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny,curr.cnt+1));//조작횟수 1 증가.
                }
            }
        }
        return INF;//이까지 오는 경우는 없겠지만.
    }
    int permutate(Point src){
        int ret = INF;
        for(int num=1;num <= 6;num++){
            List<Point> card = new ArrayList<>();
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(Board[i][j] == num){
                        card.add(new Point(i,j,0));
                    }
                }
            }
            if(card.isEmpty()) continue;
            
            int one = bfs(src,card.get(0)) + bfs(card.get(0),card.get(1)) + 2;
            int two = bfs(src,card.get(1)) + bfs(card.get(1),card.get(0)) + 2;
            //카드 2개 선택후 0으로 처리함으로써 삭제 처리
            for(int i=0;i<2;i++)
                Board[card.get(i).row][card.get(i).col] = 0;
            ret = Math.min(ret,one + permutate(card.get(1)));//재귀호출.one 경우의 수 선택시
            ret = Math.min(ret,two + permutate(card.get(0)));//재귀함수.two 경우의 수 선택시
            //아래 for문은 복원처리. 0으로 지웠던 것을 다시 숫자로 복원.
            for(int i=0;i<2;i++)
                Board[card.get(i).row][card.get(i).col] = num;
        }
        if(ret == INF) return 0;//0을 리턴한다는 것은 어떤 의미인가?
        return ret;
    }
    public int solution(int[][] board, int r, int c) {
        Board = board;
        return permutate(new Point(r,c,0));
    }
}