import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//센서
public class BOOJ2212 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[] sensors = new int[N];
		String[] input = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			sensors[i] = Integer.parseInt(input[i]);
		}
		Arrays.sort(sensors);
		int[] diff = new int[N-1];
		for(int i=0;i<N-1;i++) {
			diff[i] = sensors[i+1] - sensors[i];
		}
		Arrays.sort(diff);
		//k=2 :1개 스킵.
		int sum = 0;
		for(int i=0;i<N-K;i++) {
			sum += diff[i];
		}
		System.out.println(sum);
	}

}

//		for(int i=0;i<N;i++) {
//			System.out.print(sensors[i]+" ");
//		}