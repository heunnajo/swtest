import java.io.*;
import java.util.*;
public class MakeDouble {
	static int n;
	static boolean[] check;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		int[] b = new int[n];
		check = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
//		for(int i=0;i<n;i++) {
//			System.out.print(b[i]+" ");
//		}
		int ans = 0;
		for(int cnt=0;;cnt++) {
			ans = cnt; initCheck();
			//현재 배열값이 모두 0인지 확인. 하나라도 0이 아니면 false가 됨!
			//true이면 반복문 종료.그 때의 연산횟수 cnt가 정답!
			boolean allZero = true;
			for(int i=0;i<n;i++) {
				if(b[i]!=0) {
					allZero = false;break;
				}
			}
			if(allZero) break;
			
			//2로 나눠떨어지는지를 확인!
			for(int i=0;i<n;i++) {
				if(b[i]%2==0) {check[i] = true;}
				else {//2로 나눠떨어지지 않으면 일단 1 감소시키고, 다음 연산하러 감!
					b[i]-=1;
					break;
				}
			}
			//모두 2로 나눠떨어지는지를 확인.
			//모두 2로 나눠 떨어진다면 check배열값은 모두 true로 되어있음.
			boolean flag = true;
			for(int i=0;i<n;i++) {
				if(!check[i]) flag = false;
			}
			if(flag) {
				for(int i=0;i<n;i++) {
					b[i] /= 2;
				}
			}
		}
		System.out.println(ans);
	}
	static void initCheck() {
		for(int i=0;i<n;i++) {
			check[i] = false;
		}
	}

}