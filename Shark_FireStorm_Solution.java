import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Shark_FireStorm_Solution {

	static int powN, A[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		powN = (int) Math.pow(2, N);
		A = new int[powN][powN];
		int[] L = new int[Q];

		for (int i = 0; i < powN; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < powN; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < Q; i++) {
			rotate(L[i]);
			melt();
		}

		System.out.println(getTotalIce());
		System.out.println(getBigIce());
	}

	private static void print() {
		for (int[] is : A) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
		System.out.println();
	}


private static int getBigIce() {
	int answer = 0;

	Queue<int[]> que = new LinkedList<>();
	boolean[][] visit = new boolean[powN][powN];

	for (int i = 0; i < powN; i++) {
		for (int j = 0; j < powN; j++) {
			if (A[i][j] == 0 || visit[i][j])
				continue;
			que.clear();
			que.add(new int[] { i, j });
			visit[i][j] = true;
			int size = 1;

			while (!que.isEmpty()) {
				int queSize = que.size();
				for (int s = 0; s < queSize; s++) {
					int r = que.peek()[0];
					int c = que.poll()[1];
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (!check(nr, nc) || visit[nr][nc] || A[nr][nc] < 1)
							continue;
						size++;
						que.add(new int[] { nr, nc });
						visit[nr][nc] = true;
					}
				}
			}
			answer = Math.max(answer, size);
		}
	}

	return answer;
}

	private static int getTotalIce() {
		int answer = 0;

		for (int r = 0; r < powN; r++) {
			for (int c = 0; c < powN; c++) {
				answer += A[r][c];
			}
		}
		return answer;
	}

	


	private static void melt() {
		int[][] tmp = new int[powN][powN];
		for (int r = 0; r < powN; r++) {
			for (int c = 0; c < powN; c++) {
				if (A[r][c] < 1)
					continue;
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!check(nr, nc) || A[nr][nc] < 1)
						continue;
					cnt++;
				}
				tmp[r][c] = A[r][c];
				if (cnt < 3)
					tmp[r][c]--;
			}
		}
		A = tmp;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < powN && c >= 0 && c < powN)
			return true;
		return false;
	}

	private static void rotate(int level) {
		int[][] tmp = new int[powN][powN];
		int rotLen = (int) Math.pow(2, level);

		for (int i = 0; i < powN; i += rotLen) {
			for (int j = 0; j < powN; j += rotLen) {
				int nr = i;
				for (int c = j; c < j + rotLen; c++) {
					int nc = j;
					for (int r = i + rotLen - 1; r >= i; r--) {
						tmp[nr][nc++] = A[r][c];
					}
					nr++;
				}
			}
		}
		A = tmp;
	}

}