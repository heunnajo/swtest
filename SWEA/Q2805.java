package swea;
import java.util.*;
import java.io.*;
//농작물 수확하기
public class Q2805 {
	static int N,Sum;
	static int[][] Map;
	static boolean[][] Visited;
	static int[] dx = {-1,1,1,-1};
	static int[] dy = {1,1,-1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=tc;t++) {
			N = Integer.parseInt(br.readLine());
			Sum = 0;
			if(N == 0) {
				sb.append("#").append(t).append(" ").append(Sum).append("\n");
				continue;
			}
			Map = new int[N][N];
			Visited = new boolean[N][N];
			
			for(int i=0;i<N;i++) {
				String[] input = br.readLine().split("");
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			solve();//Sum을 합산
			sb.append("#").append(t).append(" ").append(Sum).append("\n");
		}
		System.out.print(sb.toString());
	}
	static void solve() {
		Sum = 0;         
		int count = N/2;
		
		int nx = N/2, ny = 0;
		
		for(int i=0;i<N/2;i++) {
//			if(nx == N/2 && ny == N/2) {//완전히 끝남. 리턴해서 종료! 
//				Sum += Map[nx][ny];
//				return;
//			}
			for(int dir = 0;dir<4;dir++) {
				for(int j=0;j<count;j++) {
					Sum += Map[nx][ny];
					Visited[nx][ny] = true;
					nx += dx[dir]; ny += dy[dir];
				}
			}
			ny++;
			count--;
			//System.out.println("Sum: "+Sum);
		}
		Sum += Map[N/2][N/2];
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=N;
	}
}

//			for(int i=0;i<N;i++) {
//				for(int j=0;j<N;j++) {
//					System.out.print(Map[i][j]+" ");
//				}
//				System.out.println();
//			}

//if(isOut(nx,ny)) {
//	System.out.println("현재의 round: "+i);
//	System.out.println("현재의 dir: "+dir);
//	System.out.println("현재의 좌표(nx,ny): "+nx+","+ny);
//}