
// create available sheet dropdown list
fetch("http://localhost:3000/get-flashcard-sets")
.then(response => response.json())
.then(data => {
    data.map(d => d.name);
    // data.forEach(function(que) {
    //     console.log(que.name)
    // });
    const list = document.querySelector('#flashcardSet');
    data.forEach(d => {
        const li = document.createElement('OPTION');
        li.value = d.name;
        li.innerText = d.name;
        list.appendChild(li);
    });
})
.catch(err => console.log(err));

function setDefaultFcSet() {
    const lastUsedFcSet = document.cookie
    .split('; ')
    .find(row => row.startsWith('lastAddedFcSet'))
    .split('=')[1];
    if(lastUsedFcSet){
        const fcSetInput = document.querySelector('#flashcardSet');
        fcSetInput.value = lastUsedFcSet;
    }
}
setDefaultFcSet();