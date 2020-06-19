class Flashcard {
    constructor(q, ans, pic_url, user_ans){
        this.question = q;
        this.answer = ans;
        this.picture = pic_url;
        this.user_ans = user_ans;

    }
}

let f = new Flashcard("A srasz?", "a ty?", "aco.png", "e sralem xd");

console.log(f);

const data = {};