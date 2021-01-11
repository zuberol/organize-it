fetch('http://localhost:3000/get-flashcard-sets')
.then(response => response.json())
.then(data => data.forEach(f => spawnListItem(f)));


function spawnListItem(flashcardSetInfo) {
    const flashcardList = document.querySelector('.flashcard-list');
    const li = document.createElement('li');
    li.innerText = flashcardSetInfo.name;
    li.className = 'flashcard-list__item';
    flashcardList.appendChild(li);
    li.addEventListener('click', function(event) {
        window.location = 
            'http://localhost:3000/coding-quiz/'+
            '?googleSheetId='+flashcardSetInfo.googleSheetId+'&'+
            'scopes='+flashcardSetInfo.scopes;
    });
}