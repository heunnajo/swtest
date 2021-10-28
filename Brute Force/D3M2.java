package ss;
import java.util.*;
public class D3M2 {
	static class Pair implements Comparable<Pair>{
		int three;
		long num;
		Pair(){}
		Pair(int three,long num){
			this.three = three;
			this.num = num;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.three<o.three) return 1;//자리바꿈
			else if(this.three>o.three) return -1;//자리바꿈X
			else {//this.three == o.three
				if(this.num>o.num) return 1;//자리바꿈
				else if(this.num<o.num) return -1;//자리바꿈X
				else return 0;//자리바꿈X
			}
		}
	}
	static Pair[] nums;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		nums = new Pair[n];
		
		
		for(int i=0;i<n;i++) {
			long tmp = sc.nextLong();
			int three = 0;
			for(long j=tmp;j%3 == 0;j/=3) {
				three++;
			}
			nums[i] = new Pair(three,tmp);
		}
		Arrays.sort(nums);
		for(int i=0;i<n;i++) {
			System.out.print(nums[i].num+" ");
		}
		System.out.println();
	}

}
