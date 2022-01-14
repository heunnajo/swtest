package ss;
import java.io.*;
import java.util.*;
public class BuyCards2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int INF = 987654321;
		int n = Integer.parseInt(br.readLine());
		int[] d = new int[n+1];
		int[] p = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		//1.초기값 설정
		for(int i=1;i<=n;i++) {
			d[i] = INF;
		}
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=i;j++) {
				d[i] = Math.min(d[i],p[j]+d[i-j]);//기존의 d와 최댓값 비교해줘야함~!
			}
		}
		System.out.println(d[n]);
	}

}
//for(int i=1;i<=n;i++) {
//			System.out.println(p[i]+" ");
//		}