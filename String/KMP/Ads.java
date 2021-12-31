import java.io.*;
import java.util.*;
public class Ads {
	static int[] preprocessing(char[] p) {
		int n = p.length;
		int[] pi = new int[n];
		pi[0] = 0;//안해줘도 어차피 0인 값 아닌감..
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
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String s = br.readLine();
		int[] pi = preprocessing(s.toCharArray());
		System.out.println(L-pi[L-1]);
	}

}