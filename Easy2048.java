package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Easy2048 {
	static int ans,N,Map[][];
	static void dfs(int index,int[][] tmp,boolean[][] merged) {
		//정답 찾은 경우
		if(index>=5) {
			ans = Math.max(ans,getMax(tmp));
			return;
		}
		for(int dir=0;dir<4;dir++) {
			//현재 방향 정하고 이동/병합:4가지 방향으로 반복한다.
			tmp = transform(dir,tmp);//이동 전 tmp를 d방향으로 이동/병합시킨다!
			//다음 경우 호출
			dfs(index+1,tmp,new boolean[N][N]);//각 이동 턴마다 병합 체크배열 초기화해야함.
		}
	}
	static int[][] transform(int dir,int[][] Map) {//dir방향으로 Map을 조작한다!
		int[][] tmp = new int[N][N];
		boolean[][] merged = new boolean[N][N];
		if(dir == 0) {//상. 열단위
			for(int j=0;j<N;j++) {
				for(int i=0;i<N-1;i++) {
					if(Map[i][j] == Map[i+1][j] && !merged[i][j]) {
						tmp[i][j] = Map[i][j] * Map[i][j];
						merged[i][j] = true;
						merged[i+1][j] = true;
					} else {
						tmp[i][j] = Map[i][j];
					}
				}
			}
			System.out.println("위로 이동 방향 : "+dir);
			checkpnt(tmp);
		} else if(dir == 1) {//하. 열단위
			for(int j=0;j<N;j++) {
				for(int i=N-1;i>=1;i--) {
					if(Map[i][j] == Map[i-1][j] && !merged[i][j]) {
						tmp[i][j] = Map[i][j] * Map[i][j];
						merged[i][j] = true;
						merged[i-1][j] = true;
					} else {
						tmp[i][j] = Map[i][j];
					}
				}
			}
			System.out.println("아래로 이동 방향 : "+dir);
			checkpnt(tmp);
		} else if(dir == 2) {//좌. 행단위
			for(int i=0;i<N;i++) {
				for(int j=0;i<N-1;i++) {
					if(Map[i][j] == Map[i][j+1] && !merged[i][j]) {
						tmp[i][j] = Map[i][j] * Map[i][j];
						merged[i][j] = true;
						merged[i][j+1] = true;
					} else {
						tmp[i][j] = Map[i][j];
					}
				}
			}
			System.out.println("좌로 이동 방향 : "+dir);
			checkpnt(tmp);
		} else if(dir==3){//우. 행단위
			for(int i=0;i<N;i++) {
				for(int j=N-1;j>=1;j--) {
					if(Map[i][j] == Map[i][j-1] && !merged[i][j]) {
						tmp[i][j] = Map[i][j] * Map[i][j];
						merged[i][j] = true;
						merged[i][j-1] = true;
					} else {
						tmp[i][j] = Map[i][j];
					}
				}
			}
			System.out.println("우로 이동 방향 : "+dir);
			checkpnt(tmp);
		}
		return tmp;
	}
	static int getMax(int[][] tmp) {
		int max = -1;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				max = Math.max(max, tmp[i][j]);
			}
		}
		return max;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = -1;//최댓값을 찾기 위해 작은 값 셋팅.
		dfs(0,Map,new boolean[N][N]);//모든 경우의 최댓값을 구해서 ans에 저장.
		System.out.println(ans);
		
	}
	static void checkpnt(int[][] arr) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}