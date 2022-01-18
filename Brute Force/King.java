package ss;
import java.util.*;
import java.io.*;
public class King {
	static String[] move = {"R","L","B","T","RT","LT","RB","LB"};
	static int[] dx = {0,0,1,-1,-1,-1,1,1};
	static int[] dy = {1,-1,0,0,1,-1,1,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[][] map = new int[8][8];
		String strKing = st.nextToken();
		int y = strKing.charAt(0)-'A';
		int x = 7-(strKing.charAt(1)-'1');
//		System.out.println("킹의 위치: ");
//		System.out.println("x: "+x+"y: "+y);
		map[x][y] = 10;//킹 마킹
		
		String strStone = st.nextToken();
		y = strStone.charAt(0)-'A';
		x = 7-(strStone.charAt(1)-'1');
//		System.out.println("킹의 위치: ");
//		System.out.println("x: "+x+"y: "+y);
		map[x][y] = -1;//돌 마킹
		int n = Integer.parseInt(st.nextToken());
		String[] cmd = new String[n];
		for(int i=0;i<n;i++) {
			cmd[i] = br.readLine();
			//System.out.println(move[i]);
		}
		solve();
	}

}

//		System.out.println(strKing);
//		System.out.println(strStone);
//		System.out.println(n);