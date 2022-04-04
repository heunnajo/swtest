//BOJ #21608 상어초등학교
package ss;
import java.util.*;
import java.io.*;

public class SharkElementarySchool {
	static int N;
	static HashMap<Integer,int[]> info;//입력. 학생별 좋아하는 학생들
	static HashMap<Integer,Point> classroom;
	static int[][] finalMap,nearEmptySeat;
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;this.y = y;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		info = new HashMap<>();
		classroom = new HashMap<>();
		finalMap = new int[N][N];
		
		fillNearEmptySeat();
		
		for(int i=1;i<=N*N;i++) {
			st = new StringTokenizer(br.readLine());
			//좋아하는 친구 정보 : map에 저장~!
			int[] flist = new int[4];
			int num = Integer.parseInt(st.nextToken());
			flist[0] = Integer.parseInt(st.nextToken());
			flist[1] = Integer.parseInt(st.nextToken());
			flist[2] = Integer.parseInt(st.nextToken());
			flist[3] = Integer.parseInt(st.nextToken());
			
			info.put(num, flist);
			
			findSeat(num,flist);
		}
		
		//정답 도출
		int ans = 0;
		for(int me : classroom.keySet()) {
			int cnt = 0;
			int[] friendList = info.get(me);//O
			
			Point myPos = classroom.get(me);
			for(int j=0;j<4;j++) {
				Point yourPos = classroom.get(friendList[j]);
				if(isNearby(myPos,yourPos)) cnt++;
			}
			
			if(cnt==1) ans+= 1;
			else if(cnt==2) ans+= 10;
			else if(cnt==3) ans+= 100;
			else if(cnt==4) ans+= 1000;
		}
		System.out.println(ans);
	}
	static void findSeat(int num,int[] flist) {
		//num학생의 자리를 착석시킨다!
		//i번 학생의 좋아하는 학생이 착석해있는지 확인
		int[][] friendScore = new int[N][N];//각 학생마다 좋아하는 학생이 많은 위치에 점수 매긴다!
		for(int i=0;i<4;i++) {
			if(classroom.containsKey(flist[i])) {
				Point pos = classroom.get(flist[i]);
				
				for(int d=0;d<4;d++) {
					int nx = pos.x +dx[d], ny = pos.y+dy[d];
					
					if(isOut(nx,ny)) continue;
					if(finalMap[nx][ny] != 0) continue;//이미 착석해있는 자리.
					
					friendScore[nx][ny] += 1;
				}
			}
		}
		//map에서 값이 가장 큰 곳에 착석!
		int maxFriends = -1;
		int maxEmpty = -1;
		Point maxPos = new Point(N/2,N/2);
		
		for(int row=0;row<N;row++) {
			for(int col=0;col<N;col++) {
				if(finalMap[row][col] != 0) continue;//0이 아니라면(이미 착석해있다면) 컨티뉴 처리
				if(friendScore[row][col]>maxFriends) {
					maxPos.x = row; maxPos.y = col;
					maxFriends = friendScore[row][col];
					maxEmpty = nearEmptySeat[row][col];//친구 점수가 동일할 때, 조건판단을 위해 갱신해야함.
				} else if(friendScore[row][col] == maxFriends && maxEmpty < nearEmptySeat[row][col]) {
					maxEmpty = nearEmptySeat[row][col];
					maxPos.x = row; maxPos.y = col;
				}
			}
		}
		//i번 학생 착석
		classroom.put(num, maxPos);
		finalMap[maxPos.x][maxPos.y] = num;
		
		//빈칸 갯수 정보 전역 변수 nearEmptySeat 갱신!
		for(int d=0;d<4;d++) {
			int nx = maxPos.x+dx[d];
			int ny = maxPos.y+dy[d];
			
			if(isOut(nx,ny) || finalMap[nx][ny] != 0) continue;//finalMap에 누가 앉아있든 없든 빈칸갯수 감소되야하는 거 아님? 고로 ||후의 것은 없어도 되는 조건 아닌가:없어도 정답처리됨.
			
			nearEmptySeat[nx][ny]--;
		}
	}
	static void fillNearEmptySeat() {
		nearEmptySeat = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int cnt = 4;
				if(i==0 || i==N-1) cnt--;
				if(j==0 || j==N-1) cnt--;
				nearEmptySeat[i][j] = cnt;
			}
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	static boolean isNearby(Point a,Point b) {
		return Math.abs(a.x-b.x)+Math.abs(a.y-b.y) == 1;
	}
}