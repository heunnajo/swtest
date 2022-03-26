#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#define NM 5005
using namespace std;

//각 문자열마다 필요한 카드를 먼저 확인해야함!
int N;
int need[NM][20];

regex re("^team_name : [a-zA-Z]+ application_name : [a-zA-Z]+ error_level : [a-zA-Z]+ message : [a-zA-z]+$");
void Input(){
    cin >> N >> '\n';
    cin.ignore();
}

void Pro() {
    int ans = 0;
    string str;
    for(int i=1;i<=N;i++){
        //string str;
        getline(cin,str);
        if(!(regex_match(str,re)) ans += 1;
    }
    cout << ans;
}