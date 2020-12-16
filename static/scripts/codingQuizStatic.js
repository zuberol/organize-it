class CodingFlashcard {
    constructor(question, page, short_answer, long_answer, ref_url, code_sample_url) {
      this.question = question;
      this.page = page,
      this.short_answer = short_answer;
      this.long_answer = long_answer;
      this.ref_url = ref_url;
      this.code_sample_url = code_sample_url;
    }
}



const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const getFlashcardSetUrl = 'http://localhost:3000/get-questions/'
                                +urlParams.get('googleSheetId')
                                +'/scopes/'+urlParams.get('scopes');

var database = [];
fetch(getFlashcardSetUrl)
.then(response => response.json())
.then(data => {
    data.forEach(function(que) {
        database.push(que);
    })
})
.then(() => next())
.catch(err => console.log(err));

function revealAnswer(){
    const ans = document.querySelector('.flashcard__answer-wrapper');
    ans.style.display = 'block';
}
function hideAnswer(){
    const ans = document.querySelector('.flashcard__answer-wrapper');
    ans.style.display = 'none';
}


function next(){
    const fc_props = [
        document.querySelector('.flashcard__question'),
        document.querySelector('.flashcard__short_answer'),
        document.querySelector('.flashcard__long_answer'),
        document.querySelector('.flashcard__answer_ref-url'),
        document.querySelector('.flashcard__code pre')
    ];
    const fc = database.pop();

    hideIfNull(fc_props[0], fc.question);
    //hideIfNull(fc_props[1], fc.p);
    hideIfNull(fc_props[1], fc.short_answer);
    hideIfNull(fc_props[2], fc.long_answer);
    hideIfNull(fc_props[3], fc.ref_url);
    hideIfNull(fc_props[4], fc.code_sample_url);
    hideAnswer();
}

function hideIfNull(fc_prop, data) {
    if(data != null && data != "") {
        fc_prop.innerText = data;
        fc_prop.style.display = 'block';
    }
    else fc_prop.style.display = 'none';
}



document.addEventListener('DOMContentLoaded', function() {
    hideAnswer();
    const ref_url = document.querySelector('.flashcard__answer_ref-url');
    ref_url.addEventListener("click", function(event) {
        //openInNewTab(event); dlaczego to nie dziala? XD
        var win = window.open(event.target.innerText, '_blank');
        win.focus();
    })
});

document.addEventListener('keypress', function(event) {
    switch (event.key) {
        case ',': hideAnswer()
            break;
        case '.': revealAnswer()
            break;
        case '/': next()
            break;
    }
});
// function openInNewTab(ev) {
//     console.log(this)
//     var win = window.open(url, '_blank');
//     win.focus();
// }