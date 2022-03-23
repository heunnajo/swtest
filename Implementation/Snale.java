//BOJ #1913 달팽이
import java.io.*;
import java.util.*;

public class Snale {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());//크기
		int x = Integer.parseInt(br.readLine());//찾고자 하는 좌표
		
		//변수 초기화
		int[][] arr = new int[n+2][n+2];
		int i = (n/2)+1, j = (n/2)+1, num = 1, it = 1, d = 0;
		arr[i][j] = num;
		int row = i, col = j;//정답이 되는 좌표
		
		//방향 델타어레이 
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		while(it<=n) {
			for(int cnt = 0;cnt<2;cnt++) {
				for(int k=0;k<it;k++) {
					i += dx[d%4]; j += dy[d%4];
					arr[i][j] = ++num;
					if(num == x) {
						row = i; col = j;
					}
				}
				d++;
			}
			it++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int ii=1;ii<=n;ii++) {
			for(int jj=1;jj<=n;jj++) {
				//System.out.print(arr[ii][jj]+" ");
				sb.append(arr[ii][jj]+" ");
			}
//			System.out.println();
			sb.append("\n");
		}
//		System.out.println(row+" "+col);
		sb.append(row+" "+col);
		System.out.print(sb);
	}

}