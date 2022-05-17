//바이러스
import java.util.*;
import java.io.*;
public class Q4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int mod = 1000000007;
		long[] d = new long[n+1];
		d[0] = k;
		
		for(int i=1;i<=n;i++) {
			d[i] = (d[i-1]*p)%mod;
		}
		//for(int i=1;i<=n;i++) 
		System.out.println(d[n]);
	}

}
