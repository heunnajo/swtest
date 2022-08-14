import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ11060 {
	public static int N;
	public static int[] A;

	public static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];
		dp = new int[N + 1];

		String[] token = br.readLine().split(" ");

		for (int i = 1; i <= N; ++i) {
			A[i] = Integer.parseInt(token[i - 1]);
		}

		Arrays.fill(dp, -1);
		dp[1] = 0;

		for (int i = 1; i <= N; ++i) {
			if (dp[i] == -1) {
				continue;
			}

			int limit = Math.min(i + A[i], N);

			for (int j = i + 1; j <= limit; ++j) {
				if (dp[j] == -1 || dp[i] + 1 < dp[j]) {
					dp[j] = dp[i] + 1;
				}
			}
		}

		System.out.println(dp[N]);

		br.close();
	}
}
