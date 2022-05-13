//거리두기 문제  변형
import java.util.*;
class Solution {
    int ans,size;
    char[][] map;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    boolean[][] v;

    class Pos{
        int x,y,dist;
        Pos(int x,int y,int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public int solution(String[] seat) {
        ans = 0;
        size = seat.length;
        v = new boolean[size][size];

        map = new char[size][size];
        for(int i=0;i<size;i++){
            map[i] = seat[i].toCharArray();
        }

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(map[i][j] == 'C'){
                    bfs(i,j);//(i,j)에서 bfs 수행, ans 증가
                }
            }
        }
        //printList();
        return ans;
    }
    // void printList(){
    //     System.out.println("인접 좌표 출력");
    //     for(Pos p:list){
    //         System.out.println("("+p.x+","+p.y+")");
    //     }
    // }
    //LinkedList<Pos> list = new LinkedList<>();
    void bfs(int sx,int sy){
        // if(sx == 2 && sy == 2){
        //     System.out.println("bfs 시작");
        //     System.out.println("sx: "+sx+","+"sy:"+sy);
        // }

        Queue<Pos> q = new LinkedList<>();
        v[sx][sy] = true;
        q.add(new Pos(sx,sy,0));

        while(!q.isEmpty()){
            Pos cur = q.poll();
            if(cur.dist == 3) return;
            for(int d=0;d<4;d++){
                int nx = cur.x+dx[d], ny = cur.y+dy[d];

                if(isOut(nx,ny) || v[nx][ny]) continue;
                if(map[nx][ny] == 'C') continue;//방문X

                //거리값, 배열값 유효성 판단 후 ans값 증가, 다음칸 이동
                if(cur.dist+1 <= 2 && map[nx][ny] == 'M') {
                    ans++;
                    q.add(new Pos(nx,ny,cur.dist+1));
                    v[nx][ny] = true;
                    continue;
                }
                if(cur.dist+1 <= 3 && map[nx][ny] == 'N') {
                    ans++;
                    q.add(new Pos(nx,ny,cur.dist+1));
                    v[nx][ny] = true;
                    continue;
                }
                if(cur.dist+1<=3 && map[nx][ny] != 'N') {
                    q.add(new Pos(nx,ny,cur.dist+1));
                    continue;
                }
                //다음칸이 일반 칸이라도 이동해야할 것.
                q.add(new Pos(nx,ny,cur.dist+1));
                    v[nx][ny] = true;
            }
        }
    }
    boolean isOut(int x,int y){
        return x<0 || x>=size || y<0 || y>=size;
    }
}