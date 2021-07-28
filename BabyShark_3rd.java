import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class BabyShark_3rd {
	static int n,size,cnt,time,sx,sy;//전체 크기 n, 상어크기, 상어가 먹은 물고기 갯수 
	static int[][] map;
	static ArrayList<Pnt> fishes;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pnt{
		int x,y,dist;
		Pnt(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	static void bfs() {
		
		while(true) {
			fishes = new ArrayList<Pnt>();
			Queue<Pnt> q = new LinkedList<>();
			boolean[][] visited = new boolean[n][n];
			q.add(new Pnt(sx,sy,0));
			visited[sx][sy] = true;
			
			//물고기 찾는 완탐.
			while(!q.isEmpty()) {
				Pnt cur = q.remove();
				
				for(int d=0;d<4;d++) {
					int nx = cur.x+dx[d];
					int ny = cur.y+dy[d];
					if(range_check(nx,ny))continue;
					if(visited[nx][ny]) continue;
					
					//물고기 먹을 수 있는 경우.
					if(1<=map[nx][ny] && map[nx][ny]<size) {
						q.add(new Pnt(nx,ny,cur.dist+1));
						visited[nx][ny] = true;
						fishes.add(new Pnt(nx,ny,cur.dist+1));
					}
					//물고기 먹을 수 없는 경우.(이동만 하는 경우)
					if(map[nx][ny] == size || map[nx][ny] == 0) {
						q.add(new Pnt(nx,ny,cur.dist+1));
						visited[nx][ny] = true;
					}
				}
			}
			if(fishes.size()==0) {
				System.out.println(time);
				return;
			}
			//문제 조건에 따라 물고기 선택.
			Pnt fish = fishes.get(0);
			for(int i=1;i<fishes.size();i++) {
				if(fishes.get(i).dist < fish.dist) {
					fish = fishes.get(i);
				}
				if(fishes.get(i).dist == fish.dist && fishes.get(i).x < fish.x) {
					fish = fishes.get(i);
				}
			}
			//물고기 먹는 처리.
			time += fish.dist;
			map[fish.x][fish.y] = 0;
			cnt++;
			if(cnt==size) {
				size++;
				cnt=0;
			}
			sx = fish.x; sy = fish.y;
		}
		
	}
	static boolean range_check(int x,int y) {
		return x<0 || y<0 || x>=n || y>=n;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		//초기 상어 크기, 먹은 물고기 갯수 초기화.
		size = 2; cnt = 0;
		for(int i=0;i<n;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j]==9) {
					sx = i;sy = j;
					map[i][j] = 0;
				}
			}
		}
		bfs();
	}

}