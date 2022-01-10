package ss;

public class SelfNumber {
	
	public static void main(String[] args){
		boolean[] check = new boolean[10001];
		for(int i=1;i<=10000;i++){
			int n = d(i);
			if(n<=10000) check[n] = true;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=10000;i++){
			if(check[i]) continue;
				sb.append(i+"\n");
		}
		System.out.print(sb);
	}
	public static int d(int number){
		int sum = number;

		while(number != 0){
			sum += number % 10;
			number /= 10;
		}
		return sum;
	} 
}
