//7.블록 이동하기_정답 코드
import java.util.*;

class Solution {
    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;
    
    int[][] D = {{-1,0},{1,0},{0,-1},{0,1}};
    //회전해서 이동하는 좌표에 대한 델타 어레이 정의
    //첫번째 인덱스는 U/R/D/L를 의미!=>중심축 좌표U/R/D/L에 따라 이동할 좌표가 다르다..? DU일 때, 시계방향인 경우 RL로 바뀜. 이 때 (x,y)=>(x+1,y+1)
    //그리고 반시계 방향/시계 방향 구분 위해 차원 더 추가
    int[][][] Drot = {{{1,1},{1,-1},{-1,-1},{-1,1}},//시계
                     {{1,-1},{-1,-1},{-1,1},{1,1}}};//반시계
    //회전시킬 때 ⭐️0인지 확인하기 위한 델타 어레이 정의!
    int[][][] Dcor = {{{-1,1},{1,1},{1,-1},{-1,-1}},//시계
                     {{-1,-1},{-1,1},{1,1},{1,-1}}};//반시계
    class Point{
        Point(int x,int y,int dir,int time){
            this.x = x; this.y = y; this.dir = dir; this.time = time;
        }
        int x, y, dir, time;
    }
    int[][] Board;
    int N;
    Queue<Point[]> Q = new LinkedList<>();//Point를 2개씩 저장해야하기 때문에 Point타입의 배열로.
    boolean[][][] Visited = new boolean[100][100][4];
        
    boolean isValid(Point[] pt){
        //2가지 좌표를 아래 내용 모두 확인해야함.
        //1.인덱스 유효성 2. 배열값 유효성 3. 방문체크 유효성(중복 방문 방지)
        for(int i=0;i<2;i++){
            int x = pt[i].x, y = pt[i].y, dir = pt[i].dir;
            if(x<0 || x>N-1 || y<0 || y>N-1) return false;
            if(Board[x][y] == 1) return false;
            if(Visited[x][y][dir] == true) return false;
        }
        return true;
    }
    int rotate(Point[] cur,int ccw,int idx){//목적지에 도달했을 때 0이 아닌 값을 반환~
        Point[] next = new Point[2];
        int a = idx, b = (idx+1)%2;//a = idx를 회전축, b를 이동시킴. b = 1-idx로 해도 될 것 같음.
        
        //1.회전축 좌표 a
        //회전축은 좌표 바뀌지 않고, 방향만 바뀜.=>시계 방향 회전시 회전축에 해당하는 값이 하나씩 증가.
        //시계 방향 : 1씩 더해지고, 반시계 방향 : -1=>+3 양수로 바꿈. 즉, ccw(방향)에 따라 더해지는 값이 다르기 때문에 삼항 연산자로 구현~
        int dir = cur[a].dir;
        next[0] = new Point(cur[a].x,cur[a].y,
                            (cur[a].dir+( ccw == 0?1:3))%4,cur[a].time+1);
        //2.이동하는 좌표 b : 델타어레이 Drot 하나 더 생성
        next[1] = new Point(cur[b].x+Drot[ccw][dir][0],cur[b].y+Drot[ccw][dir][1],
                            (cur[b].dir+ (ccw == 0?1:3))%4,cur[b].time+1);
        //전체적으로 다음 이동 상태 객체 만들 때 : 방향 정보도 갱신
        
        if(isValid(next) == false) return 0;
        
        //회전할 때 ⭐️칸이 0인지 확인 => 방향에 따라 확인할 칸이 다름~!=>델타어레이 하나 더 정의
        if(Board[cur[a].x+Dcor[ccw][dir][0]][cur[a].y+Dcor[ccw][dir][1]] == 1) return 0;
        
        //방문 체크 후 큐에 삽입~
        for(int i=0;i<2;i++){
            if(next[i].x == N-1 && next[i].y == N-1) return next[i].time;
            Visited[next[i].x][next[i].y][next[i].dir] = true;
        }
        Q.add(new Point[]{next[0],next[1]});
        return 0;
    }
    
    public int solution(int[][] board) {
        Board = board;
        N = board.length;
        Q.add(new Point[]{new Point(0,0,RIGHT,0),new Point(0,1,LEFT,0)});
        Visited[0][0][RIGHT] = true; Visited[0][1][LEFT] = true;
        Point[] cur = new Point[2]; Point[] next = new Point[2];//이동 상태 = Point타입의 배열2개
        while((cur = Q.poll()) != null){//while(!q.isEmpty())로 하면 안 되나?초반에는 시작좌표 하나 넣었기 때문에 null이 아니기 때문에 반복문 들어옴~
            //1.현재 방향으로 상하좌우 이동
            for(int j=0;j<4;j++){
                for(int i=0;i<2;i++){//이동할 좌표 : 2개이기 때문에
                    next[i] = new Point(cur[i].x+D[j][0],cur[i].y+D[j][1],
                                       cur[i].dir,cur[i].time+1);
                }
                //유효성 검사
                if(isValid(next) == false) continue;
                //방문 체크
                for(int i=0;i<2;i++){
                    //정답 체크
                    if(next[i].x == N-1 && next[i].y == N-1) return next[i].time;
                    Visited[next[i].x][next[i].y][next[i].dir] = true;
                }
                //큐에 삽입
                Q.add(new Point[]{next[0],next[1]});
            }
            //2.회전
            for(int ccw = 0;ccw<2;ccw++){//ccw:count clock wise, ccw=0:시계, ccw=0:반시계 
                for(int i=0;i<2;i++){//회전 축 또한 2가지가 가능!!!
                    int ret = rotate(cur,ccw,i);//현재 상태, 회전할 방향 정보, 회전축
                    if(ret != 0) return ret;
                }
            }
        }
        //unreachable : 항상 도달하는 경우만 준다고 했으므로 
        return 0;
    }
    
}