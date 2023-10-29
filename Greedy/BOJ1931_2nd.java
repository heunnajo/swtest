import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//회의실 배정
public class BOJ1931 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] meetings = new int[N][2];
		
		String[] input;
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			meetings[i][0] = Integer.parseInt(input[0]);
			meetings[i][1] = Integer.parseInt(input[1]);
			
		}
		
		//끝나는 시간 기준 오름차순 정렬
		//끝나는 시간 동일한 경우 시작 시간 기준 오름차순 정렬
		Comparator comp = new Comparator<int[]>() {
			@Override
			public int compare(int[] a,int[] b) {
				if(a[1] == b[1]) return a[0]-b[0];
				return a[1]-b[1];
			}
		};
		Arrays.sort(meetings,comp);
		//정렬 결과 확인
//		for(int i=0;i<N;i++) {
//			System.out.println(meetings[i][0]+","+meetings[i][1]);
//		}
		//겹치지 않는 회의들을 선택해 나간다.
		int cur_end = 0;
		int ans = 0;
		ArrayList<Integer> selectedM = new ArrayList<>();
		for(int i=0;i<N;i++) {
			if(cur_end <= meetings[i][0]) {
				cur_end = meetings[i][1];
				ans++;
				selectedM.add(i);
			}
		}
		System.out.println(ans);
		//System.out.println("정렬된 미팅들 중 선택한 미팅의 인덱스");
		
//		for(int i:selectedM){
//			System.out.println(i);
//			
//		}
	}

}

//		for(int i=0;i<N;i++) {
//			System.out.println(meetings[i][0]+","+meetings[i][1]);
//		}