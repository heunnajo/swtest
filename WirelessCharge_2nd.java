package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class WirelessCharge_2nd {
	static int tmpMax,ans,M,A,Info[][];
	static int[] dx = {0,-1,0,1,0};
	static int[] dy = {0,0,1,0,-1};
	static class BC{
		int x,y,C,P;
		boolean used;
		BC(int x,int y,int C,int P){
			this.x = x;
			this.y = y;
			this.C = C;
			this.P = P;
		}
		BC(int x,int y){
			this.x = x;
			this.y = y;
		}
		//사용자마다 충전양 합산해야하나?일일이..?ㅇㅂㅇ..어떻게 풀었노....
	}
	static BC[] BCs;
	static BC[] Users;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			Info = new int[2][M];
			BCs = new BC[A];
			Users = new BC[2];
			
			Users[0] = new BC(0,0);
			Users[1] = new BC(9,9);
			
			for(int i=0;i<2;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					Info[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<A;i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken())-1;
				int x = Integer.parseInt(st.nextToken())-1;
				int C = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				BCs[i] = new BC(x,y,C,P);//x y C P순으로 생성자
			}
			ans = 0;//최댓값 갱신을 위해
			solve();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}
	static void solve() {
		ans+=some();//이동하기전 현재 위치(0,0)/(9,9)에서 접속된 BC 있다면 충전양 합산!
		//ans의 존재 목적은 매초t마다 충전합을 합산하는 변수다. 따라서 "M번" 충전합을 저장해야한다.
		for(int t=0;t<M;t++) {
			for(int u=0;u<2;u++) {
				BC user = Users[u];
				user.x += dx[Info[u][t]]; user.y += dy[Info[u][t]];
				//ans += some();//사용자 이동시키고, BC 선택해서 충전.:여기 있으면 2*M번 반복.
			}
			ans += some();//사용자 이동시키고, BC 선택해서 충전.:M번.
		}
	}
	static int some() {
		tmpMax = 0;//현재시간 t에서의 충전양 합.
		permu(0,0);//사용자 번호, 현재까지 충전양->현재의 ans에서 시작?0에서 시작?
		return tmpMax;
	}
	static void permu(int uid,int sum) {//사용자 정보는 어디서 가져오지?
		//재귀 종료 : 사용자 A,B모두 BC 선택한 경우!
		if(uid==2) {
			tmpMax = Math.max(tmpMax, sum);
			System.out.println("tmpMax: "+tmpMax);
			return;
		}
		for(int i=0;i<A;i++) {
			if(!BCs[i].used && inRange(uid,i)) {//하나는 사용자 객체(or인덱스), 하나는 i번째 BC 객체(or인덱스)
				//System.out.println("접속가능한 BC:"+i);
				BCs[i].used = true;
				permu(uid+1,sum+BCs[i].P);
				BCs[i].used = false;
			}
		}
		permu(uid+1,sum);//아무런 BC에 속하지 않을 때?
	}
	static boolean inRange(int uIdx,int BCIdx) {
		BC user = Users[uIdx];
		BC bc = BCs[BCIdx];
		
		int dist = Math.abs(user.x-bc.x)+Math.abs(user.y-bc.y);
		if(dist<=bc.C) return true;
		else return false;
	}

}
//	//BC 정보 확인!완료!
//	for(int i=0;i<A;i++) {
//		BC cur = BCs[i];
//		System.out.print(i+" "+cur.x+","+cur.y+" "+cur.C+" "+cur.P+" ");
//		System.out.println();
//	}

//			for(int i=0;i<2;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(Info[i][j]+" ");
//				}
//				System.out.println();
//			}

////접속가능한 BC 찾아서 충전!:이부분이 "선택" 순열 구현하는 부분이랑 거의 흡사함!!! 
//for(int a=0;a<A;a++) {
//	BC bc = BCs[a];
//	if(inRange(u,a)) {
//		user.P += bc.P;
//	}
//	//some();
//}