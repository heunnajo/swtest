import java.io.BufferedReader;
import java.io.InputStreamReader;
public class TestSupervisor {
	static int N, A[],B,C;
	static long solve() {
		long sum = 0;
		int[] needSubSupervisor = new int[N];
		for(int i=0;i<N;i++) {//총감독수 오직1명만 들어가야하므로 총감독수 1명씩으로 셋팅.
			needSubSupervisor[i] = 1;
		}
		for(int i=0;i<N;i++) {
			int gap = A[i]-B;//총감독이 감독하고 남는 응시자들.
            if(gap<=0) continue;
			if(gap%C == 0) {//C의 배수인 경우 몫을 더하면 됨.
				needSubSupervisor[i] += gap/C;
			}else {//gap<C 또는 gap>C:나눠 떨어지지 않는 경우.
				needSubSupervisor[i] += (gap/C)+1;
			}
		}
		for(int i=0;i<N;i++) {
			sum += needSubSupervisor[i];
		}
		return sum;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(input[i]);
			
		}
		input = br.readLine().split(" ");
		B = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		
		System.out.println(solve());
		
		br.close();
	}

}