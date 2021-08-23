import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

class Snake_withDeque {
	static final int INF = 987654321;
	static int max;
	static int N;
	static Deque<Point> q = new LinkedList<Point>();
	static int[] ci = {0, 1, 0, -1};
	static int[] cj = {1, 0, -1, 0};
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		
		q.add(new Point(0,0));
		board[0][0] = 1;
		
		for(int i = 0; i < K; i++){//사과 마킹.
			String[] data = br.readLine().split(" ");
			board[Integer.parseInt(data[0])-1][Integer.parseInt(data[1])-1] = 2;
		}
		int L = Integer.parseInt(br.readLine());
		char C[] = new char[10001];
		for(int i = 0; i < L; i++){
			String[] data = br.readLine().split(" ");
			C[Integer.parseInt(data[0])] = data[1].charAt(0);
		}
		
		int time = 0;
		int dir = 0;
		int nextI = 0;
		int nextJ = 0;
		while(true){
			time++;
			nextI += ci[dir];
			nextJ += cj[dir];
			if(nextI >= N || nextI < 0 || nextJ >= N || nextJ < 0) break;
			if(board[nextI][nextJ] == 1) break;
			
			q.addFirst(new Point(nextI,nextJ));
			
			if(board[nextI][nextJ] == 2){
				board[nextI][nextJ] = 1;
			} else{//사과가 아닌 경우 꼬리 있는 칸 0으로 셋팅!
				board[nextI][nextJ] = 1;
				board[q.peekLast().x][q.peekLast().y] = 0;
				q.pollLast();
			}
			
			if(C[time] == 'L'){
				dir = (dir + 3) % 4;
			} else if(C[time] == 'D'){
				dir = (dir + 1) % 4;
			}
		}
		System.out.println(time);
	}
}