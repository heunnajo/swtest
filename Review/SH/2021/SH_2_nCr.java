package ss;
import java.util.*;
public class SH_2_nCr {
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static LinkedList<Integer> group_size;
	static int ans,n,k;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//1.숫자 2개를 1쌍으로 입력받아서 인접리스트로 저장한다.
		n = sc.nextInt();
		//정해지지 않은 순서쌍을 입력받는다.=>k개를 입력받는다고 가정
		k = sc.nextInt();
		
		//2.인접리스트 완성
		//인접리스트 list 생성, 초기화
		list = new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			list[i] = new ArrayList<>();
		}
		//각 정점, 간선 정보를 인접리스트에 저장!
		for(int i=0;i<k;i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			list[u].add(v);
			list[v].add(u);
		}
		
		//3.n개의 노드 순회하면서 아직 방문하지 않은 노드를 방문하고, 그 때의 그래프 크기를 저장한다!
		visited = new boolean[n+1];
		group_size = new LinkedList<>();
		for(int i=1;i<=n;i++) {
			if(visited[i]) continue;
			//i정점을 시작으로 bfs, i와 연결된 정점 갯수를 리턴
			group_size.add(bfs(i));
		}
		//System.out.println("group_size 크기 : "+group_size.size());
		//for(int a:group_size) System.out.print(a+" ");
		//System.out.println();
		
		//4.group_size에 저장된 각 그룹의 학생 수를 이용해 O(n)으로 정답 도출!
		//O_N_square();//7
		//O_N_impossibleCnt();//7
		O_N_multiplicationFormula();//7
		System.out.println(ans);//(1,2,3) (4) (5)에 대해 모두 동일하게 7이라는 정답이 나오는 것을 확인!
	}
	static void O_N_square() {
		int size = group_size.size();//3
		for(int i=0;i<size;i++) {
			for(int j=i+1;j<size;j++) {
				ans += group_size.get(i)*group_size.get(j);
			}
		}
	}
	static void O_N_impossibleCnt() {
		int impossible_cnt = 0;
		int total = n*(n-1)/2;//nC2
		
		//붉가능한 경우:각 그룹마다 2명씩 뽑는 경우의 수 합산
		//각 그룹의 학생수 x이면 각 그룹마다 2명씩 뽑는 경우의 수 = xC2 = x*(x-1)/2 
		for(int x:group_size) {
			impossible_cnt += x*(x-1)/2;
		}
		ans = total-impossible_cnt;
	}
	static void O_N_multiplicationFormula() {
		//곱셈 공식 이용 : ab+bc+ca = ((a+b+c)^2-(a^2+b^2+c^2))/2 
		//a+b+c = n, a^2 b^2 c^2 각각은 각ㄱ 그룹의 학생 수를 의미 = group_size의 각 원소!
		ans = n*n;
		for(int x:group_size) {
			ans -= x*x;
		}
		
		ans /= 2;
	}
	static int bfs(int x) {
		//bfs 함수 역할 :x와 연결된 정점을 방문하고, 그 정점 갯수를 리턴, group_size에 저장
		//x와 연결된 정점의 갯수는 list[x]의 크기 아님?아니다아니다
		//group A :1 2 3
		//group B  : 4
		//group C : 5
		//라고 한다면  3,1,1 이렇게 저장을 해야한다!
		int sum = 1;//초기에는 그 노드 자체로 1개이므로 sum은 1부터 카운팅한다!
		for(int a:list[x]) {
			if(visited[a]) continue;
			visited[a] =true;
			sum++;
		}
		return sum;
	}
	
}
