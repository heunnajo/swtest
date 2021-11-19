package ss;
import java.util.*;
public class MakeMagicSquare {
	static int[][] Map;
	static int ans;
	static ArrayList<Integer> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Map = new int[3][3];
		list = new ArrayList<>();
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				Map[i][j] = sc.nextInt();
			}
		}
		go(0,0);
		System.out.println(ans);
	}
	static void go(int pos,int costSum) {
		if(pos == 9) {
			if(check()) {
				ans = Math.min(ans, costSum);
				return;
			}
		}
		int cost = 0;int curX = pos/3; int curY = pos%3;
		System.out.println("curX:"+curX+"curY:"+curY);
		for(int i=1;i<=9;i++) {
			int tmp = Map[curX][curY];
			Map[curX][curY] = i;
			cost = Math.abs(i-tmp);
			go(pos+1,costSum+cost);
			Map[curX][curY] = tmp;
		}
		
	}
	static boolean check() {
		int fRS,sRS,tRS,fCS,sCS,tCS,rD,lD;
		fRS = Map[0][0]+Map[0][1]+Map[0][2];
		sRS = Map[1][0]+Map[1][1]+Map[1][2];
		tRS = Map[2][0]+Map[2][1]+Map[2][2];
		
		fCS = Map[0][0]+Map[1][0]+Map[2][0];
		sCS = Map[0][1]+Map[1][1]+Map[2][1];
		tCS = Map[0][2]+Map[1][2]+Map[2][2];
		
		rD = Map[0][0]+Map[1][1]+Map[2][2];
		lD = Map[0][2]+Map[1][1]+Map[2][0];
		list.add(fRS);
		list.add(sRS);
		list.add(tRS);
		list.add(fCS);
		list.add(sCS);
		list.add(tCS);
		list.add(lD);
		list.add(rD);
		
		int st = list.get(0);
		for(int i=1;i<list.size();i++) {
			if(st != list.get(i)) return false;
		}
		return true;
	}
}
