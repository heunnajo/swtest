import java.util.*;
public class MazeSearch {
	static int N,M;//N:행, M:열
	static char[][] Map;
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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		Map = new char[N][M];
		for(int i=0;i<N;i++) {
			Map[i] = sc.next().toCharArray();
		}
		System.out.println(bfs(0,0));
	}
	static int bfs(int sx,int sy) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new Point(sx,sy,1));
		visited[sx][sy] = true;
		int ans = 987654321;
		while(!q.isEmpty()) {
			Point cur = q.remove();
			if(cur.x == N-1 && cur.y == M-1) ans = Math.min(ans, cur.d);
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				if(isOut(nx,ny) || visited[nx][ny]) continue;
				if(Map[nx][ny]=='0') continue;
				
				q.add(new Point(nx,ny,cur.d+1));
				visited[nx][ny] = true;
			}
		}
		return ans;
	}
	static boolean isOut(int x,int y) {
		return x<0 ||x>N-1 || y<0 || y>M-1;
	}
}

////아니면 그냥 String n개 저장하는 배열 생성해서 charAt(j)로 접근하던가.
////아래 방법으로 입력받기 성공~!
//for(int i=0;i<N;i++) {
//	for(int j=0;j<M;j++) {
//		System.out.print(Map[i][j]);
//	}
//	System.out.println();
//}