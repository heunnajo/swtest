package ss;
import java.awt.*;//실제 셤장에서는 Point 객체 만들어서 쓰깅.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class CatchEscapedPrisoner {
	static int ans,N,M,R,C,L,Map[][],Time[][];//N세로=행,M가로=열,맨홀 뚜경위치 R,C:idx 0부터 시작.
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new Point(R,C));
		visited[R][C] = true;
		Time[R][C] = 1;
		
		while(!q.isEmpty()) {
			Point cur = q.remove();
			
			for(int dir=0;dir<4;dir++) {
				int nx = cur.x+dx[dir],ny = cur.y+dy[dir];
				if(isOut(nx,ny)) continue;
				if(visited[nx][ny]) continue;
				//다음 이동하는 조건 판단 구현!
				q.add(new Point(nx,ny));
				Time[nx][ny] = Time[cur.x][cur.y]+1;
				visited[nx][ny] = true;
			}
		}
		
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			Map = new int[N][M];
			Time = new int[N][M];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			sb.append("#"+t+" "+ans+"\n");
		}
	}

}

//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(Map[i][j]+" ");
//				}
//				System.out.println();
//			}