package ss;
import java.util.*;
import java.io.*;
public class CirculationPermutation_NaiveString {
	
	public static void main(String[] args) throws Exception {
		//입력값 2개 밖에 없어서 스캐너 써도 될 것 같지만 br 습관화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer A = Integer.parseInt(br.readLine());
		String strB = br.readLine();
		Integer B = Integer.parseInt(strB);
		int len = strB.length();
				
		int answer = 0;
		for(int i=0;i<len;i++) {
			int res = A^B;
			String cur = String.valueOf(B);
			System.out.println("현재 B: "+cur);
			cur = cur.substring(1)+cur.charAt(0);//다음 순환 순열 만듦
			B = Integer.parseInt(cur);//정수로 형변환!
			System.out.println("변환 후 B: "+B);
			if(res == 0) answer++;
		}
		System.out.println(answer);
		//
	}

}
