package boj;
import java.util.*;
import java.io.*;
//미세먼지 안녕!
public class BOJ17144 {
	static int R,C,T,Ans,Row;//Row :공기청정기 시작 행번호
	static int[][] Room;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		Room = new int[R][C];
		boolean isFirst = true;
		
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				Room[i][j] = Integer.parseInt(st.nextToken());
					
				if(isFirst == true && Room[i][j] == -1) {
					Row = i; isFirst = false;
				}
			}
		}
		//System.out.println("Row: "+Row);
		Ans = 0;
		while(T-- >0) {
			fineDustMove(); //print();
			cleanerWork(); //print();
		}
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(Room[i][j] > 0) {Ans += Room[i][j];}
			}
		}
		
		System.out.println(Ans);
	}
	static void fineDustMove() {
		int[][] newRoom = new int[R][C];
		
		//5초과인 칸들은 미세먼지 확산하는 칸!
		int count,amount;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
//				if(Room[i][j] < 5) {
//					newRoom[i][j] = Room[i][j];
//					continue;
//				}
				count = 0; amount = 0;
				if(Room[i][j] >= 5) {
					amount = Room[i][j]/5;
					for(int d=0;d<4;d++) {
						int nx = i+dx[d]; int ny = j+dy[d];
						
						if(isOut(nx,ny)) continue;
						if(Room[nx][ny] == -1) continue;
						
						newRoom[nx][ny] += amount;
						count++;
						
					}
					
				}
				newRoom[i][j] += Room[i][j] - amount*count;//Room[i][j]<5인 경우 여기서 처리됨.
			}
		}
		Room = newRoom;
	}
	static void print() {
		System.out.println();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(Room[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
	}
	static void cleanerWork() {
		//윗부분 : 하->좌->상->우
		//하
		for(int i=Row-1;i>0;i--) {//행버너
			Room[i][0] = Room[i-1][0];
		}
		//좌
		for(int j=0;j<C-1;j++) {
			Room[0][j] = Room[0][j+1];//행번호 틀림.
		}
		//상
		for(int i=0;i<Row;i++) {
			Room[i][C-1] = Room[i+1][C-1];
		}
		//우
//		for(int j=C-1;j<1;j--) {
		for(int j=C-1;j>1;j--) {
			Room[Row][j] = Room[Row][j-1];
		}
		//아랫부분 : 상->좌->하->우
		//상
		for(int i=Row+2;i<R-1;i++) {//i시작값 틀림.
			Room[i][0] = Room[i+1][0];
		}
		//좌
		for(int j=0;j<C-1;j++) {
			Room[R-1][j] = Room[R-1][j+1];
		}		
		//하
		for(int i=R-1;i>Row+1;i--) {
			Room[i][C-1] = Room[i-1][C-1];
		}
		//우
//		for(int j=C-1;j<1;j--) {
		for(int j=C-1;j>1;j--) {
			Room[Row+1][j] = Room[Row+1][j-1];
		}
		//공기청정기 지난 칸은 정화됨 : 먼지양 0
		Room[Row][1] = Room[Row+1][1] = 0;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>R-1 || y<0 || y>C-1;
	}

}
