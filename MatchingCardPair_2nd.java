import java.util.*;
class MatchingCardPair_2nd {
    static final int INF = 987654321;
    static int[][] Board = new int[4][4];
    static class Point{//x,y 좌표와 조작횟수(=거리값)을 저장한다!
        int x,y,cnt;
        Point(int x,int y,int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    //2차원 배열을 너비우선탐색으로 해서 start -> destination으로 가는 최소 이동 횟수(최소 조작 횟수) 구해본ㄴ다!
    static int bfs(Point start,Point dest){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        q.add(start);
        visited[start.x][start.y] = true;
        
        while(!q.isEmpty()){
            Point cur = q.remove();
            //도착지에 도착한 경우!
            if(cur.x == dest.x && cur.y == dest.y){
                return cur.cnt;
            }
            //도착지 못 찾은 경우1 : 상하좌우 탐색
            for(int d=0;d<4;d++){
                int nx = cur.x+dx[d],ny = cur.y+dy[d];
                if(check(nx,ny)) continue;
                
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny,cur.cnt+1));
                }
            //도착지 못 찾은 경우2 : Ctrl + 상하좌우 탐색
            //큐에 넣을 때 이동한 거리값은 현재 큐에서 꺼낸 노드의 cnt 기준 cnt+1하면 된다!
                for(int i=0;i<2;i++){//4x4크기이기 때문에 최돼 2번더 이동할 수 있음.
                    //1. 한번 더 이동하기 전에 먼저 숫자 찾은 경우 검사!
                    if(Board[nx][ny] != 0) break;//이동 반복을 종료. 숫자를 찾은 경우. 근데 이게 찾으려는 숫자가 맞나??
                    //2. 범위 초과 검사!
                    if(check(nx+dx[d],ny+dy[d])) break;
                    //d와 동일한 방향으로 범위가 넘지 않을 때까지 계속 반복해서 이동한다!!!
                    nx += dx[d];
                    ny += dy[d];
                }
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny,cur.cnt+1));
                }
            }
        }
        return INF;//최댓값을 리턴.목적지 좌표 못 찾는 경우.(거의 불가능한 경우)
    }
    static boolean check(int x,int y){//범위 벗어나면 true를 리턴!
        return x<0 || x>3 || y<0 || y>3;
    }
    //재귀함수로 순열을 생성한다.
    static int permutate(Point src){//매개변수는 순열을 만들 시작점, 리턴 : 최소 조작횟수를 리턴!?
        int ret = INF;
        //1.src에서 출발.num=1부터 6까지 숫자카드 위치 찾아서 선택하는 순서 정해야 한다!
        for(int num=1;num<=6;num++){
            //숫자마다 위치 저장을 위한 리스트 생성!
            List<Point> cards = new LinkedList<>();
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    //if(num == Board[i][j]){
                    if(Board[i][j] == num){
                        cards.add(new Point(i,j,0));
                    }
                }
            }
            if(cards.isEmpty())continue;//숫자가 삭제되어 없거나 해서 컬렉션이 비어있다면 다음 num으로 넘어간다!
            //컬렉션이 차있는 경우! 순열을 만든다~
            //선택1.
            int one = bfs(src,cards.get(0)) + bfs(cards.get(0),cards.get(1)) + 2;//조작횟수는 bfs로 이동하는 최소 횟수 + 2장의 카드 선택하는 엔터키 2번
            //선택2.
            int two = bfs(src,cards.get(1)) + bfs(cards.get(1),cards.get(0)) + 2;
            //카드 2장 선택했으면 삭제 처리.
            for(int i=0;i<2;i++){
                Board[cards.get(i).x][cards.get(i).y] = 0;
            }
            //선택1.순열 생성한 결과값:0번째 먼저 선택했으니 1번째부터 순열만드는 재귀호출하면 됨.
            ret = Math.min(ret,one + permutate(cards.get(1)));
            //선택2.순열 생성한 결과값
            ret = Math.min(ret,two + permutate(cards.get(0)));
            //원복 : 순열을 다 생성하고 리턴되면 원래의 숫자로 원복
            for(int i=0;i<2;i++){
                Board[cards.get(i).x][cards.get(i).y] = num;
            }
        }
        if(ret == INF) return 0;//연산을 실행하지 않은 경우.
        return ret;
    }
    public int solution(int[][] board, int r, int c) {
        Board = board;
        return permutate(new Point(r,c,0));//초기 시작점(r,c)와 초기조작횟수0을 넣는다.
    }
}