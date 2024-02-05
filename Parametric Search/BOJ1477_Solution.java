//휴게소 세우기
import java.util.*;
import java.io.*;
public class BOJ1477_Solution {
	static int N, M, L;
    static int[] arr;
	public static void main(String[] args) throws Exception {
		inputAndSettingData();
        solve();
	}
	static void solve() {
		int s=1;
		int e=L;
		while(s < e) {
			int mid = (s+e) >>> 1;// /2를 하려면 1비트만 오른쪽으로 이동하면 되는 거 아닌가...
			int cnt = restCnt(mid);
			if(cnt <= M) e = mid; //갯수가 작으니 갯수를 늘이고, 간격은 좁아짐.
			else s = mid+1; //갯수를 줄이거나 동일하게 유지하면, 간격은 넓어짐. 
		}
	}
	static int restCnt(int dist) {
		int ret = 0;
		for(int i=1;i<=N+1;i++) {
			ret += (arr[i]-arr[i-1]-1) / dist;
		}
		return ret;
	}
	static void inputAndSettingData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());//고속도로 길이
        arr = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N] = L;
        arr[N + 1] = 0;
        Arrays.sort(arr);
    }

}
