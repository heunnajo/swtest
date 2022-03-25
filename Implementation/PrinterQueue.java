//BOJ #1966 프린터 큐
import java.io.*;
import java.util.*;
public class PrinterQueue {
	static class Doc {
		int idx,imp;
		Doc(int idx,int imp){
			this.idx = idx;
			this.imp = imp;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		while(tc-- >0) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			LinkedList<Doc> list = new LinkedList<>();
			int idx = 0;
			st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<n;i++) {
				int imp = Integer.parseInt(st.nextToken());
				list.add(new Doc(idx++,imp));
			}
//			for(int k=0;k<list.size();k++) {
//				System.out.println(list.get(k).idx+","+list.get(k).imp);
//			}
			//현재 리스트에서 제일 앞 문서 cur와 그 뒤의 문서들과 중요도 비교!
			//더 큰 것이 있다면 cur과 그 이전의 문서들을 뒤로 넣는다!
			int order = 0;
			
			while(!list.isEmpty()) {
				Doc cur = list.remove();
				boolean isMax = true;
				
				//1.현재 문서 cur와 뒤의 문서들 중요도 비교
				for(int i=0;i<list.size();i++) {
					if(cur.imp < list.get(i).imp) {
						list.add(cur);
						for(int j=0;j<i;j++) {
							list.add(list.remove());
						}
						isMax = false;
						break;//반복문 탈출 : 현재보다 큰 값 찾았으면 바로 비교 종료
					}
				}
				//2a.현재 cur가 최댓값이 아니라면 1을 반복
				if(!isMax) continue;
				
				//2b.현재 cur가 최댓값이라면 인쇄!:찾고자하는 문서인지 확인
				order++;
				if(cur.idx == x) {
					sb.append(order+"\n");
					break;
				}
			}
		}
		System.out.print(sb);
	}

}
