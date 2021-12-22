package ss;
import java.util.*;
import java.io.*;
public class SetExpression {
	static int[] parent;
	static int Find(int x) {
		if(parent[x] == x) return x;
		else {
			return parent[x] = Find(parent[x]);
		}
	}
	static void Union(int a,int b) {
		int x = Find(a), y = Find(b);
		parent[y] = x;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[1000001];//n은 1부터 시작한다.
		for(int i=0;i<=n;i++) parent[i] = i;//1이 아니라 0부터!1은 n제한의 최솟값일 뿐이가 모든 원소는 0부터 시작하기 때문에 0부터 초기화해줘야함!
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cmd == 0) {//union
				Union(a,b);
			} else if(cmd == 1){//find
				if(Find(a) == Find(b)) sb.append("yes"+"\n");
				else sb.append("no"+"\n");
			}
		}
		System.out.print(sb);
	}

}
