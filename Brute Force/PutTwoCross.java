package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class PutTwoCross {
	static int ans,N,M;
	static char[][] Map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//string으로 한줄씩 입력받아서 char 타입 2차원 배열로 저장한다!
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map = new char[N][M];
		
		for(int i=0;i<N;i++)
			Map[i] = br.readLine().toCharArray();
		
		ans = 0;
		for(int x1=0;x1<N;x1++) {
			for(int y1=0;y1<M;y1++) {
				for(int s1=0;;s1++) {
					if(isOut(x1-s1,y1)||isOut(x1+s1,y1)||isOut(x1,y1-s1)||isOut(x1,y1+s1)) break;
					if(Map[x1-s1][y1] != '#' || Map[x1+s1][y1] !='#') break;
					if(Map[x1][y1-s1] != '#' || Map[x1][y1+s1] !='#') break;
					Map[x1-s1][y1] = '*';Map[x1+s1][y1] = '*';
					Map[x1][y1-s1] = '*';Map[x1][y1+s1] = '*';
					//십자가2 선택하러 간다! 십자가1 선택한 후, 십자가2 선택. 둘의 넓이 구할 때 s1, s2가 필요하다.
					for(int x2=0;x2<N;x2++){
						for(int y2=0;y2<M;y2++) {
							for(int s2=0;;s2++) {
								if(isOut(x2-s2,y2)||isOut(x2+s2,y2)||isOut(x2,y2-s2)||isOut(x2,y2+s2)) break;
								if(Map[x2-s2][y2] != '#' || Map[x2+s2][y2] !='#') break;
								if(Map[x2][y2-s2] != '#' || Map[x2][y2+s2] !='#') break;
								int tmp_sum = (s1*4+1)*(s2*4+1);
								if(ans<tmp_sum) ans = tmp_sum;
							}
						}
					}
				}
				//다 선택하고 난 후 원복처리!
				for(int s1=0;;s1++) {
					if(isOut(x1-s1,y1)||isOut(x1+s1,y1)||isOut(x1,y1-s1)||isOut(x1,y1+s1)) break;
					if(Map[x1-s1][y1] != '*' || Map[x1+s1][y1] !='*') break;
					if(Map[x1][y1-s1] != '*' || Map[x1][y1+s1] !='*') break;
					Map[x1-s1][y1] = '#';Map[x1+s1][y1] = '#';
					Map[x1][y1-s1] = '#';Map[x1][y1+s1] = '#';
				}
			}
		}
		System.out.println(ans);
	}
	static boolean isOut(int x,int y) {
		return x<0 ||x>=N || y<0 || y>=M;
	}
}

//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(Map[i][j]+" ");
//			}
//			System.out.println();
//		}