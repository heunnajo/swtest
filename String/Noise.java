package ss;
import java.io.*;
import java.util.*;
public class Noise {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		String op = br.readLine();
		int b = Integer.parseInt(br.readLine());
		if(op.equals("*")) System.out.println(a*b);
		else System.out.println(a+b);
	}

}
