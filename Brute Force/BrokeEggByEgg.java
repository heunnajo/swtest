package ss;
import java.util.*;
public class BrokeEggByEgg {
	static int N;
	static class Egg{
		int durability,weight;
		Egg(int durability,int weight){
			this.durability = durability;
			this.weight = weight;
		}
	}
	static Egg[] Eggs;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		Eggs = new Egg[N];
		
		for(int i=0;i<N;i++) {
			int dur = sc.nextInt();
			int w = sc.nextInt();
			Eggs[i] = new Egg(dur,w);
		}
		//left,right 계란 선택
		int ans = 0;
		for(int i=0;i<N-1;i++) {
			int broken = 0;
			if(Eggs[i].durability<=0) continue;
			Egg left = new Egg(Eggs[i].durability,Eggs[i].weight);
			for(int j=i+1;j<N;j++) {
				if(Eggs[j].durability<=0) continue;
				Egg right = new Egg(Eggs[j].durability,Eggs[j].weight);
				//계란치기!
				left.durability -= right.weight; right.durability -= left.weight;
				if(left.durability<=0) broken++;
				if(right.durability<=0) broken++;
			}
			if(ans<broken) ans = broken;
		}
		System.out.println(ans);
	}

}

//		for(int i=0;i<N;i++) {
//			Egg cur = Eggs[i];
//			System.out.print(cur.durability+" ");
//			System.out.println(cur.weight+" ");
//		}