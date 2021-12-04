package ss;
import java.util.*;
import java.io.*;
public class SubArraySum2 {
	static int N,S,arr[],up[],down[],mid,m;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		mid = N/2;
		m = N-mid+1;
		up = new int[mid];
		down = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//up의 모든 부분수열합 구하기
		for(int i=0;i<mid;i++) {
			
		}
		//up의 모든 부분수열합 구하기
		for(int i=mid;i<N;i++) {
			
		}
	}

}
