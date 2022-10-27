package boj;
import java.util.*;
import java.io.*;
//수들의 합
public class BOJ1789 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long s = Long.parseLong(br.readLine());
		int cnt = 0;
		
		long i = 1;
		
		while(s >= 0) {
			//System.out.println("s: "+s+" i: "+i+" cnt: "+cnt);
			s -= i;
			i += 1;
			cnt += 1;
//			System.out.println();
//			System.out.println();
		}
		cnt -= 1;
		System.out.println(cnt);
		

	}

}
