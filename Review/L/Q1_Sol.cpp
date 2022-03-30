#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#define NM 5005
using namespace std;

#include <regex>

// team_name : teamOne application_name : line error_level : serious message : Fuck
// team_name : team123 application_name : line error_level : serious message : Fuck
// team_name : teamOne application_name : line error_level : serious!!! message : Fuck
//     team_name : teamOne application_name : line error_level : serious message : Fuck
// team_name : teamOne application_name : line error_level : serious message : Fuck       
// team_name: teamOne application_name : line error_level : serious message : Fuck
// team_name : teamOne application_name : line error_level : ThisIsVeryVerySeriousSoThatYouNeedToFixItRrightAwayNoMatterWhatYouAreDoingNowYouNeedToComeToOfficeRIGHTNOW message : Fuck

int N;
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