package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class SecurityService {
	static int maxK;
	static int ans,T,N,M,Map[][];
	static int HouseCnt;
	static class Pnt{
		int x,y,dist;
		Pnt(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	static LinkedList<Pnt> HList;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int getCost(int k) {
		return k*k + (k-1)*(k-1);
	}
	static int getHouseCnt(boolean[][] visited) {
		//방문한 서비스 영역들 중 집인 노드 갯수 리턴!
		int sum = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j] && Map[i][j] == 1) sum++;
			}
		}
		return sum;
	}
	static boolean minus(int profit,int cost) {
		if(profit-cost<=0) return true;//손해라는건, 보안회사 이익=수익-운영비 가 음수일 때.
		return false;
	}
	static void bfs() {
		
		for(int i=0;i<HList.size();i++) {
			Pnt start = HList.get(i);
			for(int k=1;k*k+(k-1)*(k-1)<=N*N;k++) {
				//각 집마다 모든 k에 대해 BFS
				int profit,cost,house;
				
				Queue<Pnt> q = new LinkedList<>();
				boolean[][] visited = new boolean[N][N];
				q.add(start);
				visited[start.x][start.y] = true;
				
				//k거리 BFS
				while(!q.isEmpty()) {
					Pnt cur = q.remove();
					if(cur.dist==k) continue;//현재 노드까지 거리 dist가 이미 k이면 더이상 BFS 안함.
					for(int d=0;d<4;d++) {
						int nx = cur.x+dx[d];
						int ny = cur.y+dy[d];
						
						if(nx<0 || nx>N-1|| ny<0 || ny>N-1) continue;
						if(!visited[nx][ny]) {
							visited[nx][ny] = true;
							q.add(new Pnt(nx,ny,cur.dist+1));
						}
					}
				}
				//k거리 BFS 끝난 후 그 때의 집갯수,이익,운영비 계산.
				house = getHouseCnt(visited);
				profit = house*M;
				cost = getCost(k);
				if(!minus(profit,cost)) {
					ans = Math.max(ans, house);//현재 경우 집 갯수와 ans비교, 최댓값 갱신!
				}
				maxK = Math.max(k, maxK);
			}
		}
		
	}

	static void stMax() {//(N/2,N/2)에서 BFS한 결과.
		Pnt start = new Pnt(N/2,N/2,1);
		int profit,cost,house;
		
		Queue<Pnt> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.add(start);
		visited[start.x][start.y] = true;
		
		//k거리 BFS
		while(!q.isEmpty()) {
			Pnt cur = q.remove();
			if(cur.dist==maxK) continue;//현재 노드까지 거리 dist가 이미 k이면 더이상 BFS 안함.
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				if(nx<0 || nx>N-1|| ny<0 || ny>N-1) continue;
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Pnt(nx,ny,cur.dist+1));
				}
			}
		}
		house = getHouseCnt(visited);
		profit = house*M;
		cost = getCost(maxK);
		if(!minus(profit,cost)) {
			ans = Math.max(ans, house);//현재 경우 집 갯수와 ans비교, 최댓값 갱신!
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			Map = new int[N][N];
			HList = new LinkedList<>();
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					if(Map[i][j] == 1) {
						HList.add(new Pnt(i,j,1));
					}
				}
			}
			
			ans = -1;//최댓값을 구한다.집은 최소 1개이기 때문에 1보다 작은 값을 넣어줌.
			maxK = 0;
			bfs();
			stMax();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);;
	}

}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}