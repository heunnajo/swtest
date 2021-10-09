package ss;
import java.util.*;
public class DiagonalTrace {
	static int[][] arr;
	static void updateMap(int sx,int sy,int ex,int ey,int typ) {
		if(typ == 1) {//행-열 이 같은 칸:x++,y++방향으로 탐색하기 때문에 ex,ey값 필요.
			for(int i=ey;i>=sy;i--)
				reverseMap(sx,i,ex,ey);
			for(int i=sx;i<=ex;i++)
				reverseMap(i,sy,ex,ey);
		}else {//행+열 이 같은 칸 : x--,y++ 방향으로 탐색하기 때문에 sx,ey값 필요.
			for(int i=sx;i<=ex;i++)
				reverseMap2(i,sy,sx,ey);
			for(int i=sy+1;i<=ey;i++)
				reverseMap2(ex,i,sx,ey);
		}
	}
	static void reverseMap(int x,int y,int ex,int ey) {//x++, y++ 방향 탐색
		Stack<Integer> st = new Stack<>();
		int src_x = x, src_y = y;//탐색을 반복할 인덱스 변수 초기값 셋팅
		
		while(src_x<=ex && src_y<=ey) {
			st.push(arr[src_x++][src_y++]);
		}
		src_x = x;src_y = y;//시작점과 방향 동일하게 하되 스택의 특성을 이용해서 값 reverse할 수 있다.
		while(!st.isEmpty()) {
			arr[src_x++][src_y++] = st.pop();//
		}
	}
	static void reverseMap2(int x,int y,int sx,int ey) {//x--, y++ 방향 탐색
		Stack<Integer> st = new Stack<>();
		int src_x = x, src_y = y;
	
		while(src_x>=sx && src_y<=ey) {
			st.push(arr[src_x--][src_y++]);
		}
		src_x = x;src_y = y;//시작점과 방향 동일하게 하되 스택의 특성을 이용해서 값 reverse할 수 있다.
		while(!st.isEmpty()) {
			arr[src_x--][src_y++] = st.pop();
		}
	}
	public static void main(String[] args) {
		arr = new int[5][8];
		int val = 0;
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<8;j++) {
				arr[i][j] = val++;
			}
		}
		printArr();
//		updateMap(0,0,4,7,1);//sx,sy,ex,ey,typ
//		System.out.println("typ=1일 때 update 후 ");//
//		printArr();
		updateMap(0,0,4,7,-1);//sx,sy,ex,ey,typ
		System.out.println("typ=-1일 때 update 후 ");//
		printArr();
	}
	static void printArr() {
		System.out.println("====================");
		for(int i=0;i<5;i++) {
			for(int j=0;j<8;j++) {
				System.out.printf("%3d",arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("====================");
	}
}
