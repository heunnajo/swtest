//암호 만들기
import java.util.*;
import java.io.*;
public class BOJ1759 {
	static int L,C;
	static int[] Selected;
	static String[] Input;
	static StringBuilder Ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Selected = new int[L];
		Input = br.readLine().split(" ");
		Ans = new StringBuilder();
		Arrays.sort(Input);
		
		go(0,0);
		System.out.println(Ans.toString());
	}
	static boolean check(String str) {
		int jaCnt = 0; int moCnt = 0;
		for(int i=0;i<L;i++) {
			if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
				moCnt++;
			} else jaCnt++;
		}
		if(moCnt >= 1 && jaCnt >= 2) return true;
		
		return false;
	}
	static void go(int idx,int from) {
		//1.종료
		if(idx == L) {
			StringBuilder cur = new StringBuilder(L);
			for(int i=0;i<L;i++) {
				cur.append(Input[Selected[i]]);
			}
			if(check(cur.toString())) {
				Ans.append(cur).append("\n");
			}
			return;
		}
		
		if(idx < L && from >= C) {
			return;
		}
		
		//2.현재 경우 선택 & 다음 경우 호출
		//2-1.선택O
		Selected[idx] = from;
		go(idx+1,from+1);
		
		//2-1.선택X
		go(idx,from+1);
		
	}
}

//		for(int i=0;i<C;i++) {
//			System.out.print(Input[i]+" ");
//		}