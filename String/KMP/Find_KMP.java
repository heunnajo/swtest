package KMP;
import java.io.*;
import java.util.*;
public class Find_KMP {
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
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str_s = br.readLine();
		String str_p = br.readLine();
		ArrayList<Integer> ans = solve(str_s,str_p);

		System.out.println(ans.size());
		for(int index:ans) {
			System.out.print((index+1)+" ");
		}
	}

}