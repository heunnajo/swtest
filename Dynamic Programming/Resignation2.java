import java.util.*;
import java.io.*;
public class Resignation2 {
	static int n,t[],p[],d[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		t = new int[n+1];
		p = new int[n+1];
		d = new int[n+51];
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			t[i] = a; p[i] = b;
		}
		for(int i=1;i<=n;i++) {
			//if(i+t[i]>n+1 || i+1>n+1) continue;
			d[i+t[i]] = Math.max(d[i+t[i]], d[i]+p[i]);
			d[i+1] = Math.max(d[i+1], d[i]);
		}
		System.out.println(d[n+1]);
	}
	
}