package ss;
import java.util.*;
import java.io.*;
public class CirculationPermutation_KMP {
	static int[] preprocessing(char[] p) {
		int n = p.length;
		int[] pi = new int[n];
		pi[0] = 0;//없어도 되지만 명시적으로 셋팅해준다.
		int j = 0;
		for(int i=1;i<n;i++) {
			while(j>0 && p[i] != p[j]) j = pi[j-1];
			if(p[i] == p[j]) {
				pi[i] = j+1;
				j++;
			} else {pi[i] = 0;}
		}
		return pi;
	}
	static ArrayList<Integer> solve(String str_s,String str_p){
		char[] s = str_s.toCharArray();
		char[] p = str_p.toCharArray();
		int[] pi = preprocessing(p);
		ArrayList<Integer> ans = new ArrayList<>();
		int n = str_s.length(), m = str_p.length();
		int j=0;
		//pi를 이용해서 위의 preprocessing과 유사하게 구현할 수 있다!
		for(int i=0;i<n;i++) {
			while(j>0 && s[i] != p[j]) {j=pi[j-1];}
			if(s[i] == p[j]) {
				if(j == m-1) {
					ans.add(i-m+1);
					j = pi[j];
				} else {j++;}
			}
		}
		return ans;
	}
	public static void main(String[] args) throws Exception {
		//입력값 2개 밖에 없어서 스캐너 써도 될 것 같지만 br 습관화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		int len = B.length();
		//문자열의 순환 순열 아이디어 : 2번 이어붙인다!
		String realB = B + B;
		len *= 2;//2번 붙였으니 길이 변화!
		//2번 이어붙인 p(B)에 대해 s(A)가 있는지 판단해본다!
		char[] bArr = B.toCharArray();
		char[] aArr = A.toCharArray();
		int j=0;
		int[] pi = preprocessing(aArr);
		int tmpCnt = 0;
		for(int i=1;i<len-1;i++) {
			while(j>0 && aArr[i] != bArr[j]) j = pi[j-1];
			if(aArr[i] == bArr[j]) {
				tmpCnt++;
			} else {
				pi[i] = 0;
			}
		}
		int answer = 0;
		
	}

}
