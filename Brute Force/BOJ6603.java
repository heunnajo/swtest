//로또
import java.util.*;
import java.io.*;

public class BOJ6603 {
	static int K;
	static int[] Num;
	static int[] Selected;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		String[] input;
		sb = new StringBuilder();
		
		while(true) {
			input = br.readLine().split(" ");
			if(input[0].equals("0")) break;
			K = Integer.parseInt(input[0]);
			Num = new int[K];
			Selected = new int[6];
			for(int i=1;i<input.length;i++) {
				Num[i-1] = Integer.parseInt(input[i]);
				//System.out.print(Num[i-1]+" ");
			}
			go(0,0);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static void go(int idx,int from) {
		//1.종료
		if(idx == 6) {
			//StringBuilder에 append 후 리턴
			for(int i=0;i<6;i++) {
				sb.append(Selected[i]).append(" ");
			}
			sb.append("\n");
			//System.out.println(sb.toString());
			return;
		}
		//2.현재 경우 선택, 다음 경우 호출
		for(int i=from;i<K;i++) {
			Selected[idx] = Num[i];
			go(idx+1,i+1);
		}
	}
}

//			for(int i=1;i<input.length;i++) {
//				Num[i-1] = Integer.parseInt(input[i]);
//				System.out.print(Num[i-1]+" ");
//			}
//			System.out.println();