package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;
public class CultureCell {
	static int ans,T,N,M,K,Map[][];
	static class Cell{
		int x,y,age,off,on,die;
		boolean alive;
		
		Cell(int x,int y,int age,int off){
			this.x = x;
			this.y = y;
			this.age = age;
			this.off = off;
		}
		Cell(int x,int y,int age,int off,int on){
			this.x = x;
			this.y = y;
			this.age = age;
			this.off = off;
			this.on = on;
		}
		Cell(int x,int y,int age,int off,int on,boolean alive){
			this.x = x;
			this.y = y;
			this.age = age;
			this.off = off;
			this.on = on;
			this.alive = alive;
		}
		Cell(int x,int y,int age,int off,int on,boolean alive,int die){
			this.x = x;
			this.y = y;
			this.age = age;
			this.off = off;
			this.on = on;
			this.alive = alive;
			this.die = die;
		}
	}
	static Cell[][] CellMap;
	static LinkedList<Cell> Cells;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static void culture() {
		//K시간 동안 반복해서 세포를 배양/사망 처리한다.BFS로 탐색.
		int time = 0;
		Queue<Cell> q;
		q = new LinkedList<>();
		boolean[][] visited;
		for(int i=0;i<Cells.size();i++) {
			q.add(Cells.get(i));
		}
		int size;
		while(time<=K) {
			visited = new boolean[N+500][M+500];
			//현재 시점이 활성상태인 세포의 위치가 탐색 시작점이 된다.=>완탐으로 구하지말고,애초에 0이아닌값(세포)위치 저장해서 조회
			while(!q.isEmpty()) {
				size = q.size();
				Cell cur = q.remove();
				int cx,cy,ca,nx,ny;
				cx = cur.x; cy=cur.y; ca=cur.age;
				for(int i=0;i<size;i++) {
					for(int d=0;d<4;d++) {
						nx = cx+dx[d];ny = cy+dy[d];
						
						if(!CellMap[nx][ny].alive) continue;
						if(ca>CellMap[nx][ny].age) {
							q.add(new Cell(nx,ny,ca,time,time+ca,true,time+2*ca));
						}
					}
				}
			}
		}
		//살아있는 남은 세포 카운팅
		ans = countCell();
	}
	static int countCell() {
		int sum = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(CellMap[i][j].alive) sum++;
			}
		}
		return sum;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			Map = new int[N][M];
			CellMap = new Cell[N+500][M+500];//시간은 최대 300시간이므로
			Cells = new LinkedList<>();
			int X = 0;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					Map[i][j] = X = Integer.parseInt(st.nextToken());
					if(Map[i][j]>0) Cells.add(new Cell(i,j,X,0,X,true,2*X));//세포의 현재위치,비활성화시점,활성화시점,
				}
			}
			culture();
			sb.append("#"+t+" "+ans+"\n");
		}
	}

}
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(Map[i][j]+" ");
//				}
//				System.out.println();
//			}
