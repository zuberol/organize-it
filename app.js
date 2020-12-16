const express = require('express');
const bodyParser = require('body-parser');

const { getSheet } = require ('./static/scripts/googleAPI');

const app = express();

app.use(express.static('static', {root:__dirname+'/static'}))
app.use(bodyParser.json());


app.use('/coding-quiz', (req, res) => {
  res.sendFile('static/Views/coding-quiz.html', {root: __dirname});
});
app.use('/get-questions/:googleSheetId/scopes/:scopes', (req, res) => {
  getSheet(req.params.googleSheetId, req.params.scopes, res);
 // getSheet('13VsYi1cnJ-3n4H9dt1Gc96VgMLt5PAgLpMFlxmGvzc4', "'java-pytania'!A:F", res);
});

app.get('/get-flashcard-sets', (req, res) => {
  const flashcardSets = [
    {
      name: 'java fundamentals',
      googleSheetId: '13VsYi1cnJ-3n4H9dt1Gc96VgMLt5PAgLpMFlxmGvzc4',
      scopes: "'java-pytania'!A:F"
    },
    {
      name: 'spring',
      googleSheetId: '1xtsqyloR7YWikuFVi95juoRkvOpQS6w9W5whzRPiJlk',
      scopes: "'spring'!A:F"
    },
    {
      name: 'AWS',
      googleSheetId: '1xtsqyloR7YWikuFVi95juoRkvOpQS6w9W5whzRPiJlk',
      scopes: "'AWS'!A:F"
    },
    {
      name: 'react.js',
      googleSheetId: '1xtsqyloR7YWikuFVi95juoRkvOpQS6w9W5whzRPiJlk',
      scopes: "'react.js'!A:F"
    }
  ];
  res.json(flashcardSets);
});

app.use((req, res) => {
    res.sendFile('static/Views/index.html', {root: __dirname});
});


const listeningPort = 3000;
app.listen(listeningPort, () => {
    console.log(`Server listening on port ${listeningPort}`)
});
