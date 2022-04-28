//BOJ #16918 봄버맨
import java.util.*;
import java.io.*;
public class Main {
	static int R,C,N;
	static int[][] Map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] v;
	
	static boolean isOut(int x,int y) {
		return x<0 || x>=R || y<0 || y>=C;
	}
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		Map = new int[R][C];
		for(int i=0;i<R;i++) Arrays.fill(Map[i], -1);
		
		v = new boolean[R][C];
		
		String[] input = new String[R];
		for(int i=0;i<R;i++) {  
			input[i] = br.readLine();
		}
		
		int time = 0;//현재 시간
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(input[i].charAt(j) == 'O') Map[i][j] = time+3;
			}
		}
		
        time++;
		while(time<N) {
            time++;
			if(time%2 == 0) {
				installBomb(time);
			} else if(time%2 == 1) {
				explodeBomb(time);
			}
            
		}
		
		print();
		
	}
	static void installBomb(int time) {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(Map[i][j] == -1) Map[i][j] = time+3;
			}
		}
	}
	static void explodeBomb(int time) {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(Map[i][j] == time) {
					Map[i][j] = -1;
					for(int d=0;d<4;d++) {
						int nx = i+dx[d], ny = j+dy[d];
						if(isOut(nx,ny)) continue;
						if(Map[nx][ny] == time) continue;
						Map[nx][ny] = -1;
					}
				}
			}
		}
	}
	static void print() {
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(Map[i][j] == -1) sb.append(".");
				else sb.append("O");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}