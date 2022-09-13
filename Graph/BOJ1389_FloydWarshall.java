package ss;
import java.util.*;
import java.io.*;
//케빈베이컨의 6단계 법칙 
public class BOJ1389_FloydWarshall {
	static int INF = 101;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N+1][N+1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				arr[i][j] = arr[j][i] = INF;
				if(i == j) arr[i][j] = 0;
			}
		}
		
		for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = arr[to][from] = 1;
        }
		
		for(int m=1;m<=N;m++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(arr[i][j] > arr[i][m] + arr[m][j])
						arr[i][j] = arr[i][m] + arr[m][j];
				}
			}
		}
		
		int min = 101;
		int num = 0;
		for(int i=1;i<=N;i++) {
			int temp = 0;
			for(int j=1;j<=N;j++) {
				temp += arr[i][j];
			}
			if(min > temp) {
				min = temp;
				num = i;
			}
		}
		
		System.out.println(num);
	}
	
}

//			System.out.println("u: "+u+"v: "+v);
//System.out.print("st: "+st);