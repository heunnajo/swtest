//BOJ #1613 역사
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class History_Faster {
	static int n, k, s;
	static boolean[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new boolean[n + 1][n + 1];
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			map[one][two] = true;
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				if(!map[i][k]) continue;
				for(int j = 1; j <= n; j++) {
					if(map[k][j]) map[i][j] = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		s = Integer.parseInt(br.readLine());
		for(int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			boolean ch1 = map[one][two];
			boolean ch2 = map[two][one];
			
			if(!ch1 && !ch2) sb.append(0).append("\n");
			else if(ch2) sb.append(1).append("\n");
			else if(ch1) sb.append(-1).append("\n");
		}

		System.out.println(sb);
	}


}