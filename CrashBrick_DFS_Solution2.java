import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CrashBrick_DFS_Solution2 {
	static class Info {
		int x, y, val;

		public Info(int y, int x, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
	static int row, col, num, result;
	static int remain[], breakingCol[];
	final static int dx[] = { 0, 1, 0, -1 };
	final static int dy[] = { -1, 0, 1, 0 };
	static Queue<Info> q;

	static void crash(int dup[][], int m) {
		q = new LinkedList<>();
		for (int i = 0; i < row; i++) {
			if (dup[i][m] > 0) {
				q.offer(new Info(i, m, dup[i][m]));
				break;
			}
		}
		Info ii;
		while (!q.isEmpty()) {
			ii = q.poll();
			int cx = ii.x;
			int cy = ii.y;
			int cv = ii.val;
			dup[cy][cx] = 0;
			for (int i = 0; i < 4; i++) {
				for (int k = 1; k < cv; k++) {
					int nx = cx + dx[i] * k;
					int ny = cy + dy[i] * k;
					if (nx >= 0 && ny >= 0 && nx < col && ny < row) {
						if (dup[ny][nx] == 0)
							continue;
						else {
							if (dup[ny][nx] == 1)
								dup[ny][nx] = 0;
							else if (dup[ny][nx] > 1) {
								q.offer(new Info(ny, nx, dup[ny][nx]));
							}
						}
					} else
						break; // 범위 밖
				}
			}
		}
		// 터트리고 남은거 내림
		for (int j = 0; j < col; j++) {
			String ss = "";
			for (int i = row - 1; i >= 0; i--) {
				if (dup[i][j] > 0) {
					ss += Integer.toString(dup[i][j]);
				}
			}
			for (int i = 0; i < ss.length(); i++)
				dup[row - 1 - i][j] = ss.charAt(i) - '0';
			for (int i = 0; i < row - ss.length(); i++)
				dup[i][j] = 0;
		}
	}

	static void dfs(int arr[][], int cnt) {
		if (cnt == num) {
			// 계산
			int tot = 0;
			for (int i = 0; i < row; i++)
				for (int j = 0; j < col; j++)
					if (arr[i][j] > 0)
						tot++;
			result = Math.min(result, tot);
			return;
		}
		int dup[][] = new int[row][col];
		for (int i = 0; i < col; i++) {
			if (remain[i] > 0) {
				remain[i]--;
				for (int k = 0; k < row; k++)
					dup[k]=arr[k].clone();
				crash(dup,i);
				dfs(dup,cnt + 1);
				remain[i]++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		for (int t = 1; t <= test; t++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			num = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			// 초기화
			int arr[][] = new int[row][col];		
			remain = new int[col];
			result = row * col + 1;
			int sum = 0;
			for (int i = 0; i < row; i++) {
				String str = br.readLine();
				StringTokenizer st2 = new StringTokenizer(str);
				for (int j = 0; j < col; j++) {
					arr[i][j] = Integer.parseInt(st2.nextToken());
					if (arr[i][j] > 0) {
						remain[j]++;
						sum++;
					}
				}
			}
			// 벽돌을 전부 깰 수 있는 경우
			if (sum <= num) {
				System.out.println("#" + t + " " + 0);
				continue;
			}
			dfs(arr,0);
			System.out.println("#" + t + " " + result);
		}
	}
}