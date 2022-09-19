package ss;
import java.util.*;
import java.io.*;
//스타트 택시
public class BOJ19238_pureCode {
	static int N,M,fuel,sx,sy;//택시 위치(sx,sy)
	static int[][] Map;
	static boolean isPossible;
	static int curNum,minDist;//현재 이동하려는 승객의 번호, 승객까지 거리
	static Customer[] Customers;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Customer implements Comparable<Customer>{
		int sx,sy,dx,dy;
		boolean isArrived;
		
		public Customer(int sx,int sy,int dx,int dy,boolean isArrived){
			this.sx = sx;
			this.sy = sy;
			this.dx = dx;
			this.dy = dy;
			this.isArrived = isArrived;
		}
		public Customer(int sx,int sy,int dx,int dy){
			this.sx = sx;
			this.sy = sy;
			this.dx = dx;
			this.dy = dy;
		}
		
		@Override
		public int compareTo(Customer c) {
			if(this.sx == c.sx) {
				return this.sy - c.sy;//열 오름차순
			}
			return this.sx - c.sx;//행 오름차순
		}
	}
	static class Pos{
		int x,y,dist;
		
		public Pos(int x,int y,int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		Map = new int[N+1][N+1];
		Customers = new Customer[M];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int beforeX = Integer.parseInt(st.nextToken());
			int beforeY = Integer.parseInt(st.nextToken());
			int afterX = Integer.parseInt(st.nextToken());
			int afterY = Integer.parseInt(st.nextToken());
			
			Customers[i] = new Customer(beforeX,beforeY,afterX,afterY);
		}
		
		//Arrays.sort(Customers);//1
		isPossible = true;
		
		solve();
		
		if(!isPossible) System.out.println(-1);
		else System.out.println(fuel);
		
	}
	static void solve() {
		
		for(int i=0;i<M;i++) {
			//Arrays.sort(Customers);//2
			int toCustomer, toDestination;
			getCustomer();//void로 한 이유:필요한 정보는 승객번호,승객까지 거리 2개 값인데 리턴값은 하나만 가능하므로 전역변수로 필요한 정보를 저장!
			
			if(minDist >= fuel || !isPossible) {
				isPossible = false;
				return;
			}
			
			toCustomer = minDist;//getCustomer에서 구한 거리값
			toDestination = getDist(Customers[curNum].sx,Customers[curNum].sy,Customers[curNum].dx,Customers[curNum].dy);
			
			if(toDestination > fuel || fuel - toCustomer - toDestination < 0) {
				isPossible = false;
				return;
			}
			
			sx = Customers[curNum].dx;
			sy = Customers[curNum].dy;
			
			Customers[curNum].isArrived = true;
			
			fuel = fuel - toCustomer - toDestination + toDestination * 2;
			
		}
		
	}
	static void getCustomer() {//가장 가까운 승객의 번호와 거리를 찾음.
		int dist = Integer.MAX_VALUE;
		int curDist = 0;
		for(int i=0;i<M;i++) {
			Arrays.sort(Customers);//3.아직 이동시키지 않은 승객들 대상으로? 일단 행번호, 열번호 오름차순 정렬부터해야하지 않나!
			if(Customers[i].isArrived) continue;
			
			//Arrays.sort(Customers);//4
			curDist = getDist(sx,sy,Customers[i].sx,Customers[i].sy);
			if(dist>curDist) {//여기서 curDist가 -1(불가능) 가 나올 수도 있지 않나?
				curNum = i;
				dist = curDist;
				minDist = dist;
			}
		}
	}
	static int getDist(int sx,int sy,int destX,int destY) {//bfs
		Queue<Pos> q = new LinkedList<>();
		boolean[][] v = new boolean[N+1][N+1];
		q.offer(new Pos(sx,sy,0));
		v[sx][sy] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			if(cur.x == destX && cur.y == destY) {
				return cur.dist;
			}
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d], ny = cur.y+dy[d];
				
				if(isOut(nx,ny) ||v[nx][ny]) continue;
				if(Map[nx][ny] == 1) continue;
				
				q.offer(new Pos(nx,ny,cur.dist+1));
				v[nx][ny] = true;
			}
		}
		
		//unreachable
		isPossible = false;
		return -1;
	}
	static boolean isOut(int x,int y) {
		return x<1 || x>N || y<1 || y>N;
	}
}
