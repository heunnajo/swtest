package ss;
//문자열 폭발
import java.util.*;
import java.io.*;
public class BOJ9935 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String t = br.readLine();
		
		int Slen = s.length();
		int Tlen = t.length();
		boolean[] isDeleted = new boolean[Slen];
		
		char tmp = t.charAt(0);
		if(Tlen == 1) {
			for(int i=0;i<Slen;i++) {
				if(s.charAt(i) == tmp) isDeleted[i] = true;
			}
		} else {
			Stack<Integer> stA = new Stack<>();
			Stack<Integer> stB = new Stack<>();
			for(int i=0;i<Slen;i++) {
				if(s.charAt(i) == t.charAt(0)) {//1.폭발 문자열의 시작
					stA.push(i); stB.push(0);
				} else {//2.폭발 문자열의 연속
					if(!stB.isEmpty()) {//빼먹은 부분
						int prevIdx = stB.peek();
						if(s.charAt(i) == t.charAt(prevIdx+1)) {
							stA.push(i); stB.push(prevIdx+1);
							
							if(prevIdx+1 == Tlen-1) {//문자열 폭발 발생
								for(int cnt=0;cnt<Tlen;cnt++) {
									isDeleted[stA.pop()] = true;
									stB.pop();
								}
							}
						} else {//1과 2 모두 만족X :절대 폭발 문자열이 될 수 없음
							stA.clear(); stB.clear();
						}
						
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;//빼먹은 부분
		
		for(int i=0;i<Slen;i++) {
			if(!isDeleted[i]) {
				sb.append(s.charAt(i));
				flag = true;
			}
		}
		
		if(flag) System.out.println(sb);
		else System.out.println("FRULA");
	}

}
