package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Shark_FireBall {
	static int tot,N,M,K;
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
	static List<Fireball>[][] Map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);//지도 크기.
		M = Integer.parseInt(input[1]);//파이어볼 갯수.
		K = Integer.parseInt(input[2]);//이동 반복할 횟수.
		Map = new ArrayList[N][N];
		
		//객체 초기화
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				Map[i][j] = new ArrayList<Fireball>();
			}
		}
		tot = 0;
		for(int i=0;i<M;i++) {//파이어볼 정보 입력받는다!
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0])-1;
			int y = Integer.parseInt(input[1])-1;
			int m = Integer.parseInt(input[2]);
			int s = Integer.parseInt(input[3]);
			int d = Integer.parseInt(input[4]);
			
			tot+=m;//초기 질량 합 구해놓는다!
			Map[x][y].add(new Fireball(m,s,d));
		}
		int cmd = 0;
		while(cmd < K) {
			move();
			cmd++;
		}
		System.out.println(tot);//현재 맵에 남은 모든 파이어볼 질량합 = 2중 for문으로 각칸의 리스트 크기를 다 더하면 됨!
		br.close();
	}
	public static void move() {
		ArrayList<Fireball> tempList = new ArrayList<>();
		
		//이동
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(Map[i][j].size() != 0) {
					for(int k=0;k<Map[i][j].size();k++) {
						Fireball fb = Map[i][j].get(k);
						
						int nx = i + dx[fb.d]*fb.s % N;
						int ny = j + dy[fb.d]*fb.s % N;
						
						if(nx > N) {
							nx = nx % N;
						} else if(nx < 1) {
							nx = N-(Math.abs(nx) % N);
						}
						
						if(ny > N) {
							ny = ny % N;
						} else if(ny < 1) {
							ny = N - (Math.abs(ny) % N);
						}
						
						Fireball newFb = new Fireball(nx,ny,fb.m,fb.s,fb.d);
						tempList.add(newFb);
						Map[i][j].remove(k--);//k--?
					}
				}
			}
		}
		//실제 이동
		for(Fireball fb : tempList) {
			Map[fb.x][fb.y].add(fb);//임시로 만든 List의 x,y좌 표에 fb 객체를 넣는다!
		}
		
		//2개이상인지 확인
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(Map[i][j].size()>1) {
					//파이어볼 하나로 합침
					int newM = 0;
					int newS = 0;
					int odd = 0;int even = 0;
					for(Fireball fb : Map[i][j]) {
						newM += fb.m;
						newS += fb.s;
						
						if(fb.d%2 == 0) even++;
						else odd++;
					}
					
					tot -= newM;
					
					newM /= 5;
					newS /= Map[i][j].size();
					
					Map[i][j].clear();
					if(newM != 0) {
						tot += newM *4;
						
						if(even == 0 || odd == 0) {//모두 짝수이거나 모두 홀수.
							Map[i][j].add(new Fireball(newM,newS,0));
							Map[i][j].add(new Fireball(newM,newS,2));
							Map[i][j].add(new Fireball(newM,newS,4));
							Map[i][j].add(new Fireball(newM,newS,6));
						}
						else {
							Map[i][j].add(new Fireball(newM,newS,1));
							Map[i][j].add(new Fireball(newM,newS,3));
							Map[i][j].add(new Fireball(newM,newS,5));
							Map[i][j].add(new Fireball(newM,newS,7));
						}
					}
					
					
				}
			}
		}
	}

}
