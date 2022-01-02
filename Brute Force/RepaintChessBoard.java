package ss;
import java.io.*;
import java.util.*;
public class RepaintChessBoard {
	static int N,M;
	static char Board[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>M-1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Board = new char[N][M];//N:행,M:열
		
		for(int i=0;i<N;i++) {
			Board[i] = br.readLine().toCharArray();
		}
		int ans = 987654321;//최솟값 도출 위해 큰 값 셋팅!
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(isOut(i+7,j+7))continue;
				int tmp = countPainting(i,j);//(i,j)시작으로 체스판 자르기!
				ans = Math.min(ans, tmp);
			}
		}
		System.out.println(ans);
	}
	static int countPainting(int sx,int sy) {
		int cnt = 0;
		Queue<Integer> q = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		q.add(sx); q.add(sy);
		v[sx][sy] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll(),y = q.poll();
			for(int d=0;d<4;d++) {
				int nx = x+dx[d], ny = y+dy[d];
				if(isOut(nx,ny) || v[nx][ny]) continue;
				if(Board[nx][ny] == Board[x][y]) {
					cnt++;
				}
				q.add(nx); q.add(ny);
				v[nx][ny] = true;
			}
		}
		
		return  cnt;
	}
}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(Board[i][j]+" ");
//			}
//			System.out.println();
//		}