package ss;
import java.util.*;
import java.io.*;
public class RobotVaccumCleaner {
	static final int INF = 987654321;
	static int index,ans;//더러운 칸의 총 갯수
	static int[] d;//i번 더러운 칸의 위치 정보 저장!
	static int[][] dist;//dist[i][j] = i번 더럽->j번 더럽 이동 횟수
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int w, h;
		char[][] Map;
		while(true) {
			st = new StringTokenizer(br.readLine());
			//w:가로(열) h:세로(행) 
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w==0 && h==0) break;
			//변수 초기화
			Map = new char[h][w];
			index = 0;
			d = new int[10];
			visited = new boolean[10];
			dist = new int[10][10];
			
			for(int i=0;i<h;i++) {
				Map[i] = br.readLine().toCharArray();
			}
			//0.더럽 칸 위치 저장!
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					if(Map[i][j]=='*') d[index++] = i*w+j;
				}
			}
			//1.더럽-더럽 거리 계산, 저장
			for(int i=0;i<index;i++) {
				int sx = d[i]/w, sy = d[i]%w;
				for(int j=i;j<index;j++) {
					if(i==j) continue;
					int destX = d[j]/w, destY = d[j]%w;
					dist[i][j] = Math.abs(sx-destX)+Math.abs(sy-destY);
				}
			}
			//2.더럽-더럽 방문 순서 정하기
			int[] order = new int[10];
			ans = INF;
			go(0, order);//순서 생성해서 ans에 최솟값 도출,저장
			System.out.println(ans);
		}
		
	}
	static void go(int idx,int[] order) {
		if(idx == index) {
			int moveCnt = countMove(order);
			if(ans > moveCnt) ans = moveCnt;
		}
		for(int i=0;i<index;i++) {
			if(!visited[i]) {
				visited[i] = true;order[index] = i;
				go(index+1,order);
				visited[i] = false; order[index] = -1;
			}
		}
	}
	static int countMove(int[] order) {
		int sum = 0;
		for(int i=0;i<index-1;i++) {
			sum+=dist[order[i]][order[i+1]];
		}
		return sum;
	}
//			System.out.println();
//			for(int i=0;i<h;i++) {
//				for(int j=0;j<w;j++) {
//					System.out.print(Map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
}
