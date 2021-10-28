package ss;
import java.util.*;

public class TwoStickers {
	static int H,W,N;
	static boolean[][] Check;//스티커 중복 놓기 방지
	static class Shape{
		int h,w;
		Shape(int h,int w){
			this.h = h;
			this.w = w;
		}
		void rotate() {
			int tmp = 0;
			tmp = this.h;
			this.h = this.w;
			this.w = tmp;
		}
	}
	static Shape[] Shapes;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt();
		W = sc.nextInt();
		N = sc.nextInt();
		Shapes = new Shape[N];
		Check = new boolean[H][W];
		boolean[] Used = new boolean[N];
		for(int i=0;i<N;i++) {
			int h = sc.nextInt();
			int w = sc.nextInt();
			Shapes[i] = new Shape(h,w);
		}
		int ans = -1;
		//H*W 크기 모눈종이 완전탐색
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				int size_sum = 0;//각 경우마다 넓이의 합을 구한다.
				for(int s1 = 0;s1<N;s1++) {
					Shape cur1 = Shapes[s1]; Used[s1] = true;
					for(int rot=0;rot<2;rot++) {
						cur1.rotate();
						for(int a=i;a<i+cur1.w;a++) {
							for(int b=j;b<j+cur1.h;b++) {
								if(isOut(a,b)||Check[a][b]) {}//다음 방향으로 회전해야함..
								Check[a][b] = true;
							}
						}
						size_sum += cur1.w*cur1.h;
					}//회전 다 했는데도 범위밖/겹친다면 다음좌표로 가야함.
				}
				for(int s2 = 0;s2<N;s2++) {
					if(Used[s2])continue;
					Shape cur2 = Shapes[s2];
					for(int rot=0;rot<2;rot++) {
						cur2.rotate();
						for(int a=i;a<i+cur2.w;a++) {
							for(int b=j;b<j+cur2.h;b++) {
								if(isOut(a,b)||Check[a][b]) {}//다음 방향으로 회전해야함..
								
								Check[a][b] = true;
							}
						}
						size_sum += cur2.w*cur2.h;
					}//회전 다 했는데도 범위밖/겹친다면 다음좌표로 가야함.
				}
				ans = Math.max(ans, size_sum);
			}
		}
		System.out.println(ans);
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>H-1 || y<0 || y>W-1;
	}
}
