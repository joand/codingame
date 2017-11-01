#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main() {
    int n; // the number of temperatures to analyse
    cin >> n; cin.ignore();
    string temps; // the n temperatures expressed as integers ranging from -273 to 5526
    getline(cin, temps);
    int max = 5527;
    std::stringstream stream(temps);

    while(1) {
        int n;
        stream >> n;
        if(!stream)
            break;
        if (abs(max) > abs(n)) {
            max = n;
        } else if (abs(max) == abs(n)) {
            if (max < 0) max = n;
        }
    }
    if (max == 5527) {max = 0;}

    cout << max << endl;
}
