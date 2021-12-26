import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class NeverSeenNeverHeard {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Map<String,Integer> map = new HashMap<>();
		
		for(int i=0;i<n;i++){
			String name = br.readLine();
			map.put(name, 1);
		}
		List<String> list = new ArrayList<>();
		int ans = 0;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<m;i++){
			String name = br.readLine();
			if(map.containsKey(name)) {
				ans++; //sb.append(name+"\n");
				list.add(name);//정답 데이터
			}
		}
		Collections.sort(list);//오름차순 정렬!
		for(String s:list) sb.append(s+"\n");//list의 데이터를 sb에 넣어서 빠르게 출력.
		System.out.println(ans);
		System.out.print(sb);
	}

}