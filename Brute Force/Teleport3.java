package ss;
import java.util.*;
import java.io.*;
public class Teleport3 {
	static int ans,sx,sy,ex,ey,Tele[][];
	static final int INF = 987654321;
	static class Info{
		int x,y,t;
		Info(int x,int y,int t){
			this.x = x; this.y = y; this.t = t;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		ex = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());
		
		Tele = new int[3][4];
		
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			Tele[i][0] = x1; Tele[i][1] = y1; Tele[i][2] = x2; Tele[i][3] = y2;
		}
		ans = INF;
		bfs(sx,sy);
		System.out.println(ans);
	}
	static void bfs(int sx,int sy) {
		Queue<Info> q = new LinkedList<>();
		boolean[][][] visited = new boolean[2][1000000000][1000000000];
		q.add(new Info(sx,sy,0));
		visited[0][sx][sy] = visited[1][sx][sy] = true;//이 뜻은, 출발점으로 다시 이동하지 않겠다는 의미.
		
		while(!q.isEmpty()) {
			Info cur = q.remove();
			//정답 찾은 경우 : 쵯소값 갱신
			if(cur.x == ex && cur.y == ey) {
				ans = Math.min(ans, cur.t);
			}
			//텔레포트 시작점인 경우:텔레포트로 이동!
			if(cur.x == Tele[0][0] && cur.y == Tele[0][1]) {
				if(!visited[1][Tele[0][2]][Tele[0][3]]) {
					q.add(new Info(Tele[0][2],Tele[0][3],cur.t+10));
					visited[1][Tele[0][2]][Tele[0][3]] = true;
				}
			}
			if(cur.x == Tele[1][0] && cur.y == Tele[1][1]){
				if(!visited[1][Tele[1][2]][Tele[1][3]]) {
					q.add(new Info(Tele[1][2],Tele[1][3],cur.t+10));
					visited[1][Tele[1][2]][Tele[1][3]] = true;
				}
				
			}
			if(cur.x == Tele[2][0] && cur.y == Tele[2][1]) {
				if(!visited[1][Tele[2][2]][Tele[2][3]]) {
					q.add(new Info(Tele[2][2],Tele[2][3],cur.t+10));
					visited[1][Tele[2][2]][Tele[2][3]] = true;
				}
			}
			else {
				for(int dir=0;dir<4;dir++) {
					int nx = cur.x+dx[dir], ny = cur.y+dy[dir];
					if(!visited[0][nx][ny]) {
						q.add(new Info(nx,ny,cur.t+1));
						visited[0][nx][ny] = true;
					}
				}
			}
		}
	}
}
