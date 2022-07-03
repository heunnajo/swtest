//simulation
class Solution {
    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};
    int N,ans[][];
    boolean H;
    boolean isOut(int x,int y){
        return x<0 || x>=N || y<0 || y>=N;
    }
    public int[][] solution(int n, boolean horizontal) {
        N = n;
        H = horizontal;

        ans = new int[n][n];

        solve();

        return ans;
    }
    void solve(){
        int amt = 1;
        int dir = 1;
        int num = 1;
        int x = 0; int y = 0;
        ans[x][y] = num++;

        if(H){//수평
            while(true){
                for(int i=0;i<amt;i++){
                    x += dx[dir]; y += dy[dir];
                    ans[x][y] = num++;
                }
                //방향 전환
                dir = (dir+1)%4;
                int nx = x+dx[dir]; int ny = y+dy[dir];
                if(ans[nx][ny] != 0) dir = (dir+2)%4;

                amt++;

                nx = x+dx[dir]; ny = y+dy[dir];
                if(ans[nx][ny] != 0 && isOut(nx,ny)) break;
            }


        } else{

        }
    }
}