package ss;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class Dijkstra_PriorityQueue {
	static int V,E,start;
	static List<Pair>[] list;
	//그래프=>인접리스트로 저장할 건데 어떻게 저장하지?
	//일단 클래스 지정. ArrayList[i] -> (j,dist1) ->(k,dist2) 이런식으로.
	static class Pair{
		int v,d;
		Pair(int v,int d){
			this.v = v;
			this.d = d;
		}
	}
	//start에서 최단 거리에 있는 정점 찾기!
	static int ShortestVertex(int s) {
		int v = 0;
		int minDist = Integer.MAX_VALUE;
		for(int i=0;i<list[s].size();i++) {//start노드의 인접리스트 크기 만큼!
			//계속 갱신되는 i가 아니라, 최단 거리 노드의 번i를 저장하려면?
			if(list[s].get(i).d < minDist) {//갱신이 되면 아래를 실행하도록 if문으로 구현!
				minDist = list[s].get(i).d;
				v = list[s].get(i).v;
			}
		}
//		if(minDist == Integer.MAX_VALUE) minDist = -1;
//		System.out.println("최단 거리 : "+minDist);
//		System.out.println("최단 거리의 정점 : "+v);
		return v;
	}
	//부분 해를 이용하여 특정 정점 start에서 모든 각 노드까지의 최단 거리를 찾는다!
	void Dijkstra() {
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		//초기화
		V = Integer.parseInt(input[0]);
		E = Integer.parseInt(input[1]);
		start = Integer.parseInt(br.readLine());
		list = new ArrayList[V];//n개의 정점마다 간선정보를 저장해야한다.
		for(int i=0;i<V;i++) {
			list[i] = new ArrayList<Pair>();
		}
		//간선 정보 저장!
		for(int i=0;i<E;i++) {
			input = br.readLine().split(" ");
			int v1 = Integer.parseInt(input[0])-1;
			int v2 = Integer.parseInt(input[1])-1;
			int w = Integer.parseInt(input[2]);
			
			list[v1].add(new Pair(v2,w));//v1과 연결된 정점 v2,가중치 저장.
			list[v2].add(new Pair(v1,w));//v1과 연결된 정점 v2,가중치 저장.
		}
		//ShortestVertex(start);
	
		
		br.close();
	}

}
//ShortestVertex 디버깅 성공.확인.
//System.out.println("정점0 ");
//ShortestVertex(0);//정점2와 연결된 최단거리 정점, 가중치 출력 
//System.out.println("정점1 ");
//ShortestVertex(1);//정점2와 연결된 최단거리 정점, 가중치 출력 
//System.out.println("정점2 ");
//ShortestVertex(2);//정점2와 연결된 최단거리 정점, 가중치 출력 
//System.out.println("정점3 ");
//ShortestVertex(3);//정점2와 연결된 최단거리 정점, 가중치 출력 
//System.out.println("정점4 ");
//ShortestVertex(4);//정점2와 연결된 최단거리 정점, 가중치 출력 


//간선정보 ArrayList 저장 성공.확인. 
//		for(int i=0;i<list.length;i++) {
//			for(int j=0;j<list[i].size();j++) {
//				System.out.print("정점 : "+i+" 연결된 정점 : "+list[i].get(j).v+" 가중치 : "+list[i].get(j).d);
//				System.out.println();
//			}
//			System.out.println();
//		}