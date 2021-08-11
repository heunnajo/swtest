package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
public class Shark_RainMaker {
	static int ans,N,M,Map[][];
	static class Order{
		int d,s;
		Order(int d,int s){
			this.d = d;this.s = s;
		}
	}
	static Order[]Orders;
	static int orderCnt;
	static class Cloud{
		int x,y;
		Cloud(int x,int y){
			this.x = x; this.y = y;
		}
	}
	static LinkedList<Cloud> cloudList;//초기 구름 정보 저장 컬렉션
	static int[] cloudX = {0,-1,-1,-1,0,1,1,1};//구름 이동 8개 방향
	static int[] cloudY = {-1,-1,0,1,1,1,0,-1};
	static int[] copyWX = {-1,-1,1,1};//물복사 대각선 4개 방향
	static int[] copyWY = {-1,1,-1,1};
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		Map = new int[N][N];
		cloudList = new LinkedList<Cloud>();
		Orders = new Order[M];
		orderCnt = 0; ans = 0;
		//격자 정보 입력받는다.
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		//명령어 정보 입력받는다.
		for(int i=0;i<M;i++) {
			input = br.readLine().split(" ");
			int d = Integer.parseInt(input[0])-1;//방향 델타 어레이 인덱스!
			int s = Integer.parseInt(input[1]);
			Orders[orderCnt++] = new Order(d,s);
		}
		//초기 구름 객체 생성!
		cloudList.add(new Cloud(N-1,0));//(N,1)
		cloudList.add(new Cloud(N-1,1));//(N,2)
		cloudList.add(new Cloud(N-2,0));//(N-1,1)
		cloudList.add(new Cloud(N-2,1));//(N-1,2)
		br.close();
	}
	static void solve() {
		for(int m=0;m<M;m++) {
			Order cur = Orders[m];
			//현재 명령으로 구름 이동!
			for(Cloud c : cloudList) {
				int nx = c.x+cloudX[cur.d]*cur.s%N;//%N를 해줘야하나?!
				int ny = c.y+cloudY[cur.d]*cur.s%N;
				//범위 모듈러 연산자로 갱신.
				if(nx<0) {//밑에서 올라온다!
					nx = N - (Math.abs(nx)%N);
				} else if(nx>=N) {
					nx = nx % N;
				}
				if(ny<0) {//밑에서 올라온다!
					ny = N - (Math.abs(ny)%N);
				} else if(ny>=N) {
					ny = ny % N;
				}
				
				c.x = nx; c.y = ny;//구름 이동!
				Map[nx][ny]++;//구름이 이동한 위치에 비가내려서 물 양 1 증가!
			}
			//물복사 : 물이 증가한칸에 대각선 방향 물있는 바구니 갯수만큼 물양 증가!
			//구름이 이동하고 물이 증가한 좌표 cloudList
			for(Cloud c : cloudList) {
				int cnt=0;
				for(int i=0;i<4;i++) {
					int nx = c.x+copyWX[i];
					int ny = c.y+copyWY[i];
					if(nx<0||nx>=N||ny<0||ny>=N) continue;
					if(Map[nx][ny]>0) cnt++;
				}//대각선 방향 탐색 종료.
				Map[c.x][c.y] += cnt;
			}
			//바구니 물양 2이상인 모든 칸에 구름 생성, 물양 2 감소, cloudList에 있는 좌표들은 제외해야함!!!!
			LinkedList<Cloud> newCloudList = new LinkedList<>();
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(Map[r][c] >= 2) {
						Cloud tmp = new Cloud(r,c);
						if(cloudList.contains(tmp)) continue;
						Map[r][c] -= 2;
						newCloudList.add(new Cloud(r,c));
					}
				}
			}
			//이렇게 생겨난 구름 위치로 다음 명령 이어서 나간다.
			cloudList = newCloudList;
			System.out.printf("%d번째 이동 후 Map 상태 : ",m);
			System.out.println();
			print_debugging();
		}//명령어 반복문.
	}
	
	static void countWater() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(Map[i][j]>0){
					ans += Map[i][j];
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		init();//초기화.
		solve();//비바라기 연산 수행.
		countWater();//최종 물 양 계산해서 ans 업데이트.
		System.out.println(ans);
	}
	static void print_debugging() {
		System.out.println("======= print current Map for debugging ========");
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.printf("%3d ",Map[i][j]);
			}
			System.out.println();
		}
		System.out.println("=====================================");
		
//		System.out.println("명령어 갯수 : "+orderCnt);
//		for(int k=0;k<M;k++) {
//			System.out.printf("Orders[%d] : %d %d ",k,Orders[k].d,Orders[k].s);
//			System.out.println();
//		}
	}
}
