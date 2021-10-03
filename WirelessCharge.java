package ss;
import java.io.*;
import java.util.*;
public class WirelessCharge {
	static int ans,T,M,A,tmpSum;
	static int[] dx = {0,-1,0,1,-1};
	static int[] dy = {0,0,1,0,0};
//	static ArrayList<Integer>[][] moveInfo;
	static int[][] moveInfo;
	static class pnt{
		int x,y;
		pnt(int x,int y){
			this.x = x;this.y = y;
		}
	}
	static LinkedList<pnt> Users;
	static class BC{
		int y,x,c,p,used;
		BC(int y,int x,int c,int p,int used){
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
			this.used = used;
		}
	}
	static LinkedList<BC> BCs;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			//moveInfo = new ArrayList[2][M];
//			for(int i=0;i<2;i++) {
//				for(int j=0;j<M;j++) {
//					moveInfo[i][j] = new ArrayList<>();
//				}
//			}
			BCs = new LinkedList<>();
			moveInfo = new int[2][M];
			Users = new LinkedList<>();
			Users.add(new pnt(0,0));
			Users.add(new pnt(9,9));
			
			for(int i=0;i<2;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					moveInfo[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0;i<A;i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				BCs.add(new BC(y,x,c,p,0));
			}
			solve();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}
	static void solve() {
		count();//현시점 (AX,AY), (BX,BY)에 따른 충전합 최댓값을 ans에 저장한다.
		for(int time=0;time<M;time++) {//사용자1 사용자2 초기위치에서 moveInfo[time] 이동 정보로 이동.
			for(int i=0;i<2;i++) {
				Users.get(i).x += dx[moveInfo[i][time]];
				Users.get(i).y += dy[moveInfo[i][time]];
			}
			ans += count();
		}
	}
	static int count() {
//		ans = 0;
		tmpSum = 0;
		go(0,0);
		return tmpSum;
	}
	static int go(int index,int sum) {
		if(index==2) {tmpSum =  Math.max(ans, sum); return tmpSum;}
		
		for(int a=0;a<A;a++) {
			BC cur = BCs.get(a);
			if(cur.used == 0&& dist(cur,index)) {
				cur.used = 1;
				go(index+1,sum+cur.p);
				cur.used = 0;
			}
		}
	}
	static boolean dist(BC curBC,int user) {
		pnt curU = Users.get(user);
		int d = Math.abs(curU.x-curBC.y) + Math.abs(curU.y-curBC.x);
		if(d<=curBC.c) return true;
		else return false;
	}
	
}
//			for(int i=0;i<2;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(moveInfo[i][j]+" ");
//				}
//				System.out.println();
//			}

//			for(int i = 0;i<A;i++) {
//				BC b = BCs.get(i);
//				System.out.println(b.y+" "+b.x+" "+b.c+" "+b.p+" ");
//			}
//			System.out.println();