import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class HikingTrail_2nd_DFS {
	static int max,ans,T,N,K,Map[][];//각 TC마다 필요한 자료구조,객체
	static class Point{
		int x,y,dist,drill;//dist는 등산로의 길이를 의미한다!
		Point(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		Point(int x,int y,int dist,int drill){
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.drill = drill;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	static void dfs(int x,int y,int dist) {
		ans = Math.max(ans, dist);

		if(isIn(x-1,y) && Map[x][y]>Map[x-1][y]) {dfs(x-1,y,dist+1);}
		if(isIn(x+1,y) && Map[x][y]>Map[x+1][y]) {dfs(x+1,y,dist+1);}
		if(isIn(x,y-1) && Map[x][y]>Map[x][y-1]) {dfs(x,y-1,dist+1);}
		if(isIn(x,y+1) && Map[x][y]>Map[x][y+1]) {dfs(x,y+1,dist+1);}
		
	}
	static boolean outRange(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	static boolean isIn(int x,int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			//각 TC마다 필요한 객체 초기화
			Map = new int[N][N];
			ans = 0;//정답 저장 
			max = -1;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					//if(max<=Map[i][j]) {
					if(max<=Map[i][j]) {
						max = Map[i][j];//최댓값으로 갱신해서 max에 저장
					}
				}
			}
			//최댓값을 찾아서 list에 저장한다!
			LinkedList<Point> list = new LinkedList<>();//최대 높이 좌표 저장.
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(max==Map[i][j]) {
						list.add(new Point(i,j,1));
					}
				}
			}
			//구현 시작:K*N^2
			for(int k=0;k<=K;k++) {
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(Map[i][j] - k <0)continue;
						Map[i][j] -= k;
						for(int l=0;l<list.size();l++) {
							dfs(list.get(l).x,list.get(l).y,1);
						}
						Map[i][j] += k;
					}
				}
			}
			System.out.println("#"+t+" "+ans);
		}
	}

}