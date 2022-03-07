//3.펭귄은 추워요.
import java.io.*;
import java.util.*;
class Main {
	static final int INF = Integer.MAX_VALUE;
	static int largest;
	static class Point{
		int a,b,c,dist;
		Point(int x,int y,int z,int dist){
			this.a = x; this.b = y;this.c = z;this.dist = dist;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] tmp = new int[3];
		tmp[0] = a;tmp[1] = b;tmp[2] = c;
		Arrays.sort(tmp);
		largest = tmp[2];//largest+1까지만 이동하면 됨!!!
		//System.out.print("a: "+a+"b: "+b+"c: "+c);
		int min = bfs(a,b,c);
		int max = max_bfs(a,b,c);
		
		System.out.println(min);
		System.out.println(max);
	}
	static int bfs(int pa,int pb,int pc){//최소 이동횟수를 리턴!
		Queue<Point> q = new LinkedList<>();
		boolean[][][] v = new boolean[largest+2][largest+2][largest+2];
		q.add(new Point(pa,pb,pc,0));
		
		while(!q.isEmpty()){
			Point cur = q.poll();
			
			sortPeng(cur);//펭귄을 위치 증가순으로 정렬
			if(isOut(cur.a,cur.c)) continue;
			//탐색 종료 : 정답 찾은 조건 = 각 펭귄 위치가 연속되어 1차이나는 경우!
			if(check(cur)) return cur.dist;
			//중복 체크 컨티뉴 처리
			if(v[cur.a][cur.b][cur.c]) continue;
			v[cur.a][cur.b][cur.c] = true;
			
			//이동
			//i)a가 이동 : _,b,c 또는 b,_,c 또는 b,c,c+1
			q.add(new Point(cur.b-1,cur.b,cur.c,cur.dist+1));
			q.add(new Point(cur.b,cur.c,cur.c+1,cur.dist+1));
			if(Math.abs(cur.b-cur.c)>1){//=>이미 b,c가 1차이난다면 안함!!!!!!!
				q.add(new Point(cur.b,cur.b+1,cur.c,cur.dist+1));
			}
			
			//ii)c가 이동:_a,b 또는 a,_,b 또는 b,c,c+1
			q.add(new Point(cur.a-1,cur.b,cur.c,cur.dist+1));
			q.add(new Point(cur.b,cur.c,cur.c+1,cur.dist+1));
			if(Math.abs(cur.a-cur.b)>1){//=>이미 a,b가 1차이난다면 안함!!!!!!!
				q.add(new Point(cur.a,cur.a+1,cur.c,cur.dist+1));
			}
			
		}
		return INF;
	}
	static int max_bfs(int pa,int pb,int pc){//최대 이동횟수를 리턴!
		int totalMax = -1;
		Queue<Point> q = new LinkedList<>();
		boolean[][][] v = new boolean[largest*2][largest*2][largest*2];
		q.add(new Point(pa,pb,pc,0));
		
		while(!q.isEmpty()){
			Point cur = q.poll();
			sortPeng(cur);//펭귄을 위치 증가순으로 정렬
			if(isOut(cur.a,cur.c)) continue;
			if(cur.c >= largest+2) return cur.dist;//상한값 도달
			//탐색 종료 : 정답 찾은 조건 = 각 펭귄 위치가 연속되어 1차이나는 경우!
			if(check(cur)){ 
				if(totalMax < cur.dist) totalMax = cur.dist;
			}
			//중복 체크 컨티뉴 처리
			if(v[cur.a][cur.b][cur.c]) continue;
			v[cur.a][cur.b][cur.c] = true;
			
			//이동
			//i)a가 이동 : _,b,c 또는 b,_,c 또는 b,c,c+1
			q.add(new Point(cur.b-1,cur.b,cur.c,cur.dist+1));
			q.add(new Point(cur.b,cur.b+1,cur.c,cur.dist+1));//=>이미 b,c가 1차이난다면 안함!!!!!!!
			q.add(new Point(cur.b,cur.c,cur.c+1,cur.dist+1));
			//ii)c가 이동:_a,b 또는 a,_,b 또는 b,c,c+1
			q.add(new Point(cur.a-1,cur.b,cur.c,cur.dist+1));
			q.add(new Point(cur.a,cur.a+1,cur.c,cur.dist+1));//=>이미 a,b가 1차이난다면 안함!!!!!!!
			q.add(new Point(cur.b,cur.c,cur.c+1,cur.dist+1));
			
		}
		return totalMax;
	}
	static boolean isOut(int a,int c){
		return a<1 || c > 1000000000;
	}
	static void sortPeng(Point cur){
		int a = cur.a; int b = cur.b; int c = cur.c;
		int[] tmp = new int[3];
		tmp[0] = a; tmp[1] = b; tmp[2] = c;
		Arrays.sort(tmp);
	}
	static boolean check(Point cur){
		int a = cur.a; int b = cur.b; int c = cur.c;
		if(a == b-1 && b == c-1) return true;
		else return false;
	}
}