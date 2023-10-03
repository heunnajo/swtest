//걷기
import java.util.*;
import java.io.*;
public class BOJ1459 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
//		int x,y,w,s;
//		x = Integer.parseInt(st.nextToken());
//		y = Integer.parseInt(st.nextToken());
//		w = Integer.parseInt(st.nextToken());
//		s = Integer.parseInt(st.nextToken());
		long x,y,w,s;
		x = Long.parseLong(st.nextToken());
		y = Long.parseLong(st.nextToken());
		w = Long.parseLong(st.nextToken());
		s = Long.parseLong(st.nextToken());
		
		//1.평행 이동으로만 이동
		long ans1 = (x+y) * w;
		
		//2.대각선 이동으로만 이동
		long ans2;
		if((x+y) % 2 == 0) {
			ans2 = Math.max(x, y) * s;
		} else {
			ans2 = (Math.max(x, y)-1) * s + w;
		}
		//2.대각선 이동 + 평행 이동
		long ans3 = Math.min(x,y) * s + Math.abs(x-y) * w;
		
		System.out.println(Math.min(Math.min(ans1, ans2),ans3));
	}

}
