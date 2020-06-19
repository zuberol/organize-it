document.getElementById('submit-btn').addEventListener('click', (event) => {
    const text = document.getElementById("task-generator__span").innerHTML;
    const newTask = document.createElement("li");
    newTask.innerHTML = text;
    document.getElementById('tasks__listID').appendChild(newTask);
})



const l = document.getElementById('leki');
l.addEventListener('click', function(){
    console.log('clicked');
});