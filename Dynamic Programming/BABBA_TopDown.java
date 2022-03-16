//BOJ #9625 BABBA, Top-down : 68ms
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int m[];
	static int m2[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.valueOf(br.readLine());
		
		m = new int[K+1];
		m2 = new int[K+1];
		
		System.out.println(go(K)+" "+go2(K));

	}

	static int go(int k){
		
		if(k == 0) return 1;
		if(k == 1) return 0;
		
		if(m[k] > 0) return m[k];
		
		return m[k] = go(k-1) + go(k-2);
	}
	
	static int go2(int k){
		
		if(k == 0) return 0;
		if(k == 1) return 1;
		
		if(m2[k] > 0) return m2[k];
		
		return m2[k] = go2(k-1) + go2(k-2);
	}
}