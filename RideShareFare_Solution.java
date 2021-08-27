import java.util.*;
class RideShareFare_Solution {
    static final int INF = 40000000;//3번 더해도 여전히 integer 범위내로 들어오기 때문에 범위 초과할 문제 없다!
    int[][] Dist = new int[200][200];//정점 갯수는 최대 200개이기 때문에.
    
    void floyd(int n){
        //3중 반복문으로 구현 : i에서 j로 갈 때, k를 거쳐서 갈 때.
        //모든 시작점, 모든 도착점, 모든 경유지 경로 각각의 경우를 다 구해서 각 출발점-도착점의 최소 비용 구할 수 있다.
        //1.가장 바깥 반복문 : 경유지 k-for
        //2.중간 반복문 : 시작점 i-for
        //3.가장 안쪽 반복문 : 도착점 j-for
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(Dist[i][k]+Dist[k][j] < Dist[i][j]){//더 적은 비용을 찾은 경우! 최솟값 갱신~!
                        
                        Dist[i][j] = Dist[i][k]+Dist[k][j];
                    }
                }
            }
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) 
                    Dist[i][j] = 0;
                else
                    Dist[i][j] = INF;
            }
        }
        //간선(fares) 정보에 따라 그래프 표현 : Floyd-Warshall로 최단거리 구한 후에는 그래프 정보 필요 없음. 따라서 그래프를 따로 저장하지 않고, Dist배열을 업데이트 한다.
        
        for(int[] edge : fares){//문제에서는 1번부터 시작하지만 0부터 시작하는 인덱스 쓰기 때문에 1 빼준다.
            Dist[edge[0]-1][edge[1]-1] = edge[2];//Dist[][]에 가중치 저장
            Dist[edge[1]-1][edge[0]-1] = edge[2];
        }
        //Floyd-Warshall 최단거리 알고리즘 수행
        floyd(n);//정점 갯수 n을 넘겨주면 된다.
        
        //출발점 s, A 도착점 a,B 도착점 b도 1부터 시작하기 때문에 1씩 빼서 사용한다! 
        --s;
        --a;
        --b;
        int answer = INF;
        //합승 도착 지점(경유지)=> 모든 정점에 대해 다 해본다!
        for(int k=0;k<n;k++){
            answer = Math.min(answer,Dist[s][k]+Dist[k][a]+Dist[k][b]);
        }
        return answer;
    }
}