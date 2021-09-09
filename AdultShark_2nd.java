package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class AdultShark_2nd {
	static int N,M,K,Map[][][],sharkIdx,ans;
	static class Shark{
		int x,y,d;
		int[][] Dir = new int[4][4];
		Shark(int x,int y,int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		Shark(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	static Shark[] sharkArr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		
		Map = new int[N][N][3];
		sharkArr = new Shark[M];
		
		//현재 Map 정보 입력 받는다.
		sharkIdx = 0;
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				int tmp = Integer.parseInt(input[j]);
				if(tmp != 0) {//상어 정보 저장~!
					Map[i][j][0] = tmp;//상어 번호 저장.
					Shark shark = new Shark(i,j);
					sharkArr[tmp-1] = shark;
					Map[i][j][1] = Map[i][j][0];
				}
			}
		}
		//M개의 상어 현재방향 정도 입력 받는다:인덱스 0부터 사용할 거기 때문에 -1해준다!
		input = br.readLine().split(" ");
		for(int i=0;i<M;i++) {
			Shark cur = sharkArr[i];
			cur.d = Integer.parseInt(input[i])-1;
			//System.out.println(cur.d);
		}
		
		for(int m = 0;m<M;m++) {//M번 반복.
			Shark cur = sharkArr[m];
			int[][] tmp = new int[4][4];
			for(int i=0;i<4;i++) {
				input = br.readLine().split(" ");
				for(int j=0;j<4;j++) {
					tmp[i][j] = Integer.parseInt(input[j])-1;
					//System.out.print(tmp[i][j]+" ");
				}
				//System.out.println();
			}
			cur.Dir = tmp;
		}
//		방향우선순위 정보 저장, 확인 성공!
//		for(int m=0;m<M;m++) {
//			Shark cur = sharkArr[m];
//			for(int i=0;i<4;i++) {
//				for(int j=0;j<4;j++) {
//					System.out.print(cur.Dir[i][j]+" ");
//				}
//				System.out.println();
//			}
//		}
		
//		for(int i=0;i<M;i++) {
//			Shark cur = sharkArr[i];
//			int[][] curSharkDir = cur.Dir;
//			for(int j=0;j<4;j++) {
//				System.out.print(curSharkDir[i][j]+" ");
//			}
//			System.out.println();
//			
//		}
		//Map입력받기 성공.
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(Map[i][j][0] +" ");
//			}
//			System.out.println();
//		}
		//상어 배열에 저장된 상어 좌표와 3차원 배열 Map에서의 값 조회.성공.
//		for(int i=0;i<M;i++) {
//			Shark cur = sharkArr[i];//i=0번째 상어부터 (x,y)좌표 조회, 맵값 확인.
//			System.out.print("cur.x: "+cur.x+" cur.y : "+cur.y);
//			System.out.println(Map[cur.x][cur.y][0]+" ");
//		}
		br.close();
	}
	
	static void solve() {
		int killedCnt=0,time=0;
		while(killedCnt<M-1 && time<=1000) {
			for(int i=0;i<M;i++) {//상어배열에는 번호작은 순서부터 저장되어있다.
				Shark cur = sharkArr[i];
				int cx = cur.x, cy = cur.y, cd = cur.d;
				int[][] DirPriority = cur.Dir;
				boolean flag = false;
				
				//냄새 강도 감소시켜줘야함.
				for(int r=0;r<N;r++) {
					for(int c=0;c<N;c++) {
						if(Map[r][c][2]>0) {
							Map[r][c][2]--;
						}
					}
				}
				
				//우선순위 방향으로 방향설정, 이동!
				//1.냄새가 없는 칸이면 이동!
				for(int j=0;j<4;j++) {
					int nd = DirPriority[cd][j];//현재 상어 방향에 따라 우선순위 방향 속성 조회!
					int nx = cx+dx[nd];
					int ny = cy+dy[nd];
					
					if(isOutOfRange(nx,ny)) continue;
					
					if(Map[nx][ny][1] == 0) {
						flag = true;
						//상어 클래스 정보, Map 정보 갱신!
						cur.x = nx;
						cur.y = ny;
						cur.d = nd;
						Map[cx][cy][0] = 0;
						Map[nx][ny][1] = i;
						Map[nx][ny][2] = K;
						break;
					}
					if(Map[nx][ny][0] != 0) {
						//이미 번호가 더 작은 앞의 상어가 이동을 해서 자리차지했다는 뜻이기 때문에 
						//뒤이어 나오는 상어들은 모두 삭제처리하면 됨.
						killedCnt++;
					}
				}
				//2.냄새 없는 칸 못 찾았다면 인접 칸들 중 자신의 냄새칸으로 방향 설정, 이동!
				if(!flag) {
					for(int j=0;j<4;j++) {
						int nd = DirPriority[cd][j];//현재 상어 방향에 따라 우선순위 방향 속성 조회!
						int nx = cx+dx[nd];
						int ny = cy+dy[nd];
						
						if(isOutOfRange(nx,ny)) continue;
						//이동 시작!
						//냄새가 없는 칸이면 이동!
						if(Map[nx][ny][1] == i) {
							flag = true;
							//상어 클래스 정보, Map 정보 갱신!
							cur.x = nx;
							cur.y = ny;
							cur.d = nd;
							Map[cx][cy][0] = 0;
							Map[nx][ny][1] = i;
							Map[nx][ny][2] = K;
							break;
						}
						//한칸에 여러마리 있을 경우!
						if(Map[nx][ny][0] != 0) {
							//이미 번호가 더 작은 앞의 상어가 이동을 해서 자리차지했다는 뜻이기 때문에 
							//뒤이어 나오는 상어들은 모두 삭제처리하면 됨.
							killedCnt++;
						}
					}
				}
				//시간 증가.
				time++;
				
			}//i-for문. M개 상어 이동.
			ans = time;//이동처리 다 한후, 반복문이 다시 한번 더 돌기 전에, 종료될 수도 있기 때문에 현재의 time을 ans에 저장! 
		}//while
	}
	static boolean isOutOfRange(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	public static void main(String[] args) throws Exception{
		init();
		ans = 0;
		solve();
		System.out.println(ans);
	}
}