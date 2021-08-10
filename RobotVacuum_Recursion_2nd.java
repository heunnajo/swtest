package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class RobotVacuum_Recursion_2nd {
	static int ans,N,M,Map[][],Rx,Ry,Rd;//N:행,M:열, 로봇현재위치 rx,ry,rx
	static int[] dx = {-1,0,1,0};//북 동 남 서 
	static int[] dy = {0,1,0,-1};//북 동 남 서 
	static void solve(int x,int y,int d) {
		//정답 찾은 경우:재귀적으로 구현하다보니, 정답 찾은 경우 조건 설정해서 구현!
		//마지막 재귀함수에서는 호출도딘 재귀함수에서 아무것도 실행하지 않고 리턴되고, 거기서부터 본격적인 재귀함수 리턴이 시작다!!
		if(Map[x][y]==0) {
			Map[x][y] = 2;
			ans++;
		}
		boolean flag = false;//아래 i-for문으로 4방으로 반시계방향 탐색 하면 true로 바꾼다!
		int origin = d;
		for(int i=0;i<4;i++) {//왼쪽 방향부터 재귀적으로 탐색!
			int nd = (d+3)%4;//왼쪽 방향.
			int nx = x+dx[nd],ny = y+dy[nd];
			if(nx<0 || ny<0 || nx>=N ||ny>M) continue;
			if(Map[nx][ny]==0) {//재귀함수이기 때문에 조건 걸어서 만족할 때만 재귀호출&연산 처리 한다!
				solve(nx,ny,nd);
				flag = true;
				break;//재귀함수가 리턴되고 나면 현재 함수도 종료!
			}
			d = (d+3)%4;//왼쪽 방향 청소 못하면 왼쪽 방향으로 방향 갱신!
		}
		//2-c.2-d :4가지 방향 청소 이미 했거나 벽인 경우!
		if(!flag) {//반시계 방향 탐색하지 못해서 flag가 여전히 false로 남아있다면 뒤로 한칸 후진!
			int backD = (origin+2)%4;//뒷방향.
			int backX = x+ dx[backD], backY = y+dy[backD];
			if(backX>0 && backY>0 || backX<N ||backY<M) {
				if(Map[backX][backY]!=1) {//벽이 아니면 1칸 후진!
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
