package ss;
import java.util.*;
import java.io.*;
public class PokemonMaster {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//n개의 포켓몬 저장
		String[] NumToName = new String[n+1];
		HashMap<String,Integer> NameToNum = new HashMap<>();
		
		for(int i=1;i<=n;i++) {
			//1.배열에 저장
			String pokemon = br.readLine();
			NumToName[i] = pokemon;
			//2.Map에 저장
			NameToNum.put(pokemon, i);
		}
		StringBuilder sb = new StringBuilder();
		while(m-- >0) {
			//숫자인지 문자인지 판별!
			//숫자:문자로 답함 => 숫자면 Map에 없을 것!
			//문자:숫자로 답함 => 문자면 Map에 있을 것!
			String test = br.readLine();
			if(NameToNum.containsKey(test)) {//문자이므로 번호(value) 반환 
				sb.append(NameToNum.get(test)+"\n");
			} else {//숫자이므로 이름을 반환! 이름 = 정수 형변환후 해당 숫자 인덱스로 하는 배열값 반환!
				int idx = Integer.parseInt(test);
				sb.append(NumToName[idx]+"\n");
			}
		}
		System.out.print(sb.toString());
	}

}
