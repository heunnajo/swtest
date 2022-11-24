//#131705 삼총사
class Solution {
    int Ans;
    int[] Num,Selected,Visited;
    int N;
    public int solution(int[] number) {
        
        Ans = 0;
        N = number.length;
        Num = new int[N];
        Selected = new int[N];
        Visited = new int[N];
        
        for(int i=0;i<N;i++){
            Num[i] = number[i];
        }
        
        go(0,0);
        
        return Ans;
    }
    void go(int idx,int from){
        //1.종료
        if(idx == 3){
            int sum = 0; 
            for(int i=0;i<3;i++){
                
                sum += Num[Selected[i]];
            }
            
            if(sum == 0) {//삼총사 조건 만족하는 경우
                System.out.print(Selected[0]+" "+Selected[1]+" "+Selected[2]+" ");//023, 134
                System.out.println();
                Ans++;
            }
            return;
        }
        if(from == N) return;
        //2.현재 & 다음
        //2-1.선택O
        for(int i=from;i<N;i++){
            if(Visited[i] == 1) continue;
            //Visited[i] = 1; 
            Selected[idx] = i;
            go(idx+1,i+1);
            //Visited[i] = 0; 
            Selected[idx] = -1;//원복
        }
        //2-2.선택X : idx인데 from+1부터!
        //go(idx,from+1);
    }
}