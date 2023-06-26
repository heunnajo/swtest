import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//요세푸스 문제
public class BOJ1158 {
	public static void main(String[] args) throws IOException {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer st = new StringTokenizer(br.readLine());
	   int N = Integer.parseInt(st.nextToken());
	   int M = Integer.parseInt(st.nextToken());
	   int n = 0;
	   ArrayList<Integer> link = new ArrayList<>();
	   StringBuilder sb = new StringBuilder("<");
	   
	   for(int i=0 ; i<N ; i++){
		   link.add(i+1);
	   }
	   while(!link.isEmpty()){
		   n = (n+M-1)%N;
		   sb.append(link.get(n));//수 도출
		   if(N!=1){sb.append(", ");}//정답 형식대로 콤마 출력
		   link.remove(n);//n번째 수 삭제
		   N--;//⭐️N을 1씩 감소시킴⭐️
	   }
	   sb.append(">");
	   System.out.println(sb);
	}
}