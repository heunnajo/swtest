import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StartTaxi_Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		int[][] arr = new int[N + 1][N + 1];
		int[][] dp = new int[N + 1][N + 1];
		int total = 0;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j = 1; j <= N; j++) {
				total += arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i][j];
			}
		}
		int sum, max, min, sum4, realMin = Integer.MAX_VALUE;
		for(int i = 1; i <= N - 2; i++) {
			for(int j = 2; j <= N - 1; j++) {
				for(int k = 1; j - k > 0; k++) {
					for(int l = 1; j + l <= N && i + k + l <= N; l++) {
						sum = 0;
						sum4 = 0;
						if(i > 1) {
							sum += dp[i - 1][j];
						}
						for(int m = i, n = j - 1; m < i + k; m++, n--) {
							sum += dp[m][n] - dp[m - 1][n] - dp[m][0] + dp[m - 1][0];
						}
						max = sum;
						min = sum;
						sum4 += sum;
						sum = 0;
						if(j + l < N) {
							sum += dp[i + l][N] - dp[0][N] - dp[i + l][j + l] + dp[0][j + l];
						}
						for(int m = j + l, n = i + l - 1; m > j; m--, n--) {
							sum += dp[n][m] - dp[0][m] - dp[n][m - 1] + dp[0][m - 1];
						}
						max = Math.max(max, sum);
						min = Math.min(min, sum);
						sum4 += sum;
						sum = 0;
						if(i + k + l < N) {
							sum += dp[N][N] - dp[i + k + l][N] - dp[N][j + l - k - 1] + dp[i + k + l][j + l - k - 1];
						}
						for(int m = i + k + l, n = j + l - k + 1; m > i + l; m--, n++) {
							sum += dp[m][N] - dp[m - 1][N] - dp[m][n - 1] + dp[m - 1][n - 1];
						}
						max = Math.max(max, sum);
						min = Math.min(min, sum);
						sum4 += sum;
						sum = 0;
						if(j - k > 0) {
							sum += dp[N][j - k - 1] - dp[i + k - 1][j - k - 1] - dp[N][0] + dp[i + k - 1][0];
						}
						for(int m = j - k, n = i + k + 1; m < j + l - k; m++, n++) {
							sum += dp[N][m] - dp[n - 1][m] - dp[N][m - 1] + dp[n - 1][m - 1];
						}
						max = Math.max(max, sum);
						min = Math.min(min, sum);
						sum4 += sum;
						sum = total - sum4;
						max = Math.max(max, sum);
						min = Math.min(min, sum);
						realMin = Math.min(realMin, max - min);
					}
				}
			}
		}
		System.out.println(realMin);
	}

}
