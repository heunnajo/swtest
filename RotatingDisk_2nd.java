package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class RotatingDisk_2nd {
	static int N,M,T,Map[][],x[],d[],k[];
	static class SumCnt{
		int sum,cnt;
		SumCnt(int sum,int cnt){
			this.sum = sum;
			this.cnt = cnt;
		}
	}
	static SumCnt countSum() {
		int sum = 0;
		int cnt = 0;
		
		for(int i=1;i<=N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] != 0) {
					sum += Map[i][j];
					cnt++;
				}
			}
		}
		SumCnt sc = new SumCnt(sum,cnt);	
		return sc;
	}
	static void rotate(int t) {
		//1.회전:d[t]방향으로 k[t]만큼 회전 반복한다!
		if(d[t] == 1) {//반시계 
			for(int kk=0;kk<k[t];kk++) {//k번 반복!
				for(int i=x[t];i<=N;i+=x[t]) {
					int tmp = Map[i][0];
					for(int j=0;j<M-1;j++) {
						Map[i][j] = Map[i][j+1];
					}
					Map[i][M-1] = tmp;
				}
			}
		} else {//시계:감소 반복문!
			for(int kk=0;kk<k[t];kk++) {
				for(int i=x[t];i<=N;i+=x[t]) {
					int tmp = Map[i][M-1];
					for(int j=M-1;j-1>=0;j--) {
						Map[i][j] = Map[i][j-1];
					}
					Map[i][0] = tmp;
				}
			}
		}
		
		//2.배열 하나 더 생성 후,값 존재하는지 확인.
		//값 존재O : 같은 수 찾아서 지우고 
		//값 존재X : 전체 원판 평균 구하고, 평균보다 크면 1 빼고, 작으면 1 더한다!
		boolean flag = false;
		int[][] Map2 = new int[N+1][M];
		for(int i=1;i<=N;i++) {
			System.arraycopy(Map[i], 0, Map2[i], 0, M);
		}
		for(int i=1;i<=N;i++) {
			for(int j=0;j<M;j++) {
				if(Map[i][j] != 0) {
					int a = Map[i][j];
					if(i+1<=N && a == Map[i+1][j]) {
						Map2[i][j] = Map2[i+1][j] = 0;
						flag = true;
					}
					if(j+1<M && a == Map[i][j+1]) {
						Map2[i][j] = Map2[i][j+1] = 0;
						flag = true;
					}
					if(j == 0 && a == Map[i][M-1]) {
						Map2[i][j] = Map2[i][M-1] = 0;
						flag = true;
					}
					if(j == M-1 && a== Map[i][0]) {//반복문 j는 M-1까지 안 닿는데. 반복ㅁ문 구성 잘못된 걸까.
						Map2[i][j] = Map2[i][M-1] = 0;
						flag = true;
					}
				}
			}
		}
		Map = Map2;
		
		if(!flag) {//값이 바뀌지 않았다면!
			int sum = countSum().sum;
			int cnt = countSum().cnt;
			double ave = 1.0 * sum/cnt;
			
			for(int i=1;i<=N;i++) {
				for(int j=0;j<M;j++) {
					if(Map[i][j] == 0)continue;
					else if(Map[i][j]<ave) {Map[i][j]++;}
					else if(Map[i][j]>ave) {Map[i][j]--;}
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		T = Integer.parseInt(input[2]);
		
		Map = new int[N+1][M];
		x = new int[T];
		d = new int[T];
		k = new int[T];
		
		for(int i=1;i<=N;i++) {
			input = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				Map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		for(int i=0;i<T;i++) {
			input = br.readLine().split(" ");
			x[i] = Integer.parseInt(input[0]);
			d[i] = Integer.parseInt(input[1]);
			k[i] = Integer.parseInt(input[2]);
		}
		
		for(int t=0;t<T;t++) {
			rotate(t);
		}
		System.out.println(countSum().sum);
		br.close();
	}

}
//for(int i=0;i<T;i++) {
//System.out.println("x[i] : "+x[i]);
//System.out.println("d[i] : "+d[i]);
//System.out.println("k[i] : "+k[i]);
//}

//for(int i=1;i<=N;i++) {
//for(int j=0;j<M;j++) {
//	System.out.print(Map[i][j]+ " ");
//}
//System.out.println();
//}