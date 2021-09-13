import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class MicroIsolation_sweaFormat_Solution {
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
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int t = 1; t <= T; t++)
		{
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
					if(nodes[id] == null) continue;
					
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
							Node other = nodes[nid];
							int key = nr * N + nc;
							
							if(dupMap.containsKey(key)) {
								ArrayList<Node> list = dupMap.get(key);
								cur.setPos(nr, nc);
								list.add(cur);
							} else {
								ArrayList<Node> list = new ArrayList<>();
								cur.setPos(nr, nc);
								list.add(other);
								list.add(cur);
								dupMap.put(key, list);
							}
						}
					} else {
						// 약품에 닿았을 때 
						if(cur.cnt == 1) {
							nodes[id] = null;
						} else {
							cur.half();
							cur.reverse();
							cur.setPos(nr, nc);
							map[nr][nc] = id;
						}
					}
				}
				
				// 같은 칸에 모인 군집들 처리하기 
				for(ArrayList<Node> list : dupMap.values()) {
					int total = 0;
					int max = 0;
					int maxId = -1;
					
					for(Node node : list) {
						total += node.cnt;
						
						if(node.cnt > max) {
							max = node.cnt;
							maxId = node.id;
						}
					}
					
					nodes[maxId].cnt = total;
					
					for(Node node : list) {
						if(node.id == maxId) continue;
						nodes[node.id] = null;
					}
				}
				
				dupMap.clear();
			}
            for(int i = 1 ; i <= K ; ++i) {
				if(nodes[i] == null) continue;
				ans += nodes[i].cnt;
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}
}