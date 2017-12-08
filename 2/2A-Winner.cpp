#include <iostream>
#include <string>
#include <map>
#include <set>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	int T;
	cin >> T;

	// Calculate each player's net-score
	map<string, int> scoreBoard;
	vector<pair<string, int>> inputs;
	while (T--) {
		string name;
		int score;
		cin >> name >> score;
		inputs.push_back(make_pair(name, score));

		auto it = scoreBoard.find(name);
		scoreBoard[name] = it == scoreBoard.end() ? score : it->second + score;
	}

	// Find maximum score
	int maxScore = 0;
	for (auto p : scoreBoard)
		maxScore = max(maxScore, p.second);

	// Find winner candidates
	set<string> candidiates;
	for (auto p : scoreBoard) {
		if (p.second == maxScore)
			candidiates.insert(p.first);
	}

	// Find final winner
	string finalWinner;
	scoreBoard.clear();
	for (auto p : inputs) {
		string name = p.first;
		int score = p.second;
		
		if (candidiates.find(name) != candidiates.end()) {
			auto it = scoreBoard.find(name);
			if (it != scoreBoard.end())
				score += it->second;
			scoreBoard[name] = score;

			it = scoreBoard.find(name);
			if (it->second >= maxScore) {
				finalWinner = name;
				break;
			}
		}
	}

	cout << finalWinner << endl;

	return 0;
}
