package ss;
import java.io.*;
import java.util.*;
public class HN_1_PQ {
	static int N=10;
	static int M,K,pocket[];//N:바구니에 들어있는 수, M:명령어 갯수,K: 삭제하려는 수
	static PriorityQueue<Integer> bucket = new PriorityQueue<>((a,b)->a-b);
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		//입력을 받기 전에 바구니, 주머니에 숫자들을 먼저 채워준다!
		//N = Integer.parseInt(br.readLine());
		pocket = new int[N+1];
		
		for(int i=2;i<=N;i++) {//N까지 채우는 게 맞나?N?N+1?
			bucket.add(i);
		}
		pocket[1] = 1;
		
		//명령어 갯수 M과 M개의 명령어 입력받는다!
		M = Integer.parseInt(br.readLine());
		String[] input;
		for(int i=0;i<M;i++) {
			input = br.readLine().split(" ");
			if(input[0].equals("bring")) {//1.bring : 바구니에서 가장 작은 수를 빼서, 내 주머니에 넣는다.
				//System.out.println("bring");
				int val = bucket.poll();
				pocket[val] = 1;
			} else {//2.back K : 내 주머니에서 K를 빼서, 바구니에 넣는다.
				//System.out.println("back"+Integer.parseInt(input[1]));
				int K = Integer.parseInt(input[1]);
				pocket[K] =  0;
				bucket.add(K);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++)
			if(pocket[i]==1) sb.append(i+" ");
		
		System.out.println(sb);
	}

}

//		while(!bucket.isEmpty()) {
//			System.out.print(bucket.poll()+" ");
//		}