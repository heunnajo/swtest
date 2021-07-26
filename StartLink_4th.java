import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class StartLink_4th {
	static int N;
	static int[][] a;
	static ArrayList<Integer> f,s;
	static int gap(ArrayList<Integer> f, ArrayList<Integer> s) {
		int diff = -1;
		int t1 = 0; int t2 = 0;
		for(int i = 0;i<N/2;i++) {
			for(int j = 0;j<N/2;j++) {
				t1 += a[f.get(i)][f.get(j)];
				t2 += a[s.get(i)][s.get(j)];
			}
		}
		diff = Math.abs(t1-t2);
		return diff;
	}
	
	static int go(int index, ArrayList<Integer> f, ArrayList<Integer> s) {//고를 index번째 사람, f와 s는 지금까지 고른 팀원, 팀.
		//1.불가능한 경우(백트랙킹)
		if(f.size()>N/2) return -1;
		if(s.size()>N/2) return -1;
		
		//2.정답 찾은 경우
		if(index == N) {
			if(f.size() <N/2) return -1;
			if(s.size() <N/2) return -1;
			return gap(f,s);//능력치합의 차이를 리턴!
		}
		
		//3.다음 경우 호출
		//선택 :f선택.
		int ans = -1;//최솟값 찾기 위한 정답 변수.
		f.add(index);
		int t1 = go(index+1,f,s);//index+1, f에 새로 추가한 f 변수로 담아서 재귀호출!!
		if(ans == -1 || t1!=-1 && t1<ans) {ans = t1;}
		f.remove(f.size()-1);
		//선택X :s선택.
		s.add(index);
		int t2 = go(index+1,f,s);
		if(ans == -1 || t2!=-1 && t2<ans) {ans = t2;}
		s.remove(s.size()-1);
		
		return ans;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		a = new int[N][N];
		f = new ArrayList<>();
		s = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				a[i][j] = Integer.parseInt(input[j]);
			}
		}
		System.out.print(go(0,f,s));
		
	}

}