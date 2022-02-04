//7. 사라지는 발판
class Solution {
    int[][] map;
    int row,col;//board의 사이즈!
    boolean[][] v;
    int[] dy = {-1,1,0,0};//가로 세로 바뀜 주의~!
    int[] dx = {0,0,-1,1};
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        //1.변수 초기화!
        row = board.length; col = board[0].length;
        map = new int[row][col];
        v = new boolean[row][col];
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++) map[i][j] = board[i][j];
        
        //2.재귀함수 실행!solve(현플레이어의 위치,상대플레이어의 위치)
        int answer = solve(aloc[0],aloc[1],bloc[0],bloc[1]);
        return answer;
    }
    public int solve(int curx,int cury,int opx,int opy){
        //1.종료 조건
        if(v[curx][cury]) return 0;
        int ret = 0;
        //2.현재 이동
        for(int d=0;d<4;d++){
            int nx = curx+dx[d], ny = cury+dy[d];
            if(isOut(nx,ny) || map[nx][ny] == 0 || v[nx][ny]) continue;
            
            //현재->다음 : "현재칸"의 발판 삭제 처리
            v[curx][cury] = true;
            //3.다음 이동
            int val = solve(opx,opy,nx,ny)+1;
            
            //3-1.재귀 리턴 후 현재 이동 원복 처리
            v[curx][cury] = false;
            
            //4.현재 이동값에 따라 정답 갱신 based on 게임 이론
            //4-0.ret : 기존의 결과, val : 현재 계산한 결과
            //4-1.기존의 결과 = 패배, 현재 계산한 결과 = 승리 : 결과 val로 갱신!
            if(ret%2 == 0 && val%2 == 1) ret = val;
            //4-2.기존의 결과 = 승리, 현재 계산한 결과 = 승리 : 최소 횟수로 결과 갱신!
            else if(ret%2 == 1 && val%2 == 1) ret = Math.min(ret,val);
            //4-3.기존의 결과 = 패배, 현재 계산한 결과 = 승리 : 최대 횟수로 결과 갱신!
            else if(ret%2 == 0 && val%2 == 0) ret = Math.max(ret,val);
        }
        return ret;
    }
    public boolean isOut(int x,int y){
        return x<0 || x>=row || y<0 || y>=col;
    }
}
