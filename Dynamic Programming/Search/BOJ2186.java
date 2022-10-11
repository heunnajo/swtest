import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//문자판 : 2D array  search +  DP
public class BOJ2186 {
	static int[] dR = { -1, 1, 0, 0 };
	static int[] dC = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M, K;
		int ans = 0;
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		char[][] map = new char[N][M];
		String data;
		for (int row = 0; row < N; row++) {
			data = br.readLine();
			for (int col = 0; col < M; col++) {
				map[row][col] = data.charAt(col);
			}
		}
		String search = br.readLine();
		char str = search.charAt(0);
		int size = search.length();
		int[][][] memory = new int[size][N][M];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (map[row][col] == str) {
					memory[0][row][col] = 1;
				}
			}
		}
		for (int idx = 1; idx < search.length(); idx++) {
			str = search.charAt(idx);
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (memory[idx - 1][row][col] > 0) {
						int plus = memory[idx - 1][row][col];
						for (int dir = 0; dir < 4; dir++) {
							for (int k = 1; k <= K; k++) {
								int goR = row + dR[dir]*k;
								int goC = col + dC[dir]*k;
								if(goR<0||goC<0||goR>N-1||goC>M-1)
									break;
								if(map[goR][goC] == str) {
									memory[idx][goR][goC] += plus;
								}
							}
						}
					}
				}
			}
		}
		for (int row = 0; row < N; row++) {
//			System.out.println(Arrays.toString(memory[size-1][row]));
			for (int col = 0; col < M; col++) {
				ans += memory[size-1][row][col];
			}
		}
		System.out.println(ans);
	}

	public static int stoi(String str) {
		return Integer.parseInt(str);
	}

}
