package ss;
import java.util.*;
import java.io.*;
public class Teleport3 {
	static int ans,sx,sy,ex,ey,path[][];
	static final int INF = 987654321;
	static class Info{
		int x,y,t;
		Info(int x,int y,int t){
			this.x = x; this.y = y; this.t = t;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[] a;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		ex = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());
		
		path = new int[6][4];
		a = new int[7];
		for(int i=0;i<7;i++) a[i] = i;
		for(int i=0;i<6;i+=2) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			path[i][0] = x1; path[i][1] = y1; path[i][2] = x2; path[i][3] = y2;
			path[i+1][0] = x2; path[i+1][1] = y2; path[i+1][2] = x1; path[i+1][3] = y1;
		}
		do {
			//현재 순열로 전체 이동 시간 구한다!
			int nx = sx, ny = sy;
			for(int i=0;i<7;i++) {
				int dist = cal(nx,ny,path[i][0],path[i][1]);
				if(path[i][2] == ex && path[i][3] == ey) break;
				
				dist+=10;
				nx = path[i][2];ny = path[i][3];
			}
		}while(next_permutation());
	}
	static int cal(int x,int y,int dx,int dy) {
		return Math.abs(x-dx)+Math.abs(y-dy);
	}
	static boolean next_permutation() {
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1;
        }
        if (i <= 0) return false;

        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }

        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }
}
