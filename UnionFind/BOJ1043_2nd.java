//거짓말
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1043 {
	static int n,m;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		List<Integer> lst =new ArrayList<>();
		int k = Integer.parseInt(st.nextToken());
		for(int i=0; i<k; i++) {
			lst.add(Integer.parseInt(st.nextToken()));
		}
		
		parents = new int[n+1];
		for(int i=1; i<n+1; i++) {
			parents[i] = i;
		}
		Set<Integer>[] party = new Set[m];
		for(int i=0; i<m; i++) {
			party[i] = new HashSet<Integer>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if(k>=1) {
				int t = Integer.parseInt(st.nextToken());
				party[i].add(t);
				while(st.hasMoreTokens()) {
					int t2 = Integer.parseInt(st.nextToken());
					party[i].add(t2);
					union(t,t2);
					t=t2;
				}
			}
		}
		int cnt=0;
		Loop:for(int i=0; i<m; i++) {
			for(int a=0; a<lst.size(); a++) {
				for(int p : party[i]) {
					if(find(p)==find(lst.get(a))){
						cnt++;
						continue Loop;
					}
				}
			}
		}
		System.out.println(m-cnt);
		
	}
	
	public static int find(int k) {
		if(parents[k]==k) {
			return k;
		}
		return parents[k] = find(parents[k]);
	}
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return;
		
		if(b>a) {
			int tmp = b;
			b =a;
			a= tmp;
		}
		//a가 더큼.
		parents[b] = a;
		
		
	}

}
