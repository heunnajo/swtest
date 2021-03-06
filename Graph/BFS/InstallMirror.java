import java.util.*;
import java.io.*;
public class InstallMirror {
	static String[] Map;
	static int N;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		Map = new String[N];
		
		for(int i=0;i<N;i++) {
			Map[i] = br.readLine();
		}
		//1.!와 #를 만나면 리스트에 저장한다!
		int start = -1, end = -1;//시작점, 끝점 저장 위해!
		int[][] nodeNum = new int[N][N];//노드번호를 저장! = 현재 리스트 크기-1
		ArrayList<Pair> list = new ArrayList<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(Map[i].charAt(j) == '#') {
					if(start == -1) {//시작점으로 저장!
						start = list.size();//시작점을 의미
					} else {
						end = list.size();//끝점을 의미
					}
					//start = list.size(); end = list.size();
					list.add(new Pair(i,j));
					nodeNum[i][j] = list.size()-1;//#는 2번 만날 거기 때문에 두번 들어간다!
				} else if(Map[i].charAt(j) == '!') {
					list.add(new Pair(i,j));
					nodeNum[i][j] = list.size()-1;
				}
			}
		}
		//2.인접 행렬 생성!:2차원 배열에서 상하좌우 방향으로 유효범위내에서 쭉 진행해서
		//인접해있는 위치에 !가 있다면 true로 마킹.
		int size = list.size();
		boolean[][] a = new boolean[size][size];//5*5 
		for(int i=0;i<size;i++) {//거울이 최대 10개라면 size는 최대 12개
			//i번 정점위치에서 2차원 배열 상에서 탐색
			for(int d=0;d<4;d++) {
				int x = list.get(i).x+dx[d];
				int y = list.get(i).y+dy[d];
				while(x>=0 && x<N && y>=0 && y<N) {
					char c = Map[x].charAt(y);//이동하는 위치 배열값을 확인
					if(c == '*') break;//*이면 벽이니까 다음 방향으로 
					//! 또는 #라면 이동 가능, 인접 정보 저장해야함!
					if(c == '!' || c == '#') {//.인 경우 제외시켜야함!
						a[i][nodeNum[x][y]] = true;
					}
					x+=dx[d];y+=dy[d];//x,y위치 이동을 안시켜버려서 무한루프에 갖혀버릶..ㅜ 
				}
			}
		}
		//3.2에서 완성한 인접행렬 이용해서 그래프 BFS한다!
		//start에서 시작, end에 도달하는 거리 구한다!
		Queue<Integer> q = new LinkedList<>();
		int[] dist = new int[size]; Arrays.fill(dist,-1);
		dist[start] = 0;
		q.add(start);//큐에 넣지도 않았네^^
		while(!q.isEmpty()) {
			int cur = q.remove();
			for(int i=0;i<size;i++) {
				if(a[cur][i] && dist[i] == -1) {
					dist[i] = dist[cur]+1;
					q.add(i);
				}
			}
		}
		System.out.println(dist[end]-1);
	}

}