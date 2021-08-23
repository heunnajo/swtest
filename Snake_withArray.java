import java.io.BufferedReader;
import java.io.InputStreamReader;

class Snake_withArray {
	static final int INF = 987654321;
	static int max;
	static int N;
	static Snake[] snake = new Snake[10000];
	static int[] ci = {0, 1, 0, -1};
	static int[] cj = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		int len = 0;
		int headIdx = 0;
		int tailIdx = 0;
		snake[0] = new Snake(0,0,-1);
		board[0][0] = 1;
		
		for(int i = 0; i < K; i++){
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
			if(board[nextI][nextJ] == 2){
				snake[++len] = new Snake(nextI, nextJ, -1);
				snake[headIdx].pIdx = len;
				headIdx = len;
				board[nextI][nextJ] = 1;
			} else{
				board[snake[tailIdx].i][snake[tailIdx].j] = 0;
				snake[headIdx].pIdx = tailIdx;
				headIdx = tailIdx;
				if(snake[tailIdx].pIdx != -1) tailIdx = snake[tailIdx].pIdx;
				snake[headIdx].i = nextI;
				snake[headIdx].j = nextJ;
				snake[headIdx].pIdx = -1;
				board[nextI][nextJ] = 1;
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

class Snake {
	int i;
	int j;
	int pIdx;
	
	public Snake(){}
	public Snake(int i, int j, int pIdx){
		this.i = i;
		this.j = j;
		this.pIdx = pIdx;
	}
}