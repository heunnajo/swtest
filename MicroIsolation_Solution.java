import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MicroIsolation_Solution {
	
	static class Node {
		int id, r, c, cnt, d;
		
		Node(int id, int r, int c, int cnt, int d){
			this.id = id;
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.d = d;
		}
		
		public void setPos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void reverse() {
			this.d = (d + 2) % 4;
		}
		
		public void half() {
			if(this.cnt % 2 == 0) {
				this.cnt = cnt / 2;
			} else {
				this.cnt = (int)Math.floor((double)(cnt / 2));
			}
		}
	}
	
	static HashMap<Integer, ArrayList<Node>> dupMap;
	static Node[] nodes;
	static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static int N, M, K, T, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			nodes = new Node[K + 1];
			dupMap = new HashMap<>();
			
			ans = 0;
			
			for(int k = 1 ; k <= K ; ++k) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				// 상 하 좌 우 
				// 1 2 3 4 (입력 데이터) 
				// 0 2 1 3 (변형 데이터) 
				if(d == 1) d = 0;
				else if(d == 3) d = 1;
				else if(d == 4) d = 3;
				
				nodes[k] = new Node(k, r, c, cnt, d);
			}
			
			// 시뮬레이션 시작
			for(int i = 0 ; i < M ; ++i) {
				int[][] map = new int[N][N];
				
				// 전체 노드 순차 진행
				for(int id = 1 ; id <= K ; ++id) {
					if(nodes[id] == null) continue;//왜 있는거지?
					
					Node cur = nodes[id];
					int nr = cur.r + dir[cur.d][0];
					int nc = cur.c + dir[cur.d][1];
					
					// 약품에 닿지 않았을 때
					if(nr >= 1 && nr <= N - 2 && nc >= 1 && nc <= N - 2) {
						int nid = map[nr][nc];
						
						// 다음 이동 지역에 아무것도 없을 때 
						if(nid == 0) {
							cur.setPos(nr, nc);
							map[nr][nc] = id;
						} else {
							// 다음 이동 지역에 이미 군집이 있을 때 
							Node other = nodes[nid];//이미 (nx,ny)에 nid가 0이 아니라면 이미 군집이 있다는 건데
							int key = nr * N + nc;//왜 추가적으로 Map을 조회하는 거지?
							
							if(dupMap.containsKey(key)) {//이번에 새로 이동한 칸들
								ArrayList<Node> list = dupMap.get(key);
								cur.setPos(nr, nc);
								list.add(cur);
							} else {
								ArrayList<Node> list = new ArrayList<>();
								cur.setPos(nr, nc);
								list.add(other);//리스트에 기존 군집 추가
								list.add(cur);//이번에 새로 이동하려는 군집 추가
								dupMap.put(key, list);
							}
						}
					} else {
						// 약품에 닿았을 때 
						if(cur.cnt == 1) {
							nodes[id] = null;//군집 컬렉션 갱신
						} else {
							cur.half();//미생물 반감
							cur.reverse();//방향 전환
							cur.setPos(nr, nc);//군집 객체 위치 갱신
							map[nr][nc] = id;//2차원 배열[nr][nc] 갱신
						}
					}
				}
				
				// 같은 칸에 모인 군집들 처리하기 
				for(ArrayList<Node> list : dupMap.values()) {
					int total = 0;
					int max = 0;
					int maxId = -1;
					
					for(Node node : list) {
						total += node.cnt;//미생물 합산해서 미리 저장.
						
						if(node.cnt > max) {
							max = node.cnt;
							maxId = node.id;
						}
					}
					
					nodes[maxId].cnt = total;//군집 배열에서 해당 군집id인덱스로 조회해서 갱신.
					
					for(Node node : list) {
						if(node.id == maxId) continue;
						nodes[node.id] = null;//최댓값 미생물 갖는 군집 제외한 나머지 군집들 null로 갱신
					}
				}
				
				dupMap.clear();//1턴 돌고 난 후, 초기화ㅗ
			}
			
			for(int i = 1 ; i <= K ; ++i) {
				if(nodes[i] == null) continue;
				ans += nodes[i].cnt;
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}
}