import java.util.*;
import java.io.*;
public class TwoArraysSum_HashMap {
	static int T,N,M,A[],B[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		//A,B 모든 부배열 합 구하기,각각 UP, DOWN에 저장
		ArrayList<Integer> up = new ArrayList<>();
		ArrayList<Integer> down = new ArrayList<>();
		
		int idx = 0;int sum;
		for(int i=0;i<N;i++) {
			sum = 0;
			for(int j=i;j<N;j++) {
				sum += A[j];
				up.add(sum);
			}
		}
		idx = 0;
		for(int i=0;i<M;i++) {
			sum = 0;
			for(int j=i;j<M;j++) {
				sum += B[j];
				down.add(sum);
			}
		}
		//Collections.sort(up);
		//Collections.sort(down);
		//B배열에서 각 원소값이 몇번 들어있는지 횟수 카운팅
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int i=0;i<down.size();i++) {
			map.put(down.get(i),map.getOrDefault(down.get(i),0)+1);
		}
		
		//T-A가 몇개인지 카운팅!
		long ans = 0;
		for(int a : up) {
			if(map.get(T-a) != null) {
				ans += map.get(T-a);//T-a가 없으면?0을 리턴하나?NULL을 리턴핮지 당연히!
			}
		}
		System.out.println(ans);
		
	}

}