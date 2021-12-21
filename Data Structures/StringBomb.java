package ss;
import java.util.*;
import java.io.*;
public class StringBomb {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = null;
		String str = br.readLine();
		String bomb = br.readLine();
		
		while(str.contains(bomb)) {
			//str = str.replaceAll("C4", "");
			str = str.replaceAll("12ab", "");
		}
		if(str.length()==0) System.out.println("FRULA");
		else System.out.println(str);
	}
	
}
//		if(str.contains(bomb)) System.out.println("yes");
//		else System.out.println("no");
