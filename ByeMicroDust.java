import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class ByeMicroDust {
	static int rx,R,C,T,Map[][],tmpMap[][];//rx는 공기청정기 시작행값!
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		Map = new int[R][C];
		
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
				if(Map[i][j] == -1 && Map[i-1][j] == -1) {//두칸 이상 떨어져있는 행에 위치하므로 i가 0부터 나올 순 없음. 고로 i-1이 -1에 접근할 수 없을 것임!
					rx = i-1;
				}
			}
		}
		
		for(int t=0;t<T;t++) {
			solve();
		}
		System.out.println(cntDust());
	}
	static void solve() {
		moveDust();
		clean();
	}
	static void moveDust() {
		tmpMap = new int[R][C];
		
		int cnt,amt;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				cnt = 0; amt = 0;//cnt:확산가능칸수 amt:확산양
				if(Map[i][j]>=5) {
					amt = Map[i][j]/5;
					for(int dir=0;dir<4;dir++) {
						int nx = i+dx[dir],ny = j+dy[dir];
						if(isOut(nx,ny)||Map[nx][ny] == -1) continue;
						cnt++;//(i,j)위치에서 확산가능한 칸 갯수 카운팅!
						tmpMap[nx][ny]+=amt;
					}
				}
				tmpMap[i][j] += Map[i][j]-amt*cnt;
			}
		}
		Map = tmpMap;
	}
	static boolean isOut(int x,int y) {
		return x<0 ||x>R-1 || y<0 || y>C-1;
	}
	static void clean() {
		//1.반시계방향.윗동네
		for(int i=rx-1;i>=1;i--)
			Map[i][0] = Map[i-1][0];
		for(int j=0;j<C-1;j++)
			Map[0][j] = Map[0][j+1];
		for(int i=0;i<rx;i++)
			Map[i][C-1] = Map[i+1][C-1];
		for(int j=C-1;j>=2;j--)
			Map[rx][j] = Map[rx][j-1];
		//2.시계방향.아랫동네
		for(int i=rx+2;i<R-1;i++)
			Map[i][0] = Map[i+1][0];
		for(int j=0;j<C-1;j++)
			Map[R-1][j] = Map[R-1][j+1];
		for(int i=R-1;i>=rx+2;i--)
			Map[i][C-1] = Map[i-1][C-1];
		for(int j=C-1;j>=2;j--)
			Map[rx+1][j] = Map[rx+1][j-1];
		
		Map[rx][1] = Map[rx+1][1] = 0;
	}
	static int cntDust() {
		int sum = 0;
		for(int i=0;i<R;i++)
			for(int j=0;j<C;j++)
				if(Map[i][j]>0) sum += Map[i][j];
		return sum;
	}
}