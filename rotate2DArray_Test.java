package ss;

public class rotate2DArray_Test {

	public static void main(String[] args) {
		int[][] square = {{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}};
//		int squareR = square.length;
//		int squareC = square[0].length;
		int squareR = square.length;
		int squareC = square.length;
		int N = squareR;
		//System.out.println("정사각형 행 크기: "+squareR + "정사각형 열 크기: "+squareC);
		
		int[][] rect = {{1,2,3,4,5},
				{6,7,8,9,10},
				{11,12,13,14,15},
				{16,17,18,19,20}};
		int rectR = rect.length;//4
		int rectC = rect[0].length;//5
		//System.out.println("직사각형 행 크기: "+rectR + "직사각형 열 크기: "+rectC);
		//1.정사각 배열 회전
		//1-1.반시계 회전
		int[][] newSquare = new int[squareR][squareC];
		for(int i=0;i<squareR;i++) {//squareR = squareC 이므로 동일한 변수로 2중 반복문 구성.
			for(int j=0;j<squareR;j++) {
				newSquare[i][j] = square[j][squareR-1-i];
			}
		}
		System.out.println("정사각 배열 회전 전:");
		for(int i=0;i<squareR;i++) {//squareR = squareC 이므로 동일한 변수로 2중 반복문 구성.
			for(int j=0;j<squareR;j++) {
				System.out.print(square[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println("정사각 배열 반시계 회전 후:");
		for(int i=0;i<squareR;i++) {//squareR = squareC 이므로 동일한 변수로 2중 반복문 구성.
			for(int j=0;j<squareR;j++) {
				System.out.print(newSquare[i][j]+ " ");
			}
			System.out.println();
		}
		//1-2.시계 회전
		int[][] rightSquare = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				rightSquare[i][j] = square[N-1-j][i];
			}
		}
		System.out.println("정사각 배열 시계 회전 후:");
		for(int i=0;i<squareR;i++) {//squareR = squareC 이므로 동일한 변수로 2중 반복문 구성.
			for(int j=0;j<squareR;j++) {
				System.out.print(rightSquare[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
		
		
		//2.직사각 배열 회전:1번 정사각 배열 회전 로직 그대로 적용 가능한가?
		//2-1.반시계 회전
		int[][] newRect = new int[rectC][rectR];//4*5 => 5*4
		for(int i=0;i<rectC;i++) {//squareR = squareC 이므로 동일한 변수로 2중 반복문 구성.
			for(int j=0;j<rectR;j++) {
				newRect[i][j] = rect[j][rectC-1-i];//기존 : N-1-i에서 N이 기존 배열의 열(rectC)로 바뀌었다!
			}
		}
		System.out.println("직사각 배열 회전 전:");
		for(int i=0;i<rectR;i++) {//squareR = squareC 이므로 동일한 변수로 2중 반복문 구성.
			for(int j=0;j<rectC;j++) {
				System.out.print(rect[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println("직사각 배열 반시계 회전 후:");
		for(int i=0;i<rectC;i++) {//squareR = squareC 이므로 동일한 변수로 2중 반복문 구성.
			for(int j=0;j<rectR;j++) {
				System.out.print(newRect[i][j]+ " ");
			}
			System.out.println();
		}
		
		//2-2.시계 회전
		int[][] rightRect = new int[rectC][rectR];//4*5 => 5*4 
		for(int i=0;i<rectC;i++) {//squareR = squareC 이므로 동일한 변수로 2중 반복문 구성.
			for(int j=0;j<rectR;j++) {
				rightRect[i][j] = rect[rectR-1-j][i];
			}
		}
		System.out.println("직사각 배열 시계 회전 후:");
		for(int i=0;i<rectC;i++) {//squareR = squareC 이므로 동일한 변수로 2중 반복문 구성.
			for(int j=0;j<rectR;j++) {
				System.out.print(rightRect[i][j]+ " ");
			}
			System.out.println();
		}
	}
}


