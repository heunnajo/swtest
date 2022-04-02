
//2.물음표 완전 탐색
import java.util.*;
class Q2 {
    char[][] Grid, tmp;
    int row,col;
    int ans;
    class Point{
        int x, y;
        Point(int x,int y){
            this.x = x; this.y = y;
        }
    }
    Point[] qPos;
    int qTotalCnt;
    boolean[] check;
    int[] order;
    int[] dx = {-1,1,0,0}; int[] dy = {0,0,-1,1};
    public int solution(String[] grid) {
        //0.변수  초기화
        int ans = 0;
        col = grid[0].length(); row = grid.length;
        Grid = new char[row][col];
        tmp = new char[row][col];
        qPos = new Point[9];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                tmp[i][j] = Grid[i][j] = grid[i].charAt(j);
            }
        }

        //1.물음표 위치 정보, 갯수 조회
        int idx = 0;qTotalCnt = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(Grid[i][j]=='?'){
                    qPos[idx++] = new Point(i,j);
                    qTotalCnt++;
                }
            }
        }
        check = new boolean[qTotalCnt];
        order = new int[qTotalCnt];
        //System.out.println("?의 총 갯수: "+qTotalCnt);
        //2.선택하는 순서 정한 후 qTotalCnt 개의 물음표 완전 탐색
        getOrder(0);
        return ans;
    }
    void getOrder(int idx){
        //1.종료 조건
        if(idx == qTotalCnt){
            go(0);
            return;
        }
        //2.현재 경우 선택, 다음 경우 호출
        for(int i=0;i<qTotalCnt;i++){
            if(check[i]) continue;
            order[idx] = i;
            getOrder(idx+1);
        }
    }
    void go(int idx){
        //1.종료 조건
        if(idx == qTotalCnt){
            //System.out.println("*****   최종 tmp 출력     *****");
            //printTmp();
            if(isConnected()) ans++;
            return;
        }
        //2.현재 경우 선택, 다음 경우 호출

        Point cur = qPos[order[idx]];
        for(int d = 0;d<4;d++){
            int nx = cur.x+dx[d], ny = cur.y+dy[d];
            if(isOut(nx,ny)) continue;
            tmp[cur.x][cur.y] = Grid[nx][ny];
            go(idx+1);

            //System.out.println("*****   현재 깊이에서 tmp 출력     *****");
            //printTmp();
        }
        //tmp[cur.x][cur.y] = '?';//원복?!
    }
    void printTmp(){

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print(tmp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    boolean isOut(int x,int y){
        return x<0 || x>=row || y<0 || y>=col;
    }
    boolean isConnected(){
        //2차원 배열 탐색, 조건 판단

        //1.사용된 알파벳 갯수 조회
        int usedAlpha = 0;

        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                map.put(tmp[i][j],1);
            }
        }

        usedAlpha = map.size();
        //System.out.println("usedAlpha: "+usedAlpha);
        //2.BFS 탐색하여 섬의 갯수 조회
        int cnt = 0;//전체 섬 갯수
        Queue<Point> q = new LinkedList<>();
        boolean[][] v = new boolean[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(!v[i][j]){//(i,j) 아직 미방문했다면 인접노드 탐색!(BFS수행!)
                    q.add(new Point(i,j)); cnt++;
                    while(!q.isEmpty()){
                        Point cur = q.remove();

                        for(int d=0;d<4;d++){
                            int nx = cur.x+dx[d],ny = cur.y+dy[d];
                            if(isOut(nx,ny) || v[nx][ny]) continue;
                            if(tmp[nx][ny] != tmp[cur.x][cur.y]) continue;

                            q.add(new Point(nx,ny));
                            v[nx][ny] = true;
                        }
                    }
                }
            }
        }
        //3.1 == 2 만족하면 true를 리턴
        if(cnt == usedAlpha) return true;
        else return false;
    }
}
// for(int i=0;i<len;i++){
//             for(int j=0;j<len;j++){
//                 System.out.print(Grid[i][j] + " ");
//             }
//             System.out.println();
//         }