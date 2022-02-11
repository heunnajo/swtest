//백준 15650 N과 M2 > 반복문 구현
import java.io.*;
import java.util.*;
public class Main {
	static int n,m;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		int[] selected = new int[m];
		go(0,0,selected);
		System.out.print(sb.toString());
	}
	static void go(int idx,int from,int[] selected) {
		//1.종료 조건
		if(idx == m) {
			for(int i=0;i<m;i++) {
				sb.append(selected[i]);
				if(i!=m-1) sb.append(' ');
			}
			sb.append("\n");
			return;
		}
		//2.현재 경우 선택, 다음 경우 호출
		for(int i=from;i<n;i++) {//i=from=0부터.
			selected[idx] = i+1;//selected[0] = 0+1 = 1
			go(idx+1,i+1,selected);
			//selected[idx] = -1;
		}
	}
}