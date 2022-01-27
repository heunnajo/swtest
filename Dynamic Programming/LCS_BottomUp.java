import java.io.*;
import java.util.*;
public class LCS_BottomUp {
	static int[][] dp;
	static char[] str1,str2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();
		
		dp = new int[1001][1001];
		int len1 = str1.length, len2 = str2.length;
		
		for(int i=1;i<=len1;i++) {
			for(int j=1;j<=len2;j++) {
				//if(i-1<0 || j-1 <0) continue;//이렇게 컨티뉴처리를 해버리면 값이 제대로 채워지지 않을 것.
				if(str1[i-1] == str2[j-1]) {
					dp[i][j] = dp[i-1][j-1]+1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		System.out.println(dp[len1][len2]);
	}
	
}