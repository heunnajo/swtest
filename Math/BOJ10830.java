package ss;
//행렬 제곱
import java.io.*;
import java.util.*;
public class BOJ10830 {
	static int N, base[][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		base = new int[N][N];
		
		for(int i=0;i<N;i++) {
			//N개의 원소를 입력받아서 저장
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				base[i][j] = Integer.parseInt(st.nextToken()) % 1000;//?!
			}
		}
		int[][] ret = pow(base,B);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				sb.append(ret[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	static int[][] pow(int[][] A,long exp){
		//1.재귀 종료 : exp ==1
		if(exp == 1L) return base;
		
		//2.현재 경우 연산
		int[][] ret = pow(A,exp/2);
		ret = multiply(ret,ret);
		
		if(exp % 2 == 1L) {//1L
			ret = multiply(ret,base);
		}
		return ret;
	}
	static int[][] multiply(int[][] m1,int[][] m2){
		int[][] ret = new int[N][N];
		
		//연산 결과를 ret[i][j]에 아래 반복문 순서대로 저장!
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					ret[i][j] += m1[i][k] * m2[k][j];
					ret[i][j] %= 1000;
				}
			}
		}
		return ret;
	}
}

//		System.out.println("N: "+N);
//		System.out.println("B: "+B);

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(base[i][j]+" ");
//			}
//			System.out.println();
//		}