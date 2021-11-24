import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class RockScissorsPaper {
	static int N,K,Map[][],round[][];//win과 index는 로컬로!
	static boolean flag,check[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Map = new int[N+1][N+1];
		round = new int[4][21];
		check = new boolean[N+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=2;i<=3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=20;j++) {
				round[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		flag = false;
		perm(1);//지우의 손동작 정한다!1번부터 20번! 21이 디ㅗ면 다 정한 것이고, 그 때 game을 실행!
		if(flag) System.out.println(1);
		else System.out.println(0);
	}
	static void perm(int index) {//개선 가능.오답 풀이 정리할 때 정리하기.
		if(flag)return;//기 진행한 경기에서 이미 flag가 true처리 됐다면 바로 리턴!
		if(index == N+1) {
			game();
			return;
		}
		//현재 순서 정한다!
		for(int i=1;i<=N;i++) {
			if(!check[i]) {
				check[i] = true;
				round[1][index] = i;
				perm(index+1);
				check[i] = false;
				round[1][index] = -1;//굳이 없어도 되지만 형식상~~
			}
		}
	}
	static void game() {
		int index[] = new int[4];
		for(int i=1;i<=3;i++) {
			index[i] = 1;
		}
		int p1 = 1,p2 = 2,np = 3;
		int win[] = new int[4];
		while(true) {
			//종료 조건
			//1.지우(=1)가 K번 이기는 경우  
			np = 6-(p1+p2);//다음 선수 먼조 조회해놓는다!
			if(win[1] == K) {
				flag = true; return;
			}
			if(win[2] == K || win[3] == K) return;//2.다른 선수들이 K번 이기는 경우:else 생략?!
			if(index[1] == N+1) return;
			if(index[2] == 21 || index[3] == 21) return;//20번 경기 다 한 경우
			//본격  현재 경기!
			int cmd1 = round[p1][index[p1]];
			int cmd2 = round[p2][index[p2]];
			int winner = checkWinner(cmd1,cmd2,p1,p2);
			win[winner]++; index[p1]++; index[p2]++;//이긴자의 승수 증가, 선수1,선수2의 경기 수 증가!(손동작 정보 조회 위히!)
			//np 코드가 여기 와도 될 것 같은디!
			p1 = winner;p2 = np;
		}
	}
	static int checkWinner(int cmd1,int cmd2,int p1,int p2) {
		if(Map[cmd1][cmd2] == 2) {
			return p1;
		}
		else if(Map[cmd1][cmd2] == 1) {
			return Math.max(p1, p2);
		}
		else return p2;
	}
}