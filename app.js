const express = require('express');
const bodyParser = require('body-parser');

const { getSheet } = require ('./static/scripts/googleAPI');

const app = express();

app.use(express.static('static', {root:__dirname+'/static'}))
app.use(bodyParser.json());


app.use('/coding-quiz', (req, res) => {
  res.sendFile('static/Views/coding-quiz.html', {root: __dirname});
});
app.use('/get-questions', (req, res) => {
  getSheet('13VsYi1cnJ-3n4H9dt1Gc96VgMLt5PAgLpMFlxmGvzc4', "'java-pytania'!A:F", res);
});
app.use((req, res) => {
    res.sendFile('static/Views/index.html', {root: __dirname});
});


const listeningPort = 3000;
app.listen(listeningPort, () => {
    console.log(`Server listening on port ${listeningPort}`)
});
