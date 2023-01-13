//2023카카오_이모티콘 할인행사
//Q150368
public class Solution {
	static int N,M;
	static int[] Ans;
	static int[][] Users;
    static int[] Emo;
    static double[] Rates = {0.1,0.2,0.3,0.4};
    static double[] Selected;
    static int[] Costs;
	public static void main(String[] args) {
		int[][] users = {{40,10000},{25,10000}};
		int[] emoticons = {7000,9000};
		
		int[] Ans = solution(users,emoticons);
		System.out.println(Ans[0]+","+Ans[1]);
	}
	static int[] solution(int[][] users, int[] emoticons) {
        N = users.length; M = emoticons.length;
        Ans = new int[2];
        Users = new int[N][2];
        Emo = new int[M];
        for(int i=0;i<N;i++){
            Users[i][0] = users[i][0];
            Users[i][1] = users[i][1];
        }
        for(int i=0;i<M;i++){
            Emo[i] = emoticons[i];
        }
        Selected = new double[M];
        
        go(0);
        
        return Ans;
    }
	static void go(int idx){
        if(idx == M){
    		solve();
            return;
        }
        
        for(int i=0;i<4;i++){
            Selected[idx] = Rates[i];
            go(idx+1);
        }
    }
	
    static void solve(){
//    	System.out.println("현재의 할인율 "+Selected[0]+", "+Selected[1]);
    	Costs = new int[N];
    	
        int tmpJoined = 0; int tmpSum = 0;
        double standardRate = 0;
        int standardPrice = 0;
        
        for(int j=0;j<N;j++){
        	standardRate = Users[j][0]*0.01;
        	standardPrice = Users[j][1];
        	//System.out.println(j+"의 기준 할인율: "+standardRate+", 기준 금액: "+standardPrice);
        	for(int i=0;i<M;i++){
                if(Selected[i] >= standardRate){
                    Costs[j] += Emo[i]*(1-Selected[i]);
                }
            }
        	if(Costs[j] >= standardPrice){
                tmpJoined += 1;
                Costs[j] = 0;
            }else {
            	tmpSum += Costs[j];
            }
        	//System.out.println(j+"사용자의 총 지불금액: "+Costs[j]);
        }
        if(Ans[0] < tmpJoined){
        	//System.out.println("현재 가입자수: "+tmpJoined+", 현재 매출액: "+tmpSum);
        	Ans[0] = tmpJoined;
        	Ans[1] = tmpSum;
        } else if(Ans[0] == tmpJoined) {
        	Ans[1] = Ans[1]<tmpSum ? tmpSum : Ans[1];
        }
//        System.out.println();
    }
    static void print() {
		for(int i=0;i<M;i++){
			System.out.print(Selected[i]+" ");
		}
		System.out.println();
		System.out.println();
	}
}
