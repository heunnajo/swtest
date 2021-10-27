package ss;
import java.io.*;
import java.util.*;
public class FindCross {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		boolean[][] check = new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		ArrayList<Integer> x_idx = new ArrayList<>();
		ArrayList<Integer> y_idx = new ArrayList<>();
		ArrayList<Integer> l_size = new ArrayList<>();
		
		//2차원 입력 배열 순회하면서 상하좌우칸이 *인지 아닌지 확인한다.
		//십자가라면 그 때의 k를 l에 저장한다
		//그리고 그 때의 x,y,l=> i,j,l을 각 배열리스트에 저장!
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j] == '*') {//십자가를 놓으려고 하기 전에, 십자가 중심(i,j)부터 *인지 검사해야함!!!
					int l=0;
					for(int k=1;;k++) {
						if(i-k>=0 && i+k<n && j-k>=0 && j+k<m) {
							if(map[i-k][j]=='*' && map[i+k][j] =='*' && map[i][j-k]=='*'&& map[i][j+k]=='*') {
								l=k;
							} else break;//현재 크기가 k인 십자가에서 조건을 만족하지 않는다면 십자가 크기가 더 커지더라도 조건 만족X
						} else break;//범위 벗어나면 바로 현재 반복문 종료(더 반복할 필요 없음)
					}
					if(l>0) {//십자가를 그릴 수 있다! : 1부터 l크기 만큼 십자가를 놔야쥬.
						//현재 (i,j)에서 십자가 놓을 수 있다면 각 배열리스트에 추가해야한다.
						x_idx.add(i+1);
						y_idx.add(j+1);
						l_size.add(l);
						check[i][j] = true;
						for(int k=1;k<=l;k++) {
							check[i-k][j] = true;
							check[i+k][j] = true;
							check[i][j-k] = true;
							check[i][j+k] = true;
						}
					}
				}
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]=='*' && check[i][j] == false) {
					System.out.println(-1);
					System.exit(0);//바로 프로그램 종료~~
				}
			}
		}
		System.out.println(x_idx.size());
		for(int i=0;i<x_idx.size();i++) {
			int x = x_idx.get(i);
			int y = y_idx.get(i);
			int size = l_size.get(i);
			
			System.out.println(x+" "+y+" "+size);
		}
		
	}

}
