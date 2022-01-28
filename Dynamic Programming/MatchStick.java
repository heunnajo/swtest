package ss;
import java.util.*;
import java.io.*;
public class MatchStick {

	public static void main(String[] args) throws  Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		int[][] info = {
				{-1},//0
				{-1},//1
				{1},//2
				{7},//3
				{4},//4
				{2,5},//5
				{0,6,9},//6
				{8}//7
				};
		
		while(tc-- >0) {
			int n = Integer.parseInt(br.readLine());
			StringBuilder tmpMax = new StringBuilder();
			StringBuilder tmpMin = new StringBuilder();
			int[] curInfo = info[n];
			int max = -1, min = -1;
			if(n%2 == 0) {//짝수
				if(n<=7) {
					max = curInfo[curInfo.length-1];
					min = curInfo[0];
				} else {
					for(int i=0;i<n/2;i++) tmpMax.append("1");
					//n이 짝수, n>7인 최솟값 만드는 로직
					tmpMin.append("10"); n-=8;
					while(n>=6) {
						tmpMin.append("0"); n-=6;
					}
					tmpMin.append(info[n][0]);
				}
			} else {//홀수
				if(n<=7) {
					max = curInfo[curInfo.length-1];
					min = curInfo[0];
				} else {
					tmpMax.append("7");
					for(int i=0;i<(n-3)/2;i++) tmpMax.append("1");
					//n이 홀수, n>7인 최솟값 만드는 로직:여기서 막힘..!
				}
			}
			max = Integer.parseInt(String.valueOf(tmpMax));//이게 최선?
			min = Integer.parseInt(String.valueOf(tmpMin));
			sb.append(max+" "+min+"\n");
		}
		System.out.print(sb);
	}

}
