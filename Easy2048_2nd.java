package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Easy2048_2nd {
	static int ans,N,Map[][];
	static BOARD board;
	static class BOARD {
		int[][] map = new int[20][20];
		
		void rotate() {
			int[][] tmp = new int[20][20];
			
			for(int x=0;x<N;x++) {
				for(int y=0;y<N;y++) {
					tmp[x][y] = map[N-y-1][x];
				}
			}
			for(int x=0;x<N;x++) {
				for(int y=0;y<N;y++) {
					map[x][y] = tmp[x][y];
				}
			}
		}
		int get_max() {
			int ret = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					ret = Math.max(ret, map[i][j]);
				}
			}
			return ret;
		}
		
		void up() {//가로방향다 돈 다음 세로 방향 낸다.
			int[][] tmp = new int [20][20];
			for(int y=0;y<N;y++) {
				int flag = 0, target = 0;//flag :합쳐진 횟수 의미, tmp에 복사하기 위해 타겟점 기준이 있어야함.탬플릿에 위해 유인물 안바ㅏ
				for(int x=0;x<N;x++) {
					if(map[x][y] == 0) continue;
					
					if(flag == 1 && map[x][y] == tmp[target][y]) {//tartget이라는 것은 비교 대상이되는 인덱스인 듯.근데 target은 처음에 -1인데?
						tmp[target][y] *= 2;
						flag = 0;
					} else {//같지 않을 때.
						tmp[target++][y] = map[x][y];
						flag = 1;
					}
				}
				for(;target<N;target++) {
					tmp[target++][y] = 0;
				}
			}
			for(int x=0;x<N;x++) {
				for(int y=0;y<N;y++) {
					map[x][y] = tmp[x][y];
				}
			}
		}//up 끝
	}
	static void dfs(int index,BOARD curBoard) {
		//정답 찾은 경우
		if(index == 5) {
			int candi = curBoard.get_max();
			if(ans<candi) ans = candi;
			return;
		}
		for(int dir=0;dir<4;dir++) {
			 BOARD nextBoard = curBoard;//nextBoard에 현재의 보드 curBoard 복사
			 nextBoard.up();
			 dfs(index+1,nextBoard);
			 curBoard.rotate();//up 방향으로 재귀실행하고 난 후, 회전시키면 4가지 방향으로 모두 수행된다?!
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  null;
		
		N = Integer.parseInt(br.readLine());
		board = new BOARD();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board.map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 0;//최댓값을 찾기 위해 작은 값 셋팅.
		dfs(0,board);//모든 경우의 최댓값을 구해서 ans에 저장.
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