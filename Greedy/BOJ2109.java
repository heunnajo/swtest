package swea;
import java.util.*;
import java.io.*;
//순회강연
public class BOJ2109 {
	static int N;
	static int[][] Pair;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Pair = new int[N][2];
		
		String[] input;
		for(int i=0;i<N;i++) {
			input = br.readLine().split(" ");
			Pair[i][0] = Integer.parseInt(input[0]);
			Pair[i][1] = Integer.parseInt(input[1]);
		}
		Arrays.sort(Pair,new Comparator<int[]>() {
			@Override
			public int compare(int[] arr1,int[] arr2) {
				if(arr1[0]==arr2[0]) {
					return arr2[1]-arr1[1];
				} else {
					return arr2[0]-arr1[0];
				}
			}
		});
		int ans = 0;
		boolean[] check = new boolean[10001];
		for(int i=0;i<N;i++) {
			for(int j=Pair[i][1];j>=1;j--) {
				if(!check[j]) {
					check[j] = true;
					ans += Pair[i][0];
					break;
				}
			}
		}
		System.out.println(ans);
		
	}

}
