package ss;
import java.util.*;
import java.io.*;
public class ThreeFriends {
	static int N,M;
	static boolean[][] a;
	static int[] degree;//degree[i] = i의 친구 수!
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		a = new boolean[N][N];
		degree = new int[N];
		
		for(int i=0;i<M;i++) {
			int x = sc.nextInt()-1;
			int y = sc.nextInt()-1;
			
			a[x][y] = a[y][x] = true;
			degree[x]++;
			degree[y]++;
		}
		int ans = -1;//최솟값 도출!if(ans == -1 || ans>sum) ans = sum;
		int sum;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(a[i][j]) {//현재 고른 i,j가 친구관계일 때만 C를 선택한다!
					for(int k=0;k<N;k++) {//세친구는 모두 친구여야하기 때문에 선택하려는 k도 i,j와친구관계야아함!
						if(a[i][k] && a[j][k]) {
							sum = degree[i]+degree[j]+degree[k]-6;
							if(ans==-1 || ans>sum) ans = sum;
						}
					}
				}
			}
		}
		System.out.println(ans);
		
	}

}
