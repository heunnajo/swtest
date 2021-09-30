package ss;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//부순 벽돌 갯수를 카운팅한다.
public class CrashBrick_DFS_Solution {
	static int N,W,H,res,burstCnt;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class State{
		int x,y,cnt;
		public State(int x,int y,int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());//구슬 갯수
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int total = 0;//전체 벽돌 갯수
			int[][] Map = new int[H][W];
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					if(Map[i][j]>0) total++;
				}
			}
			res = Integer.MAX_VALUE;//최솟값 갱신을 위해 최댓값으로 셋팅
			process(0,total,Map);
			System.out.println("#"+t+" "+res);
		}
	}
	private static boolean process(int cnt,int remain,int[][] Map) {
		//남은 벽돌 갯수 확인:남은 벽돌 없으면 더 이상 확인할 필요 없으므로 true 리턴.
		if(remain == 0) {
			res = 0;
			return true;
		}
		//던질 수 있는 구슬 모두 던진 경우:false 리턴.
		if(cnt == N) {
			res = Math.min(res, remain);
			return false;
		}
		int[][] newMap = new int[H][W];
		//모든 열에 구슬 떨어뜨린다.
		for(int j=0;j<W;j++) {
			int i=0;
			//해당 열에서 가장 위 벽돌 위치 찾기
			while(i<H && Map[i][j] == 0)i++;
			if(i==H) continue;//벽돌이 없을 경우,다음 열j로 continue처리!
			
			//벽돌이 있을 경우
			//이전 구슬의 상태를 복사
			copy(Map,newMap);
			//해당 좌표로 구슬 떨어트려서 벽돌 터뜨리기
			burstCnt = 0;
			burst(newMap,i,j,newMap[i][j]);
			//벽돌 내리기
			down(newMap);
			//다음 꾸슬 처리(더이상 확인할 필요 없으면 true)
			if(process(cnt+1,remain-burstCnt,newMap))return true;
		}
		return false;
	}
	//cnt = newMap[x][y]
	private static void burst(int[][] map,int x,int y,int cnt) {
		//벽돌이 깨지면 0
		map[x][y] = 0;
		burstCnt++;
		//벽돌 숫자가 1이면 return:(x,y)에서 영향권 크기가 1이란 말은 현재칸(x,y)만 부수는 것이기 때문에!
		if(cnt==1)return;
		
		for(int d=0;d<4;d++) {
			int nx = x;
			int ny = y;
			
			//벽돌에 적힌 숫자-1만큼 영향 =>그냥 <map[x][y]로 하면 되는데 왜 굳이 1을 빼지?
			//현재 벽도로에 적힌 숫자(x,y)=Map[x][y]=5라고하면, 4만큼만 진행되기 때문에 0부터 시작하면 -1 빼줘야지!
			for(int k=0;k<cnt-1;k++) {
				nx += dx[d];ny+=dy[d];
				
				if(nx<0 || nx>=H || ny<0 || ny>=W || map[nx][ny] == 0) continue;//map[nx][ny]일 땐 왜 continue?
				burst(map,nx,ny,map[nx][ny]);
			}
		}
	}
	private static void down(int[][] map) {
		for(int j=0;j<W;j++) {
			int i = H-1;
			while(i>0) {
				//빈공간이라면
				if(map[i][j] == 0) {
					//직전행부터 탐색
					int nx = i-1;
					//처음 만나는 벽돌 찾기
					while(nx>0 && map[nx][j] == 0) nx--;
					
					map[i][j] = map[nx][j];//아래칸에 값 넣고
					map[nx][j] = 0;//현재칸은 0으로 만드는 듯?
				}
				i--;
			}
		}
	}
	private static void copy(int[][] map,int[][] newMap) {
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

}
