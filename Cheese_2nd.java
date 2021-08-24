package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class Cheese_2nd {
	static int R,C,Cheese,time,Map[][];
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static void meltingCheese() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0));
		boolean[][] visited = new boolean[R][C];//방문체크는 사실 필요없을 듯. 0이면 치즈 녹이러 방문X 
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.remove();
			
			for(int i=0;i<4;i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				
				if(check(nx,ny) || visited[nx][ny]) continue;
				
				//치즈일때, 녹이고 치즈갯수 감소.
				if(Map[nx][ny] == 1) {
					Map[nx][ny] = 0;
					Cheese--;
				}else {//공기이면 BFS 탐색 대상이 된다!
					q.add(new Point(nx,ny));
				}
				visited[nx][ny] = true;
			}
				
			
		}
		
	}
	static boolean check(int x,int y) {
		return x<0 ||x>R-1 || y<0 || y>C-1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		
		Map = new int[R][C];
		Cheese = 0;
		time=0;
		
		for(int i=0;i<R;i++) {
			input = br.readLine().split(" ");
			boolean flag = false;
			for(int j=0;j<C;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
				if(Map[i][j] == 1) {
					Cheese++;
				}
			}
		}
		
		int finalCnt = 0;
		while(Cheese!=0) {
			time++;
			finalCnt = Cheese;//다 녹이기 전의 치즈 갯수 저장해야한다!
			meltingCheese();
		}
		
		System.out.println(time);
		System.out.println(finalCnt);
		br.close();
	}

}
//for(int i=0;i<R;i++) {
//	for(int j=0;j<C;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}
