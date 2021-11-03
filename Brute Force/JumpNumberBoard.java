import java.util.*;
import java.io.*;
public class JumpNumberBoard {
	static int[][] Map;
	static HashSet<Integer> set;
	static LinkedList<Integer> list;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Map = new int[5][5];
		list = new LinkedList<>();
		set = new HashSet<>();
		
		for(int i=0;i<5;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<5;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				go(0,i,j,0);
			}
		}
		System.out.println(set.size());
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>4 || y<0 || y>4;
	}
	static void go(int index,int x,int y,int num) {
		if(index==6) {
			set.add(num);
			return;
		}
		for(int dir=0;dir<4;dir++) {
			int nx = x+dx[dir],ny = y+dy[dir];
			if(isOut(nx,ny)) continue;
			go(index+1,nx,ny,num*10+Map[nx][ny]);
		}
	}
}