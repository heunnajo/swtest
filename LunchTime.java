package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
public class LunchTime {
	
	static int N,T,Map[][];
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static Point[] People;
	static Point[] Stairs;
	static int pCnt;//총 사람수를 저장.
	static int sCnt;
	static Queue<Point> s1 = new LinkedList<>();
	static Queue<Point> s1w = new LinkedList<>();
	static Queue<Point> s2 = new LinkedList<>();
	static Queue<Point> s2w = new LinkedList<>();
	static int solve(int index,int time) {//solve는 index번째 사람이 계단 이동하는데 걸린 시간을 더해간다? 아님. 이상함. 시간의 차원이 필요하다.
		//시간이 흐름에 따라 처리해야할 필요가 있지 않을까...
		//정답 찾은 경우: 그때의 최소시간을 리턴
		if(index == pCnt) {return time;}
		
		//현재 index번째 사람의 계단 선택, 계단 선택하고 그 때까지의 시간을 매개변수로 넘긴다. 
		//1.계단 입구 까지 시간
		int dist1 = toStairs(People[index].x,People[index].y,Stairs[0].x,Stairs[0].y);
		int dist2 = toStairs(People[index].x,People[index].y,Stairs[1].x,Stairs[1].y);
		int minDist = Math.min(dist1, dist2);
		int waitingTime1 = 0;
		int waitingTime2 = 0;
		//최소거리 계단 먼저 2번 계산 
		//2.계단 내려가는 데 걸리는 시간
		//2-1.계단1 선택,바로 내려갈 수 있는 경우
		if(s1.size()<3) {
			
		} else {//2-2.웨이팅이 있는 경우
			waitingTime1 = (s1w.size()+1)/3 * Map[Stairs[0].x][Stairs[0].y] + Map[Stairs[0].x][Stairs[0].y];
		}
		//2-1.계단2 선택,바로 내려갈 수 있는 경우
		if(s2.size()<3) {
			
		} else {//2-2.웨이팅이 있는 경우
			waitingTime2 = (s2w.size()+1)/3 * Map[Stairs[1].x][Stairs[1].y] + Map[Stairs[1].x][Stairs[1].y];
		}
		
		//계단 선택시 고려할 사항들은 다 계산한 듯.계단 선택 완료할 수 있다.and then?
		int stair = 0;
		if(dist1+waitingTime1<dist2+waitingTime2) {//1번계단 선택
			stair = 0;
		} else {
			stair = 1;
		}
		//선택한 계단에 따른 걸린 시간을 매개변수로 넘기면서 걸린 시간들을 계산하는 것은 아닌 것 같음. 
		return 0;
	}
	static int toStairs(int px,int py,int sx,int sy) {
		return Math.abs(px-sx) + Math.abs(py-sy);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			
			Map = new int[N][N];
			People = new Point[10];
			Stairs = new Point[2];
			pCnt = 0; sCnt = 0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
					if(Map[i][j] == 1) {
						People[pCnt++] = new Point(i,j);
					} else if(Map[i][j] >= 2) {
						Stairs[sCnt++] = new Point(i,j);
					}
				}
			}
			
			sb.append("#"+t+" "+solve(0,0)+"\n");//초기 solve 실행시, 0번째 사람, time=0부터 시작!?
		}
		System.out.println(sb);
	}

}
//계단 정보 저장 확인.성공.
//			for(int s=0;s<sCnt;s++) {
//				System.out.printf("%d %d %d",s,Stairs[s].x,Stairs[s].y);
//				System.out.println();
//			}
//사람 정보 저장 확인. 성공.
//for(int p=0;p<pCnt;p++) {
//	System.out.printf("%d %d %d",p,People[p].x,People[p].y);
//	System.out.println();
//}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}
//System.out.println();