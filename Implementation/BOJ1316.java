package ss;
import java.util.*;
import java.io.*;

public class BOJ1316 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		HashSet<Character> set;
		
		int cnt = N;
		
		for(int i=0;i<N;i++) {
			
			String s = br.readLine();
			set = new HashSet<>();
			set.add(s.charAt(0));
			for(int j=1;j<s.length();j++) {
				
				char cur = s.charAt(j);
				if(!set.contains(cur)) set.add(cur);
				else if(set.contains(cur) && cur != s.charAt(j-1)) {
//					System.out.println("현재 문자: "+s);
//					System.out.println("cur: "+cur);
					cnt--;
					break;
				}
			}
		}
		
		System.out.println(cnt);

	}

}
