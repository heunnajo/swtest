package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
public class TreasureChestPW_2nd {
	static int ans,N,K;
	static char[] input;
	static HashSet<Integer> set;
	static LinkedList<Integer> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			set = new HashSet<>();
			list = new LinkedList<>();
			input = br.readLine().toCharArray();
			
			for(int i=0;i<N-1;i++) {
				rotate();//회전
				//print();
				hexToDec();//회전하고난 후 숫자 4개로 끊어서 set에 저장.
			}
			//set을 바로 내림차순 정렬은 안 되남
			for(Integer i:set) list.add(i);
			Collections.sort(list,Collections.reverseOrder());
			
			ans = list.get(K-1);
			
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}
	static void rotate() {
		char tmp = input[N-1];
		for(int i=N-1;i>=1;i--)
			input[i] = input[i-1];
		input[0] = tmp;
	}
	static void hexToDec() {
		for(int i=0;i<N;i+=N/4) {
			String s=""; int dec=0;
			for(int j=i;j<i+N/4;j++) {
				s += input[j];
			}
			//System.out.println("16진수 : "+s);
			dec = Integer.parseInt(s,16);
			set.add(dec);
		}
	}
	static void print() {
		for(int i=0;i<input.length;i++)
			System.out.print(input[i]+" ");
		System.out.println();
		System.out.println();
		
	}
}
