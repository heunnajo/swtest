package ss;

public class gravityTest {
	static final int EMPTY = -2;
	static final int BLACK = -2;
	public static void main(String[] args) {
		int[][] map = {{2,2,-1,3,1},
				{-2,-2,2,0,-1},
				{-2,-2,-2,1,2},
				{-1,-2,1,3,2},
				{-2,-2,2,2,1}};
		int N = 5;
		
		System.out.println("중력 전:");
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				
				System.out.printf("%3d",map[i][j]);
			}
			System.out.println();
		}
		
		for(int row = N-1;row>0;row--) {
			for(int col=0;col<N;col++) {
				if(map[row][col] != EMPTY) continue;
				
				int r = row;
				while(r>0 && map[r][col] == EMPTY) {
					r--;
				}
				if(map[r][col] == BLACK) continue;
				map[row][col] = map[r][col];
				map[r][col] = EMPTY;
			}
		}
		System.out.println("중력 후:");
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				
				System.out.printf("%3d",map[i][j]);
			}
			System.out.println();
		}
	}

}
