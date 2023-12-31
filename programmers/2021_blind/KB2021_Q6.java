import java.util.*;
import java.io.*;
//카드 짝맞추기
public class KB2021_Q6 {
	static int CardNum,Ans,R,C;
	static int[][] CopiedB,B;
	static boolean[][] Visited;
	static boolean[] Selected;
	static int[] Order;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pos{
		int x,y,dist;
		Pos(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws IOException {
		int[][] board = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
		int r = 1;
		int c = 0;
		
//		int[][] board = {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}};
//		int r = 0;
//		int c = 1;
		solution(board,r,c);
	}
	public static int solution(int[][] board, int r, int c) {
		Ans = Integer.MAX_VALUE;
		B = new int[4][4];
        for(int i=0;i<4;i++) {
        	for(int j=0;j<4;j++) {
        		if(board[i][j] != 0) CardNum++;
        		B[i][j] = board[i][j];
        	}
        }
        R = r; C = c;
		CardNum /= 2;
		Selected = new boolean[CardNum+1];
		Order = new int[CardNum];
		makePermu(0);
		
		System.out.println(Ans);
        return Ans;
    }
	static void makePermu(int idx) {
		//1.종료
		if(idx == CardNum) {
			int curCnt = solve();
			if(Ans > curCnt) Ans = curCnt;
//			for(int i=0;i<CardNum;i++) System.out.print(Order[i]+", ");
//			System.out.println();
			return;
		}
		//2.현재 경우 선택, 다음 경우 호출
		for(int i=1;i<=CardNum;i++) {
			if(Selected[i]) continue;
			Selected[i] = true;
			Order[idx] = i;
			makePermu(idx+1);
			Selected[i] = false;
		}
	}
	static int solve() {
		//현재 Order 의 순서 정보에 따라 2차원 배열 BFS, 이동 횟수 계산해서 리턴!
		int total_cnt = 0;
		CopiedB = new int[4][4];
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				CopiedB[i][j] = B[i][j];
		int cursorX = R; int cursorY = C;
		
		System.out.print("순서정보: ");
		for(int i=0;i<CardNum;i++)System.out.print(Order[i]);
		System.out.println();
		for(int i=0;i<CardNum;i++) {
			int target = Order[i];
			
			System.out.println("target: "+target);
			//첫번째 카드 방문
			Pos next = bfs(cursorX,cursorY,target);//cursorX, cursorY 갱신 필수!
			cursorX = next.x; cursorY = next.y;
			total_cnt += next.dist;
			total_cnt += 1;
			CopiedB[cursorX][cursorY] = 0;
			
			//두번째 카드 방문
			next = bfs(next.x,next.y,target);
			cursorX = next.x; cursorY = next.y;
			total_cnt += next.dist;
			total_cnt += 1;
			CopiedB[cursorX][cursorY] = 0;
			
			System.out.println("이동횟수: "+total_cnt);
		}
		System.out.println("이동횟수: "+total_cnt);
		return total_cnt;
	}
	static Pos bfs(int sx,int sy,int target) {
		Pos pos = new Pos(0,0,0);
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(sx,sy,0));
		boolean[][] v = new boolean[4][4];
		v[sx][sy] = true;
		System.out.print("시작("+sx+","+sy+")");
		System.out.print("이동 추적");
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			if(sx == 0 && sy == 3) {
				System.out.print("("+cur.x+","+cur.y+","+cur.dist+")" +" ");
			}
			if(CopiedB[cur.x][cur.y] == target) {
				System.out.println("도착("+cur.x+","+cur.y+","+cur.dist+")"+" ");
				
				return new Pos(cur.x,cur.y,cur.dist);
			}
			//1.방향키로 이동
			for(int dir=0;dir<4;dir++) {
				int nx = cur.x+dx[dir];
				int ny = cur.y+dy[dir];
				if(isOut(nx,ny) || v[nx][ny]) continue;
				
				q.offer(new Pos(nx,ny,cur.dist+1));
				v[nx][ny] = true;
			}
			//2.ctrl+방향키로 이동
			for(int dir=0;dir<4;dir++) {
				Pos next = getCtrlCnt(cur,dir);
				if(v[next.x][next.y]) continue;
				
				v[next.x][next.y] = true;
				q.offer(next);
			}
		}
		return pos;
	}
	static Pos getCtrlCnt(Pos cur,int dir) {
		int nx = cur.x; int ny = cur.y;
		nx += dx[dir]; ny += dy[dir];//여기서 범위밖으로 되면 while문 실행하지 않게됨!(맞음)
		while(nx >= 0 && nx<4 && ny >= 0 && ny<4) {
			if(CopiedB[nx][ny] != 0) {
				return new Pos(nx,ny,cur.dist+1);
			}
			nx += dx[dir]; ny += dy[dir];
		}
		//범위 벗어난 경우
		return new Pos(nx-dx[dir],ny-dy[dir],cur.dist+1);
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=4 || y<0 || y>=4;
	}
