package ss;
import java.util.*;
public class D3M2_Solution {
	static class Pair implements Comparable<Pair> {
		int three;
		long num;
		Pair(){}
		Pair(int three,long num){
			this.three = three;
			this.num = num;
		}
		@Override //equals랑 hashCode랑 셋트인것 같은데 
		public int hashCode(){//for what??
			return Objects.hash(three,num);
		}
		@Override
		public boolean equals(Object obj) {//?
			if(obj instanceof Pair) {
				Pair that = (Pair)obj;
				if(this.three == that.three && this.num == that.num) {
					return true;
				} else return false;
			} else return false;
		}
		public int compareTo(Pair that) {
			if(this.three>that.three) return -1;//내림차순 정렬 :a>b a-b이면 -1을 리턴하는 것이기 때문에 자리바꿈X
			else if(this.three == that.three) {
				if(this.num<that.num) return -1;//자리바꿈 X
				else if(this.num == that.num) return  0;//자리바꿈 X 인듯.
				else return 1;//자리바꿈!this.num>that.num 이기 때문에 오름차순으로 바꾼다!
			} else return 1;//this.three<that.three 이면 내림차순 정렬 위해 자리바꾼다!
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Pair[] a = new Pair[n];
		for(int i=0;i<n;i++) a[i] = new Pair();
		for(int i=0;i<n;i++) {
			long num = sc.nextLong();
			int three = 0;
			for(long j=num;j%3 == 0;j/=3) {
				three += 1;
			}
			a[i] = new Pair(three,num);//입력으로 주어지는 숫자 n개 마다 3의 갯수와 그 숫자를 쌍으로 저장한다!
		}
		Arrays.sort(a);//우리가 정의한 정렬기준으로 정렬된다!
		for(int i=0;i<n;i++) {
			System.out.print(a[i].num+" ");
		}
		System.out.println();
	}

}
