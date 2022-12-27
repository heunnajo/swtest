package boj;
//행렬
import java.util.*;
import java.io.*;
public class BOJ1080 {
	static int N,M;//N:행 크기, M:열 크기
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] arr1 = new int[N][M];
		int[][] arr2 = new int[N][M];
		
		String input;
		char[] tmp = new char[M];
		for(int i=0;i<N;i++) {
			input = br.readLine();
			tmp = input.toCharArray();
			for(int j=0;j<M;j++) {
				arr1[i][j] = tmp[j]-'0';
			}
		}
		for(int i=0;i<N;i++) {
			input = br.readLine();
			tmp = input.toCharArray();
			for(int j=0;j<M;j++) {
				arr2[i][j] = tmp[j]-'0';
			}
		}
		
		int cnt = 0;
		for(int i=0;i<=N-3;i++) {
			for(int j=0;j<=M-3;j++) {
				if(arr1[i][j] != arr2[i][j]) {
					trans(arr1,i,j);
					cnt++;
				}
			}
		}
		
		if(isSame(arr1,arr2)) {
			System.out.println(cnt);
		} else {System.out.println(-1);}
		
	}
	static void trans(int[][] arr,int sx,int sy) {
		//(sx,sy)위치에서 3*3 크기 부분 반전 연산한다.
		for(int i=sx;i<sx+3;i++) {
			for(int j=sy;j<sy+3;j++) {
				//arr[i][j] = 1 - arr[i][j];
				arr[i][j] ^= 1;
			}
		}
	}
	static boolean isSame(int[][] arr1,int[][] arr2) {
		//비교하는 부분도 처음부터 할 필요 없이 (N-3),(M-3)까지는 비교를 했으니 그 다음부터 비교함으로써 복잡도를 줄일 수 있을 것 같은데.
		//틀린 이유 : 그리디하게 계속 반전시켜나가게 된다면 N-3,M-3 크기 밖 뿐만 아니라 그 내부에서도 값이 바뀐다.
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arr1[i][j] != arr2[i][j]) return false;
			}
		}
		return true;
	}

}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(arr1[i][j]+" ");
//			}
//			System.out.println();
//		}
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(arr2[i][j]+" ");
//			}
//			System.out.println();
//		}