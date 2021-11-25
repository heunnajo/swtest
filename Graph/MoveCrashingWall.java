import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
public class MoveCrashingWall {
	static int N,M,Map[][];//N:행 M:열
	static class Point{
		int x,y,dist,crash;
		Point(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		Point(int x,int y,int dist,int crash){
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.crash = crash;//부순횟수(부쉈으면 1,안 부쉈으면 0)
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][][] visited = new boolean[2][N][M];
		q.add(new Point(0,0,1));//시작점포함하기 때문에 거리값 1부터 시작.
		visited[0][0][0] = true;//벽부순적 0,(0,0)에서 시작
		int size = 0;
		while(!q.isEmpty()) {
			size = q.size();
			
			for(int i=0;i<size;i++) {
				Point cur = q.remove();//이 위치에서 큐 꺼내는 게 맞을 거야.
				if(cur.x == N-1 && cur.y == M-1) return cur.dist;
				
				for(int dir=0;dir<4;dir++) {
					int nx = cur.x+dx[dir],ny = cur.y+dy[dir];
					if(isOut(nx,ny)) continue;
					if(visited[cur.crash][nx][ny]) continue;//현재 벽 부순 횟수 차원에서 방문했으면 패스~
					
					//다음 좌표 벽(1)인 경우, cur.crash가 0일 때만 이동할 수 있다!!!!!!!!
					if(Map[nx][ny] == 1) {
						//벽을 부수는 경우 방문하러 가는 처리
						if(cur.crash<1) {
							visited[cur.crash+1][nx][ny] = true;
							q.add(new Point(nx,ny,cur.dist+1,cur.crash+1));
						}
					} else {//0인 경우.
						visited[cur.crash][nx][ny] = true;
						q.add(new Point(nx,ny,cur.dist+1,cur.crash));
					}
				}
			}
		}
		return  -1;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		Map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			input = br.readLine().split("");
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		System.out.println(bfs());
	}

}