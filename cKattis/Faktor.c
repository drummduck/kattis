#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int articlesPlanned = 0;
	int desiredImpactFactor = 0;
	int bribesNeeded;
    while (scanf("%d%d", &articlesPlanned, &desiredImpactFactor) == 2){
        bribesNeeded = articlesPlanned * desiredImpactFactor;
        while(((double)bribesNeeded/(double)articlesPlanned) > (desiredImpactFactor - 1))
        {
            bribesNeeded--;
        }
        printf("%d\n", bribesNeeded+1);
        return 0;
    }
}