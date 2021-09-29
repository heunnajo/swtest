package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.LinkedList;

public class TreasureChestPw_3rd_inMain {
	static int ans,T,N,K;
	static char[] Input;
	static HashSet<Integer> set;
	static LinkedList<Integer> result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			String[] in = br.readLine().split(" ");
			N = Integer.parseInt(in[0]);
			K = Integer.parseInt(in[1]);
			
			Input = new char[N];
			result = new LinkedList<>();
			set = new HashSet<>();
			
			Input = br.readLine().toCharArray();
			for(int i=0;i<N-1;i++) {
				//1.진법 변환해서 리스트에 저장.
				hextoDec();
				//2.오른쪽 1칸 이동
				moveRight();
			}
			
			for(int i:set) result.add(i);
            //list 컬렉션 내림차순정렬하는 2가지 방법
			//Collections.sort(result,Collections.reverseOrder());
			Collections.sort(result,(a,b)->b-a);
			
			sb.append("#"+t+" "+result.get(K-1)+"\n");
		}
		System.out.print(sb);
	}
	static void hextoDec() {//현재 16진수를 10진수로 변환후 리스트에 저장.
		for(int i=0;i<N;i+=N/4) {
			String hex = "";
			for(int j=i;j<i+N/4;j++) {
				hex += Input[j];
			}
			int dec = Integer.parseInt(hex,16);
			set.add(dec);
		}
	}
	static void moveRight() {
		char tmp = Input[N-1];
		for(int i=N-1;i>=1;i--) {
			Input[i] = Input[i-1];
		}
		Input[0] = tmp;
	}

}