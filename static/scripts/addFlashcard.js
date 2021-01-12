
fetch("http://localhost:3000/get-flashcard-sets")
.then(response => response.json())
.then(appendFcSetsToList)
.catch(err => console.log(err));

function appendFcSetsToList (rawData) {
    rawData.map(d => d.name);
    const list = document.querySelector('#flashcardSet');
    rawData.forEach(d => {
        const li = document.createElement('OPTION');
        li.value = d.name;
        li.innerText = d.name;
        list.appendChild(li);
    });
}

function setDefaultFcSet() {
    const lastUsedFcSet = getNameOfLastUsedFcSet();
    if(lastUsedFcSet){
        const fcSetInput = document.querySelector('#flashcardSet');
        fcSetInput.value = lastUsedFcSet;
    }
}

function getNameOfLastUsedFcSet() {
    return document.cookie.split('; ')
                          .find(row => row.startsWith('lastAddedFcSet'))
                          .split('=')[1];
}


setDefaultFcSet();