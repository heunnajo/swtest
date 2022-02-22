//7.블록 이동하기
class Solution {
    static final int INF = 100000;//입력 값에 따라 충분히 크게 10만 정도로 한다.
    int Answer = INF;
    int[][] Board;
    int[][][] DP;
    int N;
    public int solution(int[][] board) {
        //변수 초기화
        N = board.length;
        Board = new int[N][N];
        DP = new int[N][N][2];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++){
                Board[i][j] = board[i][j];
                DP[i][j][0] = DP[i][j][1] = INF;
            } 
        }
        
        go(0,0,0,0);
        
        return Answer;
    }
    int go(int x,int y,int dir,int cnt){
        //1.종료 조건 : (N-1,N-1)에 도달한 경우!
        if(x == N-1 && y == N-1) {
            Answer = Math.min(Answer,cnt);
            return DP[x][y][dir] = Answer;
        }
        if(DP[x][y][dir] != INF) return Math.min(DP[x][y][dir],cnt);
        //2.현재 이동, 다음 이동(재귀 호출)
        //2-1.현재 가로 방향인 경우(dir = 0)
        //가로 방향으로 이동
        if(y+1<N && Board[x][y+1] == 0){
            //DP[x][y+1][dir] = go(x,y+1,dir,cnt+1);
            DP[x][y][dir] = go(x,y+1,dir,cnt+1);
        } 
        //시계 방향으로 회전
        if(x+1<N && y-1>0 && Board[x+1][y] == 0 && Board[x+1][y] == 0 && Board[x+1][y-1] == 0) {
            //DP[x+1][y-1][1-dir] = go(x+1,y-1,1-dir,cnt+1);
            DP[x][y][dir] = go(x+1,y-1,1-dir,cnt+1);
        }
        //반시계 방향으로 회전
        if(x+1<N && Board[x+1][y] == 0 && Board[x+1][y-1] == 0 && Board[x-1][y] == 0) {
            //DP[x+1][y][1-dir] = go(x+1,y,1-dir,cnt+1);
            DP[x][y][dir] = go(x+1,y,1-dir,cnt+1);
        }
        //2-2.세로 방향인 경우
        //세로 방향으로 이동
        if(x+1<N && Board[x+1][y] == 0) {
            //DP[x+1][y][dir] = go(x+1,y,dir,cnt+1);
            DP[x][y][dir] = go(x+1,y,dir,cnt+1);
        }
        
        //시계 방향으로 회전:x값은 그대로, y만 -1
        if(x-1>0 && y-1>0 && Board[x][y-1] == 0 && Board[x-1][y-1] == 0) {
            //DP[x][y-1][1-dir] = go(x,y-1,1-dir,cnt+1);
            DP[x][y][dir] = go(x,y-1,1-dir,cnt+1);
        }
        //반시계 방향으로 회전
        if(x-1>0 && y+1<N && Board[x][y+1] == 0 && Board[x-1][y+1] == 0) {
            //DP[x-1][y+1][1-dir] = go(x-1,y+1,1-dir,cnt+1);
            DP[x][y][dir] = go(x-1,y+1,1-dir,cnt+1);
        }
        return DP[x][y][dir];
    }
}