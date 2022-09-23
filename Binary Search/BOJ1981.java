import java.util.*;
import java.io.*;
//배열에서 이동
public class BOJ1981 {
	static int n,ans,min,Max;
	static int start,end,mid;
	static int[][] arr;
	static boolean flag;
	static boolean[][] visited;
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	static class Point{
		int x,y;
		
		public Point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		visited = new boolean[n][n];
		Max = -1;
		min = 200;
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(Max < arr[i][j]) Max = arr[i][j];
				if(min > arr[i][j]) min = arr[i][j];
			}
		}
		
		ans = Max - min;
		
		solve();
		
		System.out.println(ans);
	}
	static void solve() {
		start = 0; end = Max - min;
		
		while(start <= end) {
			mid = (start+end) / 2;
			flag = false;
			
			for(int i=min;i<=Max;i++) {
				if(i <= arr[0][0] && arr[0][0] <= i+mid) {
					if(flag = bfs(i,i+mid)) break;
				}
			}
			
			if(flag) {
				end = mid-1;
				ans = Math.min(ans, mid); 
			} else {
				start = mid+1;
			}
		}
	}
	static boolean bfs(int s, int e) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0,0));
		
		for(int i=0;i<n;i++) Arrays.fill(visited[i], false);
		
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.x == n-1 && cur.y == n-1) return true;
			
			for(int d=0;d<4;d++) {
				int nx = cur.x + dir[d][0];
				int ny = cur.y + dir[d][1];
				
				if(isOut(nx,ny) || visited[nx][ny]) continue;
				
				if(s <= arr[nx][ny] && arr[nx][ny] <= e) {
					q.offer(new Point(nx,ny));
					visited[nx][ny] = true;
				}
			}
		}
		
		return false;
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>n-1 || y<0 || y>n-1;
	}
}