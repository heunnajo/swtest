import java.io.*;
import java.util.*;
//백준 2003번 수들의 합2
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] a = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		//1.자료구조
		int left = 0,right = 0,sum = a[0],ans = 0;
		//2.알고리즘 :투포인터 이용
		while(true) {
            //2-1.정답 찾은 경우 : sum == m
			if(sum == m) {//left를 늘려줘야할까 right를 늘려줘야할까? right를 늘려줌으로써 전진해간다!
				ans++;
                right++;
                if(right == n) break;
				sum += a[right];
			}
            //2-2.sum < M
			else if(sum < m) {
                right++;
                if(right == n) break;
				sum += a[right];
				
			}
			//2-3.sum > M
			else if(sum > m) {
				sum -= a[left];
				left++;
				if(left>right && left < n) {
					right = left; sum = a[left];//sum을 a[left]로 초기화, 다시 시작.
				}
			}
		}
		System.out.println(ans);
	}

}

//		for(int i=0;i<n;i++) {
//			System.out.print(a[i]+" ");
//		}
//		System.out.println();

//		System.out.println("n: "+n);
//		System.out.println("n: "+m);