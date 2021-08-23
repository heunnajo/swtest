package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class Snake_Correction_pureCode {
	static int N,K,L,Map[][];
	static class Info{
		int x;
		String dir;
		Info(int x,String dir){
			this.x = x;
			this.dir = dir;
		}
	}
	static class Point{
		int x,y;
		Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static List<Info> infoList;
	static char[] dirs;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static Deque<Point> q = new LinkedList<>();
	static int solve() {
		int t = 0;
		int nx =0,ny =0;//뱀의 초기 위치!
		Map[nx][ny] = 1; q.add(new Point(0,0));
		int d = 0;//초기 방향은 오른쪽방향!
		while(true) {
			t++; 
			nx += dx[d];
			ny += dy[d];
			//t를 먼저 증가한 후이기 때문에 현재  1초 후 이동한 위치이다!그러므로 현재의 t를 리턴하면 된다!
//			if(Map[nx][ny] == 1 ||check(nx,ny)) return t;//배열범위초과 오류 뜬다!!
			if(check(nx,ny) || Map[nx][ny] == 1) return t;//t를 먼저 증가한 후이기 때문에 현재  1초 후 이동한 위치이다!그러므로 현재의 t를 리턴하면 된다!
			
			//뱀 위치 정보를 저장 : 뱀이 사과를 만나는 만큼 몸의 길이가 길어지고,
			//계속 사과를 만나다가 사과 아닌 칸 만나면 길이 유지한 채 꼬리칸 0으로 해줘야하기 때문에
			//증가하기 시작한 칸의 위치를 기억해야하기 때문이다.
			//가장 처음 추가한 뱀의 위치 좌표를 0으로 마킹하고, 큐에서 삭제함으로써 길이를 유지한채 뱀 정보 저장할 수 있다!!!
			q.addFirst(new Point(nx,ny));//머리 위치.
			
			if(Map[nx][ny] == 2) {
				Map[nx][ny] = 1;
			} else {
				Map[nx][ny] = 1;
				Map[q.peekLast().x][q.peekLast().y] = 0;
				q.pollLast();
			}
			//방향 전환. 컬렉션 크기가 양수이면 0번째 조회, 현재 시간 t와 0번째 요소의 x와 같으면 방향 전환하고, 그 후에 0번째 요소 삭제!!!
			if(infoList.size()>0) {
				Info cur = infoList.get(0);
				if(t == cur.x) {
					if(cur.dir.equals("L")) {
						d = (d+3)%4;
					} else {
						d = (d+1)%4;
					}
					infoList.remove(0);
				}
			}
			/* 방향 전환 - 정답 
			if(dirs[t]=='L') {
				d = (d+3) % 4;
			} else if(dirs[t] == 'D') {
				d = (d+1) %4;
			}
			*/
		}
		
	}
	static boolean check(int x,int y) {
		return x<0 || x>N-1 || y<0 || y>N-1;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		Map = new int [N][N];
		infoList = new LinkedList<>();//초기화해주고.
		
		String[] input;
		for(int i=0;i<K;i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0])-1;
			int y = Integer.parseInt(input[1])-1;
			Map[x][y] = 2;//사과  위치를 2로 마킹!
		}
		L = Integer.parseInt(br.readLine());
		dirs = new char[10001];
		for(int i=0;i<L;i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			String dir = input[1];
			
			infoList.add(new Info(x,dir));
		}
		System.out.println(solve());
		br.close();
	}

}
//입력제대로 됐는지, 사과 위치 제대로 마킹됐는지 확인.
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}
//for(int i=0;i<infoList.size();i++) {
//	Info cur = infoList.get(i);
//	System.out.println("x: "+cur.x+"dir: "+cur.dir);
//}