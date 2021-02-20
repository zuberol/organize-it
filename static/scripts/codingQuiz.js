class CodingFlashcard {
    constructor(question, page, short_answer, long_answer, ref_url, code_sample_url, picture_url) {
      this.question = question;
      this.page = page,
      this.short_answer = short_answer;
      this.long_answer = long_answer;
      this.ref_url = ref_url;
      this.code_sample_url = code_sample_url;
      this.picture_url = picture_url;
    }
}


const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const getFlashcardSetUrl = 'http://localhost:8080/get-questions/'
                                +urlParams.get('googleSheetId')
                                +'/scopes/'+urlParams.get('scopes');

var database = [];
fetch(getFlashcardSetUrl)
.then(response => response.json())
.then(data => {
    data.forEach(function(flashcard) {
        database.push(flashcard);
        if(flashcard.picture_url) {
            // console.log(flashcard.picture_url)

            // fetch('http://localhost:3000/get-questions/' + flashcard.picture_url)
            // .then(file => {
            //     console.log(file)
            // })
            // .catch(err => console.log(err));
        }
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
        document.querySelector('.flashcard__code pre'),
        document.querySelector('img')
    ];
    const fc = database.pop();

    hideIfNull(fc_props[0], fc.question);
    //hideIfNull(fc_props[1], fc.p);
    hideIfNull(fc_props[1], fc.short_answer);
    hideIfNull(fc_props[2], fc.long_answer);
    hideIfNull(fc_props[3], fc.ref_url);
    hideIfNull(fc_props[4], fc.code_sample_url);

    // set picture, TODO rewrite it
    const imageWrapper = document.querySelector('.flashcard-wrapper__image');
    if(fc.picture_url != null && fc.picture_url != "") {
        fc_props[5].src = 'http://localhost:8080/get-questions/' + fc.picture_url;
        imageWrapper.style.display = 'block';
    }
    else {
        imageWrapper.style.display = 'none';
    }
    // hideAnswer(fc_props[]);
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



