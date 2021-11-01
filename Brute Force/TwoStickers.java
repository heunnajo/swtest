package ss;
import java.util.*;

public class TwoStickers {
	static int H,W,N;
	static class Shape{
		int r,c;
		Shape(int h,int w){
			this.r = h;
			this.c = w;
		}
		void rotate() {
			int tmp = 0;
			tmp = this.r;
			this.r = this.c;
			this.c = tmp;
		}
	}
	static Shape[] Shapes;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt();
		W = sc.nextInt();
		N = sc.nextInt();
		Shapes = new Shape[N];
		boolean[] Used = new boolean[N];
		for(int i=0;i<N;i++) {
			int h = sc.nextInt();
			int w = sc.nextInt();
			Shapes[i] = new Shape(h,w);//n개의 스티커 정보를 저장한다!
		}
		int ans = 0;
		//H*W 크기 모눈종이 완전탐색
		for(int i=0;i<N;i++) {
			Shape s1 = Shapes[i];
			for(int j=i+1;j<N;j++) {
				Shape s2 = Shapes[j];
				for(int r1=0;r1<2;r1++) {
					for(int r2=0;r2<2;r2++) {
						//가로로 놓는 경우
						if(Math.max(s1.r, s2.r)<=H && s1.c+s2.c<=W) {
							int tmp = s1.r*s1.c + s2.r*s2.c;
							if(ans<tmp) ans = tmp;
						}
						//세로로 놓는 경우
						if(Math.max(s1.c, s2.c)<=W && s1.r+s2.r<=H) {
							int tmp = s1.r*s1.c + s2.r*s2.c;
							if(ans<tmp) ans = tmp;
						}
						s2.rotate();
					}
					s1.rotate();
				}
			}
		}
		System.out.println(ans);
	}
}
