package ss;
import java.util.*;

public class Tetris {
	static int ans,C,P,height[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();//필드의 열 수
		P = sc.nextInt();//블록 모양 번호
		height = new int[C];
		for(int i=0;i<C;i++) {
			height[i] = sc.nextInt();
		}
		ans = 0;
		for(int i=0;i<C;i++) {
			if(P==1) {
				ans += check(i,new int[] {0})+check(i,new int[] {0,0,0,0});
			} else if(P==2) {
				ans += check(i,new int[] {0,0});
			} else if(P==3) {
				ans += check(i,new int[] {0,0,1})+check(i,new int[] {0,-1});
			} else if(P==4) {
				ans += check(i,new int[] {0,-1,-1})+check(i,new int[] {0,1});
			} else if(P==5) {
				ans += check(i,new int[] {0,0,0})+check(i,new int[] {0,-1})+check(i,new int[] {0,-1,0})+check(i,new int[] {0,1});
			} else if(P==6) {
				ans += check(i,new int[] {0,0,0})+check(i,new int[] {0,-2})+check(i,new int[] {0,1,1})+check(i,new int[] {0,0});
			} else if(P==7) {
				ans += check(i,new int[] {0,0,0})+check(i,new int[] {0,2})+check(i,new int[] {0,0,-1})+check(i,new int[] {0,0});
			}
		}
		System.out.println(ans);
	}
	static int check(int i,int[] block) {
		if(i+block.length>C) return 0;
		int base = height[i];
		for(int j=0;j<block.length;j++) {
			if(height[i+j]-base != block[j]) return 0;
		}
		return 1;
	}
}
