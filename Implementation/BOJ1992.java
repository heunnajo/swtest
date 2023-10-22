//쿼드트리
import java.util.*;
import java.io.*;
public class BOJ1992 {
	static int N;
	static char[][] Map;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Map = new char[N][N];
		
		for(int i=0;i<N;i++) {
			Map[i] = br.readLine().toCharArray();
		}
		sb = new StringBuilder();
		//sb.append("(");
		go(0,0,N-1,N-1);
		//sb.append(")");
		
		System.out.println(sb.toString());
	}
	static void go(int sx,int sy,int ex,int ey) {
		//1.종료
		if(isZeroOrOne(sx,sy,ex,ey) != -1) {
			sb.append(isZeroOrOne(sx,sy,ex,ey));
			//System.out.println("============");
			//System.out.println(sb.toString());
			//System.out.println("============");
			return;
		}
		//2.현재 경우 처리 & 다음 경우 재귀
		int N = Math.abs(ex-sx)+1;
		//System.out.println("현재 정사각형:"+sx+","+sy+", 크기:"+N);
		sb.append("(");
		//System.out.println("왼쪽위: "+sx+","+sy+","+(sx+(N/2)-1)+","+(sy+(N/2)-1));
		//System.out.println("오른쪽위: "+sx+","+(sy+N/2)+","+(sx+(N/2)-1)+","+(sy+N-1));
		//System.out.println("왼쪽아래: "+(sx+N/2)+","+sy+","+(sx+N-1)+","+(sy+(N/2)-1));
		//System.out.println("오른쪽아래: "+(sx+N/2)+","+(sy+N/2)+","+(sx+N-1)+","+(sy+N-1));
		go(sx,sy,sx+(N/2)-1,sy+(N/2)-1);
		go(sx,sy+N/2,sx+(N/2)-1,sy+N-1);
		go(sx+N/2,sy,sx+N-1,sy+(N/2)-1);
		go(sx+N/2,sy+N/2,sx+N-1,sy+N-1);
		sb.append(")");
	}
	static int isZeroOrOne(int sx,int sy,int ex,int ey) {
		int N = Math.abs(ex-sx);
		char x;
		if(Map[sx][sy] == '1') x = '1';
		else x = '0';
		
		for(int i=sx;i<=ex;i++) {
			for(int j=sy;j<=ey;j++) {
				if(Map[i][j] != x) {
					return -1;
				}
			}
		}
		return x-'0';
	}
	

}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<N;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}