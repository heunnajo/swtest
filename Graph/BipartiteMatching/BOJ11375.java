package ss;
import java.util.*;
import java.io.*;
//열혈강호
public class BOJ11375 {
	static int N,M,ans;
	static boolean[] selected;
	static ArrayList<Integer>[] list;
	static int[] task;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		selected = new boolean[M+1];
		task = new int[M+1];
		
		list = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		
		int cnt = 0;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			cnt = Integer.parseInt(st.nextToken());
			for(int j=0;j<cnt;j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		ans = 0;
		
		for(int i=1;i<=N;i++) {
			Arrays.fill(selected, false);
			if(dfs(i)) ans++;
		}
		
		System.out.println(ans);
	}
	static boolean dfs(int idx) {
		for(int work:list[idx]) {
			if(selected[work]) continue;
			
			selected[work] = true;
			
			if(task[work] == 0 || dfs(task[work])) {
				task[work] = idx;
				return true;
			}
		}
		return false;
	}

}

//		for(int i=1;i<=N;i++) {
//			for(int work:list[i]) {
//				System.out.print(work+" ");
//			}
//			System.out.println();
//		}