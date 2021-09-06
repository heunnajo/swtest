package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class SlopeWay {
	static int[][] Map;
	static int N,L,cnt;
	static boolean go(int x,int y,int dir) {
		//경사로를 놓을 수 없는 경우 false리턴함으로써 필터링하고 조건 만족하는 좌표는 true를 리턴.
		boolean[] visited = new boolean[N];
		int[] height = new int[N];
		
		for(int i=0;i<N;i++) {//dir=0이면 행에 대해 수행!행값은 x로 그대로, 열값이 바뀐다!
			height[i] = (dir == 0)? Map[x][i] : Map[i][y];
			//height[i] = (dir == 0)? Map[x][y+i] : Map[x+i][y];
		}
		//i,i+1번째 칸 계속 비교하면서 경사로 놓을 수 있는지 검사하고, 가능하다면 놓는다!
		for(int i=0;i<N-1;i++) {
			if(height[i] == height[i+1]) continue;
			//if(Math.abs(height[i]-height[i+1]) >1) continue;
			//1이상 차이가난다면 경사로를 놓지 못한다는 것이고, 이것은 높이가같지 않음에도 불구하고 경사로를 놓지 못하는 것이기 때문에 지나갈 수 있는 길이 아님을 의미한다!!!
			//따라서 false를 리턴하고 종료해야한다!더 반복할 필요 없음!
			if(Math.abs(height[i]-height[i+1]) >1) return false;
			
			//경사로 놓을 수 있는 경우 : 차이가 1나는 경우!
			//1.내리막
			if(height[i] == height[i+1]+1) {
				for(int j=i+1;j<=i+L;j++) {
					//범위체크, 중복체크, 동일한 높이인지 체크해나가야함:경사로 놓을 수 없다면 현재 반복문 탈출!
					if(j>N-1 || visited[j] || height[j] != height[i+1]) return false;//break;
					//그렇지 않다면 해당 위치 j에 경사로 놓는다!
					visited[j] = true;
				}
			}
			//2.오르막
			else if(height[i]+1 == height[i+1]) {
				for(int j=i;j>=i+1-L;j--) {
					if(j<0 || visited[j] || height[j] != height[i]) return false;
					//그렇지 않다면 해당 위치 j에 경사로 놓는다!
					visited[j] = true;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		L = Integer.parseInt(input[1]);
		
		Map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		//N개의 행, 열 각각 2N번씩 수행한다!
		cnt = 0;
		for(int i=0;i<N;i++) {
			if(go(i,0,0)) {cnt++;}//i번째 행에 대해 수행.
			if(go(0,i,1)) {cnt++;}//j번째 열에 대해 수행.
		}
		System.out.println(cnt);
		br.close();
	}

}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}