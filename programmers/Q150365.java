//Q150365 미로 탈출 명령어
class Solution {
    int N,M,K,Sx,Sy,Ex,Ey;
    int[] dx = {1,0,0,-1};
    int[] dy = {0,-1,1,0};
    char[] dirChar = {'d','l','r','u'};
    int[] dirInfo;
    String Ans;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        N = n; M = m; Sx = x-1; Sy = y-1; Ex = r-1; Ey = c-1; K = k;
        if(K < getD(Sx,Sy,Ex,Ey)) return "impossible";
        if((K - getD(Sx,Sy,Ex,Ey)) % 2 != 0) return "impossible";
        
        Ans = "";
        dirInfo = new int[K];
        dfs(Sx,Sy,0);
        
        return Ans;
    }
    int getD(int sx,int sy,int ex,int ey){
        return (int)Math.abs(sx-ex) + (int)Math.abs(sy-ey);
    }
    void dfs(int x,int y,int depth){
        //0.backtracing
        if(!Ans.equals("")) return;
        //현재 깊이에서 도착점까지 남은거리값 계산, 백트랙킹 
        if(depth + getD(x, y, Ex, Ey) > K) return;

        //1.base case
        if(depth == K){
            //System.out.print("여긴 들어오니?");
            if(x == Ex && y == Ey){
                StringBuilder sb = new StringBuilder(K);
                for(int i=0;i<K;i++){
                    sb.append(dirChar[dirInfo[i]]);
                }
                Ans = sb.toString();
                //System.out.print(Ans);
            }
            return;
        }
        //2.current depth , next depth
        //System.out.println("현재 좌표: "+x+","+y);
        for(int d=0;d<4;d++){
            int nx = x+dx[d]; int ny = y+dy[d];
            if(isOut(nx,ny) || !Ans.equals("")) continue;
            dirInfo[depth] = d;
            dfs(nx,ny,depth+1);
            dirInfo[depth] = -1;
        }
    }
    boolean isOut(int x,int y){
        return x<0 || x>=N || y<0 || y>=M;
    }
}