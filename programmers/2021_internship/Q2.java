//2. 거리두기 확인하기
import java.util.*;
class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    String[][] Places;
    class Point{
        int x,y,dist;
        Point(int x,int y,int dist){
            this.x = x; this.y = y; this.dist = dist;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Places = new String[5][5];
        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                Places[i][j] = places[i][j];
                // for(int k=0;k<5;k++){
                //     System.out.print(Places[i][j].charAt(k)+" ");//POOOP 의 형태가 출력될 것!
                // }
                // System.out.println();
            }
            
        }
        
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                boolean flag = true;
                if(places[i][j].charAt(k) == 'p') flag = bfs(i,j,k);//i번 대기실, j번행, k번 열
                if(!flag) {
                    answer[i] = 0;//places[i] 대기실 bfs 결과 false: 더 탐색 필요X 결과 저장, 다음 대기실로 넘어감!
                    break;
                }
            }
            answer[i] = 1;//리턴되지 않고 5행 5열 대기실 다 탐색했다면 거리두기 O이므로1을 저장!
        }
        return answer;
    }
    boolean bfs(int idx,int sx,int sy){
        boolean[][] v = new boolean[5][5];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sx,sy,0));
        v[sx][sy] = true;
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                Point cur = q.poll();
                if(cur.dist == 3) break;//탐색을 종료
            
                for(int d=0;d<4;d++){
                    int nx = cur.x + dx[d], ny = cur.y + dy[d];
                    if(isOut(nx,ny) || v[nx][ny] || Places[idx][nx].charAt(ny) == 'X') continue;

                    //dist<3인데 P가 나오는 경우
                    if(Places[idx][nx].charAt(ny) == 'P') return false;
                    
                    q.add(new Point(nx,ny,cur.dist+1));
                    v[nx][ny] = true;
                }
            }
            
        }
        return true;//여기까지 왔다는 건 거리두기를 지키고 있다는 의미이므로 true를 리턴!
    }
    boolean isOut(int x,int y){
        return x<0 || x>4 || y<0 || y>4;
    }
}