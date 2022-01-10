import java.util.*;
import java.io.*;
public class MsHanGoGetIceCream_2ndTrial {
	static int n,m,ans;
	static boolean[][] info;
	static int[] selected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		info = new boolean[n][n];
		selected = new int[3];
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			info[a][b] = info[b][a] = true;
		}
		
		ans = 0;
		go(0,0);
		System.out.println(ans);
	}
	static void go(int idx,int cnt) {
		//1.종료 조건
		if(cnt == 3) {
			ans++;
			return;
		}
		//2.현재 선택, 다음 경우 호출
		for(int i=idx;i<n;i++) {
			boolean flag = true;//현재 i를 선택할지, i+1로 넘어갈지 판단할 때 사용!
			selected[cnt] = i;//i는 0부터 시작?하면 안 되나?놉놉 입력 데이터:1부터 시작하는거 그대로 쓰는데!
			for(int j=0;j<cnt;j++) {
				if(info[i][selected[j]]) flag = false;
			}
			if(flag) {
				go(i+1,cnt+1);
				//재귀함수 리턴 후 selected를 굳이 원복시킬 필요는 없다 어차피 덮어쓰는 식으로 선택되기 때문.
			}
		}
	}
}