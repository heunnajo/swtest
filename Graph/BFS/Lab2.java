package ss;
import java.util.*;
import java.io.*;
public class Lab2 {
	static int ans,VCnt,N,M,Map[][],dist[][];
	static final int INF = 987654321;
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static Pair[] Viruses;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][N];
		dist = new int[N][N];
		Viruses = new Pair[10];
		VCnt = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j] == 2) {
					Viruses[VCnt++] = new Pair(i,j);
				}
			}
		}
		ans = INF;//최종해:최ㅗ솟값 도출해야함!
		int time;//부분해 뭔가 스코프가 이상함!!!!
		boolean result = true;
		for(int subset=0;subset<(1<<VCnt);subset++) {
			if(cntVirus(subset) == M) {
				resetDist();//각 경우마다 한번씩만 초기화해야함.
				time = -2;//각 경우마다 BFS 탐색으로 확산 시간 dist배열값 초기화, 최종 확산 시간 time초기화
				for(int i=0;i<VCnt;i++) {
					if((subset & (1<<i)) > 0) {
						result = bfs(Viruses[i].x,Viruses[i].y);//M개의 바이러스에 대해 bfs를 수행.
					}
				}
				if(result == false) time = -1;
				else {//전체 칸 확산했다면 현재 경우 확산 시간 time구하고, ans에 최솟값 도출!
					time = getTotalTime();//현재 경우 subset의 확산 시간 = dist배열의 최댓값!M개 바이러스 BFS 모두 수행한 후에 도출!
					if(ans>time) {//불가능한 경우라도 이후의 경우에 가능한 경우가 나올 수도 있기 때문에!
						ans = time;//갱신이 될 때, 그 때 바이러스 확인하자.
					}
				}
			}
		}
		if(ans == INF) ans = -1;
		System.out.println(ans);
	}
	static boolean bfs(int sx,int sy) {
		//resetDist();//bfs할 때마다 초기활를하네.....
		Queue<Pair> q = new LinkedList<>();
		dist[sx][sy] = 0;
		q.add(new Pair(sx,sy));
		while(!q.isEmpty()) {
			Pair cur = q.remove();
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d],ny = cur.y+dy[d];
				if(isOut(nx,ny) || Map[nx][ny] == 1)continue;
				if(dist[nx][ny] == -1 || dist[nx][ny]>dist[cur.x][cur.y]+1) {//아직 방문하지 않았거나, 이미 방문했더라도 현재 거리값이 더 짧으르 때!
					q.add(new Pair(nx,ny));
					dist[nx][ny] = dist[cur.x][cur.y]+1;
				}
			}
		}
		//모든 빈칸 다 방문했는지 유효성 검사
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				if(Map[i][j] == 0 && dist[i][j] == -1) return false;//-1로 처리.-1=불가능한 경우 의미!
		
		return true;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	static int getTotalTime() {
		int time = -2;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(time<dist[i][j]) time = dist[i][j];
			}
		}
		return time;
	}
	static void resetDist() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				dist[i][j] = -1;
			}
		}
	}
	static int cntVirus(int num) {
		int sum = 0;
		while(num>0) {
			if((num & 1) > 0) sum++;
			num = num>>1;
		}
		return sum;
	}
	static void print(int[][] arr) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.printf("%3d",arr[i][j]);
			}
			System.out.println();
		}
		
	}
}
//System.out.println("현재 subset에서 M개 바이러스 위치 정보 ");
//for(int i=0;i<VCnt;i++) {
//	if((subset & (1<<i)) > 0) {
//		System.out.println(Viruses[i].x+" "+Viruses[i].y);
//	}
//}

//for(int i=0;i<VCnt;i++) {//바이러스 저장 잘 되었음
//System.out.println(Viruses[i].x +" "+Viruses[i].y);
//}
//		System.out.println(VCnt);
