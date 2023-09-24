import java.io.BufferedReader;
import java.io.InputStreamReader;

//숫자 카드
public class BOJ2591_Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] array = br.readLine().toCharArray();
		int len = array.length;
	    int[][] dp = new int[41][3];
	    int prev = (array[0] - '0') * 10;
	    dp[1][1] = 1;
	 
	    for (int i = 2; i <= len; i++) {
	        int v = array[i - 1] - '0';
	        if (v == 0) {
	            if (prev + v <= 34) {
	                dp[i][2] = dp[i - 1][1];  
	            }
	            continue;
	        }
	 
	        if (prev + v <= 34) {
	            dp[i][1] = dp[i - 1][2] + dp[i - 1][1];
	            dp[i][2] = dp[i - 1][1];
	        } else {
	            // 34보다 큰 수라면 십의 자리가 될 수 없다.
	            dp[i][1] = dp[i - 1][1] + dp[i - 1][2];
	        }
	        prev = v * 10;
	    }
	    System.out.println(dp[len][1] + dp[len][2]);
	}

}
