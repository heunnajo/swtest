package boj;
import java.util.*;
import java.io.*;
//사파리 월드
public class BOJ2420 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");//" " 기준으로 앞 뒤 분리!
        
        long n = Long.parseLong(input[0]);
        long m = Long.parseLong(input[1]);
        
        System.out.println(Math.abs(n-m));
	}

}
