const express = require('express');
const bodyParser = require('body-parser');
const fileUpload = require('express-fileupload');


const { getSheet, saveSheet } = require ('./static/scripts/googleAPI');
const { CodingFlashcard } = require('./Model/Flashcard');

const app = express();

app.use(express.static('static', {root:__dirname+'/static'}))
app.use(bodyParser.json());
app.use(fileUpload());

const FLASHCARDSETS = [
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
  },
  {
    name: 'testing one two 1 2',
    googleSheetId: '1cyH_Ar8mibI3jifRh1mbLe5BBCnHEIkbexrdbRUA74w',
    scopes: "'react.js'!A:F"
  }
];


app.use('/coding-quiz', (req, res) => {
  res.sendFile('static/Views/coding-quiz.html', {root: __dirname});
});

app.use('/get-questions/:googleSheetId/scopes/:scopes', (req, res) => {
  getSheet(req.params.googleSheetId, req.params.scopes, res);
 // getSheet('13VsYi1cnJ-3n4H9dt1Gc96VgMLt5PAgLpMFlxmGvzc4', "'java-pytania'!A:F", res);
});

app.get('/get-flashcard-sets', (req, res) => {
  res.json(FLASHCARDSETS);
});

app.get("/add-flashcard", (req, res) => {
  res.sendFile('static/Views/add-flashcard.html', {root: __dirname});
})

app.post("/add-flashcard", (req, res) => {
  if (!req.files || Object.keys(req.files).length === 0) {
    return res.status(400).send('No files were uploaded.');
  }
  // console.log(req.files)
  console.log(req.body)
  console.log(req.body.flashcardSet)

  const googleSheetId = '1cyH_Ar8mibI3jifRh1mbLe5BBCnHEIkbexrdbRUA74w'

  const fc = new CodingFlashcard(
    req.body.question,
    "1",
    "sh ans",
    "lng ans",
    "fer usdl",
    "heheheh sample"
  );

  saveSheet(fc, res, googleSheetId);

  // The name of the input field (i.e. "fileUploaded") is used to retrieve the uploaded file
  let sampleFile = req.files.fileUploaded;
  // Use the mv() method to store the file somewhere on your server
  sampleFile.mv(__dirname+'/receivedFiles/'+sampleFile.name, function(err) {
    if (err) console.log("Error. File couldn't been saved!");
    else console.log("File uploaded!");
  });
});

app.use((req, res) => {
    res.sendFile('static/Views/index.html', {root: __dirname});
});


const listeningPort = 3000;
app.listen(listeningPort, () => {
    console.log(`Server listening on port ${listeningPort}`)
});
