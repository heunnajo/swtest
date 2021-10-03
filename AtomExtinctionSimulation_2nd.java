package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.HashMap;
public class AtomExtinctionSimulation_2nd {
	static int E,T,N;
    static int UP = 0;
    static int DOWN = 1;
    static int LEFT = 2;
    static int RIGHT = 3;
	static class Atom{
		int x,y,dir,K;
		Atom(int x,int y,int dir,int K){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.K = K;
		}
        void move(){
            if(dir == UP) this.y++;
            else if(dir == DOWN) this.y--;
            else if(dir == LEFT) this.x--;
            else if(dir == RIGHT) this.x++;
        }
	}
	static LinkedList<Atom> Atoms;
	static HashMap<Integer,LinkedList<Atom>> map;
	static LinkedList<Atom> delList;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	//원자 이동, 원자 충돌 처리, 원자 삭제
	static void solve() {
		while(true) {
			//매 이동마다 초기화해줘야함.
			map = new HashMap<>();
			delList = new LinkedList<>();
			//1.원자 이동
			for(Atom a:Atoms) {
				if(a.dir==0) a.y++;
				else if(a.dir==1) a.y--;
				else if(a.dir==2) a.x--;
				else if(a.dir==3) a.x++;
				//이동한 위치가 범위 밖이라면!
				if(isOut(a.x,a.y)) {delList.add(a);}
				else {makeMap(a);}
			}
			//2.원자 충돌 처리:map 순회하면서 2개이상 원자들 delList에 추가
			checkCrash();
			//3.원자 삭제:delList에 있는 원자들 Atoms에서 삭제,삭제하는 원자들 에너지 합산
			Atoms.removeAll(delList);
			if(Atoms.size()<=1) return;
		}
		
	}
    static void checkCrash(){
        for(Integer pos:map.keySet()) {
            LinkedList<Atom> list = map.get(pos);
            if(list.size()>=2) {
                delList.addAll(list);
                for(Atom crash:list) {E+=crash.K;}
            }
        }
    }
	static void makeMap(Atom a) {
		int pos = a.x*4001 + a.y;
		if(map.containsKey(pos)) {
			map.get(pos).add(a);
		} else {
			LinkedList<Atom> list = new LinkedList<>();
			list.add(a);
			map.put(pos, list);
		}
	}
	static boolean isOut(int x,int y) {
		return x<-2000 || x>2000 || y<-2000 || y>2000;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			
			Atoms = new LinkedList<>();
			
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())*2;
				int y = Integer.parseInt(st.nextToken())*2;
				int dir = Integer.parseInt(st.nextToken());
				int K = Integer.parseInt(st.nextToken());
				Atoms.add(new Atom(x,y,dir,K));
			}
			E = 0;
			solve();
			sb.append("#"+t+" "+E+"\n");
		}
		System.out.print(sb);
	}
}
