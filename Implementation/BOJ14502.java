import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//연구소
public class BOJ14502 {
	static int ans,N,M,Map[][],copiedMap[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] Visited;
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		Map = new int[N][M];
		copiedMap = new int[N][M];
		
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		ans = 0;
		go(0,0);
		System.out.println(ans);
	}
	static void go(int idx,int st) {
		//1.종료
		if(idx == 3) {
			//print();
			copyMap();
			spread();//bfs
			int cnt = countZero();
			if(cnt > ans) {
				ans = cnt;
			}
			return;
		}
		if(st >= N*M) return;
		//2.다음 경우
		for(int i=st;i<N*M;i++) {
			int r = i / M; int c = i % M;
			if(Map[r][c] == 0) {
				Map[r][c] = 1;
				go(idx+1,i+1);
				Map[r][c] = 0;
			}
		}
	}
	// 기존 맵을 유지하기 위해 바이러스 퍼트릴 맵 복사하기
    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copiedMap[i][j] = Map[i][j];
            }
        }
    }
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(Map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static void spread() {
		Visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(copiedMap[i][j] == 2 && !Visited[i][j]) {//미방문한 0인칸 방문, 확산시킨다!
					bfs(i,j);
				}
			}
		}
	}
	static void bfs(int sx,int sy) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(sx,sy));
		Visited[sx][sy] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d];
				int ny = cur.y+dy[d];
				if(isOut(nx,ny)) continue;
				
				if(copiedMap[nx][ny] == 0) {
					q.offer(new Pair(nx,ny));
					Visited[nx][ny] = true;
					copiedMap[nx][ny] = 2;
				}
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=M;
	}
	static int countZero() {
		int sum = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(copiedMap[i][j] == 0) sum++;
			}
		}
		return sum;
	}
}
