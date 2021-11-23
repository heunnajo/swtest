import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RockScissorsPaper_fasterSolution {
	static int N, K;
	static int A[][] = new int[10][10];
	static int GM[][] = new int[3][21];
	static boolean visit[] = new boolean[10];

	// 지우 경희 민호
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 20; i++) {
			GM[1][i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 20; i++) {
			GM[2][i] = Integer.parseInt(st.nextToken());
		}

		// 지우 경희 민호
		if (dfs(2, 0, 0, 0, 0, 0, 0)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}

	}

	public static boolean dfs(int late, int cnt, int k1, int k2, int k3, int cnt2, int cnt3) {
		if (k2 == K || k3 == K)
			return false;

		if (k1 == K) {
			return true;
		}
		if (cnt == 20)
			return false;

		// 앞에가 이기면 true 뒤에가 이기면 false
		boolean win = false;
		if (late == 2) {
			// 0 1싸움
			for (int i = 1; i <= N; i++) {
				if (visit[i])
					continue;
				visit[i] = true;
				win = Win(i, GM[1][cnt2]);
				if (win) {
					if (dfs(1, cnt + 1, k1 + 1, k2, k3, cnt2 + 1, cnt3))
						return true;
				} else {
					if (dfs(0, cnt + 1, k1, k2 + 1, k3, cnt2 + 1, cnt3))
						return true;
				}
				visit[i] = false;
			}
		} else if (late == 1) {
			// 0 2 싸움
			for (int i = 1; i <= N; i++) {
				if (visit[i])
					continue;
				visit[i] = true;
				win = Win(i, GM[2][cnt3]);
				if (win) {
					if (dfs(2, cnt + 1, k1 + 1, k2, k3, cnt2, cnt3 + 1))
						return true;
				} else {
					if (dfs(0, cnt + 1, k1, k2, k3 + 1, cnt2, cnt3 + 1))
						return true;
				}
				visit[i] = false;
			}

		} else if (late == 0) {
			win = Win(GM[1][cnt2], GM[2][cnt3]);
			if (win) {
				if (dfs(2, cnt + 1, k1, k2 + 1, k3, cnt2 + 1, cnt3 + 1))
					return true;
			} else {
				if (dfs(1, cnt + 1, k1, k2, k3 + 1, cnt2 + 1, cnt3 + 1))
					return true;
			}
		}

		return false;
	}

	static private boolean Win(int a, int b) {
		if (A[a][b] == 2) {
			return true;
		}
		return false;
	}

}