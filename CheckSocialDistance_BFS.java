import java.util.*;
class CheckSocialDistance_BFS {
    static class Point{
        int x,y,dist;
        Point(int x,int y,int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0;i<5;i++){
            if(check(places[i])) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }
    boolean check(String[] str){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(str[i].charAt(j) == 'P'){
                    if(!bfs(str,i,j)) return false;
                }
            }
        }
        return true;
    }
    boolean bfs(String[] str,int sx,int sy){//각 대기실을 의미하는 1차원 String 배열에 대해 연산 수행, 전역변수X이므로 매개변수로 전달받아서 수행한다~!
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        q.add(new Point(sx,sy,0));
        visited[sx][sy] = true;
        
        while(!q.isEmpty()){
            Point cur = q.remove();
            if(cur.dist == 3) return true;
            for(int dir=0;dir<4;dir++){
                int nx = cur.x+dx[dir],ny = cur.y+dy[dir];
                if(isOut(nx,ny) || visited[nx][ny]) continue;
                if(str[nx].charAt(ny) == 'X') continue;
                
                if(cur.dist+1<3 && str[nx].charAt(ny) == 'P') return false;//유효범위 이내인데 P가 나온다면 바로 false를 리턴!
                visited[nx][ny] = true;
                q.add(new Point(nx,ny,cur.dist+1));
            }
        }
        return true;
    }
    boolean isOut(int x,int y){
        return x<0 || x>4 || y<0 || y>4;
    }
}