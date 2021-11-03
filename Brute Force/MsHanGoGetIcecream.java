package ss;
import java.util.*;
import java.io.*;
public class MsHanGoGetIcecream {
	static boolean[][] Dont;
	static boolean[] Used;
	static int ans,N,M;
    static int[] selected;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		Dont = new boolean[N+1][N+1];
		Used = new boolean[N+1];
		selected = new int[4];
        
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			Dont[a][b] = Dont[b][a] = true;
		}
		ans = 0;
		go(1);
		System.out.println(ans);
	}
	static void go(int index) {
		if(index==4) {
			ans++;
			return;
		}
		for(int i=1;i<=N;i++) {
			if(Used[i]) continue;
			boolean flag = true;
			Used[i] = true; selected[index] = i;//i번째 수를 선택:selected에 저장, 중복 체크
			for(int j=1;j<index;j++) {
				if(Dont[i][selected[j]]) flag = false;
			}
			if(flag) {//하나라도 현재 i와 비추조합 정보가 있었다면 continue로 다음 i를 선택하도록 한다.
				go(index+1);//flag가 true일 때만 다음 아이스크림을 선택한다.
				Used[i] = false; selected[index] = 0;
			} else continue;
		}
	}
}