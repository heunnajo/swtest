package ss;
import java.util.*;
import java.io.*;
public class King {
	static String[] getChar = {"A","B","C","D","E","F","G","H"};
	static String[] move = {"R","L","B","T","RT","LT","RB","LB"};
	static int[] dx = {0,0,1,-1,-1,-1,1,1};
	static int[] dy = {1,-1,0,0,1,-1,1,-1};
	static int kx,ky,sx,sy;
	static String[] cmd;
	static int[][] map;
	static int n;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		map = new int[8][8];
		String strKing = st.nextToken();
		int y = strKing.charAt(0)-'A';
		int x = 7-(strKing.charAt(1)-'1');
		map[x][y] = 10;//킹 마킹
		kx = x; ky = y;
		
		String strStone = st.nextToken();
		y = strStone.charAt(0)-'A';
		x = 7-(strStone.charAt(1)-'1');
		map[x][y] = -1;//돌 마킹
		sx = x; sy = y;
		
		n = Integer.parseInt(st.nextToken());
		cmd = new String[n];
		for(int i=0;i<n;i++) {
			cmd[i] = br.readLine();
		}
		solve();
	}
	static void solve() {
		for(int i=0;i<n;i++) {
//			System.out.println("이동 전");
//			System.out.println("킹 위치: " +kx +" "+ky);
//			System.out.println("돌 위치: " +sx +" "+sy);
			String curCmd = cmd[i];
			int d = -1;//현재 이동 방향 벡터
			for(int k=0;k<8;k++) {
				if(curCmd.equals(move[k])) {
					d = k;
				}
			}
			//현재 이동 방향 d에 대해 킹/돌 다음 위치가 범위 넘어가면 다음 이동 명령!
			//새로운 위치로 전역변수 sx,sy,kx,ky와 map 업데이트!
			//map :기존 위치는 0으로
			//kx,ky : d 방향으로 갱신
			if(isOut(kx+dx[d],ky+dy[d])) continue;
			if(kx+dx[d] == sx && ky+dy[d] == sy) {//킹의 다음 이동하려는 위치 = 돌의 현재위치 : 돌 이동!
				if(isOut(sx+dx[d],sy+dy[d])) continue;//돌 다음 이동위치 범위밖이면 현재 이동 컨티뉴처리!
				map[sx][sy] = 0;//기존 위치는 0 
				sx  = sx+dx[d]; sy = sy+dy[d];
				map[sx][sy] = -1;
			}
			//돌 먼저 이동한 후 킹 이동!
			map[kx][ky] =  0;
			kx  = kx+dx[d]; ky = ky+dy[d];
			map[kx][ky] = 10;
			
//			System.out.println("이동 후");
//			System.out.println("킹 위치: " +kx +" "+ky);
//			System.out.println("돌 위치: " +sx +" "+sy);
		}
		System.out.println(convertXY_Str(kx,ky));
		System.out.println(convertXY_Str(sx,sy));
	}
	static String convertXY_Str(int x,int y) {
		String ans = "";
		ans += getChar[y];
		ans += (8-x);
		return ans;
	}
	static boolean isOut(int  x,int y) {
		return x<0 || x>7  || y<0 || y>7;
	}
}

//		System.out.println(strKing);
//		System.out.println(strStone);
//		System.out.println(n);