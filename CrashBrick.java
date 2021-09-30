package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class CrashBrick {
	static int ans,T,N,W,H,Map[][];//W:열,H:행
	static int[] selectedCol;
	static class Pnt{
		int x,y;
		Pnt(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	static Pnt[] startPos;
	static void permu(int index,int selected) {
		//1.불가능한 경우
		if(selected>=W) return;//index<W인데 selected>=W인 경우 : 그냥 selected>=W만으로 판단 가능!?
		if(index>=W) return;
		//2.정답 찾은 경우
		if(selected >= N) {
			findStart();//dfs도 여기서 진행해야할 타이밍인데..
			return;
		}
		//3.현재 index열 선택, 다음 경우 호출
		//3-1.현재 index 선택
		selectedCol[selected] = index;
		permu(index+1,selected+1);
		//3-2.현재 index 선택X
		selectedCol[selected] = 0;//선택안하는 처리
		permu(index+1,selected);
		selectedCol[selected] = 0;//원복
		
	}
	static void findStart() {//permu메서드로 만든 selectedCol배열로 시작위치 저장!
		for(int j=0;j<N;j++) {
			int col = selectedCol[j];int row = -1;
			for(int i=0;i<H;i++) {
				if(Map[i][j]!=0)row = i;break;
			}
			startPos[j] = new Pnt(row,col);
		}
	}
	static void dfs(int x,int y) {//startPost의 N개의 위치에서 시작한다!
		if(x<0 ||x>H-1 || y<0||y>W-1) return;
		for(int i=0;i<Map[x][y];i++) {
			dfs(x-1,y);
			dfs(x+1,y);
			dfs(x,y-1);
			dfs(x,y+1);
			drop();
		}
	}
	static void drop() {//벽돌 사이 빈 공간 있으면 떨어트리는 처리
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			Map = new int[H][W];
			selectedCol = new int[N];
			startPos = new Pnt[N];
			
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					Map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;//최솟값 도출 위해 최댓값으로 셋팅.
			permu(0,0);//index=0,selected=0
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.print(sb);
	}

}
//for(int i=0;i<H;i++) {
//	for(int j=0;j<W;j++) {
//		System.out.print(Map[i][j]+" ");
//	}
//	System.out.println();
//}