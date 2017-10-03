#include <iostream>
#include <string>
#include <algorithm>

using namespace std;


int main() {
    int count, strength, diff;
    cin >> count; cin.ignore();
    int horses[count];

    for (int i = 0; i < count; i++) {
        cin >> horses[i]; cin.ignore();
    }
    sort(horses,horses+count);
    diff = abs(horses[0]-horses[1]);
    for (int j = 1; j < count; j++) {
        if (diff > abs(horses[j]-horses[j+1])) {
            diff = abs(horses[j]-horses[j+1]);
        }
    }

    cout << diff << endl;
}
