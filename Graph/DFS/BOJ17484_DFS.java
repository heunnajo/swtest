package swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
//진우의 달여행(Small)
public class BOJ17484_DFS {
	static int n,m;
	static int arr[][];
	static int min = Integer.MAX_VALUE;
	static int[] ydir = {-1,0,1};
	static int[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		arr = new int[n][m];
		
		for(int i=0;i<n;i++) {
			String[] temp2 = br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j] = Integer.parseInt(temp2[j]);
			}
		}
		
		for(int i=0;i<m;i++) {
			visited = new int[n];
			visited[0] = i;
			dfs(1,i,-1);
		}
		
		bw.write("" + min);
		bw.flush();
		bw.close();
		br.close();

	}
	
	public static void dfs(int depth,int y,int dir) {
		//1.종료
		if(depth == n) {
			int sum = arr[0][visited[0]];
			for(int i=1;i<n;i++) {
				sum += arr[i][visited[i]];
			}
			
			min = min > sum ? sum : min;
			return;
		}
		//2.탐색
		for(int i=0;i<3;i++) {
			int dy = y+ydir[i];
			if(isValidPosition(dy) && dir != i) {
				visited[depth] = dy;//현재의 방향을 기록
				dfs(depth+1,dy,i);
			}
		}
	}
	public static boolean isValidPosition(int y) {
		if(y<0 || y >= m) return false;
		
		return true;
	}
}
