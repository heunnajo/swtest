package ss;
import java.util.*;
public class TriangleValue {
	static int ans,n;
	static int[][] a = new int[401][801];
	static int[][] s = new int[401][801];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int tc=1;;tc++) {
			n = sc.nextInt();
			if(n == 0) break;
			ans = -100000;
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=2*i-1;j++) {
					a[i][j] = sc.nextInt();
					s[i][j] = s[i][j-1] + a[i][j];
				}
			}
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=2*i-1;j++) {
					calc(i,j,j,0);
				}
			}
			System.out.printf("%d. %d\n",tc,ans);
		}
	}
	static void calc(int row,int left,int right,int sum) {
		if(row<1 || row>n) return;//행 범위 체크 
		if(left<1 || right>2*row-1) return;//열 범위 체크 
		
		sum+= s[row][right]-s[row][left-1];
		if(sum>ans) ans = sum;
		if(left % 2 == 0) {
			calc(row-1,left-2,right,sum);
		} else {
			calc(row+1,left,right+2,sum);
		}
	}
}
