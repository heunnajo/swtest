package boj;
import java.util.*;
import java.io.*;
//모든 순열
public class BOJ10974_2nd {
	static int N;
	static boolean[] Check;
	static int[] Selected;
	static StringBuilder Sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Check = new boolean[N];
		Selected = new int[N];
		Sb = new StringBuilder();
		
		go(0);
		System.out.print(Sb.toString());
	}
	static void go(int idx) {
		//1.종료
		if(idx == N) {
			for(int i=0;i<N;i++) {
				Sb.append(Selected[i]).append(" ");
			}
			Sb.append("\n");
			return;
		}
		//2.현재 선택 및 다음 선택(재귀 호출)
		for(int i=0;i<N;i++) {
			if(Check[i]) continue;
			Check[i] = true;
			Selected[idx] = i+1;
			go(idx+1);
			Check[i] = false;
			Selected[idx] = -1;
		}
	}
}
