//가르침
package cdTest;

import java.util.*;
import java.io.*;
public class BOJ1062 {
	static int Ans,N,K;
	static boolean[] Alpha;
	static String[] Word;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		Ans = 0;
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		Word = new String[N];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			str = str.replace("anta", "");
			str = str.replace("tica", "");
			
			//System.out.println("str: "+str);
//			for(int j=0;j<str.length();j++) {
//				Alpha[str.charAt(j)-'a'] = true;
//			}
			Word[i] = str;
		}
//		System.out.print("Word: ");
//		for(int i=0;i<N;i++) {
//			System.out.print(Word[i]+", ");
//		}
		if(K == 26) {
			System.out.println(N);
			return;
		} else if(K < 5) {
			System.out.println(0);
			return;
		}
		
		Alpha = new boolean[26];
		//a,n,t,i,c true로 처리
		Alpha['a'-'a'] = true;
		Alpha['n'-'a'] = true;
		Alpha['t'-'a'] = true;
		Alpha['i'-'a'] = true;
		Alpha['c'-'a'] = true;
		
		go(0,0);
		
		System.out.println(Ans);
	}
	static void go(int idx,int from) {
		//1. 종료 조건 : K-5개 다 고른 경우
		if(idx == K-5) {
			//System.out.println("여기 들어오니?");
			//읽을 수 있는 단어 갯수 구하고, 최댓값 도출
			int count  =  0;
			for(int i=0;i<N;i++) {
				boolean read = true;
				for(int j=0;j<Word[i].length();j++) {
					if(!Alpha[Word[i].charAt(j)-'a']) {
						read = false;
						break;
					}
				}
				if(read) count++;
			}
			Ans = Math.max(Ans, count);
			return;
		}
		
		//2.현재 경우 선택, 다음 경우 호출
		for(int i=from;i<26;i++) {
			if(!Alpha[i]) {
				Alpha[i] = true;
				go(idx+1,i);
				Alpha[i] = false;
			}
			
		}
	}
	
}
//		System.out.println("N: "+N+"K: "+K);