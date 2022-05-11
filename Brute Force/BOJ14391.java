package ss;
import java.util.*;
import java.io.*;
//종이 조각
public class BOJ14391 {
	static int rowSize,colSize;
	static String[][] input;
	static int[][] inputInt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		input = new String[rowSize][colSize];
		inputInt = new int[rowSize][colSize];
		
		for(int i=0;i<rowSize;i++) {
			input[i] = br.readLine().split("");
		}
		
		for(int i=0;i<rowSize;i++) {
			for(int j=0;j<colSize;j++) {
				inputInt[i][j] = Integer.parseInt(input[i][j]);
			}
		}
		
		int ans = 0;
		for(int subset = 0;subset<(1<<rowSize*colSize);subset++) {
			int num = checkRow(subset)+checkCol(subset);
			if(num > ans) ans = num;
		}
		System.out.println(ans);
	}
	static int checkRow(int subset) {
		int res = 0;
		for(int i=0;i<rowSize;i++) {
			int cur = 0;
			for(int j=0;j<colSize;j++) {
				if((subset & (1<< i*colSize+j)) != 0) {
					cur = cur*10 + inputInt[i][j];
				} else {
					res += cur;
					cur = 0;
				}
			}
			res += cur;
		}
		return res;
	}
	static int checkCol(int subset) {
		int res = 0;
		for(int j=0;j<colSize;j++) {
			int cur = 0;
			for(int i=0;i<rowSize;i++) {
				if((subset & (1<<i*colSize+j)) == 0) {
					cur = cur*10 + inputInt[i][j];
				} else {
					res += cur;
					cur = 0;
				}
			}
			res += cur;
		}
		return res;
	}

}

//		for(int i=0;i<rowSize;i++) {
//			for(int j=0;j<colSize;j++) {
//				System.out.print(input[i][j]+" ");
//			}
//			System.out.println();
//		}