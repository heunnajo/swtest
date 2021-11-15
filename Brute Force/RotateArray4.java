package ss;
import java.util.*;
public class RotateArray4 {
	static int ans,N,M,K,Arr[][],rotInfo[][];
	static int size;
	static class Tuple{
		int r,c,s,i;
		Tuple(int r,int c,int s,int i){
			this.r = r;
			this.c = c;
			this.s = s;
			this.i = i;
		}
		Tuple(int r,int c,int s){
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	static Tuple[] Info;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		Info = new Tuple[K];
		
		Arr = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				Arr[i][j] = sc.nextInt();
			}
		}
		for(int i=0;i<K;i++) {
			int r = sc.nextInt()-1;
			int c = sc.nextInt()-1;
			size = sc.nextInt();
			
			Info[i] = new Tuple(r,c,size);//배열에 넣는다!
		}
		do {
			//1.배열 복사
			int[][] tmp = new int[N][M];
			for(int i=0;i<N;i++)
				for(int j=0;j<M;j++)
					tmp[i][j] = Arr[i][j];
			
			//2.배열 회전 연산 모두 실행
			for(Tuple t:Info) {
				rotate(tmp,t);
			}
			//3.최소합 구하기
			ans = getMinSum(tmp);
		}while(next_permutation(Info));//4.다음 순열 구해서 1~3을 반복
		System.out.println(ans);
	}
	static boolean next_permutation(Tuple[] Info) {
		//Tuple의 i값을 기준으로 다음 순열을 만든다!
		//1.1234 가장 처음 index-1<index인 것을 찾느다!
		int index = Info.length-1;
		while(index>0 && Info[index-1].i>=Info[index].i) {
			index--;
		}
		if(index<=0) return false;
		
		//2.index-1번째 와 index-1번째 보다 큰 값을 뒤에서 부터 찾고, swap한다!
		int j = Info.length-1;
//		while(j>0 && Info[index-1].i>=Info[j].i) {
		while(Info[index-1].i>=Info[j].i) {
			j--;
		}
		//if(j<=0) return false;
		
		Tuple tmp = Info[index-1];
		Info[index-1] = Info[j];
		Info[j] = tmp;
		
		//4.index~ 끝까지 오름차순 정렬!
		j = Info.length-1;
		while(index < j) {
			tmp = Info[index];
			Info[index] = Info[j];
			Info[j] = tmp;
			index++;j--;
		}
		return true;
	}
	static void rotate(int[][] a,Tuple t) {
		int row = t.r;
		int col = t.c;
		int size = t.s;
		//1.배열 옮겨담는다.
		ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
		for(int s=1;s<=size;s++) {
			//N = row, M = col
			ArrayList<Integer> group = new ArrayList<>();
			for(int r=row-s,c=col-s;c<col+s;c++) {//상 
				group.add(a[r][c]);
			}
			for(int r=row-s,c=col+s;r<row+s;r++) {//우 
				group.add(a[r][c]);
			}
			for(int r = row+s,c=col+s;c>col-s;c--) {//하
				group.add(a[r][c]);
			}
			for(int r=row+s,c=col-s;r>row-s;r--) {//좌
				group.add(a[r][c]);
			}
			groups.add(group);
		}
		//2.회전시킨 후, 원래 배열에 담는다.
		for(int s=1;s<=size;s++) {
			ArrayList<Integer> group = groups.get(s-1);
			Collections.rotate(group,1);
			int index = 0;
			for(int r=row-s,c=col-s;c<col+s;c++) {//상 
				a[r][c] = group.get(index++);
			}
			for(int r=row-s,c=col+s;r<row+s;r++) {//우 
				a[r][c] = group.get(index++);
			}
			for(int r = row+s,c=col+s;c>col-s;c--) {//하
				a[r][c] = group.get(index++);
			}
			for(int r=row+s,c=col-s;r>row-s;r--) {//좌
				a[r][c] = group.get(index++);
			}
		}
	}
	static int getMinSum(int[][] Arr) {
		int minS = 10000;
		int tmp;
		for(int i=0;i<N;i++) {
			tmp = 0;
			for(int j=0;j<M;j++) {
				tmp += Arr[i][j];
			}
			minS = Math.min(minS, tmp);
		}
		return minS;
	}
}
