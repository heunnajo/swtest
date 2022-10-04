package ss;
import java.util.*;
import java.io.*;
//새로운 게임
public class BOJ17780_Solution {
	static int n,k,ans,nx,ny,gnum,qsize,tsize;
	static int[][] map;
	static int[] dx = {0,0,0,-1,1};//1~4:우좌상하
	static int[] dy = {0,1,-1,0,0};
	static ArrayList<P> horse;//말의 정보
	static ArrayList<Integer> temp;
	static Queue<Integer>[][] q;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		init();
		ans = -1;
		for(int turn = 1;turn <= 1000;turn++) {
			for(int now=1;now<=k;now++) {
				nx = horse.get(now).x;//nx,ny :현재 말의 좌표
				ny = horse.get(now).y;
				
				if(q[nx][ny].peek()!=now) continue;//이동하려는 말이 맨 아래에 있지 않은 경우, 다음말로 넘어감!
				
				gnum = check_go(now);//gnum: 말의 이동 방법을 미리 확인
				
				if(gnum == 2) {
					change_dir(now);//change_dir:현재 말의 방향만 바꿔줌.
				} else {
					if(gnum == 1) change_dir(now);//gnum 1:방향이 바뀌고 이동하는 경우
					go(now);//go: gnum이 0 또는 1일 때 말의 이동
					add();//임시 저장한 temp를 이동한 말 위에 쌓아올림
				}
				if(q[nx][ny].size() >= 4) {//종료조건:말이 4개 쌓이는 경우 턴 종료
					ans = turn;
					break;
				}
			}
			if(ans != -1) break;//종료 조건을 통해 결과값 ans가 바뀐 경우 break;
		}
		System.out.println(ans);
	}
	private static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n+2][n+2];
		q = new LinkedList[n+2][n+2];
		
		for(int i=0;i<=n+1;i++) {
			for(int j=0;j<=n+1;j++) {
				map[i][j] = 2;
				q[i][j] = new LinkedList<>();
			}
		}
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=1;j<=n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		horse = new ArrayList<>();
		horse.add(new P(0,0,0));
		
		for(int i=1;i<=k;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int tx = Integer.parseInt(st.nextToken());
			int ty = Integer.parseInt(st.nextToken());
			int tdir = Integer.parseInt(st.nextToken());
			horse.add(new P(tx,ty,tdir));
			q[tx][ty].add(i);
		}
	}
	
	private static int check_go(int now) {//결과값 gcnt(0:그냥 이동, 1:방향 전환 이동, 2:방향만 전환)
		int tdir = horse.get(now).dir;
		int gcnt = 0;
		if(map[nx+dx[tdir]][ny+dy[tdir]] == 2) {
			gcnt++;
			if(map[nx-dx[tdir]][ny-dy[tdir]] == 2) gcnt++;
		}
		return gcnt;
	}
	
	private static void change_dir(int now) {//change_dir:말의 이동방향을 반대로 전환
		int tdir = horse.get(now).dir;
		if(tdir == 1) horse.get(now).dir = 2;
		else if(tdir == 2) horse.get(now).dir = 1;
		else if(tdir == 3) horse.get(now).dir = 4;
		else horse.get(now).dir = 3;
 	}
	
	private static void go(int cur) {//go:말의 이동
		temp = new ArrayList<>();//temp: 말 번호 임시 저장 장소
		qsize = q[nx][ny].size();//qsize : 현재 좌표에서의 말 갯수
		for(int s = 0;s < qsize;s++) {
			temp.add(q[nx][ny].poll());//이동하려는 말의 현재 좌표에 있는 말들을 임시 ArrayList에 저장
		}
		//말의 좌표 이동
		nx = horse.get(cur).x = horse.get(cur).x+dx[horse.get(cur).dir];
		ny = horse.get(cur).y = horse.get(cur).y+dy[horse.get(cur).dir];
	}
	
	private static void add() {//add:임시 저장한 말 번호들을 2차원 배열 Queue에 추가
		tsize = temp.size();//tsize : 임시 ArrayList에 들어있는 말의 갯수
		if(map[nx][ny] == 0) {
			for(int s=0;s<tsize;s++) {
				q[nx][ny].add(temp.get(s));
				horse.get(temp.get(s)).x = nx;
				horse.get(temp.get(s)).y = ny;
			}
		} else {//이동하려는 위치가 1(빨간색)인 경우
			for(int s=tsize-1;s>=0;s--) {
				q[nx][ny].add(temp.get(s));
				horse.get(temp.get(s)).x = nx;
                horse.get(temp.get(s)).y = ny;
			}
		}
	}
	
	static class P{
		int x,y,dir;
		public P() {}
		public P(int x,int y,int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
