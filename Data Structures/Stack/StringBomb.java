package ss;
import java.util.*;
import java.io.*;
public class StringBomb {
	static class Pair{
		int Sidx,Tidx;
		Pair(int Sidx,int Tidx){
			this.Sidx = Sidx;
			this.Tidx = Tidx;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = null;
		String s = br.readLine();
		String t = br.readLine();
		boolean[] erased = new boolean[s.length()];
		int Slen = s.length(), Tlen = t.length();
		char tmp = t.charAt(0);
		if(Tlen == 1) {
			for(int i=0;i<Slen;i++) {
				if(s.charAt(i) == tmp) {
					erased[i] = true;
				}
			}
		} else {
			Stack<Pair> st = new Stack<>();
			for(int i=0;i<Slen;i++) {
				if(s.charAt(i) == t.charAt(0)) {//1.t의 첫 문자열인지 확인
					st.push(new Pair(i,0));//(6,0)이 들어가야함.
					//System.out.println("i : "+i);
				} else {//2.t의 연속된 부분 문자열 중 하나인지 확인
					if(!st.isEmpty()) {
						Pair cur = st.peek();
						if(s.charAt(i) == t.charAt(cur.Tidx+1)) {
							
							//System.out.println("연속된 문자 : "+t.charAt(cur.Tidx+1));
							st.push(new Pair(i,cur.Tidx+1));
							//이 때 스택에 넣는 t의 인덱스가 마지막 인덱스인지 확인!=>그렇다면 문자열 폭발 발생!
							if(cur.Tidx+1 == Tlen-1) {
								for(int cnt=0;cnt<Tlen;cnt++) {
									erased[st.peek().Sidx] = true;
									st.pop();
								}
							}
						} else {
							//1,2 둘 다 해당되지 않는다면
							while(!st.isEmpty()) {
								st.pop();
							}
						}
					}
				}
			}
		}
		//정답 도출!
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i=0;i<Slen;i++) {
			if(!erased[i]) {
				sb.append(s.charAt(i));
				flag = true;
			}
		}
		sb.append("\n");
		if(flag) {
			System.out.print(sb);
		} else System.out.print("FRULA");
	}
	
}
//		if(str.contains(bomb)) System.out.println("yes");
//		else System.out.println("no");
