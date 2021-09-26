package ss;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Easy2048_Solution {
	static int N, result=0;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(result<map[i][j]) {
					result = map[i][j];
				}
			}
		}
		start(0, map);
		System.out.println(result);
	}
	private static int[][] transform(int dir,int[][] map) {
		
		int[][] tmpMap = new int[N][N];
		int lastNum,count;
		if(dir == 0) {
			for(int i=0;i<N;i++) { // 상
				lastNum=0;count=0;
				for(int j=0;j<N;j++) {
					if(map[j][i]==0) {
						continue;
					}
					if(lastNum==map[j][i]) {
						if(result==lastNum) {
							result*=2;
						}
						tmpMap[count-1][i]*=2;
						lastNum=0;
					}else {
						tmpMap[count++][i]=map[j][i];
						lastNum=map[j][i];
					}
				}
			}
		}
		else if(dir==1) {
			for(int i=0;i<N;i++) { //하
				lastNum=0;count=N-1;
				for(int j=N-1;j>=0;j--) {
					if(map[j][i]==0) {
						continue;
					}
					if(lastNum==map[j][i]) {
						if(result==lastNum) {
							result*=2;
						}
						tmpMap[count+1][i]*=2;
						lastNum=0;
					}else {
						tmpMap[count--][i]=map[j][i];
						lastNum=map[j][i];
					}
				}
			}
		} else if(dir==2) {
			for(int i=0;i<N;i++) { //좌
				lastNum=0;count=0;
				for(int j=0;j<N;j++) {
					if(map[i][j]==0) {
						continue;
					}
					if(lastNum==map[i][j]) {
						if(result==lastNum) {
							result*=2;
						}
						tmpMap[i][count-1]*=2;
						lastNum=0;
					}else {
						tmpMap[i][count++]=map[i][j];
						lastNum=map[i][j];
					}
				}
			}
		} else {
			for(int i=0;i<N;i++) { //우
				lastNum=0;count=N-1;
				for(int j=N-1;j>=0;j--) {
					if(map[i][j]==0) {
						continue;
					}
					if(lastNum==map[i][j]) {
						if(result==lastNum) {
							result*=2;
						}
						tmpMap[i][count+1]*=2;
						lastNum=0;
					}else {
						tmpMap[i][count--]=map[i][j];
						lastNum=map[i][j];
					}
				}
			}
		}
		return tmpMap;
	}
	private static void start(int turn, int[][] map) {
		if(turn==5) {
			return;
		}
		for(int dir=0;dir<4;dir++) {
			int[][] next = map;//현재의 2차원 배열
			next = transform(dir,next);
			start(turn+1,next);//현재 방향 선택, 변형한 배열로 재귀 실행
		}
		
	}

}
