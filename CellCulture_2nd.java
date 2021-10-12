package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
public class CellCulture_2nd {
	static int ans,N,M,K,Map[][];//N행 M열, Map:초기 세포 배양된 정보
	static PriorityQueue<Cell> q;
	static class Cell implements Comparable<Cell>{
		int X,off;//X:생명력, off:비활성시점
		Cell(int X,int off){
			this.X = X;
			this.off = off;
		}
		public int compareTo(Cell c) {
			if(this.off == c.off) {
				return c.X-this.X;//=b-a꼴.나이 내림차순!this가 더 작을 때 자리바꾼다는 거니까!
			} else {
				return this.off-c.off;//=a-b꼴.비활성 시점 오름차순!
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			Map = new int[N][M];
			q = new PriorityQueue<>();
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());//입력받을 때 바로 전역변수큐에 저장!?
					if(Map[i][j]>0) {
						q.add(new Cell(Map[i][j],0));
					}
				}
			}
			ans = 0;//K시간 후 살아있는(비활+활) 세포 갯수
			culture();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}
	static void culture() {
		while(true) {
			while(!q.isEmpty()) {
				Cell cur = q.remove();
				int cx = cur.X, coff = cur.off;
				if(coff>=K) ans++;
				//번식
				q.add(new Cell(cx,coff+cx+1));
				
			}
		}
	}
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(Map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
