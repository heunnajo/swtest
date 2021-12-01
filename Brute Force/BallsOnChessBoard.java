package ss;
import java.util.*;
import java.io.*;
public class BallsOnChessBoard {
	static int R,C,Ans[][],Arr[][];
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x; this.y = y;
		}
	}
	static Pair[][] dp;
	static int[] dx = {-1,1,0,0,-1,-1,1,1};
	static int[] dy = {0,0,-1,1,-1,1,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Arr = new int[R][C];//체스판 각 칸 숫자 저장
		Ans = new int[R][C];//(x,y)에 있는 공 갯수 저장
		dp = new Pair[R][C];//각 칸마다 이동할 위치로 가는 변위값 저장
		
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				Arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				moveBall(i,j,new Pair(-1,-1));
			}
		}
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				sb.append(Ans[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	static void moveBall(int x,int y,Pair end) {
		//1.dp 메모이제이션 먼저 확인!
		Pair e = dp[x][y];
		if(e != null) {
			Ans[e.x][e.y]++;
			end.x = e.x; end.y = e.y;
			return;
		}
		//2.최솟값 찾기
		int nx = -1,ny = -1; int min = Arr[x][y];
		for(int dir=0;dir<8;dir++) {
			int tmpX = x+dx[dir],tmpY = y+dy[dir];
			if(isOut(tmpX,tmpY)) continue;
			if(min>Arr[tmpX][tmpY]) {
				nx = tmpX; ny = tmpY;
				min = Arr[nx][ny];
			}
		}
		
		if(nx==-1) {
			dp[x][y] = new Pair(x,y);
			Ans[x][y]++;
			end.x = x; end.y = y;
			return;
		}
		moveBall(nx,ny,end);
		dp[x][y] = new Pair(end.x,end.y);
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>R-1 || y<0 || y>C-1;
	}
}
