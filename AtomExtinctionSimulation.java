package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.LinkedList;
public class AtomExtinctionSimulation {
	static int T,N,ans;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class Atom{
		int x,y,dir,energy;
		boolean alive;
		Atom(int x,int y,int dir,int energy,boolean alivee){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
			this.alive = alive;
		}
		Atom(int x,int y,int dir,int energy){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}
		Atom(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static Atom[] Atoms;
	static HashMap<Atom,LinkedList<Atom>> map;
	static void extinction() {
		while(true) {
			//N개의 원자들 이동하는 반복문
			for(int i=0;i<N;i++) {
				Atom cur = Atoms[i];
				int cx,cy,cd,ce;
				cx = cur.x; cy = cur.y; cd = cur.dir;ce = cur.energy;
				cx += cx+dx[cd];
				cy += cy+dy[cd];
				cur.x = cx; cur.y = cy;
				Atom a = new Atom(cx,cy,cd,ce);
				if(map.get(a) == null) {
					LinkedList<Atom> list = new LinkedList<>();
					list.add(a);
					map.put(a, list);
				} else {map.get(a).add(a);}
			}
			//N개의 원자들 위치 비교하는 반복문:위치 비교해서 위치 같은 것들의 에너지 다 합산해야함!
//			for(int i=0;i<N-1;i++) {
//				Atom cur = Atoms[i];
//				if(cur.x == Atoms[i+1].x && cur.y == Atoms[i+1].y) {
//					ans += cur.energy;
//					ans += Atoms[i+1].energy;
//				}
//			}
			
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			
			Atoms = new Atom[N];
			map = new HashMap<>();
			int x,y,dir,energy;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken());
				energy = Integer.parseInt(st.nextToken());
				Atoms[i] = new Atom(x,y,dir,energy,true);
			}
			ans = 0;
			extinction();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}

}