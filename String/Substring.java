package ss;
import java.util.*;
import java.io.*;
public class Substring {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String check = br.readLine();
		if(str.contains(check))System.out.println(1);
		else System.out.println(0);
	}

}
