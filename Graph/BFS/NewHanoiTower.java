package ss;
import java.util.*;
import java.io.*;
/*
 *http://colorscripter.com/s/vX2OcH3 
 * 
 */
public class NewHanoiTower {
	static String[] s;
	static HashMap<String,Integer> visited;
	static class TowerState{
		String A,B,C;
		int t;
		TowerState(String A,String B,String C,int t){
			this.A = A;
			this.B = B;
			this.C = C;
			this.t = t;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		s = new String[3];
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			if(cnt == 0) s[i] = "";
			else s[i] = st.nextToken();//이렇게 하면 문자열 없는 건 입력을 못받아 버린다!
		}
		visited = new HashMap<>();
		//solve();
	}
	static void solve() {
		Queue<TowerState> q = new LinkedList<>();
		TowerState a = new TowerState(s[0],s[1],s[2],0);
		q.add(a);
		makeMap(a,q);
		int[] dx = {0,0,1,1,2,2};
		int[] dy = {1,2,2,0,0,1};
		while(!q.isEmpty()) {
			TowerState cur = q.remove();
			String A = cur.A, B = cur.B, C = cur.C;
			int t = cur.t;
			if(check(cur)) {
				System.out.println(t);
				return;
			}
			for(int k=0;k<6;k++) {
				//from->to 방향으로 원반 옮긴다! from 막대기에 원반 없으면 컨티뉴처리!
				if(s[dx[k]]=="") continue;
				//옮기는 작업
				char c = s[dx[k]].charAt(s[dx[k]].length()-1);
				s[dy[k]] += c;
				//옮기고 난 후 from 업데이트
				if(s[dx[k]].length()>1) {
					s[dx[k]] = s[dx[k]].substring(0,s[dx[k]].length()-1);
				} else {
					s[dx[k]] = "";
				}
				//옮기고 난 후 해시맵 업데이트
				makeMap(new TowerState(s[0],s[1],s[2],t+1),q);
			}
		}
	}
	static boolean check(TowerState ts) {
		//String A = ts.A, B = ts.B, C = ts.C;
		String[] str = {ts.A,ts.B,ts.C};
		char[] c = {'A','B','C'};
		for(int i=0;i<3;i++) {
			for(int j=0;j<str[i].length();j++) {
				if(str[i].charAt(j) != c[i]) return false;
			}
		}
		
		return true;
	}
	static void makeMap(TowerState ts,Queue<TowerState> q) {//해시맵에 기록하는 역할!
		String A = ts.A, B = ts.B, C = ts.C;
		int t = ts.t;
//		String st = A+""+B+""+C;
		String st = A+" "+B+" "+C;
		if(!visited.containsKey(st)) {
//			visited.put(st, t);
			visited.put(st, 1);
			q.add(new TowerState(A,B,C,t));
		}
	}
}
//for(int i=0;i<3;i++) {
//	System.out.println(s[i]);
//}

