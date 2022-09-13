package ss;
import java.util.*;
import java.io.*;
//단지 번호 붙이기
public class BOJ2667 {
	static int N,num,size;
	static char[][] Map;
	static boolean[][] v;
	static LinkedList<Integer> DanjiSet;
	
	static class Pair {
		int x,y;
		
		Pair(int x,int y){
			this.x = x; this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		Map = new char[N][N];
		v = new boolean[N][N];
		num = 0; size = 0;
		DanjiSet = new LinkedList<>();
		
		String str;
		for(int i=0;i<N;i++) {
			str = br.readLine();
			Map[i] = str.toCharArray();
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(v[i][j]) continue;
				if(Map[i][j] == '1') {
					bfs(i,j);//단지별 크기 저장 : DanjiSet 완성
				}
			}
		}
		Collections.sort(DanjiSet);
		StringBuilder sb = new StringBuilder();
		
//		System.out.println(num);
		sb.append(num).append("\n");
		for(int x:DanjiSet) {
//			System.out.println(x);
			sb.append(x).append("\n");
		}
		System.out.print(sb.toString());
	}
	static void bfs(int sx,int sy) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		v[sx][sy] = true;
		num++;
		size = 0;//크기 초기화~
		size++;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(sx,sy));
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = cur.x+dx[d], ny = cur.y+dy[d];
				if(isOut(nx,ny) || v[nx][ny]) continue;
				if(Map[nx][ny] != '1') continue;//1(집)인 칸만 방문 가능!
				
				size++;
				q.add(new Pair(nx,ny));
				v[nx][ny] = true;
			}
		}
		DanjiSet.add(size);
	}
	static boolean isOut(int x,int y) {
		return x<0 || x>=N || y<0 || y>=N;
	}
	
}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}