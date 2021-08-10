import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class Shark_FireBall {
	static int TotalM,N,M,K;
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	static class Fireball{
		int x,y,m,s,d;
		Fireball(int m,int s,int d){
			this.m = m;
			this.s = s;
			this.d = d;
		}
		Fireball(int x,int y,int m,int s,int d){
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	static List<Fireball>[][] Flist;
	static void solve() {
		while(K-- >0){
			ArrayList<Fireball> tempList = new ArrayList<>();
			//1.방향,속도만큼 이동.
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(Flist[i][j].size() != 0) {
						//한칸에 1개밖에 없을 경우!X
						//1. 이동 : Map[i][j]에 있는 파이어볼들을 속도와 방햐에 따라 이동시킨다!
						for(int k=0;k<Flist[i][j].size();k++) {
							Fireball cur = Flist[i][j].get(k);//Map[i][j] k번째 노드 순회!
							int nx = i+dx[cur.d]*cur.s %N;
							int ny = j+dy[cur.d]*cur.s %N;
							if(nx<0) {//밑에서부터 나머지만큼 이동ㅇ.
								nx = N - (Math.abs(nx)%N);
							} else if(nx >= N){
								nx = nx % N;
							}
							if(ny<0) {//밑에서부터 나머지만큼 이동ㅇ.
								ny = N - (Math.abs(ny)%N);
							} else if(ny >= N){
								ny = ny % N;
							}
							tempList.add(new Fireball(nx,ny,cur.m,cur.s,cur.d));
							Flist[i][j].remove(k--);//k번째 파볼 이동 시킨 후!! 삭제!
						}
					}	
				}
			}
			//2. 본격 이동!
			for(Fireball fb:tempList) {
				//Flist[fb.x][fb.y].add(new Fireball(fb.m,fb.s,fb.d));
				Flist[fb.x][fb.y].add(fb);
			}
			
			//3. 한 칸에 두개이상있는 경우!
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(Flist[i][j].size()>1) {
						//합치기
						int newM = 0;int newS = 0;
						int odd = 0;int even = 0;
						for(int k=0;k<Flist[i][j].size();k++) {
							Fireball cur = Flist[i][j].get(k);
							newM += cur.m; newS += cur.s;
							if(cur.d % 2 == 0) {even++;}
							else odd++;
						}
						
						TotalM -= newM;
						//분화.
						newM /= 5; newS /= Flist[i][j].size();
						
						Flist[i][j].clear();
						if(newM != 0) {
							TotalM += newM*4;
							if(even == 0 || odd == 0) {
								Flist[i][j].add(new Fireball(newM,newS,0));
								Flist[i][j].add(new Fireball(newM,newS,2));
								Flist[i][j].add(new Fireball(newM,newS,4));
								Flist[i][j].add(new Fireball(newM,newS,6));
								
							} else {
								Flist[i][j].add(new Fireball(newM,newS,1));
								Flist[i][j].add(new Fireball(newM,newS,3));
								Flist[i][j].add(new Fireball(newM,newS,5));
								Flist[i][j].add(new Fireball(newM,newS,7));
							}
						}
						
					}
				}
			}
		}//이동 명령 반복문.
			
	}//solve()
		
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);//지도 크기.
		M = Integer.parseInt(input[1]);//파이어볼 갯수.
		K = Integer.parseInt(input[2]);//이동 반복할 횟수.
		Flist = new ArrayList[N][N];
		
		//객체 초기화
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				Flist[i][j] = new ArrayList<Fireball>();
			}
		}
		TotalM = 0;
		for(int i=0;i<M;i++) {//파이어볼 정보 입력받는다!
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0])-1;
			int y = Integer.parseInt(input[1])-1;
			int m = Integer.parseInt(input[2]);
			int s = Integer.parseInt(input[3]);
			int d = Integer.parseInt(input[4]);
			TotalM += m;
			Flist[x][y].add(new Fireball(m,s,d));
			//System.out.println("TotalM : "+TotalM);
		}
		//System.out.println("TotalM : "+TotalM);
		solve();
		System.out.println(TotalM);//현재 맵에 남은 모든 파이어볼 질량합 = 2중 for문으로 각칸의 리스트 크기를 다 더하면 됨!
		br.close();
	}

}