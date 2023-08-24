import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//좋다
public class BOJ1253 {

    /**
     * 1) 주어진 수를 정렬한다.
     * 2) 수를 역순으로 한개씩 선택하면서 선택한 수보다 작은 수들의 합으로 만들 수 있는지 판단한다.
     */

    public static int[] numbers;

    public static boolean isGood(int index, int left, int right) {
        while(left < right) {

            int sum = numbers[left] + numbers[right];

            if(numbers[index] == sum && left != index && right != index) {
                return true;
            }

            if(numbers[index] == sum) {
                if(index == right) right--;
                else left++;
            } else if(numbers[index] < sum) {
                right--;
            } else {
                left++;
            }
        }

        return false;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        int cnt = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        for (int i = N - 1; i >= 0; i--) {
            if (isGood(i, 0, N - 1)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}