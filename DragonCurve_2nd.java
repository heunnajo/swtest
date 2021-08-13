package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
public class DragonCurve_2nd {
	static int ans,x,y,N;//현재 좌표 x,y
	static final int SIZE = 101;
	static int[][] Map = new int[101][101];
	//static LinkedList<Integer> dir;
	static int[] dx = {0,-1,0,1};//우상좌하
	static int[] dy = {1,0,-1,0};
	static void makeDragon(LinkedList<Integer> dir) {//i세대 드래곤 커브 생성, 꼭짓점 마킹, 리스트에 방향값 추가!
		//다음 방향값 구하기.
		int nx = x,ny = y;//다음 좌표 nx,ny는 현재 x,y에서 시작하기 때문에 x,y로 초기화!!!!!!
		for(int k=dir.size()-1;k>=0;k--) {
			int new_dir = (dir.get(k)+1)%4;//새로운 방향값 구한다!
			//다음 좌표 구하기.
			nx = nx +dx[new_dir];//다음 nx는 현재의 nx에 방향값을 더한 것이다!!!
			ny = ny +dy[new_dir];
			
			if(nx >= 0 && nx < 101 && ny >= 0 && ny < 101)//새로운 좌표값 범위체크?!
				Map[nx][ny] = 1;//다음 좌표값에 꼭짓점 마킹!
			
			dir.add(new_dir);
		}
		x = nx; y = ny;//현재 좌표를 nx,ny로 갱신!!!
	}
	static int check() {
		int ans = 0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(Map[i][j] == 1 && Map[i][j+1] == 1 && Map[i+1][j] == 1 && Map[i+1][j+1] == 1) {
					ans++;
					
				}
			}
		}
		return ans;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strN = br.readLine();
		N = Integer.parseInt(strN);
		while(N-- >0) {
			String[] input = br.readLine().split(" ");
			//드래곤 커브마다 방향값 저장 리스트 생성 & 초기화!
			LinkedList<Integer> dir = new LinkedList<>();
			int d,g;
			x = Integer.parseInt(input[1]);
			y = Integer.parseInt(input[0]);
			d = Integer.parseInt(input[2]);
			g = Integer.parseInt(input[3]);
			
			dir.add(d);
			
			Map[x][y] = 1;//0세대 시작점 마킹
			x = x+dx[d]; y = y+dy[d];
			if(x >= 0 && x < SIZE && y >= 0 && y < SIZE) {//0세대 끝점 마킹//범위체크?!
				Map[x][y] = 1;
			}
			//0세대 드래곤 커브 형성한 후에, 다음 방향값 구하고, 다음 좌표 구해야한다!
			//시작 방향으로 다음 좌표 구하는 것의 반복. g세대 드래곤 커브 구해야한다!
			for(int i=0;i<g;i++) {
				makeDragon(dir);//현재의 g세대 인덱스, dir 리스트 넘겨준다.
			}
		}
		System.out.println(check());
		br.close();
	}

}
