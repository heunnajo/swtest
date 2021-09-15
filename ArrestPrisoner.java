import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
public class ArrestPrisoner {
    static int T,N,M,R,C,L;
    static int[][] Map,Time;
    static int[][] Ternal = {{-100},//1번인덱스부터 터널 이동방향 저장!
            {0,1,2,3},//1:상하좌우 
            {0,1},//2:상하 
            {2,3},//3:좌우  
            {0,3},//4:상우 
            {1,3},//5:하우 
            {1,2},//6:하좌 
            {0,2}//7:상좌
            };
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] possibleTernal = {
            {6,5,2,1},
            {4,7,2,1},
            {4,5,3,1},
            {7,6,3,1}
    };
    static HashMap<Integer,int[]> possibleDir;
    static class Point{
        int x,y;
        Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    static void bfs() {//전역변수 Map을 BFS하면서 Time 배열을 채운다!
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new Point(R,C));
        Time[R][C] = 1;
         
        while(!q.isEmpty()) {
            Point cur = q.remove();
            int curTernal = Map[cur.x][cur.y];
            //현재 좌표의 배열값=터널의 모양.에 따라 다음 이동 방향과 좌표가 정해진다!
            for(int d=0;d<Ternal[curTernal].length;d++) {
                int nx = cur.x+dx[Ternal[curTernal][d]];
                int ny = cur.y+dy[Ternal[curTernal][d]];
                //범위 체크, 방문  체크, Map에 값에 따라 이동 못하는 경우  체크!
                if(outRange(nx,ny) || visited[nx][ny]) continue;
                if(Map[nx][ny] == 0) continue;
                if(!isConnected(Map[cur.x][cur.y],Ternal[curTernal][d],Map[nx][ny])) continue;//연결가능한지 검사.
                 
                //이동 가능한 방향의 좌표!
                q.add(new Point(nx,ny));
                visited[nx][ny] = true;
                Time[nx][ny] = Time[cur.x][cur.y]+1;
            }
        }
    }
    static boolean isConnected(int curTernal,int curDir,int nextTernal) {
        //연결 가능 정보 2차원 배열의 curDir 행의 각 열순회. 원소 각각 nextTernal과 같은지 확인함으로써
        //존재한다면 같은 것이 있다면 true를 리턴!
        for(int i=0;i<possibleTernal[curDir].length;i++) {//4번 반복하겠지만 형식상.
            if(possibleTernal[curDir][i] == nextTernal) return true;
        }
         
        return false;
    }
    static boolean outRange(int x,int y) {
        return x<0 ||x>=N || y<0 || y>=M;//x=행=N, y=열=M
    }
    static int getAns() {//L이하인 Time 배열 칸 갯수 카운팅 후 리턴!=정답 
        int sum = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(Time[i][j] != 0 && Time[i][j]<=L) {
                    sum++;
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
         
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());//따로 1뺄 필요 없음.
            N = Integer.parseInt(st.nextToken());//세로=행!
            M = Integer.parseInt(st.nextToken());//가로=열!
            R = Integer.parseInt(st.nextToken());//1씩 안 빼도 됨.
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
             
            Map = new int[N][M];
            Time = new int[N][M];
             
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++) {
                    Map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            bfs();
             
            sb.append("#"+t+" "+getAns()+"\n");
 
        }
        System.out.println(sb);
         
        br.close();
    }
 
}