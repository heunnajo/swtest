import java.util.*;
public class MoveOfNight {
	static final int INF = 987654321;
	static int ans,I,sx,sy,destx,desty,Map[][];
	//static boolean[][] visited;
	static int[] dx = {-2,-2,-1,-1,1,1,2,2};
	static int[] dy = {-1,1,-2,2,-2,2,-1,1};
	static class Point{
		int x,y,d;
		Point(int x,int y,int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			I = sc.nextInt();
			Map = new int[I][I];
			sx = sc.nextInt();
			sy = sc.nextInt();
			destx = sc.nextInt();
			desty = sc.nextInt();
			
			ans = INF;
			bfs(sx,sy);//bfs내에서 ans를 갱신
			sb.append(ans+"\n");
		}
		System.out.print(sb);
	}
	static void bfs(int sx,int sy) {
		if(sx == destx && sy == desty) {
			ans = 0;return;
		}
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[I][I];
		q.add(new Point(sx,sy,0));
		visited[sx][sy] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.remove();
			if(cur.x == destx && cur.y == desty) ans = Math.min(ans, cur.d);
			for(int d=0;d<8;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				if(isOut(nx,ny) || visited[nx][ny]) continue;
				q.add(new Point(nx,ny,cur.d+1));
				visited[nx][ny] = true;
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 ||x>I-1 || y<0 || y>I-1;
	}
}