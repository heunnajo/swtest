package ss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class JailBreak_Solution {
	static class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	public static String[] Jail;
	public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static int[][] bfs(int x,int y){
    	int n = Jail.length;
    	int m = Jail[0].length();
    	int[][] d = new int[n][m];
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<m;j++) {
    			d[i][j] = -1;//가중치는 0,1로만 존재하고, 문 열 때마다 1씩 증가 
    		}
    	}
    	ArrayDeque<Pair> deque = new ArrayDeque<Pair>();
    	deque.add(new Pair(x,y));
    	d[x][y] = 0;
    	while(!deque.isEmpty()) {
    		Pair p = deque.poll();
    		x = p.x; y = p.y;
    		for(int k=0;k<4;k++) {
    			int nx = x+dx[k], ny = y+dy[k];
    			if(nx<0 || nx>=n || ny<0 || ny >= m) continue;
    			if(d[nx][ny] != -1) continue;//방문했다는 뜻?
    			char c = Jail[nx].charAt(ny);
    			if(c == '*') continue;//벽이면 이동 못하므로 컨티뉴 처리
    			if(c == '#') {//문이면 가중치 1증가시키고 덱의 뒤에 넣는다.
    				d[nx][ny] = d[x][y] + 1;
    				deque.addLast(new Pair(nx,ny));
    			} else {//빈칸이면 현재 가중치 그대로 넣고 덱의 앞에 넣는다.
    				d[nx][ny] = d[x][y];
    				deque.addFirst(new Pair(nx,ny));
    			}
    		}
    	}
    	return d;
    }
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int h,w,ans;
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			Jail = new String[h+2];//행을 2증가
			for(int i=1;i<=h;i++) {
				Jail[i] = br.readLine();
				Jail[i] = "."+Jail[i]+".";
			}
			h+=2; w+=2;
			Jail[0] = Jail[h-1] = "";
			for(int j=0;j<w;j++) {
				Jail[0] += ".";
				Jail[h-1] += ".";
			}
			int[][] d0 = bfs(0,0);//?
			int x1,y1,x2,y2;//죄수들 위치 저장!
			x1 = y1 = x2 = y2 = -1;
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					if(Jail[i].charAt(j) == '$') {
						if(x1 == -1) {//죄수1
							x1 = i; y1 = j;
						} else {//x1!=-1이라는 건 죄수1의 좌표 저장했다는 의미.죄수2
							x2 = i; y2 = j;
						}
					}
				}
			}
			int[][] d1 = bfs(x1,y1);
			int[][] d2 = bfs(x2,y2);
			ans = h*w;
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					char c = Jail[i].charAt(j);
					if(c == '*') continue;
					if(d0[i][j]==-1 || d1[i][j]==-1||d2[i][j]==-1) continue;
					int cur = d0[i][j] + d1[i][j] + d2[i][j];
					if(c == '#') cur -= 2;
					if(ans > cur) ans = cur;
				}
			}
			System.out.println(ans);
  		}
		
	}

}

//			for(int i=0;i<h;i++) {
//				for(int j=0;j<w;j++) {
//					System.out.print(Jail[i].charAt(j)+" ");
//				}
//				System.out.println();
//			}