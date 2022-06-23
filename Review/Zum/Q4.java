//programmers 퍼즐 조각 채우기st
import java.util.*;
class Solution {
    int N;
    int[][] Grid;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    int[] ans = new int[2];//ans[0] : 크기, ans[1] : 갯수

    class Pair{
        int x,y;
        Pair(int x,int y){
            this.x = x; this.y = y;
        }
    }
    public int[] solution(int[][] grid) {
        N = grid.length;

        Grid = new int[N][N];

        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++) Grid[i][j] = grid[i][j];


        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                bfs(i,j);//도형 만들고, 유효성 판단.
            }
        }

        return ans;
    }
    void bfs(int sx,int sy){
        Queue<Pair> q = new LinkedList<>();
        boolean[][] v = new boolean[N][N];
        q.add(new Pair(sx,sy));
        v[sx][sy] = true;
        int val = Grid[sx][sy];
        LinkedList<Pair> list = new LinkedList<>();
        int minX = Integer.MAX_VALUE; int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE; int maxY = Integer.MIN_VALUE;

        while(!q.isEmpty()){
            Pair cur = q.poll();

            list.add(new Pair(cur.x,cur.y));
            minX = minX>cur.x?cur.x:minX; maxX = maxX<cur.x?cur.x:maxX;
            minY = minY>cur.y?cur.y:minY; maxY = maxY<cur.y?cur.y:maxY;

            for(int d=0;d<4;d++){
                int nx = cur.x+dx[d], ny = cur.y+dy[d];
                if(isOut(nx,ny) || v[nx][ny]) continue;
                if(Grid[nx][ny] != val) continue;

                q.add(new Pair(nx,ny));
                v[nx][ny] = true;
            }
        }
        makeBlock(minX,maxX,minY,maxY,list);
    }
    void makeBlock(int minX,int maxX,int minY,int maxY,LinkedList<Pair> list){
        int rowSize = maxX-minX+1; int colSize = maxY-minY+1;
        int n = Math.min(rowSize,colSize);
        int[][] block = new int[rowSize][colSize];

        for(Pair p:list){
            block[p.x-minX][p.y-minY] = 1;
        }
        //block 배열로 유효성 검사:4가지 방향으로 검사
        boolean isValid = false;
        for(int d=0;d<4;d++){
            block = rotate(block);
            isValid = check(n,block);
            if(isValid) break;
        }
        if(isValid) {//최대 크기를 저장!
            if(ans[0]<n){
                ans[0] = n; ans[1] = 1;
            } else if(ans[0] == n){
                ans[1]++;
            }
        }
    }
    boolean check(int n,int[][] arr){
        int R = arr.length, C = arr[0].length;

        int y = C-1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][y-j] != 1) return false;
            }
            y--;

        }
        return true;
    }
    boolean isOut(int x,int y){
        return x<0 || x>=N || y<0 || y>=N;
    }
    int[][] rotate(int[][] arr){
        int R = arr.length, C = arr[0].length;
        int[][] newArr = new int[C][R];

        for(int i=0;i<C;i++){
            for(int j=0;j<R;j++){
                newArr[j][C-1-i] = arr[i][j];
            }
        }
        return newArr;
    }
}
// for(int i=0;i<N;i++){
//             for(int j=0;j<N;j++){
//                 System.out.print(Grid[i][j]+" ");
//             }
//             System.out.println();
//         }