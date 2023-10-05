package boj;
import java.util.*;
import java.io.*;
//미세먼지 안녕!

public class BOJ17144_2nd {
	static int R,C,T,Cleaner;//Cleaner:공기청정기 시작행번호
	static int[][] Dust;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		Dust = new int[R][C];
		Cleaner = -1;
		String[] input;
		for(int i=0;i<R;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<C;j++) {
				Dust[i][j] = Integer.parseInt(input[j]);
				if(Dust[i][j] == -1 && Cleaner == -1) Cleaner = i;
			}
		}
		//System.out.println("Cleaner :"+Cleaner);
		
		for(int i=0;i<T;i++) {
			//System.out.println((i+1)+"초");
			spread();
			//print();
			clean();
			//print();
			//System.out.println("=============================");
		}
		
		System.out.println(countDust());
	}
	static void print() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(Dust[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=R || y<0 || y>=C;
	}
	static void spread() {
		int count = 0;
		int[][] amount = new int[R][C];
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(Dust[i][j] <= 0) continue;
				
				count = 0;//확산되는 칸 수. 
				for(int d=0;d<4;d++) {
					int nx = i+dx[d]; int ny = j+dy[d];
					if(isOut(nx,ny) || (nx == Cleaner&&ny==0) || ((nx == Cleaner+1)&&ny==0)) continue;
					
					count++;
					amount[nx][ny] += Dust[i][j]/5;
				}
				amount[i][j] += (Dust[i][j] - (Dust[i][j]/5)*count);
			}
		}
		Dust = amount;
	}
	static void clean() {
		//윗부분
		//1.아래. 열고정(0)
		for(int i=Cleaner-1;i>0;i--) {
			Dust[i][0] = Dust[i-1][0];
		}
		//2.왼. 행고정(0)
		for(int j=0;j<C-1;j++) {
			Dust[0][j] = Dust[0][j+1];
		}
		//3.위. 열고정(C-1)
		for(int i=0;i<Cleaner;i++) {
			Dust[i][C-1] = Dust[i+1][C-1];
		}
		//4.오른쪽. 행고정(Cleaner, 공청기시작행)
		for(int j=C-1;j>0;j--) {
			Dust[Cleaner][j] = Dust[Cleaner][j-1];
		}
		Dust[Cleaner][1] = 0;
		//아랫부분
		//1.위. 열고정(0)
		for(int i=Cleaner+2;i<R-1;i++) {
			Dust[i][0] = Dust[i+1][0];
		}
		//2.왼. 행고정(R-1)
		for(int j=0;j<C-1;j++) {
			Dust[R-1][j] = Dust[R-1][j+1];
		}
		//3.아래. 열고정(C-1)
		for(int i=R-1;i>=Cleaner+2;i--) {
			Dust[i][C-1] = Dust[i-1][C-1];
		}
		//4.오른쪽. 행고정(Cleaner+1)
		for(int j=C-1;j>0;j--) {
			Dust[Cleaner+1][j] = Dust[Cleaner+1][j-1];
		}
		Dust[Cleaner+1][1] = 0;
	}
	static int countDust() {
		int sum = 0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(Dust[i][j]>0) sum += Dust[i][j];
			}
		}
		return sum;
	}

}
