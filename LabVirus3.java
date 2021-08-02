import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class LabVirus3 {
	static int N,M,Vcnt,Map[][];
	static int ans = Integer.MAX_VALUE;//최솟값을 구하기 위한 최댓값을 저장!
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Virus{
		int x,y,d;
		Virus(int x,int y,int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	static Virus[] Vlist = new Virus[10];//최대 10개.
	static int OneCnt(int subset) {//10101. subset을 오른쪽으로 한칸씩 시프트하고 1과 앤드연산해서 결과가 1이면 cnt++
		int cnt = 0;
		while(subset>0){
			if((subset & 1) == 1) {//오른쪽으로 1칸씩 시프트연산해서 1과 AND연산한 값이 0이 아니면(1이면) 갯수 증가!
				cnt++;
			}
			subset = subset>>1;
			
		}
		return cnt;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		Map = new int[N][N];
		Vcnt = 0;
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
				if(Map[i][j]==2) {
					Vlist[Vcnt] = new Virus(i,j,0);
					Vcnt++;
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int subset=0;subset<(1<<Vcnt);subset++) {//N은 바이러스 갯수, 모든 부분집합 구한다!
			if(OneCnt(subset) == M) {//부분집합 1의 갯수가 M개인 부분집합에 대해서만.
				//1.자료구조 생성.
				Queue<Virus> q = new LinkedList<Virus>();//바이러스 완전탐색시작!=>부분집합마다 BFS 새로 하기 때문에 큐, 방문체크배열 초기화 또는 새로 생성 필요.
				boolean[][] visited = new boolean[N][N];
				int dist = 0;//현재 부분집합에 대해서 바이러스 전체 확산 소요시간.
				//2.몇변째 바이러스가 있는지 비트연산으로 검사.
				for(int i=0;i<Vcnt;i++) {//n번째에 1이 있는지 검사 :AND
					if((subset&(1<<i)) != 0) {//i는 부분집합,(1<<j) = 2^j = 2^0~2^4 = 0,1,2,3,4번째 비트가 1인 값과 앤드 연산!
						Virus vir = Vlist[i];
						q.add(vir);
						visited[vir.x][vir.y] = true;
					}
				}
				//3.BFS 
				while(!q.isEmpty()) {//바이러스 확산. 완탐.
					Virus cur = q.remove();
					if(Map[cur.x][cur.y] != 2) {
						dist = Math.max(dist, cur.d);
					}
					for(int d=0;d<4;d++) {
						int nx = cur.x+dx[d];
						int ny = cur.y+dy[d];
						
						if(nx<0||nx>N-1 || ny<0 || ny>N-1) continue;
						if(visited[nx][ny] || Map[nx][ny] == 1) continue;
						q.add(new Virus(nx,ny,cur.d+1));
						visited[nx][ny] = true;
					}
				}
				//4.완탐 검사, 최종 정답으로 최솟값 비교 갱신. => 완탐일 경우에만!!(불완탐일 경우는ㄴ -1이고, 이경운는 제외하고 최솟값을 저장!)
				boolean flag = true;
				for(int r=0;r<N;r++) {
					for(int c=0;c<N;c++) {
						if(Map[r][c] == 0 && visited[r][c]==false) {
							flag = false;
						}
					}
				}
				if(flag) ans = Math.min(dist, ans);
				
			}//바이러스 M개인 부분집합.
		}//subset-for문.
		if(ans == Integer.MAX_VALUE) {ans = -1;}
		System.out.println(ans);
		
		br.close();
	}//main

}