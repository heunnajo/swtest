package ss;
import java.io.*;
import java.util.*;
public class FashionQueen {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		Map<String,LinkedList<String>> map;
		
		int n;
		StringBuilder sb = new StringBuilder();
		while(TC-- >0) {
			n = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			while(n-- >0) {//테케마다 map 생성, 초기화 
				st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String category = st.nextToken();
				if(map.containsKey(category)) {
					map.get(category).add(name);
				} else {
					LinkedList<String> list = new LinkedList<>();
					list.add(name);
					map.put(category, list);
				}
			}
			int ans = 1;
			for(String s:map.keySet()) {
				ans *= (map.get(s).size()+1);
			}
			ans -= 1;
			sb.append(ans+"\n");
		}
		System.out.print(sb);
		
	}

}

//				System.out.println("name: "+name);
//				System.out.println("category: "+category);