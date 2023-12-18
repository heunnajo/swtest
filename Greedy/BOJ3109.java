import java.util.*;
import java.io.*;
//빵집
public class BOJ3109 {
	static int R,C,Ans;
	static char[][] Map;
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static ArrayList<Pair> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
//		System.out.println("R: "+R+", C: "+C);
		Map = new char[R][C];
		for(int i=0;i<R;i++) {
			Map[i] = br.readLine().toCharArray();
		}
		
		Ans = 0;
		for(int i=0;i<R;i++) {
			if(check(i,0)) Ans++;
		}
		System.out.println(Ans);
	}
	static boolean check(int x,int y) {
		Map[x][y] = '-';
		list.add(new Pair(x,y));
		//1.종료
		if(y == C-1) {
			//Ans++;
			return true;
//			for(Pair p:list) {
//				System.out.print("("+p.x+","+p.y+")"+" ");
//			}
//			System.out.println();
//			list = new ArrayList<>();
//			return false;
		}
		//2.현재 위치(x,y)에서 이동가능한 위치로 이동
		//Map[x][y] = '-';
		if(x-1 >= 0 && Map[x-1][y+1] == '.') {
			if(check(x-1,y+1)) {
//				Ans++;
				return true;
			}
		}
		if(Map[x][y+1] == '.') {
			if(check(x,y+1)){
//				Ans++;
				return true;
			}
		}
		if(x+1 < R && Map[x+1][y+1] == '.') {
			if(check(x+1,y+1)){
//				Ans++;
				return true;
			}
		}
		return false;
	}

}

//		for(int i=0;i<R;i++) {
//			for(int j=0;j<C;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}