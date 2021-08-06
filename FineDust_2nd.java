package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class FineDust_2nd {
	static int R,C,T,Room[][],CleanRow;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int solve() {
		while(T-- >0) {
			//1.미세먼지 확산!
			int[][] tmp = new int[R][C];//미세먼지가 1초동안 확산될 때! 확산양을 기록하기 때문에!
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(Room[i][j]>= 5) {
						int spread_amount = Room[i][j]/5;
						for(int d=0;d<4;d++) {
							int nx = i+dx[d];
							int ny = j+dy[d];
							if(nx<0||nx>R-1||ny<0||ny>C-1||Room[nx][ny]==-1) continue;
							tmp[nx][ny] += spread_amount;
							tmp[i][j] -= spread_amount;
						}
					}
				}//C
			}//R
			//2.미세먼지 이동!
			//그전에 확산된양을 원래 미세먼지양에 가감연산해준다!!!
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					Room[i][j] += tmp[i][j];
				}
			}
			
			//본격 미세먼지 이동.
			//2-1.윗부분.
			for(int i = CleanRow-1; i>=1;i--){
			    Room[i][0] = Room[i-1][0];
			}
			for(int j=0;j<=C-2;j++){
			    Room[0][j] = Room[0][j+1];
			}
			for(int i=0;i<=CleanRow-1;i++){
			    Room[i][C-1] = Room[i+1][C-1];
			}
			for(int j=C-1;j>=2;j--){
			    Room[CleanRow][j] = Room[CleanRow][j-1];
			}
			Room[CleanRow][1] = 0;
			//2-2.아랫부분.
			for(int i=CleanRow+2;i<=(R-2);i++) {//위.
				Room[i][0] = Room[i+1][0];
			}
			for(int j=0;j<=C-2;j++) {//왼.
				Room[R-1][j] = Room[R-1][j+1];
			}
			for(int i=(R-1);i>=(CleanRow+2);i--) {//아래.
				Room[i][C-1] = Room[i-1][C-1];
			}
			for(int j=(C-1);j>=2;j--) {//오른.
				Room[CleanRow+1][j] = Room[CleanRow+1][j-1];
			}
			Room[CleanRow+1][1] = 0;
			
		}//T초만큼 반복.
		int sum = 0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(Room[i][j]>0) sum+= Room[i][j];
			}
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		T = Integer.parseInt(input[2]);
		Room = new int[R][C];
		
		for(int i=0;i<R;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<C;j++) {
				Room[i][j] = Integer.parseInt(input[j]);
				if(Room[i][j]==-1 && Room[i-1][j] == -1) {
					CleanRow = i-1;//공기청정기의 행 좌표 기록!
				}
			}
		}
		System.out.println(solve());
		br.close();
	}

}
//for(int i=0;i<R;i++) {
//	for(int j=0;j<C;j++) {
//		System.out.print(Room[i][j]+" ");
//	}
//	System.out.println();
//}