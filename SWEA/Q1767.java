//프로세서 연결하기
package swea;
import java.util.*;
import java.io.*;
public class SWEA1767_2nd {
	static int N,Reached,LineLengthSum;
	static int[][] Cell;
	static class Core{
		int x,y,dist;
	
		Core(int x,int y,int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	static ArrayList<Core> corePos;
	static int coreCnt;
	static int[] distSet;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		
		for(int t=1;t<=tc;t++) {
			//초기화
			N = Integer.parseInt(br.readLine().trim());
			Cell = new int[N][N];
			corePos = new ArrayList<>();
			//Reached = 0;LineLengthSum = 0;
			Reached = Integer.MIN_VALUE;LineLengthSum = Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				String[] input = br.readLine().split(" ");
				for(int j=0;j<N;j++) {
					Cell[i][j] = Integer.parseInt(input[j]);
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(Cell[i][j] == 1) {
						if(i == 0 || i == N-1 || j == 0 || j == N-1) continue;
						corePos.add(new Core(i,j,0));
					}
				}
			}
			coreCnt = corePos.size();
			//여기까지 입력받은 것을 저장!
			go(0,0,0);
			
			sb.append("#").append(t).append(" ").append(LineLengthSum).append("\n");//distSet 거리합 합산
		}
		System.out.print(sb.toString());
		
	}
	static void go(int idx,int curReached,int curLineLengthSum) {
		//1.종료 조건
		if(idx == coreCnt) {
			if(Reached < curReached) {//현재 도달 갯수가 더 많을 때, 최솟값으로!
				Reached = curReached;
				LineLengthSum = curLineLengthSum;
			} else if(Reached == curReached) {
				if(curLineLengthSum < LineLengthSum) {
					LineLengthSum = curLineLengthSum;
				}
			}
			return;
		}
		//2.현재 경우 처리
		int x = corePos.get(idx).x;
		int y = corePos.get(idx).y;
		
		for(int d=0;d<4;d++) {
			//int nx = x+dx[d], ny = y+dy[d];
			int nx = x, ny = y, count = 0;
			
			while(true) {
				nx += dx[d]; ny +=dy[d];
				if(isOut(nx,ny)) break;
				
				if(Cell[nx][ny] == 1) {
					count = 0; break;
				}
				count++;
			}
			//count만큼 방문 체크 처리
			int fill_x = x, fill_y = y;
			
			for(int i=0;i<count;i++) {
				fill_x += dx[d]; fill_y +=dy[d];
				Cell[fill_x][fill_y] = 1;//정답은 순서가 바꼈음. 이유는 Core 그 자체로 입력값이 1이기 때문인듯.
			}
			//3.다음 경우 처리
			if(count == 0) {
				go(idx+1,curReached,curLineLengthSum);
			} else {
				//System.out.println("현재 Core의 전선 길이: "+count);
				go(idx+1,curReached+1,curLineLengthSum+count);
				//원복
				fill_x = x; fill_y = y;
				for(int i=0;i<count;i++) {
					fill_x += dx[d]; fill_y +=dy[d];
					Cell[fill_x][fill_y] = 0;
				}
			}
			
		}
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=N;
	}
}
//Core 제대로 저장 됐는지 확인:확인완료
//for(int i=0;i<coreCount;i++) {
//	System.out.println("("+corePos.get(i).x+","+corePos.get(i).y);
//}

//System.out.print(sb.toString());
//System.out.println();
//for(int i=0;i<coreCount;i++) {
//	System.out.println(distSet[i]);
//}

