package ss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//2. 부서진 키보드
import java.util.*;
class Q2_2nd_Solved {
	static int ans;
	static int N,K;
	static String[] Sens;
	static int[][] needed;
	static int[] scores;
	public static void main(String[] args) throws Exception {
		String[] sentences = {"ABcD","a","LineneWs"};//output : 10?12?
		int n = 7;
//		String[] sentences = {"line in line","LINE","in lion"};//output : 20
//		int n = 5;
		solution(sentences,n);
		System.out.println(ans);
	}
	public static int solution(String[] sentences, int n) {
		init(sentences,n);
		preprocessing();
	    DFS(0,0,new int[30]);//idx,sum,union
	    return ans;
	}
	static void init(String[] sentences, int n) {
		//변수 초기화
		ans = -1;
		N = n; K = sentences.length;
		needed = new int[K][30];
		scores = new int[K];
		Sens = new String[K];
		for(int i=0;i<K;i++) {
			Sens[i] = sentences[i];
		}
	}
	static void preprocessing() {
		//K개의 문자열마다 필요한 알파벳 정보, 점수 계산
		for(int i=0;i<K;i++) {
			String cur = Sens[i];
			for(int j=0;j<cur.length();j++) {
				//1.대문자인 경우
				char ch = cur.charAt(j);
				if('A' <= ch && ch <= 'Z') {
					scores[i]++;
					needed[i][26] = 1;//빼먹어버림~
					needed[i][ch-'A'] = 1;
				} else if('a' <= ch && ch <= 'z') {//2.소문자인 경우
					needed[i][ch-'a'] = 1;
				} 
				scores[i]++;
			}
		}
	}
	static LinkedList<Integer> list = new LinkedList<>();
	static void DFS(int idx,int sum,int[] union) {
		//1.종료 조건
		if(idx == K) {
			int cnt = 0;//합집합 갯수
			for(int i=0;i<=26;i++) {
				if(union[i]>=1) cnt++;
			}
			if(cnt <= N) {
				/*오답 디버깅
				if(sum == 27) {
//					for(int a=0;a<=26;a++) {
//						System.out.print(union[a]+" ");
//					}
//					System.out.println();
					for(int a = 0;a<list.size();a++) {
						System.out.print(list.get(a));
					}
					System.out.println();
				}
				if(sum == 10) {
					for(int a = 0;a<list.size();a++) {
						System.out.print(list.get(a));
					}
					System.out.println();
				}*/
				ans = Math.max(sum, ans);
			}
			return;
		}
		//2.현재 경우 선택, 다음 경우 재귀 호출
		//2-1.선택 O :점수 합산 후 재귀 호출
		for(int i=0;i<=26;i++) {
			union[i] += needed[idx][i];
		}
		list.add(idx);
		DFS(idx+1,sum+scores[idx],union);
		//2-2.선택 X :원복 후 재귀 호출
		for(int i=0;i<=26;i++) {
			union[i] -= needed[idx][i];
		}
		list.remove(list.size()-1);
		DFS(idx+1,sum,union);
	}
}
