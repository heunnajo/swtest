package ss;
import java.util.*;
import java.io.*;
public class BallsOnChessBoard {
	static int R,C,Ans[][],Arr[][];
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x; this.y = y;
		}
	}
	static Pair[][] Map;
	static int[] dx = {-1,1,0,0,-1,-1,1,1};
	static int[] dy = {0,0,-1,1,-1,1,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Arr = new int[R][C];//체스판 각 칸 숫자 저장
		Ans = new int[R][C];//(x,y)에 있는 공 갯수 저장
		Map = new Pair[R][C];//각 칸마다 이동할 위치로 가는 변위값 저장
		
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				Arr[i][j] = Integer.parseInt(st.nextToken());
				Ans[i][j] = 1;
			}
		}
		
		//각 칸 별로 최솟값갖는 칸 찾아서 변위값으로 저장!
		//Map 배열 완성!
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				//일단 현재위치값을 최솟값 min으로 갖고, 변위값 또한 (0,0)으로. 
				int min = Arr[i][j];
				Map[i][j] = new Pair(0,0);
				for(int dir=0;dir<8;dir++) {
					int nx = i+dx[dir],ny = j+dy[dir];
					if(isOut(nx,ny)) continue;
					if(min>Arr[nx][ny]) {//더 작은 (nx,ny)를 찾을 때만 그 칸으로 변위값 갱신!
						min = Arr[nx][ny];
						Map[i][j] = new Pair(dx[dir],dy[dir]);
					}
				}
			}
		}
		//변위값 확인
//		for(int i=0;i<R;i++) {
//			for(int j=0;j<C;j++) {
//				Pair cur = Map[i][j];
//				System.out.print("("+cur.x+","+cur.y+")"+"  ");
//			}
//			System.out.println();
//		}
		
		while(true) {
			//각 이동 턴마다 이동했는지를 판별.
			boolean flag = false;
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(Ans[i][j] == 0) continue;//이동시킬 공이 없으면 컨티뉴 처리!
					Pair cur = Map[i][j];
					if(cur.x == 0 && cur.y == 0) continue;//변위값이 (0,0)이면 이동X
					//여기까지 왔다는 건 현재위치에서 공이 1개 이상 있고, 유의미한 변위값이 있다는 의미!
					flag = true;
					//int nx = i+dx[cur.x], ny = j+dy[cur.y];
					int nx = i+cur.x, ny = j+cur.y;
					Ans[nx][ny] += Ans[i][j];
					Ans[i][j] = 0;
				}
			}
			if(!flag) {//RxC 칸 다 순회했는데 공이동이 발생하지 않았다는 의미.
				break;
			}
		}
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(Ans[i][j]+" ");
			}
			System.out.println();
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>R-1 || y<0 || y>C-1;
	}
}
