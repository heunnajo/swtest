import java.util.*;
class CheckSocialDistance_BFS_2nd_wrong {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static class Point{
        int x,y,d;
        Point(int x,int y,int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0;i<5;i++){
            //거리둑기 지켜지면 1, 아니면 0을 저장
            if(bfs(places[i])) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }
    static boolean bfs(String[] tmp){
        char[][] room = new char[5][5];
        for(int i=0;i<5;i++){//5줄
            room[i] = tmp[i].toCharArray();
        }
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(room[i][j] == 'P') {
                    q.add(new Point(i,j,0));
                    visited[i][j] = true;
                }
            }
        }
        while(!q.isEmpty()){
            Point cur = q.remove();
            //if(cur.d == 3) return true;
            for(int d=0;d<4;d++){
                int nx = cur.x+dx[d], ny = cur.y+dy[d];
                if(isOut(nx,ny) || visited[nx][ny]) continue;
                if(room[nx][ny] == 'X') continue;
                
                if(cur.d+1<=2 && room[nx][ny]=='P') return false;
                q.add(new Point(nx,ny,cur.d+1));
                visited[nx][ny] = true;
            }
        }
        return true;
    }
    static boolean  isOut(int x,int y){
        return x<0 || x>4 || y<0 || y>4;
    }
}
//2차원 char 배열로 변환 성공!
// for(int i=0;i<5;i++){//5줄
//             for(int j=0;j<5;j++){
//                 System.out.print(cur[i][j]+" ");
//             }
//             System.out.println();
//         }

//             char[][] curr = new char[5][5];
//             curr[i] = tmp[i].toCharArray();
            
//             for(int j=0;j<5;j++){
//                 System.out.print(curr[j]+" ");
//             }
//             System.out.println();