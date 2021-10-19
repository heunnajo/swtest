import java.awt.*;
import java.util.*;
class CheckSocialDistance {
    static char[][] tmp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    char[][] toCharTwoD(String[] str){
		char[][] tmp = new char[5][5];
		for(int i=0;i<str.length;i++) {//5
			tmp[i] = str[i].toCharArray();
		}
		return tmp;
	}
    int go(int sx,int sy,int nx,int ny){
        //범위 체크
        if(nx<= 0||nx>=5||ny<=0||ny>=5) return 0;//어떤 값을 리턴해야하나?
        //중복 체크:X. 방문한 노드 다시 재방문 가능하다. 다른 P-P 조건판단을 위해서
        if(Math.abs(sx-nx)+Math.abs(sy-ny)>2){
            go(nx,ny,nx-1,ny);
            go(nx,ny,nx+1,ny);
            go(nx,ny,nx,ny-1);
            go(nx,ny,nx,ny+1);
        } else{
            boolean flag = distCheck(sx,sy,nx,ny);
            if(!flag) return 0;//거리두기 지켜지지 않음.
        }
        return 1;
    }
    boolean distCheck(int sx,int sy,int nx,int ny){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        while(!q.isEmpty()){
            Point cur = q.remove();
            if(cur.x == nx && cur.y == ny) return false;
            for(int dir = 0;dir<4;dir++){
                int nnx = cur.x+dx[dir], nny = cur.y+dy[dir];
                if(isOut(nnx,nny)||visited[nnx][nny]) continue;
                
                visited[nnx][nny] = true;
                q.add(new Point(nnx,nny));
            }
        }
        return true;
    }
    boolean isOut(int x,int y){
        return x<0 || x>=5 || y<0 || y>=5;
    }
    public int[] solution(String[][] places) {
        
        int[] answer = new int[5];//5X5크기 대기실은 총 5개이므로
        char[][] tmp = new char[5][5];
        for(int i=0;i<5;i++){
            tmp = new char[5][5];//i번째 대기실마다 초기화
            tmp = toCharTwoD(places[i]);//2차원 char 타입의 배열로 변환.tmp로 연산수행하면 됨.
            answer[i] = go(0,0,0,0);//sx,sy,nx,ny
        }
        return answer;
    }
}