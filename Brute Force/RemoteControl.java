package ss;
import java.util.*;
import java.io.*;
public class RemoteControl {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		//예외 케이스1.n == 100인 경우
		if(n==100) {
			System.out.println(0);
			System.exit(0);
		}
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		//예외 케이스2.고장난 버튼이 없을 경우
		if(m==0) {
			System.out.println(String.valueOf(n).length());//n자릿수 그대로!
			System.exit(0);
		}
		boolean[] impossible = new boolean[10];
		st = new StringTokenizer(br.readLine());
		int ans = 987654321;
		for(int i=0;i<m;i++) {
			int x = Integer.parseInt(st.nextToken());
			impossible[x] = true;
		}
		
		for(int i=0;i<=999999;i++) {
			String str = String.valueOf(i);
			int len = str.length();
			boolean okay = true;
			for(int j=0;j<len;j++) {
				if(impossible[str.charAt(j)-'0']) {
					okay = false;
					break;
				}
			}
			if(okay) {
				int cnt = len+Math.abs(i-n);
				ans = Math.min(ans, cnt);
			}
		}
		System.out.println(ans);
	}
	
}
//		System.out.println(n);
//		System.out.println(m);
//		for(int i=0;i<10;i++) {
//			if(impossible[i]) System.out.print(i+" ");
//		}
//		System.out.println();
