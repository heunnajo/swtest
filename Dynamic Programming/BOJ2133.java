//타일 채우기
import java.util.*;
import java.io.*;
public class BOJ2133 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] d = new int[N+1];
		d[0] = 1;
		
		for(int i=2;i<=N;i++) {
			d[i] = 3*d[i-2]; //d[2] = 3
			for(int j=i-4;j>=0;j-=2) {
				d[i] += d[j]*2;
			}
		}
		System.out.println(d[N]);
	}

}
