//BOJ #1205 등수구하기
import java.io.*;
import java.util.*;
public class GetRanking {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//변수 초기화
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int[] ranks = new int[p+1];
		
		//예외케이스 처리1
		if(n == 0) {
			System.out.println(1);
			return;
		}
		
		LinkedList<Integer> list = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		//idx : 추가한 x의 등수를 의미!
		int idx = -1;
		for(int i=0;i<n;i++) {
			if(list.get(i) < x) {
				list.add(i,x);
				idx = i;
			}
		}
		
		if(idx == -1) {//리스트의 제일 마지막에 추가했다는 뜻!
			list.add(x);
			idx = list.size()-1;
		}
		//예외케이스 처리2:추가 불가능한 경우 
		if(idx > p-1) {
			System.out.println(-1);
			return;
		} else {//리스트 끝에 추가했지만 p 범위 이내인 경우 or 리스트의 가장 마지막 원소에 추가한 경우
			ranks[0] = 1; int tmp = 0;//문제 조건에 따라 동점자 수를 저장
			for(int i=1;i<=idx;i++) {
				if(list.get(i) < list.get(i-1)) {
					ranks[i] = ranks[i-1] + 1 + tmp;
					tmp = 0;
				} else {
					ranks[i] = ranks[i-1];
					tmp++;
				}
			}
		}
		System.out.println(ranks[idx]);
		
	}

}

//		for(int i=0;i<n;i++) {
//			System.out.print(list.get(i)+" ");
//		}