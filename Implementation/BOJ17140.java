package boj;
import java.io.*;
import java.util.*;

//이차원 배열과 연산
public class BOJ17140 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int R,C,K,time,answer,row,col;
		int[][] Arr = new int[100][100];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				Arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		time = 0;
		answer = -1;
		
		while(true) {//문제에서 제시된 조건(100 넘어가면 버린다)에 따라 반복 조건 설정 필요.
			if(checkRCK(Arr,R,C,K)) {
				answer = time;
				break;
			}
			
			row = getSize(Arr,0); col = getSize(Arr,1);
			if(row >= col) {
				operate(Arr,0);
			} else {
				operate(Arr,1);
			}
			
			time++;
			
		}
		
	}
	static boolean checkRCK(int[][] Arr,int R,int C,int K) {
		if(Arr[R-1][C-1] == K) return true;
		
		return false;
	}
	static int getSize(int[][] Arr, int flag) {
		int size = 0;
		
		
		return size;
	}
	static void operate(int[][] Arr, int flag) {
		
	}

}

//		for(int i=0;i<3;i++) {
//			for(int j=0;j<3;j++) {
//				System.out.print(Arr[i][j]+" ");
//			}
//			System.out.println();
//		}