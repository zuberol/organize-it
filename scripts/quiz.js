class Flashcard {
    constructor(q, ans, pic_url, user_ans){
        this.question = q;
        this.answer = ans;
        this.picture = pic_url;
        this.user_ans = user_ans;

    }
}

let f1 = new Flashcard("A srasz?", "a ty?", "https://www.euractiv.pl/wp-content/uploads/sites/6/2020/05/max-baskakov-OzAeZPNsLXk-unsplash-scaled-e1588503189401-800x450.jpg", "e sralem xd");
let f2 = new Flashcard("A sraszdsadsadasd?", "a tyyyyyy?", "accccccco.png", "eeeeeeeeeeeeee sralem xd");
let f3 = new Flashcard("A dsasdadsa?", "tytytytyty?", "cocoococooco", "eeeeeeeeeeeeee ??SDA?!?!");

// console.log(f1);

const database = [f1, f2, f3];


// function previous(){


// }

function revealAnswer(){
    const ans = document.getElementById('answer');
    ans.style.display = 'block';
}
function hideAnswer(){
    const ans = document.getElementById('answer');
    ans.style.display = 'none';
}


function next(){
    const ans = document.getElementById('answer');
    const question = document.getElementById('question');
    const img = document.querySelector('.flashcard-wrapper__image img');

    const fc = database.pop();


    img.src = fc.picture;
    

    ans.innerText = fc.answer;
    question.innerText = fc.question;

    hideAnswer();

}


