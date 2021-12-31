import java.io.*;
import java.util.*;
public class Cubeditor {
	static int[] preprocessing(String p) {
        int m = p.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int j = 0;
        for (int i=1; i<m; i++) {
            while (j>0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j-1];
            }
            if (p.charAt(i) == p.charAt(j)) {
                pi[i] = j+1;
                j += 1;
            } else {
                pi[i] = 0;
            }
        }
        return pi;
    }
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		//s의 각 인덱스마다 부분 문자열을 구하고, KMP 수행, 최댓값 도출
		//각 부분 문자열의 최댓값들 중에서 최댓값이 정답이 된다!
		int n = s.length(); String a = ""; int ans = -1;//커봤자 prefix==suffix최장 문자열 길이는 최대 5000 보다 작으므로 int.
		for(int i=n-1;i>=0;i--) {
			a = s.charAt(i)+a;
			int[] pi = preprocessing(a);
			int m = pi.length;
			for(int j=0;j<m;j++) {
				if(ans<pi[j])ans = pi[j];
			}
		}
		System.out.println(ans);
	}

}