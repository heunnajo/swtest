//섬의 크기 오름차순 출력
import java.util.*;
class Solution {
    char[][] input;
    int row, col;
    boolean[][] v;
    HashSet<Integer> set;
    int curSize;//각 섬의 크기를 저장
    class Point{
        int x,y;
        Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    public int[] solution(String[] maps) {

        row = maps.length;
        col = maps[0].length();
        v = new boolean[row][col];
        set = new HashSet<>();

        input = new char[row][col];//전역변수
        for(int i=0;i<row;i++){
            input[i] = maps[i].toCharArray();
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(input[i][j] == '1' && !v[i][j]){
                    if(v[i][j]) continue;//이미 방문한 칸이면 컨티뉴 처리
                    bfs(i,j);//현재 섬의 크기curSize 카운팅
                    set.add(curSize);
                }
            }
        }
        System.out.println("size: "+set.size());
        int[] answer = new int[set.size()];
        int idx = 0;
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            answer[idx++] = it.next();
        }

        Arrays.sort(answer);
        return answer;
    }
    void bfs(int x,int y){
        curSize = 1;
        //다음 칸이 1이면 크기curSize 1씩 증가!
        Queue<Point> q = new LinkedList<>();
        v[x][y] = true;
        q.add(new Point(x,y));

        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int d=0;d<4;d++){
                int nx = cur.x+dx[d], ny = cur.y+dy[d];
                if(isOut(nx,ny) || v[nx][ny]) continue;
                if(input[nx][ny] == '0') continue;

                q.add(new Point(nx,ny));
                v[nx][ny] = true;
                curSize++;

            }
        }
    }
    boolean isOut(int x,int y){
        return x<0 || x>=row || y<0 || y>=col;
    }

}

// for(int i=0;i<row;i++){
//             for(int j=0;j<col;j++){
//                 System.out.print(input[i][j]+" ");
//             }
//             System.out.println();
//         }