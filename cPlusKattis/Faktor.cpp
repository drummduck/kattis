#include <iostream>
using namespace std;

int main() {
    int articlesPlanned = 0;
	int desiredImpactFactor = 0;
	int bribesNeeded;
    while (cin >> articlesPlanned >> desiredImpactFactor){
        bribesNeeded = articlesPlanned * desiredImpactFactor;
        while(((double)bribesNeeded/(double)articlesPlanned) > (desiredImpactFactor - 1))
        {
            bribesNeeded--;
        }
        printf("%d\n", bribesNeeded+1);
        return 0;
    }
}