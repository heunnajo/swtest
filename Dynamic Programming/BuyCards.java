package ss;
import java.io.*;
import java.util.*;
public class BuyCards {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] d = new int[n+1];
		int[] p = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		//1.초기값 설정
		d[0] = 0;//디폴트값이라 설정안해도될 것 같지만 형식상 해줌.
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=i;j++) {
				d[i] = Math.max(d[i],p[j]+d[i-j]);//기존의 d와 최댓값 비교해줘야함~!
			}
		}
		System.out.println(d[n]);
	}

}
//for(int i=1;i<=n;i++) {
//			System.out.println(p[i]+" ");
//		}