package ss;
import java.util.*;
import java.io.*;
public class HN_3_try {
	static int N;//갈등 정보 갯수
	static int a[][];
	static int ans;
	static boolean[] used;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		a = new int[9][9];
		used = new boolean[9];//8명의 아이 선택했는지 여부 체크
		N = Integer.parseInt(br.readLine());
		//갈등 정보 저장!
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			a[x][y] = a[y][x] = 1;//O(1)로 갈등정보 조회가능!
		}
		ans = 0;
		go(1,0);
		System.out.println(ans);
	}
	static void go(int index,int prev) {
		if(index == 9) {
			ans++;
			return;
		}
		for(int i=1;i<=8;i++) {
			if(used[i]) continue;
			if(a[i][prev] == 1) continue;//첫번째 아이 선택할 때
			used[i] = true;
			go(index+1,i);
			used[i] = false;
		}
	}
}
