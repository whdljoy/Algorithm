#include <iostream>
#include <deque>
#include <vector>
using namespace std;

class Node {
public:
    int value;
    string st;
    Node* left;
    Node* right;

    Node(int value = 0, string st = "", Node* left = nullptr, Node* right = nullptr) {
        this->value = value;
        this->st = st;
        this->left = left;
        this->right = right;
    }
};

void bfs_insert(Node* root, vector<string> val) {
    if (root == nullptr) {
        return;
    }
    deque<Node*> q;
    q.push_back(root);
    while (!q.empty()) {
        Node* cur_node = q.front();
        q.pop_front();
        if (cur_node->value == stoi(val[0])) {
            for (int j = 0; j < val.size(); j++) {
                if (j == 1) {
                    cur_node->st = val[1];
                }
                if (j == 2) {
                    cur_node->left = new Node(stoi(val[2]));
                }
                if (j == 3) {
                    cur_node->right = new Node(stoi(val[3]));
                }
            }
            return;
        }
        if (cur_node->left != nullptr) {
            q.push_back(cur_node->left);
        }
        if (cur_node->right != nullptr) {
            q.push_back(cur_node->right);
        }
    }
}

vector<int> bfs(Node* root) {
    vector<int> visited;
    if (root == nullptr) {
        return visited;
    }
    deque<Node*> q;
    q.push_back(root);
    while (!q.empty()) {
        Node* cur_node = q.front();
        q.pop_front();
        cout << cur_node->st << endl;
        visited.push_back(cur_node->value);
        if (cur_node->left != nullptr) {
            q.push_back(cur_node->left);
        }
        if (cur_node->right != nullptr) {
            q.push_back(cur_node->right);
        }
    }
    return visited;
}

vector<string> answer;

void dfs(Node* root) {
    if (root == nullptr) {
        return;
    }
    dfs(root->left);
    answer.push_back(root->st);
    dfs(root->right);
}

int main(int argc, char** argv)
{
	int test_case;
	freopen("input.txt", "r", stdin);

	for(test_case = 1; test_case <= 10; ++test_case)
	{
    int T;
    cin >> T;
    Node* root = new Node(1);
    for (int i = 0; i < T; i++) {
        vector<string> arr;
        string input;
        getline(cin, input);
        for (int j = 0; j < input.length(); j++) {
            if (input[j] != ' ') {
                string s = "";
                s += input[j];
                arr.push_back(s);
            }
        }
        cout << input << endl;
        bfs_insert(root, arr);
    }
    dfs(root);
    cout << "#" << to_string(test_case) << endl;
    for (string s : answer) {
        cout << s;
    }
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}