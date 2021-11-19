package ss;
import java.util.*;
public class AtoB_reversingB {
	//역연산을 이용한 풀이 방법
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A,B;
		A = sc.nextInt();
		B = sc.nextInt();
		
		for(int i=1;A<=B;i++) {
			if(A==B) {
				System.out.println(i);
				System.exit(0);
			}
			if(B%10 == 1) {//1.B의 끝자리가 1일 때
				B = (B-1)/10;
			}
			else if(B%2 == 0) {//2.B가 짝수일 때
				B = B/2;
			} else {break;}//1,2를 제외하고 다른 경우가 존재:불가능한  경우
		}
		System.out.println(-1);
	}

}