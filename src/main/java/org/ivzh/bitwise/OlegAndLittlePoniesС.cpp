#include <iostream>
#include <bits/stdc++.h>

using namespace std;

bool visited[4002];
bitset<1014> wishes[4002][2], presentToys;

int main() {
    int n, m;
    cin >> n >> m;
    for(int i = 0; i < m; i++) {
      cin >> wishes[i][0];
      cin >> wishes[i][1];
    }
    
    cin >> presentToys;
    while(true){
      bool continueLoop = false;
      for(int i = 0; i < m; i++) {
        if(!visited[i]) {
            if((presentToys&wishes[i][0]) == wishes[i][0]){
              visited[i] = true;
              continueLoop = true;
              presentToys |= wishes[i][1];
            }
        }
      }
      if(!continueLoop) {
          break;
      }
    }
    string binary = presentToys.to_string();
    cout << binary.substr(binary.size()-n, binary.size()) << endl;
    return 0;
}
