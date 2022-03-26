#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#define NM 5005
using namespace std;

//3 5
//line in line
//LINE
//in lion
//output : 20


//4 7
//ABcD
//a
//LineneWs
//output : 12 //오답 19가 나와버림 : 디버깅ㄱ
int N;
int need[NM][30];//각 문자열마다 필요한 카드를 먼저 확인해야함!:필요하면 1, 아니면 0
int score[NM];
string str;
regex re("^team_name : [a-zA-Z]+ application_name : [a-zA-Z]+ error_level : [a-zA-Z]+ message : [a-zA-z]+$");
void Input(){
    cin >> N >> K;
    cin.ignore();//공백도 있기 때문에 getline을 써줘야함
    for(int i=1;i<=N;i++){
        getline(cin,str);
        cin >> str;
        //전처리 : N개의 문자열 각각에 대해 점수, 필요한 알파벳 계산
        for(char c: str){
            //마지막에 공백문자가 들어오는지 모르므로
            if(c == '\n') continue;
            if('A' <= c && c <= 'Z'){
                score[i]++;//i번 문자열의 점수 증가~
                need[i][26] = 1;//이 문자열의 shift위치에 필요하다고 기록
                need[i][c-'A'] = 1;//빼먹었던 게 오답 원인
            }
            else if('a' <= c && c <= 'z'){//소문자인 경우
                need[i][c-'a'] = 1;//소문자일 때는 배열의 해당 알파벳의 배열값을 1로.
            }
            score[i]++; //길이 점수
        }
    }
}

int need[30], ans;//필요한 합집합 정보
//재귀 설계
//필요한 정보(매개변수)
//1. 무슨 카드가 필요한지 체크하는 배열
//2. 지금까지 점수
void DFS(int x, int need[], int score){
    //1.재귀 종료
    if(x == N +1){
        int cnt = 0;
        for(int i=0;i<=26;i++){
            if(need[i]>=1) cnt+=1;//추가적으로 더 필요하다는 뜻
        }
        if(cnt <= K){//가능한 경우
            if(score == 19){//유추 : 모든 문자열을 만들었다는 뜻?
                x = x;
            }
            ans = max(ans,score);
        }
        return;
    }
    //2.현재 경우 선택 : 현재 문자 선택O/X
    //2-1.선택O
    for(int i=0;i<=26;i++){
        need[i] += need[x][i];
    }
    DFS(x+1,need,score + scores[x]);

    //2-1.선택X
    for(int i=0;i<=26;i++){
        need[i] -= need[x][i];
    }
    DFS(x+1,need,score);
}
//무슨 문자열 만들지 결정해줘야함!
void Pro() {
    DFS(1, need, 0);
    cout << ans;
}