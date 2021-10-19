import java.util.*;
class CheckSocialDistance_Solution {
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
    //1.메인 메서드
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0;i<5;i++){
            if(check(places[i])) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }
    //2.check메서드 구현
    boolean check(String[] place){//place는 각 대기실을 의미한다! String 배열
        for(int i=0;i<5;i++){//5행
            for(int j=0;j<5;j++){//5열
                if(place[i].charAt(j) == 'P'){//P인 좌표에 대해서 bfs 수행한다.
                    if(bfs(place,i,j) == false) return false; 
                }
            }
        }
        return true;
    }
    boolean isOut(int x,int y){
        return x<0 || x>=5 || y<0 || y>=5;
    }
    //3.bfs 구현:맨해튼 거리 계산법으로 2인 칸까지만 탐색하면 됨! 맨해튼 거리 2이내에 P가 있다면 false를 리턴!!
    boolean bfs(String[] str, int row,int col){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        q.add(new Point(row,col,0));
        visited[row][col] = true;
        
        while(!q.isEmpty()){
            Point cur = q.remove();
            //if(cur.dist == 3) return true;//길이가 2인 노드 다 탐색한후 큐에 든 dist가 3인 노드
            if(cur.dist > 2) continue;//길이가 2인 노드 다 탐색한후 큐에 든 dist가 3인 노드
            if(cur.dist != 0 && str[cur.x].charAt(cur.y) == 'P') return false;//큐에서 꺼낸 객체 cur로 조건 판단한다!
            for(int dir=0;dir<4;dir++){
                int nx = cur.x+dx[dir],ny = cur.y+dy[dir];
                if(isOut(nx,ny)||visited[nx][ny]) continue;
                if(str[nx].charAt(ny) == 'X') continue;
                
                //if(str[nx].charAt(ny).equals('P')) return false;
                visited[nx][ny] = true;
                q.add(new Point(nx,ny,cur.dist+1));
            }
        }
        return true;
    }
}