package ss;
import java.util.*;
public class MovePipe1 {
	static int ans,N,Map[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				Map[i][j] = sc.nextInt();
			}
		}
		ans = 0;
		//go(0,1,0);//ans 값을 합산한다. 실질적으로 여기서 리턴값을 갖지 않고.
		System.out.println(go(0,1,0));
	}
	static int go(int x,int y,int dir) {
		if(x== N-1 && y== N-1) return 1;
		
		if(dir==0) {//현재 가로 방향:가로, 대각선
			if(y+1<N && Map[x][y+1] == 0) {//가로 
				ans += go(x,y+1,0);
			} else if(x+1<N && y+1<N && Map[x][y+1] == 0
					&& Map[x+1][y]==0 && Map[x+1][y+1] == 0) {//대각선
				ans += go(x+1,y+1,1);
			}
		} else if(dir==1) {//현재 대각선 방향:가로,세로,대각선 
			if(y+1<N && Map[x][y+1] == 0) {//가로 
				ans += go(x,y+1,0);
			} else if(x+1<N && y+1<N && Map[x][y+1] == 0
					&& Map[x+1][y]==0 && Map[x+1][y+1] == 0) {//대각선
				ans += go(x+1,y+1,1);
			} else if(x+1<N && Map[x+1][y] == 0) {
				ans += go(x+1,y,2);
			}
		} else if(dir==2) {
			if(x+1<N && Map[x+1][y] == 0) {
				ans += go(x+1,y,2);
			} else if(x+1<N && y+1<N && Map[x][y+1] == 0
					&& Map[x+1][y]==0 && Map[x+1][y+1] == 0) {//대각선
				ans += go(x+1,y+1,1);
			}
		}
//		return 0;//0을 리턴하는 것이 맞나..?
		return ans;//0을 리턴하는 것이 맞나..?
	}
}
