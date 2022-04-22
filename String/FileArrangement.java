//BOJ #20291 파일 정리
import java.util.*;
import java.io.*;
public class FileArrangement {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		HashMap<String,Integer> map = new HashMap<>();
		
		while(n-- >0) {
			
			String input = br.readLine();
			
			int idx = input.indexOf('.');
			
			String ex = input.substring(idx+1);
			//map완성
			map.put(ex, map.getOrDefault(ex, 0)+1);
		}
		//완성한 map의 keySet() 들을 저장
		int idx = 0;
		String[] arr = new String[map.size()];
		for(String s:map.keySet()) {
			arr[idx++] = s;
		}
		 
		Arrays.sort(arr, new Comparator<String>() {		
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);	// 사전 순 정렬
			}
		});
		
		
		//정답 출력
		StringBuilder sb = new StringBuilder();
		for(String s:arr) {
			sb.append(s+" "+map.get(s)+"\n");
		}
		System.out.print(sb);
	}

}