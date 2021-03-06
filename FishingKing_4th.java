import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FishingKing_4th {
	static int ans,R,C,M;//ans에 잡은 상어크기 합산!
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static class Shark{
		int speed,dir,size;
		Shark(int speed,int dir,int size){
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	static Shark[][] SharkMap;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		SharkMap = new Shark[R][C];
		
		for(int i=0;i<M;i++) {//M개의 상어 정보 저장!
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken())-1;
			int size = Integer.parseInt(st.nextToken());
			if(dir == 1)dir=2;
			else if(dir == 2)dir=1;
			
			SharkMap[x][y] = new Shark(speed,dir,size);
		}
		ans = 0;
		solve();
		System.out.println(ans);
	}
	
	static void solve() {
		Shark[][] backup = new Shark[R][C];
		for(int c=0;c<C;c++) {//1.낚시왕 오른쪽으로 이동
			for(int r=0;r<R;r++) {//2.상어 갓챠
				if(SharkMap[r][c]!=null) {//null로 비교해도 되려나?!
					ans += SharkMap[r][c].size;
					SharkMap[r][c] = null;
					break;//물고기 한번 잡았으면 계속 밑으로 가는 게 아니라 종료!
				}
			}
			//3-1.상어 이동 전 2차원 배열 하나 더 생성!
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					backup[i][j] = SharkMap[i][j];//원래 배열
					SharkMap[i][j] = null;//새로 이동시킬 배열!
				}
			}
					
			//3-2.상어 이동:상어 위치들을 이미 알고 있는데 R*C 전체를 순회해야만 할까..!일단 생각한 방법으로 구현.
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(backup[i][j]!=null) {//이동시킬 상어가 있다는 뜻.
						//이동하려는 칸에 상어가 1마리가 없다는 가정 하.
						Shark cur = backup[i][j];
						int speed = cur.speed, dir = cur.dir, size = cur.size;
						int nx = i+dx[dir]*speed;
						int ny = j+dy[dir]*speed;
						if(nx<0) {nx = -nx; cur.dir = (cur.dir+2)%4;}//아래로 이동시킴.
						if(nx>R-1) {
							int a = nx/(R-1);
							int b = nx%(R-1);
							if(a%2 == 0) {nx = b;}
							else {nx = R-1-b;cur.dir = (cur.dir+2)%4;}
						}
						if(ny<0) {ny = -ny;cur.dir = (cur.dir+2)%4;}
						if(ny>C-1) {
							int d = ny/(C-1);
							int e = ny%(C-1);
							if(d%2 == 0) {ny = e;}
							else {ny = C-1-e;cur.dir = (cur.dir+2)%4;}
						}
						if(SharkMap[nx][ny] == null||SharkMap[nx][ny]!= null && SharkMap[nx][ny].size<size) {
							SharkMap[nx][ny] = cur;
						}
					}
				}
			}
		}
	}
}