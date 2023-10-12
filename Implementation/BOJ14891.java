//톱니바퀴
import java.util.*;
import java.io.*;

public class BOJ14891 {
	static int[][] Wheel;
	static int[] isRotated;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Wheel = new int[4][8];
		String[] input;
		
		for(int i=0;i<4;i++) {
			input = br.readLine().split("");
			for(int j=0;j<8;j++) {
				Wheel[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		int cnt = Integer.parseInt(br.readLine());
		int idx,dir;
		
		for(int i=0;i<cnt;i++) {
			input = br.readLine().split(" ");
			isRotated = new int[4];
			idx = Integer.parseInt(input[0])-1;
			dir = Integer.parseInt(input[1]);
			check(idx,dir);
			rotate();
		}
		
		System.out.println(calculated());
		
	}
	static void print() {
		System.out.print("isRotated: ");
		for(int i=0;i<4;i++) {
			System.out.print(isRotated[i]+" ");
		}
		System.out.println();
		
	}
	static void printWheel() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<8;j++) {
				System.out.print(Wheel[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	static int calculated() {
		int sum = 0;
		
		if(Wheel[0][0] == 1) sum += 1;
		if(Wheel[1][0] == 1) sum += 2;
		if(Wheel[2][0] == 1) sum += 4;
		if(Wheel[3][0] == 1) sum += 8;
		
		return sum;
	} 
	static void check(int idx,int dir) {
		isRotated[idx] = dir;
		
		if(idx-1>=0 && isRotated[idx-1] == 0) {
			if(Wheel[idx-1][2] != Wheel[idx][6]) {
				check(idx-1,dir * -1);
			}
		}
		
		if(idx+1<=3 && isRotated[idx+1] == 0) {
			if(Wheel[idx][2] != Wheel[idx+1][6]) {
				check(idx+1,dir * -1);
			}
		}
	}
	static void rotate() {
		for(int i=0;i<4;i++) {
			if(isRotated[i] == -1) {
				int tmp = Wheel[i][0];
				for(int j=0;j<7;j++){
					Wheel[i][j] = Wheel[i][j+1];
				}
				Wheel[i][7] = tmp;
			} else if(isRotated[i] == 1) {
				int tmp = Wheel[i][7];
				for(int j=7;j>0;j--){
					Wheel[i][j] = Wheel[i][j-1];
				}
				Wheel[i][0] = tmp;
			}
		}
	}
}

//		
//		for(int i=0;i<cnt;i++) {
//			System.out.println(opInfo[i][0]+" "+opInfo[i][1]);
//		}