import java.io.*;
import java.util.*;
public class WolfAndSheep {
	static int R,C,ans;
	static char[][] Map;
	static boolean[][] visited,visitCheck;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Map = new char[R][C];
		visited = new boolean[R][C];
		sb = new StringBuilder();
		
		for(int i=0;i<R;i++) {
			Map[i] = br.readLine().toCharArray();
		}
		ans = 1;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(Map[i][j]=='S') {//(i,j)위치에서 BFS탐색 시작해서 인접 노드에 W가 있는지만 체크하면 됨.
					for(int d=0;d<4;d++) {
						int nx = i+dx[d],ny = j+dy[d];
						if(isOut(nx,ny)) continue;//방문체크도 해야하지 않을까?
						if(Map[nx][ny] == 'W') ans = 0;
					}
				}
			}
		}
		System.out.println(ans);
		if(ans!=0) printMap();//빈칸을 모두 D로 바꾸면 게임 끝.
	}
	static void printMap() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(Map[i][j]=='.') { Map[i][j] = 'D';}
				sb.append(Map[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>R-1 || y<0 || y>C-1;
	}
}

//		for(int i=0;i<R;i++) {
//			for(int j=0;j<C;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}