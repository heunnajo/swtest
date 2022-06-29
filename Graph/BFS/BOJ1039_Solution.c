//교환
#include <iostream>
#include <string>
#include <queue>
#include <set>
#include <algorithm>
using namespace std;

string arr;
int k;
int maxed = 0;
bool jud() {
	int cnt = 0;
	for (int i = 0; i < arr.size(); i++)
		if (arr[i] > '0')
			cnt++;
	if (cnt > 1)
		return true;
	return false;
}

void bfs() {
	queue <string> que;
	que.push(arr);

	for (int u = 0; u < k; u++) { // k번 반복 
		int sized = que.size();
		set <string> visitied;

		for (int s = 0; s < sized; s++) {
			string temp = que.front();
			que.pop();

			for (int i = 0; i < temp.size() - 1; i++) {
				for (int j = i + 1; j < temp.size(); j++) {
					if (i == 0 && temp[j] == '0') continue;

					swap(temp[i], temp[j]); // #include algo
					if (visitied.find(temp) == visitied.end()) {
						if (u == k - 1) { //마지막 턴
							int val = stoi(temp);
							maxed = max(maxed, val);
						}
						que.push(temp);
						visitied.insert(temp);
					}
					swap(temp[i], temp[j]);
				}
			}


		}



	}


}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> arr >> k;
	if (arr.size() == 1) { // 한자리 수
		cout << -1 << endl;
		return 0;
	}
	else if (arr.size() == 2 && jud() == false) { // 두자리 수이지만 수가 하나라 swap을 못할 때
		cout << -1 << endl;
		return 0;
	}

	bfs();

	cout << maxed << endl;

	return 0;
}