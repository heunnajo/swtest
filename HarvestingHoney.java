package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class HarvestingHoney {
	static int N,M,C,T,ans;
	static int[][] Map;
	static void solve() {
		boolean[][] visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				//1.일꾼1 선택
				int sx = i,sy = j,tmp_sum=0;
				LinkedList<Integer> hList1 = new LinkedList<>();
				int endY=-1;//일꾼1의 window가 끝나는 열.endY초깃값 처리 잘 해주기.
				int sum1=0;//일꾼1의 최댓값 갖는 합.
				for(int y=sy;y+M-1<=N-1;y++) {
					if(tmp_sum+Map[sx][y]<=C && !visited[sx][y]) {
						tmp_sum += Map[sx][y];
						visited[sx][y] = true;
						hList1.add(Map[sx][y]);
						endY = y;//합산되면, 그때의 y를 window 끝나는 열로 저장한다!
					}
				}
				sum1 = sum1<tmp_sum?tmp_sum:sum1;//그때의 컬렉션 또한 최대가 되는 컬렉션으로 갱신해야함.
				
				//2.일꾼2 선택
				int sx2 = sx,sy2 = endY+1,tmp_sum2=0;
				LinkedList<Integer> hList2 = new LinkedList<>();
				int endY2=-1;
				int sum2=0;
				if(endY+1+M-1<=N-1) {
					for(int y=endY+1;y+M-1<=N-1;y++) {
						if(tmp_sum2+Map[sx2][y]<=C) {
							tmp_sum2 += Map[sx2][y];
							visited[sx2][y] = true;
							hList2.add(Map[sx2][y]);
							endY2 = y;
						}
					}
				} else break;//다음행으로 넘어가야함.
				sum2 = sum2<tmp_sum2?tmp_sum2:sum2;
				
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			Map = new int[N][N];
			ans = 0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			solve();
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.println(sb);
		// TODO Auto-generated method stub

	}

}
//for(int i=0;i<N;i++) {
//	for(int j=0;j<N;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}
