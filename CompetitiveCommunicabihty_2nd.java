package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
public class CompetitiveCommunicabihty_2nd {
	static int N,K,S,X,Y,Map[][];
	static class Point implements Comparable<Point>{
		int x,y,time,virus_num;
		Point(int x,int y,int time, int virus_num){
			this.x = x;
			this.y = y;
			this.time = time;
			this.virus_num = virus_num;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.time == o.time) {//시간이 같을 때만! 바이러스번호가 작은 것부터 빼준다.
				return this.virus_num - o.virus_num;
			} else {
				return this.time - o.time;//원래 BFS 특성상 디폴트 같은데 else문이 꼭 있어야하니까 굳이 구현해줌...
			}
		}
			
		
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	static PriorityQueue<Point> pq = new PriorityQueue<>();
	static void solve() {
			
		while(!pq.isEmpty()) {
			//1.시간 작은 것 먼저 2. 바이러스 번호 작은 것.
			Point cur = pq.remove();
			//1.불가능한 경우 : S를 초과하면 0을 출력.
			if(cur.time > S) {
				System.out.println(0);
				return;
			}
			//2.정답 찾은 경우
			if(cur.x == X && cur.y == Y && Map[cur.x][cur.y] != 0) {
				System.out.println(Map[X][Y]);
				return; 
			}
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				
				//범위 체크 먼저, 그 다음 방문 체크 
				if(nx<0 || nx>N-1 || ny<0 || ny>N-1) continue;
				if(visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				Map[nx][ny] = cur.virus_num;
				//재정의한 정렬기준 comparable, compareTo 로 인해
				//pq에 노드를 넣으면 큐에서 꺼낼 때 자동으로 우선순위가 높은 것이 먼저 나온다!
				pq.add(new Point(nx,ny,cur.time+1,cur.virus_num));
			}
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);//바이러스 갯수.
		
		Map = new int [N][N];
		visited = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
				if(Map[i][j] != 0) {
					visited[i][j] = true;
					pq.add(new Point(i,j,0,Map[i][j]));
				}
			}
		}
		input = br.readLine().split(" ");
		S = Integer.parseInt(input[0]);
		X = Integer.parseInt(input[1])-1;
		Y = Integer.parseInt(input[2])-1;
		
		solve();
		br.close();
	}

}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}