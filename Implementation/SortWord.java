//BOJ#1181 단어 정렬
import java.util.*;
import java.io.*;
public class SortWord {
	static class Comp implements Comparator<String> {
	    public int compare(String one, String two) {
	    	if(one.length() == two.length()) {
	    		return one.compareTo(two);//사전 순
	    	} 
	    	else {
	    		return one.length()-two.length();
	    	}
	    }
	 } 

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] arr = new String[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = br.readLine();
		}
		
		Comp comp = new Comp();
		Arrays.sort(arr,comp);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(arr[0]+"\n");
		
		for(int i=1;i<N;i++) {
			if(!arr[i].equals(arr[i-1])) {
				sb.append(arr[i]+"\n");
			}
		}
		System.out.print(sb);
		
	}

}