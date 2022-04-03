//BOJ #21608 상어초등학교
package ss;
import java.util.*;
import java.io.*;

public class SharkElementarySchool {
	static int N;
	static int[][] info;
	static HashMap<Integer,Point> classroom;
	static int[][] map;
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
		info = new int[N*N+1][4];
		classroom = new HashMap<>();
		
		
		int idx = -1;
		for(int i=1;i<=N*N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<5;j++) {
				if(j==0) {
					idx = Integer.parseInt(st.nextToken());
				} else {
					info[idx][j-1] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		for(int i=1;i<=N*N;i++) {
			//i번 학생의 좋아하는 학생이 착석해있는지 확인
			int[] list = info[i];
			map = new int[N][N];//초기화 시점..
			for(int j=0;j<4;j++) {
				if(classroom.containsKey(j)) {
					Point pos = classroom.get(j);
					for(int d=0;d<4;d++) {
						int nx = pos.x +dx[d], ny = pos.y+dy[d];
						if(isOut(nx,ny) || map[nx][ny] != 0) continue;
						map[nx][ny] += 1;
					}
				}
			}
			//map에서 값이 가장 큰 곳에 착석!
			int max = -1; Point maxPos = new Point(N/2,N/2);
			for(int row=0;row<N;row++) {
				for(int col=0;col<N;col++) {
					if(map[row][col]>max) {
						maxPos.x = row; maxPos.y = col;
					}
				}
			}
			//i번 학생 착석
			classroom.put(i, maxPos);
			map[maxPos.x][maxPos.y] = i;
		}
		//정답 도출
		int ans = 0;
		for(int i=1;i<=N*N;i++) {
			for(int me : classroom.keySet()) {
				//0.좋아하는 학생 정보 조회
				int cnt = 0;//현재 내가 좋아하는 학생이 몇명이나 인접해있는지!
				int[] friendList = info[me];
				//1.내 위치 조회
				Point myPos = classroom.get(me);
				//2.친구의 위치 정보 조회, 인접해있는지 판단!
				for(int j=0;j<4;j++) {
					Point yourPos = classroom.get(friendList[j]);
					if(isNearby(myPos,yourPos)) cnt++;
				}
				//3.만족도 합산
				if(cnt==1) ans+= 1;
				else if(cnt==2) ans+= 10;
				else if(cnt==3) ans+= 100;
				else if(cnt==4) ans+= 1000;
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


//		for(int i=1;i<=N*N;i++) {
//			System.out.print(i+"번의 좋아하는 친구 : ");
//			for(int j=0;j<4;j++) {
//				System.out.print(info[i][j]+" ");
//			}
//			System.out.println();
//		}