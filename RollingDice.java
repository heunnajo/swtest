package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class RollingDice {
	static int N,M,x,y,T,Orders[],Map[][],Dice[];
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static StringBuilder sb;
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static void solve() {
		Point cur_dice = new Point(x,y);//주사위 현재위치.
		//명령어가 저장된 Orders 배열에서 t번째에 해당하는 명령어에 따라 주사위 굴린다!
		for(int t = 0;t<T;t++) {
			int order = Orders[t];
			//주사위 굴리기(이동시키기).지도의 (0,0)에서 시작.
			int nx = cur_dice.x+dx[order];//주사위 현재위치(x,y) 기준으로 이동해야하는데.
			int ny = cur_dice.y+dy[order];
			//범위체크.
			if(nx<0 || nx>N-1 || ny<0 || ny>M-1) continue;
			/*명령어에 따라 주사위 이동, 윗면값 갱신!(현재 명령어 order에 따라 주사위값 먼저 회전.)*/
			      //if(Map[nx][ny]!=0) {//이스코프는 아래 55번째줄로 이동시켜도 될 듯?!
			//범위체크후, 현재 주사위의 논리적 위치는 (nx,ny)
			//1.상하 이동: Dice[4],Dice[5] 불변:남북 = 북(위)2 또는 남(아래)3
			//Dice[0],[1],[2],[3] 회전.
			if(order == 3) {//아래. 남.
				int tmp = Dice[3];
				for(int k=3;k>=1;k--) {
					Dice[k] = Dice[k-1];
				}
				Dice[0] = tmp;
			} else if(order == 2) {//위. 북.
				int tmp = Dice[0];
				for(int k=0;k<=2;k++) {
					Dice[k] = Dice[k+1];
				}
				Dice[3] = tmp;
			}
			
			//2.좌우 이동: Dice[0],Dice[2] 불변.
			//Dice[1],[3],[4],[5] 회전.
			if(order == 1) {//왼쪽. 서.
				int tmp = Dice[3];
				Dice[3] = Dice[4];
				int tmp2 = Dice[1];
				Dice[1] = Dice[5];
				Dice[4] = tmp2;//Dice[4] = Dice[1]
				Dice[5] = tmp;//Dice[5] = Dice[3]
				//Dice[3] = Dice[4];
			} else if(order == 0) {//오른쪽. 동.
				int tmp = Dice[3];
				Dice[3] = Dice[5];
				int tmp2 = Dice[1];
				Dice[1] = Dice[4];
				Dice[4] = tmp;//Dice[4] = Dice[3]
				Dice[5] = tmp2;//Dice[5] = Dice[1]
				//Dice[3] = Dice[5];
			}
			if(Map[nx][ny]!=0) {//이스코프는 아래 55번째줄로 이동시켜도 될 듯?!
				//지도값에 따라 주사위 값 갱신.
				Dice[3] = Map[nx][ny];
				Map[nx][ny] = 0;
			} else {//주사위가 이동한 위치(nx,ny)
				Map[nx][ny] = Dice[3];
			}
			sb.append(Dice[1]+"\n");//주사위 윗칸을 저장!
			cur_dice.x = nx;
			cur_dice.y = ny;
//			System.out.println("=======  주사위 디버깅 =====");
//			for(int di=0;di<6;di++) {
//				System.out.print(Dice[di]+" ");
//			}
//			System.out.println();
//			System.out.println("=========================");
		}//명령어 갯수만큼 반복하는 for문.
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");//세로 N=행크기,가로 M=열크기,x,y,order
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		x = Integer.parseInt(input[2]);
		y = Integer.parseInt(input[3]);
		T = Integer.parseInt(input[4]);//명령어 수.
		//객체 초기화.
		Map = new int[N][M];//세로 N = 행의 크기!
		Orders = new int[T];
		Dice = new int[6];//처음엔 모두 0.
		sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		input = br.readLine().split(" ");
		for(int i=0;i<T;i++) {
			Orders[i] = Integer.parseInt(input[i])-1;//델타어레이 사용!
		}
	
		solve();
//		System.out.println("정답 출력");
		System.out.print(sb);
		
		br.close();
		
	}

}
//디버깅.
//for(int i=0;i<N;i++) {
//	for(int j=0;j<M;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}
//System.out.println();
//for(int i=0;i<T;i++) {
//	System.out.print(order[i]+" ");
//}
//System.out.println();