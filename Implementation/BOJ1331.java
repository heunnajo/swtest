package boj;
import java.io.*;
import java.util.*;
//나이트 투어
//2ndTrial
public class BOJ1331 {
	static int firstR,firstC,prevR,prevC,curR,curC;
	static boolean[][] grid;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//첫위치와 마지막 위치 비교 위해 첫위치만 반복문 밖에서 처리
		int tc = 35;
		grid = new boolean[6][6];
		
		boolean isValid = true;
		
		String input = br.readLine();
		//제대로 행열값 도출되는지 확인 :  O
		firstR = input.charAt(1)-'0';
		firstR = 6- firstR;
		prevR = firstR;
		prevC = firstC = input.charAt(0)-'A';
		//System.out.println("쳣번째 좌표: ("+prevR+","+prevC+")");
		grid[prevR][prevC] = true;
		
		while(tc-- >0) {
			input = br.readLine();
			//curR = 6-input.charAt(1)-'0';
			curR = input.charAt(1)-'0';
			curR = 6- curR;
			curC = input.charAt(0)-'A';
			//System.out.println("현재 좌표: ("+curR+","+curC+")");
			if(!isValidMovement()) {
				isValid = false;
				break;
			} else {
				grid[curR][curC] = true;
			}
			prevR = curR; prevC = curC;
		}
		
		//마지막 위치와 시작 위치 이동 가능 확인 : 이전 위치 = 마지막 위치, 현재 위치 = 시작 위치
		curR = firstR; curC = firstC;
		if(isValid && (!isValidMovement() || !allVisited())) {
			isValid = false;
		}
		
		if(isValid) { System.out.println("Valid");}
		else {System.out.println("Invalid");}
		
	}
	static boolean allVisited() {
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				if(!grid[i][j]) return false; //미방문 좌표 존재
			}
		}
		return true;
	}
	static boolean isOut(int x,int y) {
		return  x<0 || x>5 || y<0 || y>5;
	}
	//prev와 cur 이동 유효성 판단
	static boolean isValidMovement() {
		int[] dx = {1,2,2,1,-1,-2,-2,-1};
		int[] dy = {2,1,-1,-2,-2,-1,1,2};
		int nx,ny;
		
		for(int d=0;d<8;d++) {
			nx = curR+dx[d]; ny = curC+dy[d];
			if(isOut(nx,ny)) continue;
			if(nx == prevR && ny == prevC) return true;
		}
		
		return false;
	}

}
