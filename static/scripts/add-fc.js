
fetch("http://localhost:3000/get-flashcard-sets")
.then(response => response.json())
.then(data => {
    data.map(d => d.name);
    data.forEach(function(que) {
        console.log(que.name)
    });
    const list = document.querySelector('#flashcardSets');
    data.forEach(d => {
        const li = document.createElement('OPTION');
        li.value = d.name;
        list.appendChild(li);
    });
})
.catch(err => console.log(err));