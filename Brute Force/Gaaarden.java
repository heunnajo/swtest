import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gaaarden {
	static int N, M, G, R;
	static int map[][] = new int[50][50];
	static int dy[] = { 1, -1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };
	static boolean visit[][] = new boolean[50][50];
	static int times[][] = new int[50][50];
	static int color[][] = new int[50][50];
	static boolean rv[][] = new boolean[50][50];
	static boolean gv[][] = new boolean[50][50];
	static int seq[] = new int[10];
	static boolean RG[] = new boolean[10];
	static int ans;

	static class Pair {
		int y;
		int x;
		int rg;

		public Pair(int y, int x, int rg) {
			super();
			this.y = y;
			this.x = x;
			this.rg = rg;
		}

		public Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static ArrayList<Pair> list = new ArrayList<Pair>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		// Input
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					// 배양액을 뿌릴수 있는곳
					list.add(new Pair(i, j));
				}
			}
		}

		// Combi list 중에서 R+G 만큼 선택한다 그것을 seq에 담을거다
		ans = 0;
		Pick(0, 0);
		System.out.println(ans);
	}

	public static void Pick(int cnt, int start) {
		if (cnt == R + G) {
			// 선택된 seq 중에 R개를 true 로 하는 경우의 조합을 뽑는다.
//			System.out.println(Arrays.toString(seq));
			PickRG(0, 0);
			return;
		}
		for (int i = start; i < list.size(); i++) {
			seq[cnt] = i;
			Pick(cnt + 1, i + 1);
		}

	}

	public static void PickRG(int cnt, int r) {
		if (cnt == R + G) {
			if (r == R) {
				// seq와 RG를 이용해서 BFS를 만든다.
				ans = Math.max(ans, BFS());
//				System.out.println(Arrays.toString(RG));
			}
			return;
		}

		PickRG(cnt + 1, r);

		RG[cnt] = true;
		PickRG(cnt + 1, r + 1);
		RG[cnt] = false;
	}

	public static int BFS() {
		int cnt = 0;
		// seq 순서와 RG 배양액색깔을 이용해서 BFS를 만든다.
		// 0 : 빈공간, 1 : 빨강 , 2: 초록 , 3: 플라워
		for (int i = 0; i < N; i++) {
			Arrays.fill(color[i], 0);
			Arrays.fill(times[i], 0);
		}
		Queue<Pair> q = new LinkedList<Pair>();

		for (int s = 0; s < R + G; s++) {
			Pair cur = list.get(seq[s]);
			// cur의 위치에 RG 배양액의 색갈을 넣는다.
			cur.rg = RG[s] ? 1 : 2;
			color[cur.y][cur.x] = RG[s] ? 1 : 2;
			q.add(cur);
		}

		// q를 돌면서 탐색한다.
		// visit은 접근자체를 못하는 완전히 죽은애들
		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			time++;
			for (int s = 0; s < size; s++) {
				Pair cur = q.poll();
				if(color[cur.y][cur.x]==3) {
					cnt++;
					continue;
				}
				// 상하좌우로 보내본다.
				for (int d = 0; d < 4; d++) {
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M)
						continue;
					if (map[ny][nx] == 0)
						continue;
					if (color[ny][nx] == 0) {
						color[ny][nx] = cur.rg;
						q.add(new Pair(ny, nx, cur.rg));
						times[ny][nx] = time;
					} else if (color[ny][nx] != 3 && color[ny][nx] != cur.rg && times[ny][nx] == time) {
						color[ny][nx] = 3;
						continue;
					}
				}

			}

		}
		return cnt;
	}
}