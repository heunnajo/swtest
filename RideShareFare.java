class RideShareFare {
    static int[][] Dist;
    static final int INF =100000000;
    void floyd(int n){//Dist 배열을 완성한다.
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(Dist[i][k]+Dist[k][j] < Dist[i][j]){
                        Dist[i][j] = Dist[i][k]+Dist[k][j];
                    }
                }
            }
        }
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        Dist = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i == j) {
                    Dist[i][j] = 0;
                } else {
                    Dist[i][j] = INF;
                }
                
            }
        }
        // for(int[] edge :fares){
        //     Dist[edge[0]-1][edge[1]-1] = edge[2];
        //     Dist[edge[1]-1][edge[0]-1] = edge[2];
        // }
        for(int i=0;i<fares.length;i++){
            Dist[fares[i][0]-1][fares[i][1]-1] = fares[i][2];
            Dist[fares[i][1]-1][fares[i][0]-1] = fares[i][2];
        }
        floyd(n);
        
        s--;a--;b--;
        // --s;--a;--b;
        //합승 vs 갠플 최솟값 리턴하기!
        int answer = INF;
        for(int i=0;i<n;i++){
            // answer = Math.min(answer,Dist[s][i]+Dist[i][a]+Dist[i][b]);
            if(answer > Dist[s][i]+Dist[i][a]+Dist[i][b]){
                answer = Dist[s][i]+Dist[i][a]+Dist[i][b];
            }
        }
        
        return answer;
    }
}