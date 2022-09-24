//이동 조건 K
import java.util.*;
class Solution {
    static int N,M;//n : 세로 = 행, m : 가로 = 열
    static int sx,sy,ex,ey;
    static int K;
    static HashSet<String> set;
    static int[] dx = {1,0,0,-1};//하좌우상
    static int[] dy = {0,-1,1,0};

    static class Pos{
        int x,y;
        String route;

        Pos(int x,int y,String route){
            this.x = x;
            this.y = y;
            this.route = route;
        }
    }
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n; M = m; sx = x; sy = y; ex = r; ey = c; K = k;

        String answer;

        search();//(sx,sy) => (ex,ey)
        
        if(set.size() == 0){
            answer = "impossible";
        } else{
            ArrayList<String> ans = new ArrayList<>(set);

            Collections.sort(ans);

            answer = ans.get(0);
            //System.out.println("answer: "+ answer);
        }
        
        return answer;
    }
    void search(){
        //(ex,ey)에 도착 시 그 때의 이동 경로를 set에 저장

        set = new HashSet<>();

        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(sx-1,sy-1,""));
        while(!q.isEmpty()){
            //정답 찾은 조건 : sb의 길이가 k이고, 그 때의 좌표 = (ex,ey)
            Pos cur = q.poll();
            if(cur.route.length() == K && cur.x == ex-1 && cur.y == ey-1){
                //System.out.println("도착! 그 때의 경로 : "+cur.route);
                set.add(cur.route);
                continue;
            }
            if(cur.route.length() > K) return;

            //System.out.println("cur.route: "+cur.route);

            for(int d=0;d<4;d++){
                int nx = cur.x + dx[d], ny = cur.y + dy[d];
                //if(d==1) System.out.println("d==1일 때 "+nx+","+ny);

                if(isOut(nx,ny)) continue;

                if(d==0) {//하
                    q.offer(new Pos(nx,ny,cur.route+"d"));
                    //System.out.println("하: "+cur.route+"d");//u
                }
                else if(d==1) {//좌
                    q.offer(new Pos(nx,ny,cur.route+"l"));
                    //System.out.println("좌: "+cur.route+"l");//d
                }
                else if(d==2) {//우
                    q.offer(new Pos(nx,ny,cur.route+"r"));
                    //System.out.println("우: "+cur.route+"r");//l
                }
                else if(d==3) {//상
                    q.offer(new Pos(nx,ny,cur.route+"u"));
                    //System.out.println("상: "+cur.route+"u");//r
                }
            }
        }
    }
    boolean isOut(int x,int y){
        return x<0 || x>=N || y<0 || y>=M;
    }
}