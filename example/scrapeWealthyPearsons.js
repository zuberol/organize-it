let ammounts = [...document.querySelectorAll('.table-row .netWorth')];

ammounts = ammounts
    .map(e => e.innerHTML
    .match('[0-9]+')[0])
    .map(e => parseFloat(e));
const SUM_OF_AMMOUNTS = ammounts.reduce((prev, curr) => prev + curr)
const PEOPLE_ON_EARTH = 7.8; // in bilions

const resume = `200 people's money sum is ${SUM_OF_AMMOUNTS} billion dollars
Number of people on earth is ${PEOPLE_ON_EARTH}
Money per person is <b>${SUM_OF_AMMOUNTS / PEOPLE_ON_EARTH} $`;

document.querySelector('body').innerHTML = resume;

const ALL_BILIONAIRES_MONEY = 8000.0; // in bilions
console.log(`All bilionaire's money sum is ${ALL_BILIONAIRES_MONEY} bilion dollars
Number of people on earth is ${PEOPLE_ON_EARTH}
Money per person is ${ALL_BILIONAIRES_MONEY / PEOPLE_ON_EARTH} $`)