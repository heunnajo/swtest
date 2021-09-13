package ss;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class MicroIsolation_2nd {
	static int N,M,K,T,Map[][];
	static HashMap<Integer,LinkedList<Group>> dupMap;
	static Group[] Groups;
	static class Group{
		int id,x,y,micro,dir;
		Group(int x,int y,int micro,int dir){
			this.x = x;
			this.y = y;
			this.micro = micro;
			this.dir = dir;
		}
		Group(int id,int x,int y,int micro,int dir){
			this.id = id;
			this.x = x;
			this.y = y;
			this.micro = micro;
			this.dir = dir;
		}
	}
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Groups = new Group[K];
		
		//K개의 군집 정보 저장해야함.
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());//x y micro dir
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int micro = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken())-1;
			
			if(dir == 2) dir = 1;
			else if(dir == 1) dir = 2;
			
			Groups[i] = new Group(i,x,y,micro,dir);
		}
	}
	static void solve() {//N,M,K,Groups로 본격적으로 연산수행하면 됨!
		for(int i=0;i<M;i++) {//현재 TC에서 M번만큼 반복한다!
			Map = new int[N][N];//초기화.
			dupMap = new HashMap<>();
			for(int id=0;id<K;id++) {//K개의 바이러스.
				if(Groups[id] == null) continue;//M번반복하다가 삭제된 군집이 있을수도 있기 때문! 
				Group cur = Groups[id];
				//해당 군집의 방향대로ㅗ 이동한다!
				int nx = cur.x+dx[cur.dir];
				int ny = cur.y+dy[cur.dir];
				if(outRange(nx,ny)) continue;
				//다음 좌표가 범위를 벗어난다면?=>논리적으로 범위벗어날일 없음.0,n-1 번째 열일 때방향전환하고 더 진행하지 않기 때문에.
				//배열 초과 에러=> 약품 처리 도달한 경우 제대로 처리하지 않았다는 뜻.
				int nid = Map[nx][ny];
				//1.이동한 칸이 빈칸인 경우
				if(nid == 0) {
					cur.x = nx; cur.y = ny;
					Map[cur.x][cur.y] = 0;
					Map[nx][ny] = id;//현재 이동하는 군집의 인덱스를 저~장!
				}
				//2.빈칸이 아닌 경우
				//2-1.약품처리 행/열인 경우
				if(isChemi(nx,ny)) {
					//미생물 수 반감, 삭제처리.
					if(cur.micro>0) {cur.micro /= 2;}
					if(cur.micro == 0) {Groups[id] = null;}
					
					cur.dir = (cur.dir+2)%4;//방향 전환
					//위치 갱신:갱신된 방향대로 값 갱신~!
					nx = cur.x+dx[cur.dir];
					ny = cur.y+dy[cur.dir];
					Map[cur.x][cur.y] = 0;
					Map[nx][ny] = id;
				} else {//2.약품에 닿지 않았을 때.
					//2-2.한칸에 2개 이상 군집이 도착한 경우
					if(nid != 0) {//이미 다른 군집이 있는 경우!
						//자신이 처음 도착한 제2의 군집인 경우
						Map[cur.x][cur.y] = 0;//일단 공동로직으로 현재 Map 위치에서 0으로 처리.
						int key = cur.x*N + cur.y;//현재 위치 key가 존재하는지 확인!
						if(dupMap.containsKey(key)) {//현재 위치 key가 존재하는지 확인!
							LinkedList<Group> list = new LinkedList<>();
							list.add(cur);
							dupMap.put(key, list);
						} else {
							LinkedList<Group> list = dupMap.get(key);
							list.add(cur);
						}
					}
				}
			}//K개 바이러스 모두 이동처리 다 한 후
			//한 칸에 2개 이상 군집들 처리:Map key 각각 조회.
			//각 key 위치에서 리스트 순회하면서 미생물 수 비교,최댓값 갖는 군집 도출!
			for(Integer key:dupMap.keySet()) {
				//현재 위치 key에서 최댓값 군집 도출!
				int max = 0;int maxId=-1;//이정도?
				Group maxG;
				int microSum = 0;
				LinkedList<Group> list = dupMap.get(key);
				for(Group g:list) {
					microSum += g.micro;
					if(max<g.micro) {
						max = g.micro;
						maxId = g.id;
						maxG = g;
						g.micro = max;
						g.x = key/N;//새로운 위치.
						g.y = key%N;
						Map[g.x][g.y] = g.id;
					}
					
				}
				
			}
			//K개 군집 이동&동일한 셀 군집 처리 끝나고 나면 초기화
			dupMap.clear();
		}
	}
	static boolean outRange(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	static boolean isChemi(int x,int y) {
		return x == 0 || x == N-1 || y == 0 || y == N-1;
	}
	
	static int countSum() {
		int sum = 0;
		for(int i=0;i<Groups.length;i++) {
			if(Groups[i] != null) {
				sum += Groups[i].micro;
			}
		}
		return sum;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int t=0;t<T;t++) {
			init();
			solve();
			sb.append("#"+(t+1)+" "+countSum()+"\n");
		}
		System.out.println(sb);
	}

}
