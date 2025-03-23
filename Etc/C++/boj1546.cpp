#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

double change (vector <double> &score){
    double max_v=*max_element(score.begin(),score.end());
    double sum=0;
    for(int i=0;i<score.size();i++){
       sum=sum+(score[i]/max_v*100);
    }
    return sum/score.size();
    
}
int main(){
    int n;
    vector <double> score;
    cin >> n;
    for (int i=0;i<n;i++){
        int val;
        cin >> val;
        score.push_back(val);
    }
    cout << change(score);

}