package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
public class PopulationMove_2nd {
	static int N, L, R,cnt;
	
	static int[][] map, visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int find(int x,int y,int value) {
		//범위 체크.
		//방문 체크.
		if(check(x,y)) return 0;
		if(visited[x][y]!=0) return 0;//이미 방문.
		
		if(value!=-1) {//이전 국가 인구수와 차이 인구이동 조건 판단.
			int diff = Math.abs(value-map[x][y]);
			if(diff<L || diff>R) return 0;
		}
		
		//인구이동에 해당.
		visited[x][y] = 1;
		cnt++;//한 칸 = 연합국 하나를 의미하기 때문에!
		
		int sum = map[x][y];
		sum += find(x-1,y,map[x][y]);//현재값(인구수)을 재귀호출에 인자로 넘겨주어, 이전 인구값으로써 인구이동 판단!!!
		sum += find(x+1,y,map[x][y]);
		sum += find(x,y-1,map[x][y]);
		sum += find(x,y+1,map[x][y]);
		
		return sum;
		
	}
	static void move(int x,int y,int value) {
		if(check(x,y)) return;
		if(visited[x][y]!= 1) return;
		
		map[x][y] = value;
		visited[x][y] = 2;
		move(x-1,y,value);
		move(x+1,y,value);
		move(x,y-1,value);
		move(x,y+1,value);
		
	}
	static int solve() {
		int ans=0;//인구이동 횟수.
		boolean flag;
		do {
			flag = false;
			//방문 배열 초기화!
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					visited[i][j] = 0;
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					cnt=0;
					int sum = find(i,j,-1);
					if(cnt>1) {//인구이동 발생!
						flag = true;
						move(i,j,sum/cnt);
					} else {
						visited[i][j] = 2;//인구이동 조건 해당X 
					}
				}
			}
			
			if(flag) ans++;//인구이동일어나면 갯수 카운팅.
		}while(flag);
		return ans;
	}
	static boolean check(int x,int y) {
		return x<0 || y<0 || x>=N || y>=N;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		L = Integer.parseInt(input[1]);
		R = Integer.parseInt(input[2]);
		map = new int[N][N];
		visited = new int[N][N];
		for(int i=0;i<N;i++){
			String[] input2 = br.readLine().split(" ");
			for(int j=0;j<N;j++){
				map[i][j] = Integer.parseInt(input2[j]);
			}
		}
		System.out.println(solve());
	}
}
//1.solve() :do-while로 완전탐색&인구이동 반복.
//2.find() :총 인구수 반환.
//2.move() :인구  이동.