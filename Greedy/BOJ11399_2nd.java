//ATM
import java.util.*;
import java.io.*;

public class BOJ11399 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] P = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(P);
		int sum = 0;
		int ans = 0;
		for(int i=0;i<N;i++) {
			sum += P[i];
			ans += sum;
		}
		System.out.println(ans);
	}

}

//		for(int i=0;i<N;i++) {
//			System.out.print(P[i]+" ");
//		}