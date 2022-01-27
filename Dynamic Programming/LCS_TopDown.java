import java.io.*;
import java.util.*;
public class LCS_TopDown {
	static Integer[][] dp;
	static char[] str1,str2;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str1 = br.readLine().toCharArray();//0~(len1-1)
		str2 = br.readLine().toCharArray();//0~(len2-1)
		
		int len1 = str1.length, len2 = str2.length;
		dp = new Integer[len1][len2];
		
		System.out.println(go(len1-1,len2-1));
	}
	static int go(int x,int y) {
		if(x == -1 || y==-1) return 0;
		
		if(dp[x][y] == null) {
			dp[x][y] = 0;
			if(str1[x] == str2[y]) {
				dp[x][y] = go(x-1,y-1)+1;
			}
			else {
				dp[x][y] = Math.max(go(x-1,y), go(x,y-1));
			}
		}
		return dp[x][y];
	}
}