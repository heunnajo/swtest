//BOJ #2870 수학문제
package ss;
import java.io.*;
import java.util.*;
public class MathProblem {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		LinkedList<Integer> ans = new LinkedList<>();
		
		//N개의 입력 받으면서 처리, 정답 자료구조에 저장!
		while(N-- >0) {
			String input = br.readLine();
			int len = input.length();
			String tmpNum = "";
			
			for(int i=0;i<len;i++) {
				if(!Character.isLowerCase(input.charAt(i))) {
					tmpNum += input.charAt(i);
				} else {
					if(!tmpNum.equals("")) {
						int num = Integer.parseInt(tmpNum);
						//System.out.println("형변환 후 num: "+num);
						ans.add(num);
						tmpNum = "";
					}
				}
			}
			if(!tmpNum.equals("")) {
				int num = Integer.parseInt(tmpNum);
				//System.out.println("형변환 후 num: "+num);
				ans.add(num);
				tmpNum = "";
			}
		}
		Collections.sort(ans);//내림차순으로 정렬하면 어떻게 되는가?해보기
		StringBuilder sb = new StringBuilder();
		
		for(int a:ans) {
			sb.append(a+"\n");
		}
		System.out.print(sb);
	}

}
