//2. 거리두기 확인하기
import java.util.*;
class Solution {
    char[][] room;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    class Point{
        int x,y,dist;
        Point(int x,int y,int dist){
            this.x = x; this.y = y; this.dist = dist;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0;i<5;i++){
            if(check(places[i])) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }
    boolean check(String[] place){
        room = new char[5][5];
        for(int i=0;i<5;i++){
            room[i] = place[i].toCharArray();
        }
        //2차원 배열 bfs 탐색 수행!
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                
                if(room[i][j] == 'P') {
                    boolean flag = bfs(i,j);
                    if(!flag) return false;//한번 false가 나오면 더 진행하지 않고 바로 false를 리턴!
                }
            }
        }
        return true;//여기까지 왔다는건 거리두기 만족한다는 의미이므로 true를 리턴!
    }
    boolean bfs(int sx,int sy){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        q.add(new Point(sx,sy,0)); visited[sx][sy] = true;
        
        //while(!q.isEmpty()){
            //int size = q.size();
            
        for(int cnt=0;cnt<2;cnt++){
            int size = q.size();
            for(int i=0;i<size;i++){
                Point cur = q.poll();
                if(cur.dist == 3) break;//탐색을 종료:그냥 바로 true리턴해도 됨
            
                for(int d=0;d<4;d++){
                    int nx = cur.x + dx[d], ny = cur.y + dy[d];
                    if(isOut(nx,ny) || visited[nx][ny] || room[nx][ny] == 'X') continue;

                    if(room[nx][ny] == 'P') return false;
                    q.add(new Point(nx,ny,cur.dist+1));
                    visited[nx][ny] = true;
                }
            }
            
        }
        return true;
    }
    boolean isOut(int x,int y){
        return x<0 || x>4 || y<0 || y>4;
    }
}
 // for(int i=0;i<5;i++){
        //     for(int j=0;j<5;j++){
        //         System.out.print(room[i][j]+ " ");
        //     }
        //     System.out.println();
        // }