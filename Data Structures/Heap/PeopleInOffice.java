import java.util.*;
import java.io.*;
public class PeopleInOffice {
	static int n;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		HashSet<String> bst = new HashSet<>();
		for(int i=0;i<n;i++) {
			//X enter:X 삽입, X leave : X 삭제
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String cmd = st.nextToken();
			if(cmd.equals("enter")) {
				bst.add(name);
			} else if(cmd.equals("leave")) {
				bst.remove(name);
			}
		}
		
		List<String> list = new ArrayList<>();
		for(String s:bst) {
			list.add(s);
			//System.out.println(s);
		}
		Collections.sort(list,Collections.reverseOrder());
		for(String s:list) {
			System.out.println(s);
		}
	}

}