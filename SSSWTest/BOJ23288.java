package boj;
import java.util.*;
import java.io.*;
//주사위 굴리기2
public class BOJ23288 {
	static int N,M,K,Ans;//N 행, M 열, K 이동 횟수
	static int[][] Map;
	static int[] Dice;
	static int[] dx = {0,1,0,-1};//동 서 남 북
	static int[] dy = {1,0,-1,0};
	static int curX,curY,curDir;//주사위 위치, 방향 정
	static int nx,ny;//다음으로 이동한 칸
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		Dice = new int[] {2,5,4,3,1,6};//뒤 앞 왼 오 위 아래 순서
		curX = 0; curY = 0; curDir = 0;
		nx = 0; ny = 1;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Ans = 0;
		while(K-- >0) {
			rollDice();
			getScore();
			getNextDir();
		}
		System.out.println(Ans);
	}
	static void rollDice() {
		int[] tmp = new int[6];
		//현재 진행방향으로 1칸 이동시에 범위 체크
		//범위 벗어나면 방향 반대!
		int tmpNx = curX+dx[curDir];
		int tmpNy = curY+dy[curDir];
		if(isOut(tmpNx,tmpNy)) {
			curDir = (curDir+2)%4;
		}
		//방향에 따라 바뀌는 값, 바뀌지 않는 값이 있음.
		if(curDir == 0) {//동
			//불변
			tmp[0] = Dice[0];
			tmp[1] = Dice[1];
			
			//가변
			tmp[2] = Dice[5];
			tmp[3] = Dice[4];
			tmp[4] = Dice[2];
			tmp[5] = Dice[3];
		} else if(curDir == 1) {//서
			//불변
			tmp[0] = Dice[0];
			tmp[1] = Dice[1];
			
			//가변
			tmp[2] = Dice[4];
			tmp[3] = Dice[5];
			tmp[4] = Dice[3];
			tmp[5] = Dice[2];
			
		} else if(curDir == 2) {//남
			//불변
			tmp[2] = Dice[2];
			tmp[3] = Dice[3];
			
			//가변
			tmp[0] = Dice[4];
			tmp[1] = Dice[5];
			tmp[4] = Dice[7];
			tmp[5] = Dice[0];
		} else {//북
			//불변
			tmp[2] = Dice[2];
			tmp[3] = Dice[3];
			
			//가변
			tmp[0] = Dice[5];
			tmp[1] = Dice[4];
			tmp[4] = Dice[0];
			tmp[5] = Dice[1];
		}
		Dice = tmp;
		//printDice();
	}
	static void printDice() {
		System.out.println();
		System.out.println(" "+Dice[0]);
		System.out.println(Dice[2]+""+Dice[4]+""+Dice[3]);
		System.out.println(" "+Dice[1]);
		System.out.println(" "+Dice[5]);
		System.out.println();
	}
	static void getScore() {
		//int A = Dice[5];
		int B = Map[nx][ny];
		int C = bfs();
		System.out.println("B: "+B+" C: "+C);
		Ans += B*C;//4가 되야함!
	}
	static void getNextDir() {
		int A = Dice[5];//3
		int B = Map[nx][ny];//1
		if(A > B) { //시계방향
			curDir = (curDir+1)%4;
		} else if(A < B) { //반시계방향
			curDir = (curDir+3)%4;
		}
		System.out.println("다음 방향 curDir: "+curDir);
		
	}
//	static int bfs(int x,int y) {
	static int bfs() {
		int val = Map[nx][ny];//초기 nx : 0 ny : 1
		int count = 1;
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(nx,ny));
		boolean[][] v = new boolean[N][M];
		v[nx][ny] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			for(int d=0;d<4;d++) {
				int nextX = cur.x+dx[d];
				int nextY = cur.y+dy[d];
				
				if(isOut(nextX,nextY) || v[nextX][nextY]) continue;
				if(Map[nextX][nextY] == val) {
					q.offer(new Pair(nextX,nextY));
					v[nextX][nextY] = true;
					count++;
				}
			}
		}
		
		return count;
		
	}
	static boolean isOut(int x,int y) {
		return x < 0 || x > N-1 || y < 0 || y > M-1;
	}
}
