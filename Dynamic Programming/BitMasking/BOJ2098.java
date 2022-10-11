import java.util.*;
import java.io.*;
//외판원 순회 : DP + Bit Masking
public class BOJ2098 {
	static int V;
	static int[][] m;
	static int[][] dp;
	static int end;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
		V = Integer.parseInt(st.nextToken());				
		m = new int[V + 1][V + 1];
		dp = new int[V + 1][(1 << V) + 1];

		for (int i = 1; i <= V; i++)
		{
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= V; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
				if (m[i][j] == 0)
					m[i][j] = 987654321;
			}
		}

		/*for (int i = 1; i <= V ; i++)
			for (int j = 1; j <= (1 << V); j++)
				dp[i][j] = -1;*/

		// 어디를 시작점으로 하든 사이클을 구하는 것이므로 한 번만 돌면 답을 구할 수 있음
		end = 1;
		System.out.println(shortestPath(1, 1));		
	}

	static int shortestPath(int here, int visited) {
		int result = 987654321;

		// 모든 도시를 다 방문했다면 첫 번째 도시로 돌아가고 종료
		if (visited == ((1 << V) - 1))
			return m[here][end];

		if (dp[here][visited] != 0)
			return dp[here][visited];

		for (int next = 1; next <= V; next++) {
			// 해당 도시를 이미 방문했다면 더이상 방문하지 않음
			if ((visited & (1 << (next - 1))) >= 1)
				continue;
			
			result = Math.min(result, m[here][next] + shortestPath(next, visited + (1 << (next - 1))));					
		}

		dp[here][visited] = result;
								
		return result;
	}
}

