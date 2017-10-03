#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    int n, closest; // the number of temperatures to analyse
    cin >> n;
    if(n == 0) { 
        cout << "0" << endl; 
        return 0;
    }
    
    cin >> closest;
    for (int i = 1; i < n; i++) {
        int t; // a temperature expressed as an integer ranging from -273 to 5526
        cin >> t; cin.ignore();
        if(t >= 0) { 
            if(closest >= 0) {
                if(closest >= t) closest = t;   
            } else { 
                if((closest * -1)  >= t) closest = t;    
            }
        } else {
            if(closest >= 0) {
                if(closest  > (t*-1)) closest = t;
            } else {
                if(closest < t) closest = t;    
            }
        }
    }
    cout << closest << endl;
}
