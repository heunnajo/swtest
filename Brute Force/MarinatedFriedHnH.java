package ss;
import java.util.*;
import java.io.*;
public class MarinatedFriedHnH {
	static int A,B,C,X,Y;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		A = Integer.parseInt(input[0]);
		B = Integer.parseInt(input[1]);
		C = Integer.parseInt(input[2]);
		X = Integer.parseInt(input[3]);
		Y = Integer.parseInt(input[4]);
		
		int sum = X+Y;
		int ans = Integer.MAX_VALUE;//20만*5000 = 100
		int finalZ = 0;
		for(int z=0;z<(sum/2);z+=2) {
			ans = Math.min(ans, (X-(z/2))*A+(Y-(z/2))*B+z*C);
			finalZ = z+1;
		}
		System.out.println(ans);
	}

}
