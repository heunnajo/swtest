package ss;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
//import java.util.StringBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class MicroIsolation_3rd {
	static int N,M,K,T,Map[][];
	static HashMap<Integer,LinkedList<Group>> dupMap;
	static class Group{//편의 메서드 구현해야함!
		int x,y,micro,dir,id;
		Group(int id,int x,int y,int micro,int dir){
			this.id = id;
			this.x = x;
			this.y = y;
			this.micro = micro;
			this.dir = dir;
		}
		void halfMicro(){//미생물 반감
			//this.micro /= 2;
			if(this.micro % 2 == 0) {
				this.micro = micro/2;
			} else {//홀수라 나누어떨어지지 않는 경우!
				this.micro = (int)Math.floor((double)(micro/2));//버림 연산처리를 한다고?! micro=5인 경우, micro는 2가 되야하는데 그냥 micro/2로 하면 2가 되지 않나?
			}
		}
		void reverseDir() {//방향 전환
			this.dir = (this.dir+2)%4;
		}
		void setPos(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	static Group[] Groups;
	static int[] dx = {-1,0,1,0};//상좌 하우 
	static int[] dy = {0,-1,0,1};
	
	static int countSum() {
		int sum = 0;
		for(int i=0;i<Groups.length;i++) {
			if(Groups[i] != null) {
				sum+=Groups[i].micro;
			}
		}
		return sum;
	}
//	static boolean isChemi(int x,int y) {
//		return x == 0 || x==N-1 || y==0 || y==N-1;
//	}
//	static boolean outRange(int x,int y) {
//		return x<0 || x>N-1 || y<0 || y>N-1;
//	}
	static void solve() {
		//M번 반복
		for(int m=0;m<M;m++) {
			Map = new int[N][N];
			//K개 군집을 이동!
			for(int id=0;id<K;id++) {
				if(Groups[id] == null) continue;
				Group cur = Groups[id];
				
				int nx = cur.x+dx[cur.dir];
				int ny = cur.y+dy[cur.dir];
				
				//if(outRange(nx,ny)) continue;//다음 이동칸이 범위 밖이면 처리를 안하는 게 맞나?
				//1.약품처리 X
				//if(!isChemi(nx,ny)) {
				if(1<=nx && nx<=N-2 && 1<=ny && ny<=N-2) {
					int nid = Map[nx][ny];//현재 (nx, ny)에 있는 군집의 id.
					//1-1.빈칸인 경우
					if(nid==0) {
						cur.setPos(nx, ny);
						Map[nx][ny] = cur.id;
					} else {//1-2.이미 군집이 차지하고 있는 경우
						//1-2-1.이미 다른 군집들이 있는 경우:컬렉션에 추가!
						int key = cur.x*N + cur.y;
						Group existing = Groups[Map[nx][ny]];
						
						if(dupMap.containsKey(key)) {
							LinkedList<Group> list = dupMap.get(key);
							list.add(cur);
							cur.setPos(nx, ny);
							//Map[nx][ny] = cur.id;//?! are you sure?Nuh :일괄적으로 처리할 것이기 때문에 지금 결정하기에는 부적절.
						} else {//1-2-2.자신이 제2의 군집인 경우:컬렉션 생성 후 Map에 새로 추가!
							LinkedList<Group> list = new LinkedList<>();
							list.add(existing);
							list.add(cur);
							dupMap.put(key, list);
							cur.setPos(nx, ny);
						}
						
					}
					
					
				 //else if(isChemi(nx,ny)){//2.약품처리 O
				} else {//2.약품처리 O
					if(cur.micro == 1) {
						Groups[cur.id] = null;
					} else {
						cur.halfMicro();
						cur.reverseDir();
						cur.setPos(nx, ny);
						Map[nx][ny] = cur.id;
					}
				}
				//한 칸에 2개 이상의 군집들 Map 컬렉션 처리
				for(LinkedList<Group> list:dupMap.values()) {
					//현재 컬렉션에서 최댓값 미생물 군집 도출!
					int maxMicro = 0;
					int maxId = -1;
					int sum = 0;//최댓값 군집에 미생물 양 갱신해줄 값.
					
					for(Group g:list) {
						sum += g.micro;
						if(maxMicro<g.micro) {//최댓값 군집 찾는다.
							maxMicro = g.micro;//?
							maxId = g.id;
						}
					}
//					Group maxGroup = Groups[maxId];
//					maxGroup.micro = sum;
					Groups[maxId].micro = sum;
					
					for(Group g:list) {
						if(g.id == maxId) continue;
						else {
							Groups[g.id] = null;//나머지는 삭제처리.
						}
					}
					
				}//HashMap for-each
				dupMap.clear();//턴을 돌고 난 후에는 Map 초기화!
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			//객체 초기화
			Groups = new Group[K];//K개 군집 저장.
			dupMap = new HashMap<>();
			
			//K개 군집들 정보 입력받아야함!
			for(int id=0;id<K;id++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())-1;
				int y = Integer.parseInt(st.nextToken())-1;
				int micro = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				
				//방향전환편의를 위해 입력값 조정!
				if(dir==2) dir=1;
				else if(dir==1) dir=2;
//				System.out.println("id: "+id);
//				System.out.println("x: "+x);
//				System.out.println("y: "+y);
//				System.out.println("micro: "+micro);
//				System.out.println("dir: "+dir);
				Groups[id] = new Group(id,x,y,micro,dir);
			}
			
			solve();
			
			sb.append("#"+(i+1)+countSum()+"\n");
		}
		System.out.println(sb);
	}

}
