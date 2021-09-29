import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class SecurityService_2nd {
	static int maxK;
	static int ans,T,N,M,Map[][];
	static int HouseCnt;
	static class Pnt{
		int x,y,dist;
		Pnt(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
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
	static void bfs(int x,int y) {
			//각 집마다 모든 k에 대해 BFS
			int profit,cost,house;
			house = Map[x][y];
			Queue<Pnt> q = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			q.add(new Pnt(x,y));
			visited[x][y] = true;
			
			//k거리 BFS
			int K=1;
        
        	if(getCost(K) <= house * M) {//?
				ans = K > ans ? K : ans;
			}
			while(!q.isEmpty()) {
				int size = q.size();
				K++;//큐에 넣을 때가 아니라 큐에서 뽑을 때.
				for(int i=0;i<size;i++) {//현재 큐에 들어간 노드 기준으로만 상하좌우 탐색한다!!
					Pnt cur = q.remove();//큐 크기 반복문 밑에!
					
					for(int d=0;d<4;d++) {
						int nx = cur.x+dx[d];
						int ny = cur.y+dy[d];
						
						if(nx<0 || nx>N-1|| ny<0 || ny>N-1) continue;
						if(!visited[nx][ny]) {
							visited[nx][ny] = true;
							q.add(new Pnt(nx,ny));
							if(Map[nx][ny] == 1) house++;
							//K++;//?
						}
					}
					
				}
				//k거리 BFS 끝난 후 그 때의 집갯수,이익,운영비 계산.
				//house = getHouseCnt(visited);
				profit = house*M;
				cost = getCost(K);
				if(profit-cost>=0) {
					ans = Math.max(ans, house);//현재 경우 집 갯수와 ans비교, 최댓값 갱신!
				}
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
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;//최댓값을 구한다.집은 최소 1개이기 때문에 1보다 작은 값을 넣어줌.
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					bfs(i,j);
				}
			}
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);;
	}

}