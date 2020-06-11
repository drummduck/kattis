const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.on('line', (line) => {
    var nums = line.split(' ');
    var numOfArticles = parseInt(nums[0]);
    var impactFactor = parseInt(nums[1]);
    var initialCitationsNeeded = Math.ceil(numOfArticles * impactFactor);

    while(initialCitationsNeeded/numOfArticles > impactFactor-1){
    	initialCitationsNeeded--;
    }

    console.log(initialCitationsNeeded+1);
});


