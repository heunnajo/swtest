package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class TreeInvestment {
	static int ans,N,M,K;
	static int[][] S2D2;//입력받는 배열값
	static int[][] Nutrition;//초기에 5로 셋팅
	static LinkedList<Integer>[][] Trees;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		S2D2 = new int[N][N];
		Nutrition = new int[N][N];
		Trees = new LinkedList[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				Trees[i][j] = new LinkedList<Integer>();
			}
		}
		for(int i=0;i<N;i++) 
			for(int j=0;j<N;j++)Nutrition[i][j] = 5;
		//S2D2 양분 추가값 입력 저장
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				S2D2[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//M개의 나무 정보 저장!
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			Trees[x][y].add(age);
		}
		ans = 0;
		fourSeason();//4계절 처리
		ans = countAlive();//K년 후 남은 나무 갯수 리턴
		System.out.println(ans);
	}
	static void fourSeason() {
		//봄,여름
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int size = Trees[i][j].size(); int diedAgeSum = 0;
				for(int k=0;k<size;k++) {
					int cur = Trees[i][j].get(k);
					if(Nutrition[i][j] - cur>=0) {//봄
						Nutrition[i][j] -= cur;//나이만큼 양분 감소
						Trees[i][j].set(k, cur+1);//나이 1증가
					} else {//k번째에서 양분부족하면 뒤의 나무들도 다 부족한 것이기 때문.k~끝까지 else문을 처리해야함.
						diedAgeSum += Trees[i][j].remove(k);
						k--;
					}
				}
				//(i,j)에 있는 나무 봄,여름 처리하고 난 후! 죽은 나무들 양분으로 합산.
				//죽는 나무가 없으면 Nutri[i][j]에 더해지는 양분값은 0이 되어 더해지는 양분값 없어진다.
				Nutrition[i][j] += diedAgeSum/2;
			}
		}
		//가을,겨울
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				//가을 : 나이 5배수 이상인 것들 인접 8개 위치에 나이 1인 나무 생성 
				int size = Trees[i][j].size();
				for(int k=0;k<size;k++) {
					int cur = Trees[i][j].get(k);
					if(cur%5 == 0) {
						for(int dir = 0;dir<8;dir++) {
							int nx = i+dx[dir]; int ny = j+dy[dir];
							if(isOut(nx,ny)) continue;
							Trees[nx][ny].addFirst(1);
						}
					}
				}
				//겨울 
				Nutrition[i][j] += S2D2[i][j];
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	static int countAlive() {
		int sum = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int size = Trees[i][j].size();
				if(size>0) sum+=size;
			}
		}
		return sum;
	}
}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		if(Trees[i][j].size()==0) System.out.print(0+" ");
//		int size = Trees[i][j].size();
//		for(int k=0;k<size;k++) {
//			int cur = Trees[i][j].get(k);
//			System.out.print(cur+" ");
//		}
//	}
//	System.out.println();
//}
