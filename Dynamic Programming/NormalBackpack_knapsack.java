import java.io.*;
import java.util.*;
public class NormalBackpack_knapsack {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		//n개의 w,v를 저장할 배열
		int[] w = new int[101];
		int[] v = new int[101];
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			w[i]= Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] d = new int[101][100001];
		for(int i=1;i<=n;i++) {//물건의 인덱스
			for(int j=0;j<=k;j++) {//물건의 무게
				//1.i번 물건 챙기지 않는 경우
				d[i][j] = d[i-1][j];
				//2.i번 물건 챙기는 경우
				if(j-w[i]>=0) {
					d[i][j] = Math.max(d[i][j], d[i-1][j-w[i]]+v[i]);
				}
			}
		}
		System.out.println(d[n][k]);
	}

}

//		for(int i=0;i<n;i++) {
//			System.out.println(arr[i][0]+" "+ arr[i][1]);
//		}