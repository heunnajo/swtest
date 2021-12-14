import java.util.*;
import java.io.*;
public class MakeShape {
	static int N,M,Map[][],groupn,groupSize[],group[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		group = new int[N][M];
		groupn = 0;
		groupSize = new int[N*M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//1.1의 위치마다 섬의 크기로 배열값 변형
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] == 1 && group[i][j]==0) {
					bfs(i,j);//이 재귀함수로 배열 값을 변형!
				}
			}
		}
		//2.0의 위치마다 인접한 섬의 크기가 가장 큰 위치를 선택, 그 섬크기합+1이 정답
		int ans = -1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] == 0) {
					HashSet<Integer> hash = new HashSet<>();
					for(int d=0;d<4;d++) {
						int nx = i+dx[d],ny = j+dy[d];
						if(isOut(nx,ny)) continue;
						if(Map[nx][ny]==1) hash.add(group[nx][ny]);
					}
					int sum = 1;
					for(int h:hash) {
						sum+=groupSize[h];
					}
					if(ans<sum) ans = sum;
				}
			}
		}
		System.out.println(ans);
	}
	static void bfs(int x,int y) {
		groupn+=1;
		int cnt = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(x);q.add(y);
		group[x][y] = groupn;
		
		while(!q.isEmpty()) {
			int curX = q.remove(), curY = q.remove();
			for(int d=0;d<4;d++) {
				int nx = curX+dx[d], ny = curY+dy[d];
				if(isOut(nx,ny) || Map[nx][ny] == 0 || group[nx][ny] != 0) continue;//불가능한 조건 모두 컨티뉴 처리!
				cnt++;
				q.add(nx);q.add(ny);
				group[nx][ny] = groupn;
			}
		}
		groupSize[groupn] = cnt;
	}
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(Map[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
}