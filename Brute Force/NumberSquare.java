package ss;
import java.util.*;
import java.io.*;
public class NumberSquare {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		
		for(int i=0;i<n;i++) {
			map[i] = br.readLine().toCharArray();
		}
		int x = Math.min(n, m);
		int tmp = 0;
		int ans = 0;
		for(int len=x;len>=1;len--) {
			for(int i=0;i<i+x;i++) {
				for(int j=0;j<j+x;j++) {
					if(i+x>=n || j+x>=m) continue;
					if(map[i][j] == map[i][j+x] &&
						map[i][j] == map[i+x][j] &&
						map[i][j] == map[i+x][j+x]) {
						//System.out.println(x*x);
						tmp = (x+1)*(x+1);
						ans = Math.max(tmp, ans);
						//System.exit(0);
					}
				}
			}
		}
		System.out.println(ans);
	}

}

//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}