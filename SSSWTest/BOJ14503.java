import java.io.BufferedReader;
import java.io.InputStreamReader;
//로봇청소기
public class BOJ14503 {
	static int ans,N,M,Map[][],Rx,Ry,Rd;//N:행,M:열, 로봇현재위치 rx,ry,rx
	static int[] dx = {-1,0,1,0};//북 동 남 서 
	static int[] dy = {0,1,0,-1};//북 동 남 서 
	static void solve(int x,int y,int d) {
		if(Map[x][y]==0) {
			Map[x][y] = 2;
			ans++;
		}
		boolean flag = false;
		int origin = d;
		for(int i=0;i<4;i++) {
			int nd = (d+3)%4;
			int nx = x+dx[nd],ny = y+dy[nd];
			if(nx<0 || ny<0 || nx>=N ||ny>M) continue;
			if(Map[nx][ny]==0) {
				solve(nx,ny,nd);
				flag = true;
				break;
			}
			d = (d+3)%4;
		}
		if(!flag) {
			int backD = (origin+2)%4;
			int backX = x+ dx[backD], backY = y+dy[backD];
			if(backX>0 && backY>0 || backX<N ||backY<M) {
				if(Map[backX][backY]!=1) {
					solve(backX,backY,origin);
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		Map = new int[N][M];
		
		input = br.readLine().split(" ");
		Rx = Integer.parseInt(input[0]);
		Ry = Integer.parseInt(input[1]);
		Rd = Integer.parseInt(input[2]);
	
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		solve(Rx,Ry,Rd);
		System.out.println(ans);
		br.close();
	}
}