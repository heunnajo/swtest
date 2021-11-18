package ss;
import java.util.*;
public class ControlLadder {
	static int ans,N,M,H,Map[][];
	static final int LEFT = 1;
	static final int RIGHT = 2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();//2
		M = sc.nextInt();//0
		H = sc.nextInt();//3
		
		Map = new int[H][N];//기본적으로 0이라는 값이 존재하고, 가로선 놓을 때만 시작점LEFT, 끝점 RIGHT 
		
		ans = -1;//-1?0? 어떤 값으로 초기화해야할까?
		for(int i=0;i<M;i++) {
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			Map[a][b] = LEFT;
			Map[a][b+1] = RIGHT;
		}
		go(0);//0번째 가로선부터 놓기 시작!
		System.out.println(ans);
	}
	static void go(int index) {
		if(index==3) {
			if(check()) ans = index;
			return;
		}
		if(index<3 && check()) {
			ans = index;
			return;
		}
		//가로선 놓는 시도!
		for(int i=0;i<H;i++) {
			for(int j=0;j<N;j++) {
				if(Map[i][j] != LEFT && Map[i][j] != RIGHT && 
				Map[i][j+1] != LEFT && j <N-1) {
					Map[i][j] = RIGHT;
					Map[i][j+1] = LEFT;
					go(index+1);
					Map[i][j] = 0;
					Map[i][j+1] = 0;
					
				}
			}
		}
	}
	static boolean check() {
		int row,col;
		for(int j=0;j<N;j++) {
			row = 0;col = j;
			while(row<H) {
				if(Map[row][j]==LEFT) col--;
				if(Map[row][j]==RIGHT) col++;
				row++;
			}
			if(col != j) return false;
		}
		return true;
	}
}
