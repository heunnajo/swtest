package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class HikingTrail {
	static int ans,T,N,K,Map[][];
	static Queue<Point> q;
	static class Point{
		int x,y,dist,drill;//dist는 등산로의 길이를 의미한다!
		Point(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		Point(int x,int y,int dist,int drill){
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.drill = drill;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void bfs() {
		//최장 등산로 길이를 찾기 위한 BFS
		//이동한 거리는 등산로의 길이를 의미.
		//탐색은 배열의 가장 큰 값에서 시작한다.=>입력받을 때 알 수 있지 않다.=>입력받을 때 미리 큐에 저장?!
		ans = -1;
		q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][N][2];
		Queue<Point> maxQ = new LinkedList<>();
		//최댓값을 찾아서 큐에 저장한다!
		int max = -1;
		int[][] drilledMap = new int[N][N];
		for(int i=0;i<N;i++) {
			System.arraycopy(Map, 0, drilledMap, 0, N);
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(max<=Map[i][j]) {
					max = Map[i][j];//최댓값으로 갱신해서 max에 저장
					//maxP = new Point(i,j,1);
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(max==Map[i][j]) {
					maxQ.add(new Point(i,j,1));
				}
			}
		}
		//System.out.println("max: "+max);
		//System.out.println("q.size(): "+q.size());
		while(!maxQ.isEmpty()) {
			Point curMaxStart = maxQ.remove();
			q.add(curMaxStart);
			//각 최댓값 좌표에서 BFS하는 while문
			while(!q.isEmpty()) {
				Point cur = q.remove();
				ans = Math.max(ans, cur.dist);
				//이제 큐에서 뽑을 때, drill 찬스 썼는지 안 썻는지에 따라 사용할 Map이 달라진다!
				//최댓값을 ans에 저장해야함.
				for(int d=0;d<4;d++) {
					int nx = cur.x+dx[d];
					int ny = cur.y+dy[d];
					
					if(outRange(nx,ny)) continue;
					if(visited[nx][ny][cur.drill]) continue;
					
					if(Map[cur.x][cur.y] > Map[nx][ny]) {//높->낮.
						q.add(new Point(nx,ny,cur.dist+1));
						visited[nx][ny][cur.drill] = true;
					}
				}
				//아직 지형깎는 공사 찬스 안 썼다면
				if(cur.drill == 0) {
					for(int d=0;d<4;d++) {
						int nx = cur.x+dx[d];
						int ny = cur.y+dy[d];
						
						if(outRange(nx,ny)) continue;
						if(visited[nx][ny][cur.drill+1]) continue;
						
						//낮<높 일때, 다음칸 K만큼잘라서 현재보다 작아질 수 있다면 등산로 놓을 수 있다!
						//최장 등산로 조성을 위해 K이내에 cur>next-K를 만족하는 최소 k를 찾아야한다!
						for(int k=1;k<=K;k++) {
							if(Map[cur.x][cur.y]>Map[nx][ny]-k) {
								drilledMap[nx][ny] -= k;
								q.add(new Point(nx,ny,cur.dist+1));
								visited[nx][ny][cur.drill+1] = true;
							}
							
						}
					}
				}
			}
		}
	}
	static boolean outRange(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			//각 TC마다 필요한 객체 초기화
			Map = new int[N][N];
			ans = 0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//구현 시작
			bfs();
			System.out.println(ans);
		}
	}

}

//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(Map[i][j]+" ");
//				}
//				System.out.println();
//			}