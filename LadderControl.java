package ss;
import java.util.*;
public class LadderControl {
	static int N,M,H;
	static int[][] Ladder = new int[30][10];//가로선 갯수(H)는 최대 30,세로선 갯수(N)는 최대 10 
	static final int INF = 987654321;
	static final int RIGHT = 1;//시작점
	static final int LEFT = 2;//끝점
	
	static boolean check() {//i->i 조건 판단.
		for(int i=0;i<N;i++) {//세로선에 대해 반복.
			int row = 0;int col = i;//col은 사다리로 1씩 증가하거나 감소한다!
			do {
				if(Ladder[row][col] == RIGHT) col++;
				else if(Ladder[row][col] == LEFT) col--;
				row++;
			}while(row != H);
			//if(i != col) return false;
			if(col != i) return false;
		}
		return true;
	}
	
	static int solve(int index,int cnt) {
		//재귀함수 종료 조건 or 정답 찾은 경우
		//1. 가로선 3개 넘으면(문제 조건) 조건(i->i) 판단해서 -1 또는 정답 저장.
		//2. 인덱스가 N*H 넘어가는 경
		if(cnt == 3 || index >= N*H) {
			if(check())
				return cnt;
			return INF;
		}
		int ret = INF;
		int row = index / N, col = index % N;//pos값으로 row와 col 좌표를 구할 수 있다.
		
		//1.현재 index에 가로선 선택.
		if(col != N-1&& Ladder[row][col]== 0 && Ladder[row][col+1]==0) {
			//1-1.선택.
			Ladder[row][col] = RIGHT;
			Ladder[row][col+1] = LEFT;
			//1-2.재귀.
			ret = Math.min(ret,solve(index+2,cnt+1));
			//1-3.원복.
			Ladder[row][col] = Ladder[row][col+1] = 0;
		}
		//2.현재 index에 가로선 선택X.
		//2-1,2.선택X, 재귀.
		ret = Math.min(ret, solve(index+1,cnt));
		//2-3.원복.
		//Ladder[row][col] = Ladder[row][col+1] = 0;
		return ret;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		
		for(int i=0;i<M;i++) {
			int a,b;
			a = sc.nextInt();
			b = sc.nextInt();
			//a와 b 모두 1씩 뺀 값을 사용한다!
			Ladder[a-1][b-1] = RIGHT;//시작점(1).b번째 세로줄에서 가로줄을 만나면 오른쪽으로 이동!
			Ladder[a-1][b] = LEFT;//끝점(2).b+1번째 세로줄에서 가로줄을 만나면 왼쪽으로 이동!
		}
		int answer = solve(0,0);
		if(answer == INF) System.out.println(-1);
		else System.out.println(answer);
	}

}
