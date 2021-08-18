package ss;
import java.util.*;
public class TwoDArrayOperation_Solution {
	static int R,C,K;
	static int[][] Arr = new int[100][100];
	static class Number{
		int n,c;
		Number(int n,int c){
			this.n = n;
			this.c = c;
		}
	}
	static int solve() {
		//행 갯수, 열 갯수 비교해서 R/C연산 결정!
		int rsize = 3,csize = 3;
		
		for(int t=0;t<=100;t++) {
			if(Arr[R][C] == K) return t;
			
			if(rsize >= csize) {//R연산 : 행은 고정, 아플 때 행 고정
				for(int i=0;i<rsize;i++) {
					List<Number> numlist = new ArrayList<Number>();
					int[] cnt = new int[101];//100번 인덱스를 사용해야하기 때문에!
				
					for(int j=0;j<csize;j++) {
						cnt[Arr[i][j]]++;
					}
					for(int c=1;c<=100;c++) {//숫자 0은 필요없기 때문에 1부터 시작!(카운팅X, 정렬 대상 X)
						if(cnt[c] > 0) {//등장횟수가 하나라도 있을 경우.
							numlist.add(new Number(c,cnt[c]));
						}
					}
					numlist.sort((lhs,rhs)->{
						return lhs.c-rhs.c;
					});
					int idx = 0;
					for(Number num : numlist) {//현재 행고정, idex 
						if(idx >= 99) break;
						Arr[i][idx++] =  num.n;//후위 연산자 idx++이기 때문에 값을 먼저 넣고, 1씩 증가는 먼저 한다!!
						Arr[i][idx++] =  num.c;//후위 연산자 idx++이기 때문에 값을 먼저 넣고, 1씩 증가는 먼저 한다!!
					}
					
					csize = Math.max(csize, idx);
					for(;idx<100;++idx) {
						Arr[i][idx] = 0;
					}
				}
			} else {//C연산.
				for(int j=0;j<csize;j++) {//열고정.
					List<Number> numlist = new ArrayList<Number>();
					int[] cnt = new int[101];//100번 인덱스를 사용해야하기 때문에!
				
					for(int i=0;i<rsize;i++) {//열 기준, 숫자 갯수 카운팅!
						cnt[Arr[i][j]]++;
					}
					for(int c=1;c<=100;c++) {//숫자 0은 필요없기 때문에 1부터 시작!(카운팅X, 정렬 대상 X)
						if(cnt[c] > 0) {//등장횟수가 하나라도 있을 경우.
							numlist.add(new Number(c,cnt[c]));
						}
					}
					numlist.sort((lhs,rhs)->{
						return lhs.c-rhs.c;
					});
					int idx = 0;
					for(Number num : numlist) {//현재 행고정, idex 
						if(idx >= 99) break;
						Arr[idx++][j] =  num.n;//후위 연산자 idx++이기 때문에 값을 먼저 넣고, 1씩 증가는 먼저 한다!!
						Arr[idx++][j] =  num.c;//후위 연산자 idx++이기 때문에 값을 먼저 넣고, 1씩 증가는 먼저 한다!!
					}
					
					rsize = Math.max(rsize, idx);
					for(;idx<100;++idx) {
						Arr[idx][j] = 0;
					}
				}
			}
		}
		return -1;//100초가 지나도 A[R][C] != K이면 -1 출력! 
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt()-1;
		C = sc.nextInt()-1;
		K = sc.nextInt();
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				Arr[i][j] = sc.nextInt();
			}
		}
		System.out.println(solve());
	}

}
