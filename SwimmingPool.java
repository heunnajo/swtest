package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class SwimmingPool {
	static int T,ans;
	static final int INF = 987654321;
	static int[] Price,Plan;//Price=[1일권,1달권,3달권,1년권]
	static int go(int month,int price) {
		//1.정답 찾은 경우
		if(month>=12) {return price;}
		//2.백트랙킹
		if(month<12 && price>= ans){return INF;}
		//3.현재 달 이용권 정하고, 다음 경우 호출
		//3-1.1년권!
		ans = Math.min(ans, Price[3]);
		//3-2.현재달 1일권
		ans = Math.min(ans, go(month+1,price+Plan[month]*Price[0]));
		//3-3.현재달 1달권!
		ans = Math.min(ans, go(month+1,price+Price[1]));
		//3-4.현재달 3달권!
		ans = Math.min(ans, go(month+3,price+Price[2]));
		
		return ans;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		Price = new int[4];
		Plan = new int[12];
		
		for(int t=1;t<=T;t++) {//각 TC 마다 2줄씩 읽는다!
			st = new StringTokenizer(br.readLine());
			Price[0] = Integer.parseInt(st.nextToken());
			Price[1] = Integer.parseInt(st.nextToken());
			Price[2] = Integer.parseInt(st.nextToken());
			Price[3] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<12;i++) {
				Plan[i] = Integer.parseInt(st.nextToken());
			}
			ans = INF;
			go(0,0);//month,price. 이 재귀함수 리턴값 자체로 정답이지만 가독성 좋게 ans로 정답 출력.
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.println(sb);

	}
}


/*
 * System.out.println("이용권 금액 Price ");
			for(int it=0;it<4;it++) {
				System.out.print(Price[it]+" ");
			}
			System.out.println();
			System.out.println("월별 이용 계획 Plan ");
			for(int it=0;it<12;it++) {
				System.out.print(Plan[it]+" ");
			}
			System.out.println();
 * */