//bfs
import java.util.*;

class Solution {
    boolean[][] v;
    LinkedList<Integer> lakeSize;
    Set<Integer> set;
    int size, Row, Col;
    int[][] Map;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    class Pair{
        int x,y;

        Pair(int x,int y){
            this.x = x; this.y = y;
        }
    }
    public int[] solution(int rows, int columns, int[][] lands) {

        Row = rows; Col = columns;
        Map = new int[Row][Col];

        v = new boolean[Row][Col];
        set = new HashSet<>();
        for(int i=0;i<lands.length;i++){//[0] : 행 [1] : 열
            Map[lands[i][0]-1][lands[i][1]-1] = 1;
        }

        //print();
        bfs(0,0);//이 때는 크기를 따로 기록X

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(Map[i][j] == 0 && !v[i][j]){

                    bfs(i,j);
                    set.add(size);
                }
            }
        }

        int[] answer = new int[2];
        if(set.size() >0){
            lakeSize = new LinkedList<>(set);
            for(int SIZE: lakeSize) System.out.print(SIZE+" ");
            System.out.println();

            Collections.sort(lakeSize);



            answer[0] = lakeSize.get(0);
            answer[1] = lakeSize.get(lakeSize.size()-1);
        } else{
            answer[0] = answer[1] = -1;
        }

        return answer;
    }
    void bfs(int sx,int sy){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sx,sy));
        v[sx][sy] = true;
        size = 1;

        while(!q.isEmpty()){
            Pair cur = q.poll();

            for(int d=0;d<4;d++){
                int nx = cur.x+dx[d]; int ny = cur.y + dy[d];
                if(isOut(nx,ny) || v[nx][ny]) continue;
                if(Map[nx][ny] == 1) continue;

                q.add(new Pair(nx,ny));
                v[nx][ny] = true;
                size++;
            }
        }
    }
    boolean isOut(int x,int y){
        return x<0 || x>= Row || y<0 || y>= Col;
    }
    void print(){
        for(int i=0;i<Row;i++){
            for(int j=0;j<Col;j++){
                System.out.print(Map[i][j]+" ");
            }
            System.out.println();
        }
    }
}