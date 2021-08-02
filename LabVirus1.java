import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class LabVirus1 {
	static int M,N,Map[][];//세로 N,가로 M (3<=N,M<=8)
	static int ans = Integer.MIN_VALUE;//최댓값 구해야하기 때문에 일부러 작은 값을 저장!			
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Virus{
		int x,y;
		Virus(int x,int y){
			this.x = x;
			this.y =y;
		}
	}
	static void make_wall(int wall) {//1을 3개만든다.int index=0에서 시작,3이되면 종료!
		//정답 찾은 경우:벽3개 만든 경우, 바이러스 확산한다!
		if(wall == 3) {
			spread_virus();//1을 총 3개 만들면.
			return;
		}
		//다음 경우 호출.
		for(int i=0;i<M;i++) {//가로 M.
			for(int j=0;j<N;j++) {
				if(Map[i][j]==0) {
					Map[i][j] = 1;
					make_wall(wall+1);
					Map[i][j] = 0;//다시 원상 복구해준다!
				}
			}
		}
	}
	static void spread_virus() {//BFS 완탐으로 바이러스 확산!!!
		Queue<Virus> q = new LinkedList<Virus>();
		boolean[][] visited = new boolean[M][N];
		int[][] copied_map = new int[M][N];
		for(int i=0;i<M;i++) {//가로 M.
			for(int j=0;j<N;j++) {
				copied_map[i][j] = Map[i][j];
			}
		}
		//Map값이 2인 노드들 큐에 넣넣~!!
		for(int i=0;i<M;i++) {//가로 M.
			for(int j=0;j<N;j++) {
				if(Map[i][j]==2) {
					q.add(new Virus(i,j));
					visited[i][j] = true;
				}
			}
		}
		//BFS 시작!. 현재 큐에는 바이러스 좌표들이 들어가있다.
		//Map[cur.x][cur.y]에 대해 상하좌우 nx,ny를 2로 바꾸고, (nx,ny)를 큐에 다시 넣는다.
		while(!q.isEmpty()) {
			Virus cur = q.remove();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d],ny = cur.y+dy[d];
				//가로 M, 세로 N
				if(nx<0 || nx>M-1 || ny<0 || ny>N-1) continue;
				if(visited[nx][ny] || Map[nx][ny] ==1) continue;
				
				//Map값이 0:갈 수 잇다.1:못간다, 2:갈수잇?못?0갯수세는 거라 원래 2인 값은 상관없을듯?!
				copied_map[nx][ny] = 2;//바이러스(2)로 만든다!!\
				q.add(new Virus(nx,ny));
				visited[nx][ny] = true;
				
			}
		}
		
		count_safezone(copied_map);
	}
	static void count_safezone(int[][] copied_map) {
		int zero_cnt = 0;
		for(int i=0;i<M;i++) {//가로 M.
			for(int j=0;j<N;j++) {
				if(copied_map[i][j]==0) {
					zero_cnt++;
				}
			}
		}
		ans = Math.max(ans, zero_cnt);
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		Map = new int[M][N];//가로 M, 세로 N
		for(int i=0;i<M;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		make_wall(0);//make_wall=>spread_virus=>count_safezone.
		System.out.println(ans);
	}

}