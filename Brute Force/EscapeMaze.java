package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
public class EscapeMaze {
	static int ans,N,M;//N:행,M:열 
	static char[][] Map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new char[N][M];
		
		for(int i=0;i<N;i++) {
			Map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				ans += bfs(i,j);
			}
		}
		System.out.println(ans);
	}
	static int bfs(int x,int y) {
		Queue<Integer> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(x);q.add(y);
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int curX = q.poll(), curY = q.poll();
			int dir = -1;
			switch(Map[curX][curY]) {
				case 'U':dir = 0;break;
				case 'D':dir = 1;break;
				case 'L':dir = 2;break;
				case 'R':dir = 3;break;
			}
			int nx = curX+dx[dir],ny = curY+dy[dir];
			if(isOut(nx,ny)) return 1;
			else if(!visited[nx][ny]) {
				q.add(nx);q.add(ny);
				visited[nx][ny] = true;
			}
		}
		return 0;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}