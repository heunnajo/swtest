import java.io.BufferedReader;
import java.io.InputStreamReader;
public class TestSupervisor_faster {
	static int N, A[],B,C;
	static long solve() {
		long sum = N;
		
		for(int i=0;i<N;i++) {
			int gap = A[i]-B;//총감독이 감독하고 남는 응시자들.
            if(gap<=0) continue;
            sum += gap/C;
            if((gap%C)!=0) sum++;
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