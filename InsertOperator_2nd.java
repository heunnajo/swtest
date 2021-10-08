import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class InsertOperator {

  static int max;
  static int min;
  static int[] nums;

  static void fullCalc(int[] ops) {
	int result = 0;
    for(int i=0;i<ops.length;i++) {
    	if(i==0) {
    		if(ops[i] == 0) result = nums[i]+nums[i+1];
    		else if(ops[i] == 1) result = nums[i]-nums[i+1];
    		else if(ops[i] == 2) result = nums[i]*nums[i+1];
    		else if(ops[i] == 3) result = nums[i]/nums[i+1];
    	} else {
    		if(ops[i] == 0) result += nums[i+1];
    		else if(ops[i] == 1) result -= nums[i+1];
    		else if(ops[i] == 2) result *= nums[i+1];
    		else if(ops[i] == 3) result /= nums[i+1];
    	}
    }
    if (result > max) max = result;
    if (result < min) min = result;
  }

  static void permutation(int[] set, int[] opsResult, boolean[] visited, int depth, int n, int r) {
    if (depth == r) {
      fullCalc(opsResult);
      return;
    }

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        visited[i] = true;
        opsResult[depth] = set[i];
        permutation(set, opsResult, visited, depth + 1, n, r);
        visited[i] = false;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int[] ops = new int[N - 1];
    int[] opsNum = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int index = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < opsNum[i]; j++) {
        ops[index++] = i;
      }
    }

    max = -1_000_000_001;
    min = 1_000_000_001;

    permutation(ops, new int[N - 1], new boolean[N - 1], 0, N - 1, N - 1);

    bw.write(String.format("%d\n%d", max, min));
    bw.flush();
  }
}