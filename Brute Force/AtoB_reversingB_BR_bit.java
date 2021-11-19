package ss;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class AtoB_reversingB_BR_bit {
	//역연산을 이용한 풀이 방법
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		int ans = 1;//1부터 시작하네? 만약 A,B가 같다면 연산횟수는 0일테고, 연산의 최솟값에 1 더한값을 출력하라고 했으므로!
		//A==B라면 아래 while문에 들어가지도 않음!
		while(B != A) {//직관적임. 내가 처음에 생각했던 while문.
			//B가 A보다 작아진다면 불가능한 경우!
			if(B<A) {
				ans = -1;
				break;
			}
			
			//연산 대상 : B를 문자열 변환한 것.
			String str = String.valueOf(B);//B를 스트링으로 변환
			//불가능한 경우:B의 끝자리가 0,1,2,4,6,8 가 아닌 경우
			if(str.charAt(str.length()-1) != '1' && B %2 != 0) {
				ans = -1;
				break;
			}
			
			//역연산
			if(str.charAt(str.length()-1) == '1') {//끝자리가 1인 경우:1을 짤라냄.
				str = str.substring(0,str.length()-1);//끝에 한자리 잘라내고
				B = Long.parseLong(str);//잘라낸 str을 B에 다시 저장 :Long.parseLong() 이용
			} else {//끝자리가 짝수인 경우 : 2를 나눈다. 2^1배 작아지기 때문에 오른쪽 1비트 시프트연산하면 된다!
				B >>= 1;
			}
			ans++;
		}
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}

}
