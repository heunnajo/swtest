//지뢰 찾기
import java.util.*;
import java.io.*;
public class Q4396 {
	static int n;
	static char[][] bombInfo;
	static char[][] mapInfo;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		bombInfo = new char[n][n];
		mapInfo = new char[n][n];
		for(int i=0;i<n;i++) {
			bombInfo[i] = br.readLine().toCharArray();
		}
		for(int i=0;i<n;i++) {
			mapInfo[i] = br.readLine().toCharArray();
		}
		ArrayList<int[]> bombPos = new ArrayList<>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(bombInfo[i][j] == '*') bombPos.add(new int[] {i,j});
			}
		}

		int[][] tmpAns = new int[n][n];
		char[][] ans = new char[n][n];
		
		for(int i=0;i<n;i++) {
			Arrays.fill(tmpAns[i], -2);
		}
		boolean flag = false;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(mapInfo[i][j] == '.') continue;
				//열린칸(i,j)이 지뢰인지 확인
				if(bombInfo[i][j] == '*') {
					tmpAns[i][j] = -1;
					flag = true;
					continue;
				}
				//열린칸(i,j)의 인접 위치 탐색 
				int cnt = 0;
				for(int d=0;d<8;d++) {
					int nx = i+dx[d]; int ny = j+dy[d];
					if(isOut(nx,ny)) continue;
					
					if(bombInfo[nx][ny] == '*') cnt++;
				}
				tmpAns[i][j] = cnt;
			}
		}

		//tmpAns => ans (char타입) 으로 치환
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(tmpAns[i][j] >= 0) ans[i][j] = (char)(tmpAns[i][j]+'0');
				else if(tmpAns[i][j] == -1) ans[i][j] = '*';
				else ans[i][j] = '.';
			}
		}
		if(flag) {
			for(int[] p:bombPos) {
				ans[p[0]][p[1]] = '*';
			}
		}
		//ans 확인
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(ans[i][j]);
			}
			System.out.println();
		}

	}

	static boolean isOut(int x,int y) {
		return x<0 || x>=n || y<0 || y>=n;
	}
}